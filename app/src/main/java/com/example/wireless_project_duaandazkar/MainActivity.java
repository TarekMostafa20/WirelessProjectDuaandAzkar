package com.example.wireless_project_duaandazkar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // Fetch categories from database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        categoryList = dbHelper.getCategories(db);

        adapter = new CategoryAdapter(categoryList, this::onCategoryClick);
        recyclerView.setAdapter(adapter);
    }

    private void onCategoryClick(String category) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
