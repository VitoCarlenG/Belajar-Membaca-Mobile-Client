package com.example.uas_pbp.content;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class DataAlphabet {
    public String hurufBesar;
    public String hurufkecil;
    public String kata;
    public String imgURL;

    public DataAlphabet(String hurufBesar, String hurufkecil, String kata, String imgURL) {
        this.hurufBesar = hurufBesar;
        this.hurufkecil = hurufkecil;
        this.kata = kata;
        this.imgURL = imgURL;
    }

    public String getHurufBesar() {
        return hurufBesar;
    }

    public void setHurufBesar(String hurufBesar) {
        this.hurufBesar = hurufBesar;
    }

    public String getHurufkecil() {
        return hurufkecil;
    }

    public void setHurufkecil(String hurufkecil) {
        this.hurufkecil = hurufkecil;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    //glide
    @BindingAdapter({"profileImage"})
    public static void loadImgURL(ImageView view, String imgURL)
    {
        Glide.with(view.getContext()).load(imgURL).into(view);
    }
}
