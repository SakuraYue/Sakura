package com.fh.common;

public class ServerResponse {
    private int status;
    private String msg;
    private Object data;
    private ServerResponse() {
    }
    public static ServerResponse error(String str){
        return new ServerResponse(ResponseEnum.ERROR.getCode(),str,null);
    }
    public static ServerResponse code_error(){
        return new ServerResponse(ResponseEnum.CODE_ERROR.getCode(), ResponseEnum.CODE_ERROR.getMsg());
    }
    public static ServerResponse success(){
        return new ServerResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg());
    }

    public static ServerResponse error(ResponseEnum responseEnum){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
    }

    public static ServerResponse success(Object data){
        return new ServerResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(),data);
    }
    public static ServerResponse error(){
        return new ServerResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMsg());
    }
    public static ServerResponse error(int code, String message){
        return new ServerResponse(code,message,null);
    }
    private ServerResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
