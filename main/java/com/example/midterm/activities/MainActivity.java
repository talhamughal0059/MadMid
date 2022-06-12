package com.example.midterm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.midterm.R;
import com.example.midterm.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<News> news;
    private OkHttpClient mClient; //handel netwok request and response
    private String Strurl="https://alasartothepoint.alasartechnologies.com/listItem.php?id=1 "; //Get Request
    ActivityMainBinding binding;

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        GetNews();
    }


    void GetNews(){
         news = new ArrayList<>();
         mClient=new OkHttpClient();
         NewsAdapter newsadapter;
        final String[] myResponse = new String[1];
//        ListView lv;

            OkHttpClient client = new OkHttpClient();
            String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1 ";
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful())
                    {
                        myResponse[0] = response.body().string();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject reader = new JSONObject(myResponse[0]);
                                    JSONArray superheros = reader.getJSONArray("data"); // get the whole json array list
                                    System.out.println("json size is : "+superheros.length());
                                    for(int i = 0;i<superheros.length();i++)
                                    {
                                        JSONObject hero = superheros.getJSONObject(i);
                                        String url = hero.getString("url");
                                        String description = hero.getString("description");
                                        String heading = hero.getString("heading");
                                        String id = hero.getString("id");
                                        String ref = hero.getString("reference");

                                        news.add(new News( heading,url,description,Integer.parseInt(id),ref));
                                        System.out.println("Data Added In News Array \n =>" + heading + ""+ description);
                                        System.out.println("Count =>"+ i +"\n Data Added In News Array \n =>" + news.get(i));

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                }
            });
        newsAdapter = new NewsAdapter(this,news);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1); // 2 mean k 2 coloum may show krwana ha
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsAdapter);

        }

 }




