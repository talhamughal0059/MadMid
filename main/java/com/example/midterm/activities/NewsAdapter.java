package com.example.midterm.activities;
//
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.midterm.databinding.NewsItemBinding;

import java.util.ArrayList;
import com.example.midterm.R;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
   private Context context;
   private ArrayList<News> news;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News newsdata = news.get(position);
//        holder.id.setText(newsdata.getId());
        holder.heading.setText(newsdata.getHeading());
        holder.description.setText(newsdata.getDescription());
        Glide.with(context).load(newsdata.getUrl()).into(holder.image);


        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, newsdata.getRef());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "News Link");
                context.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView id,heading,description;
        Button share;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mainnewsimage);
            heading=itemView.findViewById(R.id.mainheading);
            description=itemView.findViewById(R.id.maindescription);
            share = itemView.findViewById(R.id.sharebtn);
        }
    }
}




//.........................................................
//
//public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
//
//    Context context;
//    ArrayList<News> newslist;
//
//    public NewsAdapter(Context context, ArrayList<News> news) {
//        this.context = context;
//        this.newslist = news;
//    }
//
//
//
//    @NonNull
//    @Override
//    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
//        return new NewsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
//        News news = newslist.get(position);
//        //image , label ,price is an id that we use in xml
//        Glide.with(context).load(news.getUrl()).into(holder.binding.mainnewsimage);
//        holder.binding.mainheading.setText(news.getHeading());
//        holder.binding.mainnewsid.setText( news.getId());
//        holder.binding.maindescription.setText( news.getId());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return newslist.size();
//    }
//
//    public class NewsViewHolder extends RecyclerView.ViewHolder{
//
//        NewsItemBinding binding;
//
//        public NewsViewHolder(@NonNull View itemView) {
//            super(itemView);
//            //we use bind in Adopter because inflate is not available in Adopter inflate is available in mann Activity
//            binding= NewsItemBinding.bind(itemView);
//        }
//    }
//}
//
