package com.pravo.pravo.global.util;

public class RestResponse<T> {

    private int statusCode;
    private String error;

    private Object message;
    private T data;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getError() {
        return this.error;
    }

    public Object getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
