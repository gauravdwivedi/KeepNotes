package com.focustowardsfuture.gaurav.keepnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav Dwivedi on 15-05-2019.
 */
public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolde> {

   // private List<Note> notes = new ArrayList<>(); //bcz we passed the list to the super class, it will takecare of it
    private OnItemClickListener listener;


    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK =new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {

            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority()==newItem.getPriority();
        }
    };


    @NonNull
    @Override
    public NoteHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolde(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolde holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        if(currentNote.getPriority()>7){
            holder.relativeLayout.setBackgroundResource(R.color.priorityHigh);
        } else if(currentNote.getPriority()>4 && currentNote.getPriority()<8){
            holder.relativeLayout.setBackgroundResource(R.color.priorityMid);
        }else{
            holder.relativeLayout.setBackgroundResource(R.color.priorityLow);
        }
    }
    public Note getNoteAt(int position) {
        return getItem(position);
    }


    class NoteHolde extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private RelativeLayout relativeLayout;


        public NoteHolde(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            relativeLayout=itemView.findViewById(R.id.container);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemCLick(getItem(position));
                    }
                }
            });


        }
    }

    public interface OnItemClickListener {
        void onItemCLick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
