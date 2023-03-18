package com.example.nucha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexMainDocController {

    @FXML
    private TableView<Doctor> doctors_table;

    @FXML
    private TableColumn<Doctor, String> first_name_column;

    @FXML
    private TableColumn<Doctor, String> last_name_column;

    @FXML
    private TableColumn<Doctor, Integer> age_column;

    @FXML
    private TableColumn<Doctor, Float> salary_column;

    @FXML
    private TableColumn<Doctor, String> spec_column;

    @FXML
    private TableView<Nurse> nurses_table;

    @FXML
    private TableColumn<Nurse, String> first_name_nurse_column;

    @FXML
    private TableColumn<Nurse, String> last_name_nurse_column;

    @FXML
    private TableColumn<Nurse, Integer> age_nurse_column;

    @FXML
    private TableColumn<Nurse, Float> salary_nurse_column;

    @FXML
    private Tab doctors_tab;

    @FXML
    private Button exit_button;

    @FXML
    private Label first_name_label;

    @FXML
    private Label login_label;

    @FXML
    private TabPane menu_tab;

    @FXML
    private Tab nurses_tab;

    DataBaseHandler dbHandler = new DataBaseHandler();

    ObservableList<Doctor> doctors_list = FXCollections.observableArrayList();
    ObservableList<Nurse> nurses_list = FXCollections.observableArrayList();

    @FXML
    public void initData(MainDoc mainDoc) throws SQLException, ClassNotFoundException {

        exit_button.setOnAction(actionEvent -> {
            load_window("hello-view.fxml");
            exit_button.getScene().getWindow().hide();
        });

        ResultSet doctors_result = dbHandler.getDoctors();
        show_table_doctors(doctors_result);

        ResultSet nurses_result = dbHandler.getNurses();
        show_table_nurses(nurses_result);

    }

    private void show_table_doctors(ResultSet doctors) throws SQLException {

        doctors_list.clear();


        while (doctors.next()) {

            String first_name = doctors.getString(1);
            String last_name = doctors.getString(2);
            int age = doctors.getInt(3);
            float salary = doctors.getFloat(4);
            String specialization = doctors.getString(5);

            Doctor doctor = new Doctor();

            doctor.setFirst_name(first_name);
            doctor.setLast_name(last_name);
            doctor.setAge(age);
            doctor.setSalary(salary);
            doctor.setSpecialization(specialization);
            doctors_list.add(doctor);

        }

        first_name_column.setCellValueFactory(new PropertyValueFactory<Doctor, String>("first_name"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<Doctor, String>("last_name"));
        age_column.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("age"));
        salary_column.setCellValueFactory(new PropertyValueFactory<Doctor, Float>("salary"));
        spec_column.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));

        doctors_table.setItems(doctors_list);
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

        first_name_nurse_column.setCellValueFactory(new PropertyValueFactory<Nurse, String>("first_name"));
        last_name_nurse_column.setCellValueFactory(new PropertyValueFactory<Nurse, String>("last_name"));
        age_nurse_column.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("age"));
        salary_nurse_column.setCellValueFactory(new PropertyValueFactory<Nurse, Float>("salary"));

        nurses_table.setItems(nurses_list);
    }

    private void load_window(String url) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexMainDocController.class.getResource(url));

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



