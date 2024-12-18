package com.example.wireless_project_duaandazkar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<String> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //categories
        categoryList = new ArrayList<>();
        categoryList.add("Dua after Salah");
        categoryList.add("Morning Azkar");
        categoryList.add("Evening Azkar");
        categoryList.add("Daily Essential Dua");
        categoryList.add("40 Dua begins with 'Rabbana'");
        categoryList.add("Ruquiya (Audio Only)");
        categoryList.add("Missed Rak’ah Procedure");
        categoryList.add("Hajj & Umrah");

        //adapter
        adapter = new CategoryAdapter(categoryList, this::onCategoryClick);
        recyclerView.setAdapter(adapter);
    }

    private void onCategoryClick(String category) {

        Intent intent = new Intent(this, DetailActivity.class);


        intent.putExtra("category", category);


        startActivity(intent);
    }
}