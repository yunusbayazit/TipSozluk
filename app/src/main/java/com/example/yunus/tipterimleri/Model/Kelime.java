package com.example.yunus.tipterimleri.Model;

import android.os.Parcel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.Expose;

/**
 * Created by yunus on 22.04.2016.
 */
@Table(name = "Kelime")
public class Kelime extends Model implements SearchSuggestion {
    public Kelime() {
    }

    @Expose
    @Column(name = "kelime_adi")
    private String kelime_adi;
    @Expose
    @Column(name = "ceviri")
    private String ceviri;

    public Kelime(String kelime_adi, int id) {
        this.kelime_adi = kelime_adi;
        this.id = id;
    }

    @Expose
    @Column(name = "kelime_id")
    private int id;

    private boolean mIsHistory = false;

    public int getIdi() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKelime_adi() {
        return kelime_adi;
    }

    public void setKelime_adi(String kelime_adi) {
        this.kelime_adi = kelime_adi;
    }





    public String getCeviri() {
        return ceviri;
    }

    public void setCeviri(String ceviri) {
        this.ceviri = ceviri;
    }

    public Kelime(String ceviri, String adı) {

        this.ceviri = ceviri;
        this.kelime_adi = adı;
    }
    public static final Creator<Kelime> CREATOR = new Creator<Kelime>() {
        @Override
        public Kelime createFromParcel(Parcel in) {
            return new Kelime(in);
        }

        @Override
        public Kelime[] newArray(int size) {
            return new Kelime[size];
        }
    };
    @Override
    public String getBody() {
        return kelime_adi;
    }

    public int getid() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;

    }
    public Kelime(String suggestion) {
        this.kelime_adi = suggestion.toLowerCase();
    }

    public boolean ismIsHistory() {
        return mIsHistory;
    }
    public Kelime(Parcel source) {
        this.kelime_adi = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }
    public void setmIsHistory(boolean mIsHistory) {
        this.mIsHistory = mIsHistory;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kelime_adi);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
