package com.example.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        TextView _date_ = convertView.findViewById(R.id._cell_date_) ;
        _title_.setText(NOTES.getTile());
        _description_.setText(NOTES.getDescription());
        _date_.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date())) ;

        return convertView ;
    }
}
