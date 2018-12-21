package wzl.com.gouwuche.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

import wzl.com.gouwuche.bean.Goods;
import wzl.com.gouwuche.bean.Result;
import wzl.com.gouwuche.core.CallBacks;
import wzl.com.gouwuche.model.GoodsModel;

public class GoodsPresenter {
    private CallBacks callBacks;
    public GoodsPresenter(CallBacks callBacks) {
        this.callBacks = callBacks;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Result result = (Result) msg.obj;
            if (result.getCode()==0){
                callBacks.success(result.getData());
            }else {
                callBacks.fail(result);
            }
        }
    };
    public void goods(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GoodsModel goodsModel = new GoodsModel();
                Result result = goodsModel.getGoods(url);
                Message message = handler.obtainMessage();
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }
}
