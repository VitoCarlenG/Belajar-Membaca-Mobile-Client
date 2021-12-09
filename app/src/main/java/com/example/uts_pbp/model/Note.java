package com.example.uts_pbp.model;

public class Note {
    private long id;
    private String title;
    private String pesan;

    public Note(String title, String pesan) {
        this.title = title;
        this.pesan = pesan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
