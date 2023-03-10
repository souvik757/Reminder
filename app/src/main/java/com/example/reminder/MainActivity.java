package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView _list_view_ ;
    NotesAdapter notesAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;
        initWidgets() ;
        setNoteAdapter();
        LoadFromDBtoMemory() ;
        setOnCLickListener() ;
    }
    public void setOnCLickListener(){
        _list_view_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelNotes modelNotes = (ModelNotes) _list_view_.getItemAtPosition(position) ;
                Intent i = new Intent(getApplicationContext() , NotesDetails.class) ;
                i.putExtra(ModelNotes.NOTE_EDIT_EXTRA , modelNotes.getId()) ;
                startActivity(i) ;
            }
        });
    }
    private void initWidgets() {
        _list_view_ = findViewById(R.id._listview_) ;
    }
    private void setNoteAdapter(){
        notesAdapter = new NotesAdapter(getApplicationContext() , ModelNotes.nonDeletedNotes()) ;
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

    @Override
  protected void onResume() {
      super.onResume();
      setNoteAdapter();
  }
}