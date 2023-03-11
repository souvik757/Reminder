package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class NotesDetails extends AppCompatActivity {
    private EditText _for_TITLE_ ;
    private EditText _for_DESCRIPTION_ ;
    private ModelNotes modelNotes ;
    Button _del_btn_ ;
    FloatingActionButton _save_ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        initWidgets() ;
        _save_ = findViewById(R.id._save_new_entry_) ;
        _save_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(v);
                Snackbar.make(v , "Saved" , Snackbar.LENGTH_LONG).show() ;
            }
        });
        checkForEditNote() ;
    }

    private void checkForEditNote(){
        Intent prev_intent = getIntent() ;
        int passedID = prev_intent.getIntExtra(ModelNotes.NOTE_EDIT_EXTRA , -1) ;
        modelNotes = ModelNotes.getIDofNote(passedID) ;
        if(modelNotes != null){
            _for_TITLE_.setText(modelNotes.getTile()) ;
            _for_DESCRIPTION_.setText(modelNotes.getDescription()) ;
        }
        else
        {
            _del_btn_.setVisibility(View.INVISIBLE) ;
        }
    }

    private void initWidgets() {
        _for_TITLE_       = findViewById(R.id._edit_title_) ;
        _for_DESCRIPTION_ = findViewById(R.id._edit_description_) ;
        _del_btn_ = findViewById(R.id._DEL_) ;
    }

    public void saveNote(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceofDB(this) ;

        String title = String.valueOf(_for_TITLE_.getText()) ;
        String description  = String.valueOf(_for_DESCRIPTION_.getText()) ;
        if(title.isEmpty() || description.isEmpty()) {
            Snackbar.make(view ,"void entry" , Snackbar.LENGTH_LONG).show() ;
            Toast.makeText(this, "void entry", Toast.LENGTH_SHORT).show();
        }
        if(modelNotes == null){
            int ID = ModelNotes._list_NOTE_.size() ;
            ModelNotes _notes_ = new ModelNotes(ID , title , description) ;
            ModelNotes._list_NOTE_.add(_notes_) ;
            sqLiteManager.AddNoteToDB(_notes_) ;
        }
        else
        {
            modelNotes.setTile(title) ;
            modelNotes.setDescription(description) ;
            sqLiteManager.updateNotesDB(modelNotes);
        }
        finish();
    }

    public void deleteNote(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceofDB(this) ;
        ModelNotes._list_NOTE_.remove(modelNotes) ;
        sqLiteManager.DeleteNote(modelNotes) ;
        finish();
    }
}