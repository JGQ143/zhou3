package wzl.com.gouwuche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import wzl.com.gouwuche.R;
import wzl.com.gouwuche.adapter.GridAdapter;
import wzl.com.gouwuche.adapter.LinearAdapter;
import wzl.com.gouwuche.bean.Goods;
import wzl.com.gouwuche.bean.Result;
import wzl.com.gouwuche.core.CallBacks;
import wzl.com.gouwuche.presenter.GoodsPresenter;

public class MainActivity extends AppCompatActivity implements CallBacks<List<Goods>> {

    private EditText met;
    private GoodsPresenter presenter;
    private List<Goods> list = new ArrayList<>();
    String url = "http://www.zhaoapi.cn/product/searchProducts?keywords=手机&page=";
    private LinearAdapter adapter;
    private GridAdapter gridAdapter;
    private XRecyclerView mxrv;
    private ImageView msou;
    int page = 1;
    int sort=0;
    private Button mbt3;
    private Button mbt1;
    private Button mbt2;
    private ImageView mqie;
    boolean aaa = true;
    private GridLayoutManager layoutManager2;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        met = findViewById(R.id.mET);
        mxrv = findViewById(R.id.mXRV);
        //创建对象
        presenter = new GoodsPresenter(this);
        String urls = url+page+"&sort="+sort;
        presenter.goods(urls);
        //设置适配器
        adapter = new LinearAdapter(this,list);
        gridAdapter = new GridAdapter(MainActivity.this,list);
        //设置布局管理类
        layoutManager = new LinearLayoutManager(this);
        mxrv.setLayoutManager(layoutManager);
        mxrv.setAdapter(adapter);
       //点击按钮搜索
        msou = findViewById(R.id.mSou);
        msou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String trim = met.getText().toString().trim();
                sort = 0;
                page = 1;
                url = "http://www.zhaoapi.cn/product/searchProducts?keywords="+trim+"&page=";
                String urls = url+page+"&sort="+sort;
                presenter.goods(urls);
            }
        });
        //设置刷新
        mxrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String urls = url+page+"&sort="+sort;
                list.clear();
                presenter.goods(urls);
            }
            @Override
            public void onLoadMore() {
                page++;
                String urls = url+page+"&sort="+sort;
                presenter.goods(urls);
            }
        });
        //综合
        mbt1 = findViewById(R.id.mBt1);
        mbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sort=0;
                page=1;
                String urls = url+page+"&sort="+sort;
                presenter.goods(urls);
            }
        });
        //销量
        mbt2 = findViewById(R.id.mBt2);
        mbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sort=1;
                page=1;
                String urls = url+page+"&&sort="+sort;
                presenter.goods(urls);
            }
        });
        //价格排序
        mbt3 = findViewById(R.id.mBt3);
        mbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sort=2;
                page=1;
                String urls = url+page+"&&sort="+sort;
                presenter.goods(urls);
            }
        });
        //切换布局
        mqie = findViewById(R.id.mQie);
        mqie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aaa){
                    gridAdapter = new GridAdapter(MainActivity.this,list);
                    //设置布局管理类
                    layoutManager2 = new GridLayoutManager(MainActivity.this,2);
                    mxrv.setLayoutManager(layoutManager2);
                    mxrv.setAdapter(gridAdapter);
                    aaa=false;
                }else {
                    adapter = new LinearAdapter(MainActivity.this,list);
                    //设置布局管理类
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    mxrv.setLayoutManager(layoutManager);
                    mxrv.setAdapter(adapter);
                    aaa=true;
                }
            }
        });
        adapter.RecyclerDelete(new LinearAdapter.OnRecyclerViewItemClickListtener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("images",list.get(position).getImages());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("price",list.get(position).getPrice()+"");
                startActivity(intent);
            }
        });
    }
    @Override
    public void success(List<Goods> goods) {
        list.addAll(goods);
        adapter.notifyDataSetChanged();
        mxrv.loadMoreComplete();
        mxrv.refreshComplete();
        gridAdapter.notifyDataSetChanged();
    }
    @Override
    public void fail(Result result) {

    }
}
