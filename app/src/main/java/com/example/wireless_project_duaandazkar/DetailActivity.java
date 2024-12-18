package com.example.wireless_project_duaandazkar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

        // Getcategory
        category = getIntent().getStringExtra("category");
        if (category == null) {
            Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load category
        loadDuaList();
        adapter = new DuaAdapter(duaList);
        recyclerView.setAdapter(adapter);
    }

    private void loadDuaList() {
        duaList = new ArrayList<>();

        // data
        switch (category) {
            case "Dua after Salah":
                duaList.add(new Dua("", "Audio Link 1", ""));
                duaList.add(new Dua("", "Audio Link 2", ""));
                break;

            case "Morning Azkar":
                duaList.add(new Dua("", "Audio Link 1", ""));

                break;

            case "Evening Azkar":
                duaList.add(new Dua("", "Audio Link 1", " "));
                break;

            case "Daily Essential Dua":
                duaList.add(new Dua("", "Audio Link 1", " "));
                break;

            case "40 Dua begins with ‘Rabbana’":
                duaList.add(new Dua("", "Audio Link 1", " "));
                break;

            case "Ruquiya (Audio Only)":
                duaList.add(new Dua("", "Audio Link 1", " "));
                break;
            case "Missed Rak’ah Procedure":
                duaList.add(new Dua("", "Audio Link 1", ""));
                break;

            case "Hajj & Umrah":
                duaList.add(new Dua("", "Audio Link 1", ""));
                break;

            default:
                Toast.makeText(this, "No details available", Toast.LENGTH_SHORT).show();
        }
    }
}
