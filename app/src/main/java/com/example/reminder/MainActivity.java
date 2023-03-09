package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView _list_view_ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;
        initWidgets() ;
        setNoteAdapter() ;
    }

    private void initWidgets() {
        _list_view_ = findViewById(R.id._listview_) ;
    }
    private void setNoteAdapter(){
        NotesAdapter notesAdapter = new NotesAdapter(getApplicationContext() , ModelNotes._list_NOTE_) ;
        _list_view_.setAdapter(notesAdapter);
    }
    private void newNote(){
        Intent i = new Intent(this , NotesDetails.class) ;
        startActivity(i) ;
    }
}