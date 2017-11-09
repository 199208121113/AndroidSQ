package com.x.core.util.http;

public class ResponseModel<T> {

    /**
     *
     字段类型	参数名	类型	取值范围	说明
     系统	status	int	取值点我	请求处理的状态
     系统	msg	String		当status不为 200 时，针对status code的说明信息
     系统	t	string		记录的返回时间的服务器时间
     系统	dateTime	DateTime		记录的返回时间
     系统	server	String	取值点我	服务器实例
     业务	data	不固定，随实际业务而定
     */

    private int status;
    private String msg;
    private String t;
    private String server;
    private T data;

    private String serverDesc;
    private String statusDesc;
    private String sign;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getServerDesc() {
        return serverDesc;
    }

    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /** 3001 说明用户没有实名认证 */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
