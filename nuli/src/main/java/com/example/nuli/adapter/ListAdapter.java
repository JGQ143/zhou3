package com.example.nuli.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nuli.Main2Activity;
import com.example.nuli.MainActivity;
import com.example.nuli.R;
import com.example.nuli.bean.Goods;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Activity activity;
    private List<Goods> list;
    private ClickListener clickListener;

    public ListAdapter(MainActivity activity, List<Goods> list) {
        this.activity=activity;
        this.list=list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(activity,R.layout.item_01,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final MyHolder holder1 = (MyHolder) viewHolder;
        holder1.text1.setText(list.get(i).getTitle());
        holder1.text2.setText("￥"+list.get(i).getPrice());
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(activity).load(split[0]).into(holder1.image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"点击了",Toast.LENGTH_LONG).show();

//                clickListener.onItmeClickListener(v,i);
                Intent intent = new Intent(activity,Main2Activity.class);
                intent.putExtra("image",list.get(i).getDetailUrl());
                intent.putExtra("title",list.get(i).getTitle());
                intent.putExtra("price",list.get(i).getPrice());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView text1,text2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mIV1);
            text1 = itemView.findViewById(R.id.textview1);
            text2 = itemView.findViewById(R.id.textview2);
        }
    }
    public interface ClickListener{
        void onItmeClickListener(View view,int position);
    }
    public void setOnItmeClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
