package com.example.reminder;

import java.util.Date;

public class ModelNotes {
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

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
