package com.example.jiaguoqiang20181210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaguoqiang20181210.bean.Useru;
import com.example.jiaguoqiang20181210.core.ZhuCe;
import com.example.jiaguoqiang20181210.presenter.ZhucePresenter;

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener,ZhuCe {

    private EditText edit3,edit4;
    private Button button2;

    ZhucePresenter zhucePresenter = new ZhucePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        //获取控件
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        button2 = findViewById(R.id.mbutton2);
        button2.setOnClickListener(this);
    }

    //点击按钮
    @Override
    public void onClick(View v) {
        String name = edit3.getText().toString().trim();
        String password = edit4.getText().toString().trim();
        zhucePresenter.add(name,password);
    }

    //接口
    @Override
    public void callback(Useru user) {
        String code = user.getCode();
        if (code.equals("0")){
            Toast.makeText(ThreeActivity.this,"注册成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ThreeActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(ThreeActivity.this,"注册失败",Toast.LENGTH_LONG).show();
        }
    }


}
