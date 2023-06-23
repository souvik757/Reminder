package com.example.reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ModelNotes {
    public static ArrayList<ModelNotes> _list_NOTE_ = new ArrayList<>() ;
    public static String NOTE_EDIT_EXTRA = "noteEdit" ;
    private int id ;
    private String tile ;
    private String description ;
    private Date deleted ;

    public ModelNotes(int id, String tile, String description) {
        this.id = id;
        this.tile = tile;
        this.description = description;
        deleted = null ;
    }

    public ModelNotes(int id, String tile, String description, Date deleted) {
        this.id = id;
        this.tile = tile;
        this.description = description;
        this.deleted = deleted;
    }

    public static ModelNotes getIDofNote(int ID){
        for(ModelNotes notes : _list_NOTE_){
            if(notes.getId() == ID)
                return notes ;
        }
        return null ;
    }

    public static ArrayList<ModelNotes> nonDeletedNotes(){
        ArrayList<ModelNotes> nonDeleted = new ArrayList<>() ;
        for(ModelNotes notes : _list_NOTE_){
            if(notes.getDeleted() == null){
                nonDeleted.add(notes) ;
            }
        }

        return nonDeleted ;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }
    public String ShowDate(){
        return new SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault()).format(getDeleted()) ;
    }

    public void setDeleted(Date _deleted_) {
        this.deleted = _deleted_;
    }
}
