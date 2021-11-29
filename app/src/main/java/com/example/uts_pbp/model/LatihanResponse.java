package com.example.uts_pbp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatihanResponse {
    private String message;

    @SerializedName("latihan")
    private List<Latihan> latihanList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Latihan> getLatihanList() {
        return latihanList;
    }

    public void setLatihanList(List<Latihan> latihanList) {
        this.latihanList = latihanList;
    }
}
