package com.x.core.exception;


import com.x.core.util.ExceptionUtil;
import com.x.core.util.StringUtil;

/**
 * 网络异常
 * Created by liguo on 2015/8/28.
 */
public class NetworkRequestException extends Exception {
    private String url;
    private Exception err;
    public NetworkRequestException(String url, Exception err) {
        this.url = url;
        this.err = err;
    }


    @Override
    public String getMessage() {
        String superMessage = "";
        if(err != null){
            superMessage = err.getMessage();
            if(StringUtil.isEmpty(superMessage)){
                superMessage = ExceptionUtil.getStackTrace(err);
            }
        }else{
            superMessage = super.getMessage();
            if(StringUtil.isEmpty(superMessage)){
                superMessage = ExceptionUtil.getStackTrace(this);
            }
        }
        return "url=["+url+"],msg="+superMessage;
    }

    public Exception getErr() {
        return err;
    }

    public String getUrl() {
        return url;
    }
}
