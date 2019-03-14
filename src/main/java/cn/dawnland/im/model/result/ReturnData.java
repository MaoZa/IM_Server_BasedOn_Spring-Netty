package cn.dawnland.im.model.result;

import java.io.Serializable;

public class ReturnData<T> implements Serializable {

    private static final long serialVersionUID = -4577255781088498763L;
    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; //服务端数据
    private int code = OK; //状态码
    private String msg = ""; //描述信息

    //APIS
    public static ReturnData isOk(String msg) {
        if (msg != null) {
            return new ReturnData().status(OK).msg(msg);
        }else {
            return new ReturnData().status(OK).msg("success");
        }
    }

    public static ReturnData isOk() {
        return new ReturnData().status(OK).msg("success");
    }

    public static ReturnData isFail() {
        return new ReturnData().status(FAIL);
    }

    public static ReturnData isFail(String msg) {
        return new ReturnData().status(FAIL).msg(msg);
    }


    public static ReturnData isFail(Throwable e) {
        return isFail().msg(e);
    }

    public ReturnData msg(ResultCode resultCode) {
        this.setMsg(resultCode.getMessage());
        this.setCode(resultCode.getCode());
        return this;
    }

    public ReturnData msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public ReturnData msg(String e) {
        this.setMsg(e);
        return this;
    }

    public ReturnData data(T data) {
        this.setData(data);
        return this;
    }

    public ReturnData status(int status) {
        this.setCode(status);
        return this;
    }


    //Constructors
    public ReturnData() {

    }

    public ReturnData(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    public ReturnData(T data , ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.data = data;
    }

    public ReturnData(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnData(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    //Getter&Setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 适用于大于0成功
     * @param result
     * @param msg
     * @return
     */
    public static ReturnData returnMsg(Integer result, String msg){
        if(result > 0){
            return ReturnData.isOk(msg + "成功");
        }else {
            return ReturnData.isFail(msg + "失败");
        }
    }

    /**
     * 适用于大于true成功
     * @param result
     * @param msg
     * @return
     */
    public static ReturnData returnMsg(Boolean result, String msg){
        if(result){
            return ReturnData.isOk(msg + "成功");
        }else {
            return ReturnData.isFail(msg + "失败");
        }
    }
}