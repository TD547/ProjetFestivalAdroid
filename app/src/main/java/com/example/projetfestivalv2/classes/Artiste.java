package com.example.projetfestivalv2.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artiste implements Parcelable{
    public int id;
    public String nom;
    public String photo;
    public String heure_passage;
    public String date_passage;
    public String style;

    public Artiste(int id, String nom, String photo, String heure_passage, String date_passage, String style) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.heure_passage = heure_passage;
        this.date_passage = date_passage;
        this.style = style;
    }

    protected Artiste(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        photo = in.readString();
        heure_passage = in.readString();
        date_passage = in.readString();
        style = in.readString();
    }

    public static final Creator<Artiste> CREATOR = new Creator<Artiste>() {
        @Override
        public Artiste createFromParcel(Parcel in) {
            return new Artiste(in);
        }

        @Override
        public Artiste[] newArray(int size) {
            return new Artiste[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeure_passage() {
        return heure_passage;
    }

    public void setHeure_passage(String heure_passage) {
        this.heure_passage = heure_passage;
    }

    public String getDate_passage() {
        return date_passage;
    }

    public void setDate_passage(String date_passage) {
        this.date_passage = date_passage;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(photo);
        dest.writeString(heure_passage);
        dest.writeString(date_passage);
        dest.writeString(style);
    }
}
