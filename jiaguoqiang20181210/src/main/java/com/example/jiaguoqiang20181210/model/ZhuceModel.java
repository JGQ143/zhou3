package com.example.jiaguoqiang20181210.model;

import com.example.jiaguoqiang20181210.Http.Utils;
import com.example.jiaguoqiang20181210.bean.Useru;
import com.google.gson.Gson;

public class ZhuceModel {
    public static Useru zhuce(String mobile,String password){
        String zhuce =Utils.get("http://www.zhaoapi.cn/user/reg?mobile="+mobile+"&password="+password);
        Gson gson = new Gson();
        Useru useru = gson.fromJson(zhuce,Useru.class);
        return useru;
    }
}
