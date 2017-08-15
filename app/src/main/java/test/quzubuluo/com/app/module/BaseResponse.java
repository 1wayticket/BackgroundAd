package test.quzubuluo.com.app.module;


import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    protected int result;
    protected T data;
    protected String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk() {
        return "0".equals(String.valueOf(result)) || "400".equals(String.valueOf(result));
    }
}
