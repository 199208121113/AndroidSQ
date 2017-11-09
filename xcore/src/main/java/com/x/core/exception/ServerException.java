package com.x.core.exception;

import java.util.Map;

public class ServerException extends Exception {


    private String interfaceName;
    private Map<String, String> keyMap = null;
    private Exception error;
    private String userTips = null;



    public ServerException(String interfaceName, Exception error) {
        super(error);
        this.interfaceName = interfaceName;
        this.error = error;
    }

    public ServerException(String interfaceName, String userTips) {
        super(userTips);
        this.interfaceName = interfaceName;
        this.userTips = userTips;
    }

    public String getInterfaceName() {
        return interfaceName == null ? "" : interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Map<String, String> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, String> keyMap) {
        this.keyMap = keyMap;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public String getUserTips() {
        return userTips;
    }

    public void setUserTips(String userTips) {
        this.userTips = userTips;
    }
}
