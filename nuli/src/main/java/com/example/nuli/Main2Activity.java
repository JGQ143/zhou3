package com.example.nuli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textview1,textview2;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        image = findViewById(R.id.imageview);

        textview1 = findViewById(R.id.textvieww);
        textview2 = findViewById(R.id.textviewww);
        Intent intent = getIntent();
//        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
//        image.load
        textview1.setText(title);
        textview2.setText("￥"+price);

    }
}
