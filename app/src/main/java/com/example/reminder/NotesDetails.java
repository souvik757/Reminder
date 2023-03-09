package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class NotesDetails extends AppCompatActivity {
    private EditText _for_TITLE_ ;
    private EditText _for_DESCRIPTION_ ;
    Button _save_ ;

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
    }

    private void initWidgets() {
        _for_TITLE_       = findViewById(R.id._edit_title_) ;
        _for_DESCRIPTION_ = findViewById(R.id._edit_description_) ;
    }

    public void saveNote(View view){
        String title = String.valueOf(_for_TITLE_.getText()) ;
        String description  = String.valueOf(_for_DESCRIPTION_.getText()) ;
        int ID = ModelNotes._list_NOTE_.size() ;
        ModelNotes _notes_ = new ModelNotes(ID , title , description) ;
        ModelNotes._list_NOTE_.add(_notes_) ;
        onBackPressed() ;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}