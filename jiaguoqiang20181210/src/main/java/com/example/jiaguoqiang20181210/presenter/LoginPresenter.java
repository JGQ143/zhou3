package com.example.jiaguoqiang20181210.presenter;


import android.os.Handler;
import android.os.Message;


import com.example.jiaguoqiang20181210.bean.Result;
import com.example.jiaguoqiang20181210.core.DataCall;
import com.example.jiaguoqiang20181210.model.LoginModel;

public class LoginPresenter {
    //接口
    private DataCall dataCall;

    public LoginPresenter(DataCall dataCall) {
        this.dataCall=dataCall;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Result result = (Result) msg.obj;
            if (result.getCode()==0){
                dataCall.success(result.getData());
            }else{
                dataCall.fail(result);
            }
        }
    };

    //子线程
    public void add(final String mobile, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result login = LoginModel.login(mobile,password);
                Message message = handler.obtainMessage();
                message.obj=login;
                handler.sendMessage(message);
            }
        }).start();
    }
}
