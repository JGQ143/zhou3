package wzl.com.gouwuche.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpUtils {

    public static String get(String string) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(string).build();
        Call call = client.newCall(request);
        try {
            Response execute = call.execute();
            return execute.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void upImage(File file) {
        OkHttpClient mOkHttpClent = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "aa.png",
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("uid", "23864");

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url("http://www.zhaoapi.cn/file/upload")
                .post(requestBody)
                .build();
        Call call = mOkHttpClent.newCall(request);
        try {
            Response response = call.execute();
            final String string = response.body().string();
            Log.e("---------------------", "----" + string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
