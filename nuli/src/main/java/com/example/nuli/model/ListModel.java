package com.example.nuli.model;

import com.example.nuli.bean.Goods;
import com.example.nuli.bean.Result;
import com.example.nuli.utils.OkHttp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListModel {
    public static Result getGoods(String url){
        String s = OkHttp.get(url);
        Type type = new TypeToken<Result<List<Goods>>>() {}.getType();
        Gson gson = new Gson();
        Result result = gson.fromJson(s, type);
        return result;
    }
}
