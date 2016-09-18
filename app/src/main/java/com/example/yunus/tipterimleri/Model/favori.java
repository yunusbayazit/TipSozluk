package com.example.yunus.tipterimleri.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by yunus on 6/26/2016.
 */
@Table(name = "favori")
public class favori extends Model {
    public favori() {
    }

    @Column(name = "Kelime_adi")
    private String Kelime_adi;
    @Column(name = "Ceviri_adi")

    private String Ceviri_adi;
    @Column(name = "Kelime_id")

    private int Kelime_id;
    @Column(name = "IsFavorite")

    private int IsFavorite;

    public favori(String kelime_adi, String ceviri_adi, int kelime_id, int isFavorite) {
        Kelime_adi = kelime_adi;
        Ceviri_adi = ceviri_adi;
        Kelime_id = kelime_id;
        IsFavorite = isFavorite;
    }

    public String getKelime_adi() {
        return Kelime_adi;
    }

    public void setKelime_adi(String kelime_adi) {
        Kelime_adi = kelime_adi;
    }

    public String getCeviri_adi() {
        return Ceviri_adi;
    }

    public void setCeviri_adi(String ceviri_adi) {
        Ceviri_adi = ceviri_adi;
    }

    public int getKelime_id() {
        return Kelime_id;
    }

    public void setKelime_id(int kelime_id) {
        Kelime_id = kelime_id;
    }

    public int getIsFavorite() {
        return IsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        IsFavorite = isFavorite;
    }
}
