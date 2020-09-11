package com.example.spidernotes.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spidernotes.NoteDetails;
import com.example.spidernotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles;
    List<String> content;

    public Adapter(List<String> title, List<String> content){
        this.titles = title;
        this.content = content;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        final int code = getRandomColour();
        holder.mCardView.setCardBackgroundColor(holder.view.getResources().getColor(code, null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NoteDetails.class);
                i.putExtra("title", titles.get(position));
                i.putExtra("content", content.get(position));
                i.putExtra("code", code);
                v.getContext().startActivity(i);
            }
        });
    }

    private int getRandomColour() {
        List<Integer> colourCode = new ArrayList<>();
        colourCode.add(R.color.blue);
        colourCode.add(R.color.yellow);
        colourCode.add(R.color.skyblue);
        colourCode.add(R.color.lightPurple);
        colourCode.add(R.color.lightGreen);
        colourCode.add(R.color.gray);
        colourCode.add(R.color.pink);
        colourCode.add(R.color.red);
        colourCode.add(R.color.greenlight);
        colourCode.add(R.color.notgreen);

        Random randomColour = new Random();
        int number = randomColour.nextInt(colourCode.size());
        return colourCode.get(number);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        View view;
        CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            mCardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
