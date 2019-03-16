package com.example.ezman.models;

public class Transaction {
    public String id;
    public String customer_id;
    public String sub_total;
    public String shipping_fee;
    public String status;
    public String notice;
    public String delivery_location;
    public String delivered_at;
    public String items;
    public String rider_id;
    public String customer;
    public String image;
    public String short_date;
    public String date;
    public String total;
    public String actual_location;
    public String time;
    public String location_x;
    public String location_y;
    public String email;
    public String contact_no;
    public String address;


    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", sub_total='" + sub_total + '\'' +
                ", shipping_fee='" + shipping_fee + '\'' +
                ", status='" + status + '\'' +
                ", notice='" + notice + '\'' +
                ", delivery_location='" + delivery_location + '\'' +
                ", delivered_at='" + delivered_at + '\'' +
                ", items='" + items + '\'' +
                ", rider_id='" + rider_id + '\'' +
                ", customer='" + customer + '\'' +
                ", image='" + image + '\'' +
                ", short_date='" + short_date + '\'' +
                ", date='" + date + '\'' +
                ", total='" + total + '\'' +
                ", actual_location='" + actual_location + '\'' +
                ", time='" + time + '\'' +
                ", location_x='" + location_x + '\'' +
                ", location_y='" + location_y + '\'' +
                ", email='" + email + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
