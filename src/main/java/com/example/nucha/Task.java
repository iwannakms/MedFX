package com.example.nucha;

import java.sql.Date;

public class Task extends Patient {
    private String doctor_first_name;
    private String doctor_last_name;
    private String task_name;
    private Date task_start_date;
    private Date task_end_date;
    private String nurse_first_name;
    private String nurse_last_name;

    public Task(String doctor_first_name, String doctor_last_name, String task_name, Date task_start_date, Date task_end_date, String nurse_first_name, String nurse_last_name) {
        this.doctor_first_name = doctor_first_name;
        this.doctor_last_name = doctor_last_name;
        this.task_name = task_name;
        this.task_start_date = task_start_date;
        this.task_end_date = task_end_date;
        this.nurse_first_name = nurse_first_name;
        this.nurse_last_name = nurse_last_name;
    }

    public Task(){

    }

    public String getDoctor_first_name() {
        return doctor_first_name;
    }

    public void setDoctor_first_name(String doctor_first_name) {
        this.doctor_first_name = doctor_first_name;
    }

    public String getDoctor_last_name() {
        return doctor_last_name;
    }

    public void setDoctor_last_name(String doctor_last_name) {
        this.doctor_last_name = doctor_last_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Date getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(Date task_start_date) {
        this.task_start_date = task_start_date;
    }

    public Date getTask_end_date() {
        return task_end_date;
    }

    public void setTask_end_date(Date task_end_date) {
        this.task_end_date = task_end_date;
    }

    public String getNurse_first_name() {
        return nurse_first_name;
    }

    public void setNurse_first_name(String nurse_first_name) {
        this.nurse_first_name = nurse_first_name;
    }

    public String getNurse_last_name() {
        return nurse_last_name;
    }

    public void setNurse_last_name(String nurse_last_name) {
        this.nurse_last_name = nurse_last_name;
    }
}
