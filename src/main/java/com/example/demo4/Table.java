package com.example.demo4;

public class Table {
    private String sr_no;
    private String subject;
    private String date;
    private String time;
    private String location;
    private String description;

    public Table(String arr[]) {
        sr_no = arr[0];
        subject = arr[1];
        description = arr[2];
        location = arr[3];
        date = arr[4];
        time = arr[5];
    }

    public String getSr_no() {return sr_no;}

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
