package com.example.nucha;

import java.sql.Date;

public class Treatment {
    private String patient_first_name;
    private String patient_last_name;
    private String procedure_name;
    private Date start_treatment;
    private Date end_treatment;
    private Float price;

    public Treatment(){}

    public Treatment(String patient_first_name, String patient_last_name, String procedure_name, Date start_treatment, Date end_treatment, Float price) {
        this.patient_first_name = patient_first_name;
        this.patient_last_name = patient_last_name;
        this.procedure_name = procedure_name;
        this.start_treatment = start_treatment;
        this.end_treatment = end_treatment;
        this.price = price;
    }

    public String getPatient_first_name() {
        return patient_first_name;
    }

    public void setPatient_first_name(String patient_first_name) {
        this.patient_first_name = patient_first_name;
    }

    public String getPatient_last_name() {
        return patient_last_name;
    }

    public void setPatient_last_name(String patient_last_name) {
        this.patient_last_name = patient_last_name;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public Date getStart_treatment() {
        return start_treatment;
    }

    public void setStart_treatment(Date start_treatment) {
        this.start_treatment = start_treatment;
    }

    public Date getEnd_treatment() {
        return end_treatment;
    }

    public void setEnd_treatment(Date end_treatment) {
        this.end_treatment = end_treatment;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
