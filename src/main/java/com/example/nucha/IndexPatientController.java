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

public class IndexPatientController {

    @FXML
    private TableView<Treatment> treatments_table;

    @FXML
    private TableColumn<Treatment, String> first_name_column;

    @FXML
    private TableColumn<Treatment, String> last_name_column;

    @FXML
    private TableColumn<Treatment, String> treatment_name_column;

    @FXML
    private TableColumn<Treatment, Date> start_column;

    @FXML
    private TableColumn<Treatment, Date> end_column;

    @FXML
    private TableColumn<Treatment, Float> price_column;

    @FXML
    private Button exit_button;

    @FXML
    private Label first_name_label;

    @FXML
    private Label login_label;

    @FXML
    private TabPane menu_tab;

    @FXML
    private Tab treatments_tab;

    DataBaseHandler dbHandler = new DataBaseHandler();

    ObservableList<Treatment> treatments_list = FXCollections.observableArrayList();

    public void initData(Patient patient) throws SQLException, ClassNotFoundException {

        exit_button.setOnAction(actionEvent -> {
            load_window("hello-view.fxml");
            exit_button.getScene().getWindow().hide();
        });
        ResultSet treatments_result = dbHandler.getTreatments();
        show_table_treatments(treatments_result);

    }

    private void show_table_treatments(ResultSet treatments) throws SQLException {

        treatments_list.clear();

        while (treatments.next()) {
            Treatment treatment = new Treatment();
            treatment.setPatient_first_name(treatments.getString(1));
            treatment.setPatient_last_name(treatments.getString(2));
            treatment.setProcedure_name(treatments.getString(3));
            treatment.setStart_treatment(treatments.getDate(4));
            treatment.setEnd_treatment(treatments.getDate(5));
            treatment.setPrice(treatments.getFloat(6));

            treatments_list.add(treatment);
        }

        first_name_column.setCellValueFactory(new PropertyValueFactory<Treatment, String>("patient_first_name"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<Treatment, String>("patient_last_name"));
        treatment_name_column.setCellValueFactory(new PropertyValueFactory<Treatment, String>("procedure_name"));
        start_column.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("start_treatment"));
        end_column.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("end_treatment"));
        price_column.setCellValueFactory(new PropertyValueFactory<Treatment, Float>("price"));

        treatments_table.setItems(treatments_list);

    }

    private void load_window(String url) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexPatientController.class.getResource(url));

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

