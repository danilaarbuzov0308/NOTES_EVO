package com.example.notes_evo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;

    private String date;

    private String time;

    public Note(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime(){
        return time;
    }
}
