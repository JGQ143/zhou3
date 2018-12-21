package com.example.nuli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nuli.adapter.GridAdapter;
import com.example.nuli.adapter.ListAdapter;
import com.example.nuli.bean.Goods;
import com.example.nuli.bean.Result;
import com.example.nuli.core.CallBacks;
import com.example.nuli.presenter.ListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBacks<List<Goods>> {

    private Button button1,button2,button3,button4;
    String url = "http://www.zhaoapi.cn/product/searchProducts?keywords=手机&page=";
//    http://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    private EditText met;
    private XRecyclerView xrecyclerview;
    private ImageView image1,image2;
    List<Goods> list = new ArrayList<>();
    int page=1;
    int sort=0;
    //判断布局
    boolean a=true;
    private ListPresenter listPresenter;
    private ListAdapter adapter;
    private GridAdapter gridAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String trim="手机";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        button1 = findViewById(R.id.mBt1);
        button2 = findViewById(R.id.mBt2);
        button3 = findViewById(R.id.mBt3);
        button4 = findViewById(R.id.mBt4);
        image1 = findViewById(R.id.mSou);
        image2 = findViewById(R.id.mQie);
        met = findViewById(R.id.mET);
        xrecyclerview = findViewById(R.id.mXRV);
        //presenter层
        listPresenter = new ListPresenter(this);
        String urls=url+page+"&sort="+sort;
        listPresenter.add(urls);
        //线性adapter
        adapter = new ListAdapter(MainActivity.this,list);
        linearLayoutManager = new LinearLayoutManager(this);

        xrecyclerview.setLayoutManager(linearLayoutManager);
        gridAdapter = new GridAdapter(MainActivity.this,list);
        xrecyclerview.setAdapter(adapter);
        //搜索按钮
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                trim = met.getText().toString().trim();
                page=1;
                sort=0;
                url = "http://www.zhaoapi.cn/product/searchProducts?keywords="+ trim +"&page=";
                String urls=url+page+"&sort="+sort;
                listPresenter.add(urls);
            }
        });
        //设置刷新
        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                String urls=url+page+"&sort="+sort;
                list.clear();
                listPresenter.add(urls);
            }

            @Override
            public void onLoadMore() {
                page++;
                String urls=url+page+"&sort="+sort;
                listPresenter.add(urls);
            }
        });
        //价格
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                page=1;
                sort=2;
                url = "http://www.zhaoapi.cn/product/searchProducts?keywords="+trim+"&page=";
                String urls=url+page+"&sort="+sort;
                listPresenter.add(urls);
            }
        });
        //销量
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                page=1;
                sort=1;
                url = "http://www.zhaoapi.cn/product/searchProducts?keywords="+trim+"&page=";
                String urls=url+page+"&sort="+sort;
                listPresenter.add(urls);
            }
        });
        //切换
        image2.setOnClickListener(new View.OnClickListener() {
            private GridLayoutManager gridLayoutManager;
            @Override
            public void onClick(View v) {
                if (a){
                    gridAdapter = new GridAdapter(MainActivity.this,list);
                    gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    xrecyclerview.setLayoutManager(gridLayoutManager);
                    xrecyclerview.setAdapter(gridAdapter);
                    a=false;
                }else {
                    adapter = new ListAdapter(MainActivity.this,list);
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    xrecyclerview.setLayoutManager(linearLayoutManager);
                    xrecyclerview.setAdapter(adapter);
                    a=true;
                }
            }
        });
    }
    //成功
    @Override
    public void success(List<Goods> goods) {
        list.addAll(goods);
        adapter.notifyDataSetChanged();
        xrecyclerview.loadMoreComplete();
        xrecyclerview.refreshComplete();
        gridAdapter.notifyDataSetChanged();
        //删除条目
//        adapter.setOnItmeClickListener(new ListAdapter.ClickListener() {
//            @Override
//            public void onItmeClickListener(View view, int position) {
//                list.remove(position);
//                adapter.notifyDataSetChanged();
//            }
//        });
    }
    //失败
    @Override
    public void fail(Result result) {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
    }
}
