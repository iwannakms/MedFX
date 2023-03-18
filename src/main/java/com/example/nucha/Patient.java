package com.example.nucha;

import java.sql.Date;

public class Patient {
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private float weight;
    private float height;
    private int blood_type;
    private String rhesus_factor;
    private String user_name;
    private String password;

    public Patient(){
    }

    public Patient(String first_name, String last_name, Date date_of_birth, float weight, float height, int blood_type, String rhesus_factor, String user_name, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.weight = weight;
        this.height = height;
        this.blood_type = blood_type;
        this.rhesus_factor = rhesus_factor;
        this.user_name = user_name;
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(int blood_type) {
        this.blood_type = blood_type;
    }

    public String getRhesus_factor() {
        return rhesus_factor;
    }

    public void setRhesus_factor(String rhesus_factor) {
        this.rhesus_factor = rhesus_factor;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
