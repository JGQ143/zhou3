package wzl.com.gouwuche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wzl.com.gouwuche.R;


public class ShowActivity extends AppCompatActivity {

    private ImageView miv_show;
    private TextView mtv_show;
    private TextView mtv1_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //初始化组件
        miv_show = findViewById(R.id.mIV_show);
        mtv_show = findViewById(R.id.mTV_show);
        mtv1_show = findViewById(R.id.mTV1_show);
        //接收值
        Intent intent = getIntent();
        String images = intent.getStringExtra("images");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        //设置值
        String[] split = images.split("\\|");
        Glide.with(this).load(split[0]).into(miv_show);
        mtv_show.setText(title);
        mtv1_show.setText(price);
    }
}
