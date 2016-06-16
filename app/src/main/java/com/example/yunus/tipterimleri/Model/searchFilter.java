package com.example.yunus.tipterimleri.Model;

/**
 * Created by yunus on 6/16/2016.
 */
public class searchFilter {
    private int id;
    private String Kelime_adi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKelime_adi() {
        return Kelime_adi;
    }

    public void setKelime_adi(String kelime_adi) {
        Kelime_adi = kelime_adi;
    }

    public searchFilter(String kelime_adi) {

        this.id = id;
        Kelime_adi = kelime_adi;
    }
}
