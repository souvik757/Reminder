package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ListView _list_view_ ;
    NotesAdapter notesAdapter ;
    TextView _count_ ;
    TextView note ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;
        initWidgets() ;
        setNoteAdapter();
        LoadFromDBtoMemory() ;
        setOnCLickListener() ;
    }
    private void initWidgets() {
        _list_view_ = findViewById(R.id._listview_) ;
        _count_ = findViewById(R.id._number_of_items_) ;
        note = findViewById(R.id._note_) ;
    }
    private void setNoteAdapter() {
        notesAdapter = new NotesAdapter(getApplicationContext(), ModelNotes.nonDeletedNotes());
        _list_view_.setAdapter(notesAdapter);
        if (_list_view_ == null) {
            _count_.setText(String.valueOf(0));
            note.setText("NOTES");
        }else {
            _count_.setText(String.valueOf(_list_view_.getCount()));
            if(_list_view_.getCount() == 1)
                note.setText("NOTE");
            else
                note.setText("NOTES");
        }
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