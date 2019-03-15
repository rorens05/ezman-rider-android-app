package com.example.ezman.models;

public class Rider {
    public String id;
    public String first_name;
    public String last_name;
    public String mi;
    public String email;
    public String contact_no;
    public String address;
    public String gender;
    public String image;

    public Rider(String id, String first_name, String last_name, String mi, String email, String contact_no, String address, String gender, String image) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mi = mi;
        this.email = email;
        this.contact_no = contact_no;
        this.address = address;
        this.gender = gender;
        this.image = image;
    }

    public Rider() {
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mi='" + mi + '\'' +
                ", email='" + email + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
