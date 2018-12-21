package com.dingtao.week3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dingtao.week3.R;
import com.dingtao.week3.adapter.GoodsListAdapter;
import com.dingtao.week3.bean.Goods;
import com.dingtao.week3.bean.Result;
import com.dingtao.week3.core.DataCall;
import com.dingtao.week3.presenter.GoodsListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener,
        DataCall<List<Goods>>, View.OnClickListener,GoodsListAdapter.OnItemClickListener {

    private XRecyclerView mRecyclerView;
    private GoodsListAdapter mAdapter;
    private LinearLayoutManager mLinearManager;
    private GridLayoutManager mGridManager;

    private static final int GRID_LAYOUT_MANAGER = 1;
    private static final int LINEAR_LAYOUT_MANAGER = 2;


    private ImageView mBtnLayout;
    private EditText mKeywordsEdit;

    //新建商品列表Presenter
    private GoodsListPresenter mPresenter = new GoodsListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKeywordsEdit = findViewById(R.id.edit_keywords);
        mBtnLayout = findViewById(R.id.btn_layout);

        findViewById(R.id.btn_search).setOnClickListener(this);
        mBtnLayout.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.list_goods);//查找mRecyclerView
        mRecyclerView.setLoadingListener(this);//添加下拉和刷新的监听器

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mGridManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);//网格布局
        mLinearManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//线性布局
        mRecyclerView.setLayoutManager(mLinearManager);

        mAdapter = new GoodsListAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        //加载数据
//        mRecyclerView.refresh();//刷新
    }

    @Override
    public void onRefresh() {
        String keywords = mKeywordsEdit.getText().toString();
        mPresenter.requestData(true, keywords);
    }

    @Override
    public void onLoadMore() {
        String keywords = mKeywordsEdit.getText().toString();
        mPresenter.requestData(false, keywords);
    }

    @Override
    public void success(List<Goods> data) {
        mRecyclerView.refreshComplete();//结束刷新
        mRecyclerView.loadMoreComplete();//结束加载更多
        if (mPresenter.isResresh()) {
            mAdapter.clearList();
        }
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        mRecyclerView.refreshComplete();
        mRecyclerView.loadMoreComplete();
        Toast.makeText(this, result.getCode() + "  " + result.getMsg(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unBindCall();
    }

    private boolean isGrid = false;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search) {//搜索
            String keywords = mKeywordsEdit.getText().toString();
            mPresenter.requestData(true, keywords);
        } else if (v.getId() == R.id.btn_layout) {//切换布局

//            if (!isGrid) {
            if (mRecyclerView.getLayoutManager().equals(mLinearManager)) {
//            if (mAdapter.getItemViewType(0) == GoodsListAdapter.LINEAR_TYPE) {
                isGrid = true;
                mAdapter.setViewType(GoodsListAdapter.GRID_TYPE);
                mRecyclerView.setLayoutManager(mGridManager);
            } else {
                isGrid = false;
                mAdapter.setViewType(GoodsListAdapter.LINEAR_TYPE);
                mRecyclerView.setLayoutManager(mLinearManager);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Goods goods) {
        Intent intent = new Intent(this,WebActivity.class);
        intent.putExtra("url",goods.getDetailUrl());
        startActivity(intent);
    }
}
