package com.example.habitapp;

public class Tracker {
    int id;
    int dname;
    String name;
    String Date;
    int com;
    Tracker(){

    }
    Tracker(int idname,String name,String Date,int Com){
        this.dname=idname;
        this.name=name;
        this.Date=Date;
        this.com=Com;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDname() {
        return dname;
    }

    public void setDname(int dname) {
        this.dname = dname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCom() {
        return com;
    }

    public void setCom(int com) {
        this.com = com;
    }



}
