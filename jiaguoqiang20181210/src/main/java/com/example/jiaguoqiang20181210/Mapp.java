package com.example.jiaguoqiang20181210;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class Mapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader instance = ImageLoader.getInstance();
        instance.init(imageLoaderConfiguration);
        UMConfigure.init(this,"5c089159b465f59767000066","小米",UMConfigure.DEVICE_TYPE_PHONE,"");
/**
 * 设置组件化的Log开关
 * 参数: boolean 默认为false，如需查看LOG设置为true
 */
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
