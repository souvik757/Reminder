package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView _list_view_ ;
    NotesAdapter notesAdapter ;
    TextView _count_ ;
    TextView note ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;
        InitializeWidgets() ;
        setNoteAdapter();
        LoadFromDBtoMemory() ;
        setOnCLickListener() ;

    }
    private void InitializeWidgets() {
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
        //SaveDateCreated(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
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

    public void AlertBox(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish() ;
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        AlertBox(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
}