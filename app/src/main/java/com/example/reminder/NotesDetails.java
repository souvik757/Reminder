package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NotesDetails extends AppCompatActivity {
    private EditText _for_TITLE_ ;
    private EditText _for_DESCRIPTION_ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        initWidgets() ;
    }

    private void initWidgets() {
        _for_TITLE_       = findViewById(R.id._edit_title_) ;
        _for_DESCRIPTION_ = findViewById(R.id._edit_description_) ;
    }

    private void saveNote(){
        String title = String.valueOf(_for_TITLE_.getText()) ;
        String description  = String.valueOf(_for_DESCRIPTION_.getText()) ;
        int ID = ModelNotes._list_NOTE_.size() ;
        ModelNotes _notes_ = new ModelNotes(ID , title , description) ;
        ModelNotes._list_NOTE_.add(_notes_) ;
        finish() ;
    }
}