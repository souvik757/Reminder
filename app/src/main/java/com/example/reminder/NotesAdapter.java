package com.example.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotesAdapter extends  ArrayAdapter<ModelNotes> {
    public NotesAdapter(Context context , List<ModelNotes> _list_){
        super(context , 0 , _list_);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ModelNotes NOTES = getItem(position) ;
        if(convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.notes_cell , parent , false) ;

        TextView _title_ = convertView.findViewById(R.id._cell_title_) ;
        TextView _description_ = convertView.findViewById(R.id._cell_description_) ;
        _title_.setText(NOTES.getTile());
        _description_.setText(NOTES.getDescription());

        return convertView ;
    }
}
