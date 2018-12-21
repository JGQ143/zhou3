package com.example.nuli.core;

import com.example.nuli.bean.Result;



public interface CallBacks<T> {
    void success(T t);
    void fail(Result result);
}
