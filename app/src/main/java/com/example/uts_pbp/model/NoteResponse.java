package com.example.uts_pbp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoteResponse {
    private String message;

    @SerializedName("note")
    private List<Note> noteList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
