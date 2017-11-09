package com.x.core.util.http;

import android.support.annotation.Nullable;

import com.x.core.util.OKHttpUtil;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class HttpService {

    /** 数据字符串为空 */
    public final static String ERR_STRING_EMPTY = "respStr is empty";

    /** 数据转换出错,一般是数据结构类型不匹配 */
    public final static String ERR_DATA_CONVERT = "data convert exception";

    /** 数据为空 */
    public final static String ERR_DATA_EMPTY = "data empty";

    /** 状态异常 */
    public final static String ERR_STATUS_INVALID = "status invalid";

    public static  <T> T get(String url, Type resultType) throws Exception {
        return get(url, null,resultType, null);
    }

    public static  <T> T get(String url, Map<String,String> params, Type resultType) throws Exception {
        return get(url,params, resultType, null);
    }

    public static  <T> T get(String url,Map<String,String> params, Type resultType, Map<String,String> headers) throws Exception {
        return get(url, params, resultType, headers, null);
    }

    public static  <T> T get(String url,Map<String,String> params, Type resultType, Map<String,String> headers,HttpResponseHandler respHandler) throws Exception {
        return startRequest(url, toObjectMap(params), resultType, headers, respHandler, OKHttpUtil.Method.GET);
    }

    public static <T> T post(String url, Map<String,String> params, Type resultType) throws Exception {
        return post(url, params, resultType, null);
    }

    public static <T> T post(String url, Map<String,String> params, Type resultType, Map<String,String> headers) throws Exception {
        return post(url, params, resultType, headers, null);
    }

    public static <T> T post(String url, Map<String,String> params, Type resultType,  Map<String,String> headers, HttpResponseHandler respHandler) throws Exception {
        return startRequest(url, toObjectMap(params), resultType, headers, respHandler, OKHttpUtil.Method.POST);
    }

    private static <T> T startRequest(String url, Map<String, Object> cbd, Type resultType, Map<String, String> headers, HttpResponseHandler respHandler, OKHttpUtil.Method method) throws Exception {
        LinkedHashMap<String,Object> allParamMap = new LinkedHashMap<>();
        if(cbd != null && cbd.size() > 0){
            allParamMap.putAll(cbd);
        }

        Map<String,String> headerNew = getHeaders(headers);
        Request request = OKHttpUtil.buildRequest(url, method, allParamMap, headerNew);;
        Response response = OKHttpUtil.execute(request,null);
        return handRespBySignModel(resultType, respHandler, response);
    }

    @Nullable
    private static <T> T handRespBySignModel(Type resultType, HttpResponseHandler respHandler, Response response) throws Exception {
        if (respHandler == null)
            respHandler = DefaultHandler.getDefaultInstance();
        return respHandler.handResposne(response, resultType);
    }

    public static boolean isSuc(final int responseCode){
        if(responseCode == 200 || responseCode == 302 || (responseCode >= 200 && responseCode < 300)){
            return true;
        }else{
            return false;
        }
    }

    public static <T> Map<String,Object> toObjectMap(Map<String,T> map){
        Map<String,Object> objMap = new HashMap<>();
        if(map == null || map.size() == 0){
            return objMap;
        }
        for (String k : map.keySet()){
            Object obj = map.get(k);
            if(obj == null){
                continue;
            }
            if(obj instanceof String){
                objMap.put(k,obj);
            }else{
                String val = obj.toString();
                if(val.matches("\\d+")){
                    objMap.put(k,Long.parseLong(val));
                }else{
                    objMap.put(k,obj);
                }
            }
        }
        return objMap;
    }

    public final static String KEY_USER_AGENT = "User-Agent";
    private static final String USER_AGENT = "books by AireaderCity";
    public static final String APP_ID = "com.youloft.glsc";
    public static Map<String,String> getHeaders(Map<String,String> headers){
        Map<String,String> header = new HashMap<>();
        header.put(KEY_USER_AGENT,USER_AGENT);
        if(headers != null && headers.size() > 0){
            header.putAll(headers);
        }
        return header;
    }
}
