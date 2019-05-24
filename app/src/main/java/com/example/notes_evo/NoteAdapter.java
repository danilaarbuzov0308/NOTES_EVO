package com.example.notes_evo;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getDate().equals(newItem.getDescription()) &&
                    oldItem.getTime().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        String getNote = currentNote.getDescription();
        String upToNCharacters = getNote.substring(0, Math.min(getNote.length(), 97));
        if(upToNCharacters.length() == 97) {
            upToNCharacters += "...";
        }
        holder.noteText.setText(upToNCharacters);
        holder.date.setText(currentNote.getDate());
        holder.time.setText(currentNote.getTime());

    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }



    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView noteText;
        private TextView date;
        private TextView time;


        public NoteHolder(View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.noteText);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
