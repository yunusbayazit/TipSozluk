package com.example.yunus.tipterimleri.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yunus on 6/18/2016.
 */
public class Ceviri implements Parcelable {

    /**
     * id : 6440
     * ceviri1 :  kasık ile ilgili
     * aciklama : ,
     * kelime_id : 0
     */
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ceviri1")
    @Expose
    private String ceviri1;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("kelime_id")
    @Expose
    private int kelime_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCeviri1() {
        return ceviri1;
    }

    public void setCeviri1(String ceviri1) {
        this.ceviri1 = ceviri1;
    }

    public Ceviri(Parcel in) {
        id = in.readInt();
        ceviri1 = in.readString();
        aciklama = in.readString();
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getKelime_id() {
        return kelime_id;
    }

    public void setKelime_id(int kelime_id) {
        this.kelime_id = kelime_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ceviri1);
        dest.writeString(aciklama);
    }
    public static final Creator<Ceviri> CREATOR = new Creator<Ceviri>() {
        @Override
        public Ceviri createFromParcel(Parcel in) {
            return new Ceviri(in);
        }

        @Override
        public Ceviri[] newArray(int size) {
            return new Ceviri[size];
        }
    };
}
