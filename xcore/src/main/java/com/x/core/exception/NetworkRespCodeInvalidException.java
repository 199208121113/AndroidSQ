package com.x.core.exception;


import com.x.core.util.StringUtil;

/**
 * 网络响应异常
 * Created by liguo on 2015/8/28.
 */
public class NetworkRespCodeInvalidException extends Exception {
    private int responseCode;
    private String url;
    public NetworkRespCodeInvalidException(int responseCode, String url) {
        this.responseCode = responseCode;
        this.url = url;
    }

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        if(StringUtil.isEmpty(superMessage)){
            superMessage = "";
        }
        return "responseCode=["+responseCode+"],url=["+url+"]"+superMessage;
    }
}
