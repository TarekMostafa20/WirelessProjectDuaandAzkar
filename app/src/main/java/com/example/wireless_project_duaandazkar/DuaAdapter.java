package com.example.wireless_project_duaandazkar;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DuaAdapter extends RecyclerView.Adapter<DuaAdapter.ViewHolder> {

    private final List<Dua> duaList;

    public DuaAdapter(List<Dua> duaList) {
        this.duaList = duaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dua, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dua dua = duaList.get(position);
        holder.duaText.setText(dua.getText());
        holder.pronunciationText.setText(dua.getPronunciation());

        holder.playButton.setOnClickListener(v -> {
            // Play audio
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(dua.getAudioUrl());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return duaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView duaText, pronunciationText;
        Button playButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            duaText = itemView.findViewById(R.id.duaText);
            pronunciationText = itemView.findViewById(R.id.pronunciationText);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }
}