package com.example.yunus.tipterimleri.Model;

/**
 * Created by yunus on 22.04.2016.
 */
public class Kelime {
    private String adı;
    private String ceviri;

    public String getAdı() {
        return adı;
    }

    public void setAdı(String adı) {
        this.adı = adı;
    }

    public String getCeviri() {
        return ceviri;
    }

    public void setCeviri(String ceviri) {
        this.ceviri = ceviri;
    }

    public Kelime(String ceviri, String adı) {

        this.ceviri = ceviri;
        this.adı = adı;
    }
}
