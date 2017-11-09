package com.x.core.util.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.x.core.exception.NetworkRespCodeInvalidException;
import com.x.core.exception.ServerException;
import com.x.core.util.GsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * HttpResponse的默认处理器
 *
 * @author liguo
 */
public class DefaultHandler implements HttpResponseHandler {

    private static DefaultHandler instance = null;

    protected DefaultHandler() {
        super();
    }

    public static DefaultHandler getDefaultInstance() {
        if (instance == null) {
            instance = new DefaultHandler();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T handResposne(Response response, Type resultType) throws Exception {
        if (resultType == null ||  response == null) {
            return (T) response;
        }
        if (resultType == Void.TYPE) {
            return null;
        }
        ResponseBody responseBody = response.body();
        if(responseBody == null){
            return null;
        }
        T t = null;
        final int code = response.code();
        if(!HttpService.isSuc(code)){
            String url = "";
            if(response.request() != null) {
                url = response.request().url().toString();
            }
            throw new NetworkRespCodeInvalidException(code,url);
        }
        InputStream is = null;
        Reader reader = null;
        try {
            if (code == 200) {
                if (resultType == Bitmap.class) {
                    is = responseBody.byteStream();
                    t = (T) BitmapFactory.decodeStream(is);
                } else if (resultType == String.class) {
                    String jsonStr = responseBody.string();
                    t = (T) jsonStr;
                } else {
                    reader = responseBody.charStream();
                    t = GsonUtil.getGson().fromJson(reader, resultType);
                }
            }
        }catch (JsonIOException | JsonSyntaxException e){
            String st = "";
            if(response.request() != null){
                st = response.request().url().toString();
            }
            throw new ServerException(st,HttpService.ERR_DATA_CONVERT);
        }catch (Exception e){
            throw e;
        }finally {
            closeInputStreamReader(reader);
            closeInputStream(is);
            try {
                responseBody.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    protected void closeInputStream(InputStream is) {
        if (is == null)
            return;
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeInputStreamReader(Reader reader) {
        if (reader == null)
            return;
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
