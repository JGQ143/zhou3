package com.example.jiaguoqiang20181210;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.jiaguoqiang20181210.Fragment.Frag_One;
import com.example.jiaguoqiang20181210.Fragment.Frag_Two;

public class TwoActivity extends AppCompatActivity {

    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //开启广播
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        final Frag_One frag_one = new Frag_One();
        final Frag_Two frag_two = new Frag_Two();
        transaction.add(R.id.flow,frag_one);
        transaction.add(R.id.flow,frag_two);
        transaction.hide(frag_two);
        transaction.commit();

        radio = findViewById(R.id.radiogroup);
        //监听
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.hide(frag_one);
                transaction1.hide(frag_two);
                switch (checkedId){
                    case 1:
                        transaction1.show(frag_one);
                        break;
                    case 2:
                        transaction1.show(frag_two);
                        break;
                }
                transaction1.commit();
            }
        });

    }
}
