package com.example.reminder;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends  ArrayAdapter<ModelNotes> {
    TextView _title_ ;
    TextView _description_ ;
    static TextView _date_ ;
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

        _title_ = convertView.findViewById(R.id._cell_title_) ;
        _description_ = convertView.findViewById(R.id._cell_description_) ;
        _date_ = convertView.findViewById(R.id._cell_date_) ;
        _title_.setText(NOTES.getTile());
        _description_.setText(NOTES.getDescription());
        //String x = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()) ;
        _date_.setText(" ") ;
        return convertView ;
    }
    public  void SaveDateCreated(String x , Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DateCreated" , MODE_PRIVATE) ;
        SharedPreferences.Editor editor =sharedPreferences.edit() ;
        editor.putString("KEY" , x) ;
        editor.commit() ;
    }

    public void DisplayCreationDate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("DateCreated", MODE_PRIVATE);
        String text = sharedPreferences.getString("KEY", " ");
        // setting text to textview -->
        _date_.setText(text);
    }
}
