package com.example.ezman.models;

public class Rider {
    public String id;
    public String name;
    public String email;
    public String contact_no;
    public String address;
    public String gender;
    public String image;

    public Rider(String id, String name, String email, String contact_no, String address, String gender, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        this.address = address;
        this.gender = gender;
        this.image = image;
    }

    public Rider() {
    }

}
