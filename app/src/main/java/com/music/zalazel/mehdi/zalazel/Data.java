package com.music.zalazel.mehdi.zalazel;

public class Data {
    private Double mag;
    private String  placeKm;
    private String placeV;
    private String time;
    private String date;

    private String type;
    private String title;
    private int  tsunami;
    private String detail;


    Data(Double mag, String placeKm,
                String placeV,  String time,
                String date ,String type ,
                String title ,int tsunami,
                String detail){
        this.mag = mag;
        this.placeKm = placeKm;
        this.placeV = placeV;
        this.time = time;
        this.date = date;
        this.type = type;
        this.title = title;
        this.tsunami = tsunami;
        this.detail = detail;

    }

    public String getDetail() {
        return detail;
    }

    public Double getMag() {
        return mag;
    }

    public String getPlaceV() {
        return placeV;
    }

    public String getPlaceKm() {
        return placeKm;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getTsunami() {
        return tsunami;
    }
}

