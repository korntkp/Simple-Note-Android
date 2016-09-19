package com.example.korshreddern.a04simplenote.model;

/**
 * Created by Korshreddern on 22-Apr-16.
 */
public class Note {
    public int id;
    public String date;
    public String message;

    public Note() {

    }

    public Note(int id, String date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
