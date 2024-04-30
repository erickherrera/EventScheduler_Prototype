package com.example.eventscheduler01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycler_Event_Adapter extends RecyclerView.Adapter<Recycler_Event_Adapter.EventViewHolder>{

    private final List<String> eventInfo;
    private final OnRemoveEventListener removeEventListener;


    public Recycler_Event_Adapter(List<String> eventInfo, OnRemoveEventListener removeEventListener){
        this.eventInfo = eventInfo;
        this.removeEventListener = removeEventListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position){
        String eventData = eventInfo.get(position);
        holder.textView.setText(eventData);
        holder.removeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the event from the list
                eventInfo.remove(holder.getAdapterPosition());
                // Notify adapter that item has been removed
                notifyItemRemoved(holder.getAdapterPosition());
                // Notify listener that an event has been removed
                removeEventListener.onRemoveEvent(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount(){
        return eventInfo.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button removeEventButton;

        EventViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.cardView);
            removeEventButton = itemView.findViewById(R.id.removeEvent_button);
        }
    }

    // Interface to handle remove event action
    public interface OnRemoveEventListener {
        void onRemoveEvent(int position);
    }
}
