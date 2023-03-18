package com.example.nucha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private RadioButton doctor_radio;

    @FXML
    private Button login_button;

    @FXML
    private TextField login_field;

    @FXML
    private RadioButton main_doc_radio;

    @FXML
    private RadioButton nurse_radio;

    @FXML
    private TextField password_field;

    @FXML
    private RadioButton patient_radio;

    @FXML
    private ToggleGroup type;


    @FXML
    void initialize() {

        // ------- Настройка кнопки входа в аккаунт ------- //
        login_button.setOnAction(actionEvent -> {
            String login_text = login_field.getText().trim();
            String password_text = password_field.getText().trim();

            if ((!login_text.equals("") && !password_text.equals("")) &&
                    (main_doc_radio.isSelected() || doctor_radio.isSelected() || patient_radio.isSelected() || nurse_radio.isSelected())) {

                login_button.getScene().getWindow().hide();

                if (main_doc_radio.isSelected()) {
                    login_main_doc(login_text, password_text);
                } else if (doctor_radio.isSelected()) {
                    login_doctor(login_text, password_text);
                } else if (patient_radio.isSelected()) {
                    login_patient(login_text, password_text);
                } else if (nurse_radio.isSelected()) {
                    login_nurse(login_text, password_text);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, заполните поля.");
                alert.showAndWait();
            }
        });

    }


    private void login_main_doc(String login_text, String password_text) {

        DataBaseHandler dbHandler = new DataBaseHandler();
        MainDoc mainDoc = new MainDoc();
        mainDoc.setUser_name(login_text);
        mainDoc.setPassword(password_text);
        MainDoc mainDocResult = dbHandler.login_main_doc(mainDoc);

        System.out.println("login uaaa:" + mainDocResult.getFirst_name() + "\n");

        if (mainDocResult.getUser_name() != null) {
            load_mainDoc_window("index-mainDoc-view.fxml", mainDocResult);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            load_window("hello-view.fxml");
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неверный логин или пароль, пожалуйста попробуйте еще.");
            alert.showAndWait();
        }

    }

    private void login_doctor(String login_text, String password_text) {

        DataBaseHandler dbHandler = new DataBaseHandler();
        Doctor doc = new Doctor();
        doc.setUser_name(login_text);
        doc.setPassword(password_text);
        Doctor docResult = dbHandler.login_doctor(doc);

        System.out.println("login uaaa:" + docResult.getFirst_name() + "\n");

        if (docResult.getUser_name() != null) {
            load_doctor_window("index-doctor-view.fxml", docResult);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            load_window("hello-view.fxml");
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неверный логин или пароль, пожалуйста попробуйте еще.");
            alert.showAndWait();
        }
    }

    private void login_nurse(String login_text, String password_text) {

        DataBaseHandler dbHandler = new DataBaseHandler();
        Nurse nurse = new Nurse();
        nurse.setUser_name(login_text);
        nurse.setPassword(password_text);
        Nurse nurse_result = dbHandler.login_nurse(nurse);

        System.out.println("login uaaa:" + nurse_result.getFirst_name() + "\n");

        if (nurse_result.getUser_name() != null) {
            load_nurse_window("index-nurse-view.fxml", nurse_result);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            load_window("hello-view.fxml");
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неверный логин или пароль, пожалуйста попробуйте еще.");
            alert.showAndWait();
        }
    }

    private void login_patient(String login_text, String password_text) {

        DataBaseHandler dbHandler = new DataBaseHandler();
        Patient patient  = new Patient();
        patient.setUser_name(login_text);
        patient.setPassword(password_text);
        Patient patient_result = dbHandler.login_patient(patient);

        System.out.println("login uaaa:" + patient_result.getFirst_name() + "\n");

        if (patient_result.getUser_name() != null) {
            load_patient_window("index-patient-view.fxml", patient_result);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            load_window("hello-view.fxml");
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неверный логин или пароль, пожалуйста попробуйте еще.");
            alert.showAndWait();
        }
    }

    private void load_mainDoc_window(String url, MainDoc mainDoc) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IndexMainDocController controller = loader.getController();
        try {
            controller.initData(mainDoc);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void load_patient_window(String url, Patient patient) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IndexPatientController controller = loader.getController();
        try {
            controller.initData(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void load_doctor_window(String url, Doctor doctor) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IndexDoctorController controller = loader.getController();
        try {
            controller.initData(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void load_nurse_window(String url, Nurse nurse) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IndexNurseController controller = loader.getController();
        try {
            controller.initData(nurse);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    private void load_window(String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

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
