package com.example.jiaguoqiang20181210.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.jiaguoqiang20181210.Http.Utils;
import com.example.jiaguoqiang20181210.ThreeActivity;
import com.example.jiaguoqiang20181210.bean.Useru;
import com.example.jiaguoqiang20181210.core.ZhuCe;
import com.example.jiaguoqiang20181210.model.ZhuceModel;

public class ZhucePresenter {
    private ZhuCe zhuCe;
    public ZhucePresenter(ZhuCe zhuCe) {
        this.zhuCe=zhuCe;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Useru useru = (Useru) msg.obj;

            zhuCe.callback(useru);
        }
    };

    public void add(final String name, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Useru zhuce = ZhuceModel.zhuce(name, password);
                Message message = handler.obtainMessage();
                message.obj=zhuce;
                handler.sendMessage(message);
            }
        }).start();
    }
}
