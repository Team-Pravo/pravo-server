package com.pravo.pravo.global.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

    private int statusCode;
    private String error;

    // message can be string or arrayList
    private Object message;
    private T data;
}
