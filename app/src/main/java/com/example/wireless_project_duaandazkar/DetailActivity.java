package com.example.wireless_project_duaandazkar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DuaAdapter adapter;
    private List<Dua> duaList;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        category = getIntent().getStringExtra("category");
        if (category == null) {
            Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDuaList();
        adapter = new DuaAdapter(duaList);
        recyclerView.setAdapter(adapter);
    }

    private void loadDuaList() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("categories", new String[]{"id"}, "name=?", new String[]{category}, null, null, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int categoryId = cursor.getInt(cursor.getColumnIndex("id"));
            duaList = dbHelper.getDuasByCategory(db, categoryId);
        }
        cursor.close();
    }
}
