package com.x.core.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.x.core.exception.NetworkRequestException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author xudafeng
 * Created by xudafeng on 2017/11/9.
 */

public class OKHttpUtil {

    @SuppressWarnings("unchecked")
    public static <T> T execute(Request request, Type resultType) throws Exception {
        Response response;
        try {
            response = getOkHttpClient().newCall(request).execute();
        }catch (Exception e) {
            throw new NetworkRequestException(request.url().toString(), e);
        }

        if(resultType == null || resultType == Response.class){
            return (T) response;
        }

        if(resultType == Void.TYPE){
            return null;
        }

        if(resultType == String.class) {
            return (T) response.body().toString();
        }

        if(resultType == Bitmap.class) {
            byte[] bytes = response.body().bytes();
            return (T) BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);
        }

        return GsonUtil.getGson().fromJson(response.body().charStream(), resultType);
    }

    public static <T> Request buildRequest(String url, Method method, Map<String,T> params, Map<String,String> headers){
        Request.Builder builder;
        if(method == Method.GET){
            builder = buildRequestByGet(url,params);
        }else if(method == Method.POST){
            builder = buildRequestByPost(url, params);
        }else{
            throw new RuntimeException(method.name()+" is Supported ?");
        }
        if(headers != null && headers.size() > 0){
            for (String name : headers.keySet()) {
                builder.addHeader(name, headers.get(name));
            }
        }
        return builder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        System.setProperty("htpp.keepAlive", "false");
        return OkHttpClientHelper.INSTANCE;
    }

    private static class OkHttpClientHelper {
        private static final OkHttpClient INSTANCE = new OkHttpClient().newBuilder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    private static <T> Request.Builder buildRequestByGet(String url,Map<String,T> params){
        return new Request.Builder().url(appendParams(url, params));
    }

    public static <T> String appendParams(String url,Map<String,T> params){
        StringBuilder sb = new StringBuilder(url);
        if(params != null && params.size() > 0){
            Uri uri = Uri.parse(url);
            String query = uri.getQuery();
            boolean hasQuery = false;
            if(query != null && query.trim().length() > 0){
                hasQuery=true;
            }
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                if(obj == null) {
                    continue;
                }
                String value = "";
                String pkgName = obj.getClass().getName();
                if(obj instanceof String){
                    value = obj.toString();
                }else if((obj instanceof List) || (obj instanceof Map)){
                    value = GsonUtil.getGson().toJson(obj);
                }else if(clsMap.containsKey(pkgName)){
                    value = obj.toString();
                }else{
                    throw new RuntimeException("unSupport cls :"+pkgName);
                }

                if(StringUtil.isEmpty(value) || StringUtil.isEmpty(key))
                    continue;
                if(hasQuery){
                    sb.append("&");
                }else{
                    sb.append("?");
                    hasQuery=true;
                }
                try {
                    value = URLEncoder.encode(value,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(key).append("=").append(value);
            }
        }
        return sb.toString();
    }

    private static <T> Request.Builder buildRequestByPost(final String url,Map<String,T> params){
        return new Request.Builder().url(url).post(getFormRequestBody(params));
    }

    private static <T> RequestBody getFormRequestBody(Map<String,T> params){
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if(params != null && params.size() > 0){
            String value;
            for (String key : params.keySet()) {
                value = params.get(key).toString();
                if(TextUtils.isEmpty(value)){
                    continue;
                }
                try {
                    bodyBuilder.add(key,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bodyBuilder.build();
    }

    private static final Map<String,String> clsMap = new HashMap<>();
    static {
        Object[] arr = {1,1L,0.5f,5d,(byte)1,"abc",'a',true};
        for (Object o : arr) {
            clsMap.put(o.getClass().getName(),"-");
        }
    }

    public enum Method {
        POST, GET, PUT, DELETE
    }

    public enum ParamSendType {
        byteArray, text
    }
}
