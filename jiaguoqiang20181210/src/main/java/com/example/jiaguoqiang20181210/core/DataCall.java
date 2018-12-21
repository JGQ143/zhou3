package com.example.jiaguoqiang20181210.core;

import com.example.jiaguoqiang20181210.bean.Result;

//创建接口
public interface DataCall<T> {
    //成功
   void success(T data);
   //失败
    void fail(Result result);

}
