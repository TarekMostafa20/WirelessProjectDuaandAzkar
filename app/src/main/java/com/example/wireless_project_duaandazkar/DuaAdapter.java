package com.example.wireless_project_duaandazkar;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class DuaAdapter extends RecyclerView.Adapter<DuaAdapter.ViewHolder> {

    private final List<Dua> duaList;
    private TextToSpeech t;

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

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullText = dua.getText();
                String[] words = fullText.split(" "); // Split text into words
                Spannable spannable = new SpannableString(fullText);

                if (t != null) {
                    t.stop();
                    t.shutdown();
                }

                t = new TextToSpeech(v.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            t.setLanguage(new Locale("ar"));
                            t.setSpeechRate(1.0f);

                            // Set up utterance progress listener
                            t.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                                @Override
                                public void onStart(String utteranceId) {
                                    // No action needed on start
                                }

                                @Override
                                public void onDone(String utteranceId) {
                                    // Clear highlight on UI thread
                                    holder.duaText.post(() -> holder.duaText.setText(fullText));
                                }

                                @Override
                                public void onError(String utteranceId) {
                                    // Handle errors if needed
                                }

                                @Override
                                public void onRangeStart(String utteranceId, int start, int end, int frame) {
                                    // Highlight the current word being spoken
                                    holder.duaText.post(() -> {
                                        spannable.setSpan(
                                                new BackgroundColorSpan(Color.YELLOW), // Highlight color
                                                start,
                                                end,
                                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                        );
                                        holder.duaText.setText(spannable);
                                    });
                                }
                            });

                            // Convert the text to a speakable utterance
                            Bundle params = new Bundle();
                            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f);
                            t.speak(fullText, TextToSpeech.QUEUE_FLUSH, params, "DuaUtteranceId");
                        }
                    }
                });
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