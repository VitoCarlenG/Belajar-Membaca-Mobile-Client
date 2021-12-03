package com.example.uts_pbp.model;

import com.google.gson.annotations.SerializedName;

public class Latihan {
    private Long id;
    private String alfabet;
    private String nama;
    private String urlgambar;

    public Latihan(String alfabet, String nama, String urlgambar) {
        this.alfabet = alfabet;
        this.nama = nama;
        this.urlgambar = urlgambar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlfabet() {
        return alfabet;
    }

    public void setAlfabet(String alfabet) {
        this.alfabet = alfabet;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }
}
