package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView _list_view_ ;
    NotesAdapter notesAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;
        initWidgets() ;
        setNoteAdapter() ;
    }
    @Override
    public void onSaveInstanceState(Bundle outInstanceState) {
        super.onSaveInstanceState(outInstanceState);
        outInstanceState.putInt("value", 1);
    }

    private void initWidgets() {
        _list_view_ = findViewById(R.id._listview_) ;
    }
    private void setNoteAdapter(){
        notesAdapter = new NotesAdapter(getApplicationContext() , ModelNotes._list_NOTE_) ;
        LoadFromDBtoMemory() ;

        _list_view_.setAdapter(notesAdapter);
    }
    private void LoadFromDBtoMemory(){
        SQLiteManager sqLiteManager = SQLiteManager.instanceofDB(this) ;
        sqLiteManager.populateListDB() ;
    }

    public void newNote(View view){
        Intent i = new Intent(getApplicationContext() , NotesDetails.class) ;
        startActivity(i) ;
    }
}