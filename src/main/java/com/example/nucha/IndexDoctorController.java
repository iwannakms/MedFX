package com.example.nucha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexDoctorController {

    @FXML
    private TableView<Patient> tasks_table;

    @FXML
    private TableColumn<Patient, String> doctor_first_name_column;

    @FXML
    private TableColumn<Patient, String> doctor_last_name_column;

    @FXML
    private TableColumn<Patient, Date> start_task_column;

    @FXML
    private TableColumn<Patient, Date> end_task_column;

    @FXML
    private TableColumn<Patient, String> nurse_first_name_column;

    @FXML
    private TableColumn<Nurse, String> nurse_last_name_column;

    @FXML
    private TableColumn<Patient, String> task_name_column;

    @FXML
    private TableView<Procedure> proc_table;

    @FXML
    private TableColumn<Procedure, String> proc_name_column;

    @FXML
    private TableColumn<Procedure, Float> proc_price_column;

    @FXML
    private TableView<Patient> patients_table;

    @FXML
    private TableColumn<Patient, Date> patient_bdate_column;

    @FXML
    private TableColumn<Patient, Integer> patient_blood_column;

    @FXML
    private TableColumn<Patient, String> patient_first_name_column;

    @FXML
    private TableColumn<Patient, Float> patient_height_column;

    @FXML
    private TableColumn<Patient, String> patient_last_name_column;

    @FXML
    private TableColumn<Patient, String> patient_rfactor_column;

    @FXML
    private TableColumn<Patient, Float> patient_weight_column;

    @FXML
    private TableColumn<Nurse, Integer> nurse_info_age_column;

    @FXML
    private TableColumn<Nurse, String> nurse_info_first_name_column;

    @FXML
    private TableColumn<Nurse, String> nurse_info_last_name_column;

    @FXML
    private TableView<Nurse> nurse_info_table;

    @FXML
    private Tab nurses_tab;

    @FXML
    private Tab patients_tab;

    @FXML
    private Button exit_button;

    @FXML
    private Label first_name_label;

    @FXML
    private Label login_label;

    @FXML
    private TabPane menu_tab;

    @FXML
    private Tab procedures_tab;

    @FXML
    private Tab tasks_tab;


    DataBaseHandler dbHandler = new DataBaseHandler();

    ObservableList<Procedure> procedures_list = FXCollections.observableArrayList();
    ObservableList<Patient> tasks_list = FXCollections.observableArrayList();
    ObservableList<Patient> patients_list = FXCollections.observableArrayList();
    ObservableList<Nurse> nurses_list = FXCollections.observableArrayList();


    @FXML
    public void initData(Doctor doctor) throws SQLException, ClassNotFoundException {

        exit_button.setOnAction(actionEvent -> {
            load_window("hello-view.fxml");
            exit_button.getScene().getWindow().hide();
        });

        ResultSet procedures_result = dbHandler.getProcedures();
        show_table_procedures(procedures_result);

        ResultSet tasks_result = dbHandler.getTasks();
        show_table_tasks(tasks_result);

        ResultSet patients_result = dbHandler.getPatients();
        show_table_patients(patients_result);

        ResultSet nurses_result = dbHandler.getNurses();
        show_table_nurses(nurses_result);

    }


    private void show_table_procedures(ResultSet procedures) throws SQLException {

        procedures_list.clear();

        while (procedures.next()) {

            String name = procedures.getString(1);
            float price = procedures.getFloat(2);

            procedures_list.add(new Procedure(name, price));
        }

        proc_name_column.setCellValueFactory(new PropertyValueFactory<Procedure, String>("name"));
        proc_price_column.setCellValueFactory(new PropertyValueFactory<Procedure, Float>("price"));

        proc_table.setItems(procedures_list);
    }

    private void show_table_tasks(ResultSet tasks) throws SQLException {

        tasks_list.clear();

        while (tasks.next()) {

            Task task = new Task();
            task.setDoctor_first_name(tasks.getString(1));
            task.setDoctor_last_name(tasks.getString(2));
            task.setTask_name(tasks.getString(3));
            task.setTask_start_date(tasks.getDate(4));
            task.setTask_end_date(tasks.getDate(5));
            task.setNurse_first_name(tasks.getString(6));
            task.setNurse_last_name(tasks.getString(7));

            tasks_list.add(task);
        }

        doctor_first_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("doctor_first_name"));
        doctor_last_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("doctor_last_name"));
        task_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("task_name"));
        start_task_column.setCellValueFactory(new PropertyValueFactory<Patient, Date>("task_start_date"));
        end_task_column.setCellValueFactory(new PropertyValueFactory<Patient, Date>("task_end_date"));
        nurse_first_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("nurse_first_name"));
        nurse_last_name_column.setCellValueFactory(new PropertyValueFactory<Nurse, String>("nurse_last_name"));
        tasks_table.setItems(tasks_list);
    }

    private void show_table_patients(ResultSet patients) throws SQLException {

        patients_list.clear();

        while (patients.next()) {

            Patient patient = new Patient();
            patient.setFirst_name(patients.getString(1));
            patient.setLast_name(patients.getString(2));
            patient.setDate_of_birth(patients.getDate(3));
            patient.setWeight(patients.getFloat(4));
            patient.setHeight(patients.getFloat(5));
            patient.setBlood_type(patients.getInt(6));
            patient.setRhesus_factor(patients.getString(7));

            patients_list.add(patient);
        }
        patient_first_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("first_name"));
        patient_last_name_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("last_name"));
        patient_bdate_column.setCellValueFactory(new PropertyValueFactory<Patient, Date>("date_of_birth"));
        patient_weight_column.setCellValueFactory(new PropertyValueFactory<Patient, Float>("weight"));
        patient_height_column.setCellValueFactory(new PropertyValueFactory<Patient, Float>("height"));
        patient_blood_column.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("blood_type"));
        patient_rfactor_column.setCellValueFactory(new PropertyValueFactory<Patient, String>("rhesus_factor"));
        patients_table.setItems(patients_list);
    }

    public void show_table_nurses(ResultSet nurses) throws SQLException {

        nurses_list.clear();

        while (nurses.next()) {

            String first_name = nurses.getString(1);
            String last_name = nurses.getString(2);
            int age = nurses.getInt(3);
            float salary = nurses.getFloat(4);

            Nurse nurse = new Nurse();

            nurse.setFirst_name(first_name);
            nurse.setLast_name(last_name);
            nurse.setAge(age);
            nurse.setSalary(salary);
            nurses_list.add(nurse);
        }

        nurse_info_first_name_column.setCellValueFactory(new PropertyValueFactory<Nurse, String>("first_name"));
        nurse_info_last_name_column.setCellValueFactory(new PropertyValueFactory<Nurse, String>("last_name"));
        nurse_info_age_column.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("age"));

        nurse_info_table.setItems(nurses_list);
    }

    private void load_window(String url) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexDoctorController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


}

