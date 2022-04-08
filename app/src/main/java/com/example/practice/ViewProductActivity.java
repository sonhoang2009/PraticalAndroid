package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.practice.adapter.ListProductAdapter;
import com.example.practice.entity.Product;
import com.example.practice.repository.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewProductActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListSong;
    private List<Product> products;
    private AppDatabase db;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        initView();

        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProductActivity.this, MainActivity.class);
                ViewProductActivity.this.startActivity(intent);
            }
        });
    }

    private void initView() {
        backBtn = findViewById(R.id.view_back_to_add);
        products = new ArrayList<>();
        db = AppDatabase.getAppDatabase(this);
        products = db.productDao().findAll();
        Log.d("TAG ID", "initView: " + products.get(0).getId());
        Log.d("TAG ID", "initView: " + products.get(0).getName());
        Log.d("TAG ID", "initView: " + products.get(0).getQuantity());
        recyclerViewListSong = findViewById(R.id.recycler_view_list_product);
        recyclerViewListSong.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListSong.setAdapter(new ListProductAdapter(this, products));
        ((RecyclerView.Adapter) recyclerViewListSong.getAdapter()).notifyDataSetChanged();
    }
}