package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        InitializeWidgets() ;
        OnClickListener() ;
        checkForEditNote() ;
    }

    private void InitializeWidgets() {
        _for_TITLE_       = findViewById(R.id._edit_title_) ;
        _for_DESCRIPTION_ = findViewById(R.id._edit_description_) ;
        _del_btn_ = findViewById(R.id._DEL_) ;
        _save_ = findViewById(R.id._save_new_entry_) ;
    }
    private void OnClickListener(){
        _save_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(v);

            }
        });
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

    public void saveNote(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceofDB(this) ;

        String title = String.valueOf(_for_TITLE_.getText()) ;
        String description  = String.valueOf(_for_DESCRIPTION_.getText()) ;
        if(title.isEmpty() || description.isEmpty()) {
            ShowCustomToast("void entry");
        }
        if(modelNotes == null){
            int ID = ModelNotes._list_NOTE_.size() ;
            ModelNotes _notes_ = new ModelNotes(ID , title , description) ;
            ModelNotes._list_NOTE_.add(_notes_) ;
            sqLiteManager.AddNoteToDB(_notes_) ;
            ShowCustomToast("note created successfully");
        }
        else
        {
            modelNotes.setTile(title) ;
            modelNotes.setDescription(description) ;
            sqLiteManager.updateNotesDB(modelNotes);
            ShowCustomToast("note updated successfully");
        }
        finish();
    }

    public void deleteNote(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceofDB(this) ;
        ModelNotes._list_NOTE_.remove(modelNotes) ;
        sqLiteManager.DeleteNote(modelNotes) ;
        ShowCustomToast("note deleted successfully");
        finish();
    }
    private void ShowCustomToast(String message){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id._custom_toast_container_));

        ImageView alert = layout.findViewById(R.id._icon_) ;
        alert.setImageResource(R.drawable.baseline_notifications_none_24);
        TextView text = layout.findViewById(R.id._message_);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}