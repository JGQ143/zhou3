package wzl.com.gouwuche.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wzl.com.gouwuche.R;
import wzl.com.gouwuche.bean.Goods;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<Goods> list;
    public GridAdapter(Activity activity, List<Goods> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(activity,R.layout.item_02,null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.textView.setText(list.get(i).getTitle());
        holder1.textView2.setText("￥"+list.get(i).getPrice());
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(activity).load(split[0]).into(holder1.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mIV);
            textView = itemView.findViewById(R.id.mTV);
            textView2 = itemView.findViewById(R.id.mTV1);
        }
    }
}
