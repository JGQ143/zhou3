package wzl.com.gouwuche.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import wzl.com.gouwuche.bean.Goods;
import wzl.com.gouwuche.bean.Result;
import wzl.com.gouwuche.utils.OkHttpUtils;

public class GoodsModel {
    public static Result getGoods(String url){
        String s = OkHttpUtils.get(url);
        Type type = new TypeToken<Result<List<Goods>>>() {}.getType();
        Gson gson = new Gson();
        Result result = gson.fromJson(s,type);
        return result;
    }
}
