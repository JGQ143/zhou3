package com.example.nuli.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nuli.MainActivity;
import com.example.nuli.R;
import com.example.nuli.bean.Goods;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Activity activity;
    private List<Goods> list;
    public GridAdapter(MainActivity activity, List<Goods> list) {
        this.activity = activity;
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =View.inflate(activity, R.layout.item_02,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder =(MyHolder) viewHolder;
        myHolder.text3.setText(list.get(i).getTitle());
        myHolder.text4.setText("￥"+list.get(i).getPrice());

        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(activity).load(split[0]).into(myHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView text3,text4;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image2);
            text3 = itemView.findViewById(R.id.textview3);
            text4 = itemView.findViewById(R.id.textview4);
        }
    }

}
