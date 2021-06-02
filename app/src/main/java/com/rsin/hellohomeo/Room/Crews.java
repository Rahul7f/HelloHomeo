package com.rsin.hellohomeo.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Crews")
public class Crews {

    @ColumnInfo(name = "id") @PrimaryKey @NonNull
    String id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "agency")
    String agency;

    @ColumnInfo(name = "wikipedia")
    String wikipedia;

    @ColumnInfo(name = "status")
    String status;
    @ColumnInfo(name = "image")
    String image;

    public Crews(String id,String name, String agency, String wikipedia, String status, String image) {
        this.name = name;
        this.agency = agency;
        this.wikipedia = wikipedia;
        this.status = status;
        this.image = image;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
}

