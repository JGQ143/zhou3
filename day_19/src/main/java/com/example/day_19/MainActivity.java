package com.example.day_19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);

        ArrayList<String> list = new ArrayList<>();

        for (int i=0;i<30;i++){
            list.add("张三"+i);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(android.R.layout.simple_list_item_1,list);
//        listview.setAdapter(adapter);
    }
}
