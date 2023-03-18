package com.example.nucha;


import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }


    public MainDoc login_main_doc(MainDoc main_doc) {

        String sql = "select * from main_doctors where user_name=? and password=?";

        MainDoc mainDoc = null;
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, main_doc.getUser_name());
            prSt.setString(2, main_doc.getPassword());

            resSet = prSt.executeQuery();
            while (resSet.next()) {
                String first_name = resSet.getString(2);
                String last_name = resSet.getString(3);
                int age = resSet.getInt(4);
                float salary = resSet.getFloat(5);
                String user_name = resSet.getString(6);
                String password = resSet.getString(7);

                mainDoc = new MainDoc(first_name, last_name, age, salary, user_name, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return mainDoc;
    }

    public Doctor login_doctor(Doctor doctor) {

        String sql = "select * from doctors where user_name=? and password=?";

        Doctor doc = null;
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, doctor.getUser_name());
            prSt.setString(2, doctor.getPassword());

            resSet = prSt.executeQuery();
            while (resSet.next()) {
                String first_name = resSet.getString(2);
                String last_name = resSet.getString(3);
                int age = resSet.getInt(4);
                float salary = resSet.getFloat(5);
                String specialization = resSet.getString(6);
                String user_name = resSet.getString(7);
                String password = resSet.getString(8);

                doc = new Doctor(first_name, last_name, age, salary, specialization, user_name, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public Nurse login_nurse(Nurse nurse) {

        String sql = "select * from nurses where user_name=? and password=?";

        Nurse nurse_res = null;
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, nurse.getUser_name());
            prSt.setString(2, nurse.getPassword());

            resSet = prSt.executeQuery();
            while (resSet.next()) {
                String first_name = resSet.getString(2);
                String last_name = resSet.getString(3);
                int age = resSet.getInt(4);
                float salary = resSet.getFloat(5);
                String user_name = resSet.getString(6);
                String password = resSet.getString(7);

                nurse_res = new Nurse(first_name, last_name, age, salary, user_name, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nurse_res;
    }

    public Patient login_patient(Patient patient) {

        String sql = "select * from patients where user_name=? and password=?";

        Patient patient_res = null;
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, patient.getUser_name());
            prSt.setString(2, patient.getPassword());

            resSet = prSt.executeQuery();
            while (resSet.next()) {
                String first_name = resSet.getString(2);
                String last_name = resSet.getString(3);
                Date date_of_birth = resSet.getDate(4);
                float weight = resSet.getFloat(5);
                float height = resSet.getFloat(6);
                int blood_type = resSet.getInt(7);
                String rhesus_factor = resSet.getString(8);
                String user_name = resSet.getString(9);
                String password = resSet.getString(10);

                patient_res = new Patient(first_name, last_name, date_of_birth, weight, height, blood_type, rhesus_factor, user_name, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return patient_res;
    }

    public ResultSet getDoctors() {

        ResultSet resSet = null;
        String select = "select first_name, last_name, age, salary, specialization from doctors";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getNurses() {

        ResultSet resSet = null;
        String select = "select first_name, last_name, age, salary from nurses";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getProcedures() {

        ResultSet resSet = null;
        String select = "select name, price from procedures";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getTasks() {
        ResultSet resSet = null;
        String select = "SELECT doctors.first_name, doctors.last_name, tasks.name, tasks.date_of_start, tasks.date_of_end, nurses.first_name, nurses.last_name FROM doctors JOIN tasks ON doctors.id = tasks.doctor_id JOIN nurses ON tasks.nurse_id WHERE tasks.date_of_end < '2022-05-21' and tasks.date_of_end > '2022-05-01'; ";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getPatients(){
        ResultSet resSet = null;
        String select = "SELECT first_name, last_name, date_of_birth, weight, height, blood_type, rhesus_factor FROM patients;";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getTreatments() {

        ResultSet resSet = null;
        String select = "SELECT patients.first_name, patients.last_name, procedures.name, treatments.start_of_treatment, treatments.end_of_treatment, procedures.price FROM patients JOIN treatments ON patients.id = treatments.patient_id JOIN procedures ON procedures.id = treatments.procedure_id WHERE treatments.end_of_treatment < '2022-05-23' and treatments.end_of_treatment > '2022-05-01';";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

}