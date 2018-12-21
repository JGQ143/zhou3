package com.example.jiaguoqiang20181210.model;

import com.example.jiaguoqiang20181210.Http.Utils;
import com.example.jiaguoqiang20181210.bean.Result;
import com.example.jiaguoqiang20181210.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LoginModel {
    public static Result login(String mobile,String password){

        String logindata =Utils.get("http://www.zhaoapi.cn/user/login?mobile="+mobile+"&password="+password);
        //解析gson
        Gson gson = new Gson();
        Type type = new TypeToken<Result<User>>() {}.getType();

        Result result = gson.fromJson(logindata, type);
        return result;
    }
}
