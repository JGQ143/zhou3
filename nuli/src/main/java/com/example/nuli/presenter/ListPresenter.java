package com.example.nuli.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.nuli.MainActivity;
import com.example.nuli.bean.Result;
import com.example.nuli.core.CallBacks;
import com.example.nuli.model.ListModel;

public class ListPresenter {
    CallBacks callBacks;
    public ListPresenter(CallBacks callBacks) {
        this.callBacks=callBacks;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Result result = (Result) msg.obj;
            if (result.getCode()==0){
                callBacks.success(result.getData());
            }else {
                callBacks.fail(result);
            }
        }
    };

    public void add(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result = ListModel.getGoods(url);
                Message message = handler.obtainMessage();
                message.obj=result;
                handler.sendMessage(message);
            }
        }).start();
    }
}
