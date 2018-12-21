package com.example.jiaguoqiang20181210;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaguoqiang20181210.bean.Result;
import com.example.jiaguoqiang20181210.bean.User;
import com.example.jiaguoqiang20181210.core.DataCall;
import com.example.jiaguoqiang20181210.presenter.LoginPresenter;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DataCall<User>{

    private EditText edit1,edit2;
    private Button button,button2;

    private List<String> list;
    //创建接口
    LoginPresenter loginPresenter = new LoginPresenter(this);
    private Button qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        button = findViewById(R.id.mbutton1);
        button2 = findViewById(R.id.mbutton2);
        qq = findViewById(R.id.qq);
        //第三方登录
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){//QQ需要申请写入权限
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(MainActivity.this,mPermissionList,123);
                }else{
                    UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                }
            }
        });
        //设置监听
        button.setOnClickListener(this);
        //注册
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ThreeActivity.class);
                startActivity(intent);
            }
        });
        //全局捕获异常

    }
    // 点击Hello World进行操作
    public void click(View v){
        Log.i("---条数",list.size()+"");
    }

    @Override
    public void onClick(View v) {
            String mobile = edit1.getText().toString();
            String password = edit2.getText().toString();
            //传送
            loginPresenter.add(mobile,password);
            Intent intent = new Intent(MainActivity.this,TwoActivity.class);
            startActivity(intent);
    }
    //成功
    @Override
    public void success(User data) {
        Toast.makeText(MainActivity.this,data.getUsername(),Toast.LENGTH_LONG).show();
    }
    //失败
    @Override
    public void fail(Result result) {
        Toast.makeText(MainActivity.this,result.getCode()+""+result.getMsg(),Toast.LENGTH_LONG);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==123){
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
        }
    }


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,TwoActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),               Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }








}
