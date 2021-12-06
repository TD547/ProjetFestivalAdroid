package com.example.projetfestivalv2.classes;

public class Festival {
    public int id;
    public String date_deb;
    public String date_fin;

    public Festival(int id, String date_deb, String date_fin) {
        this.id = id;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
}
