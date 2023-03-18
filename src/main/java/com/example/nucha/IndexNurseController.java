package com.example.nucha;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class IndexNurseController {

    @FXML
    private Button exit_button;

    @FXML
    private Label first_name_label;

    @FXML
    private Label login_label;

    @FXML
    private TabPane menu_tab;

    @FXML
    private TableView<Task> tasks_table;

    @FXML
    private TableColumn<Patient, String> nurse_first_name_column;

    @FXML
    private TableColumn<Nurse, String> nurse_last_name_column;

    @FXML
    private TableColumn<Patient, Date> start_task_column;

    @FXML
    private TableColumn<Patient, Date> end_task_column;

    @FXML
    private TableColumn<Patient, String> task_name_column;

    @FXML
    private TableColumn<Patient, String> doctor_first_name_column;

    @FXML
    private TableColumn<Patient, String> doctor_last_name_column;

    @FXML
    private Tab tasks_tab;

    DataBaseHandler dbHandler = new DataBaseHandler();

    ObservableList<Task> tasks_list = FXCollections.observableArrayList();

    public void initData(Nurse nurse) throws SQLException, ClassNotFoundException {

        exit_button.setOnAction(actionEvent -> {
            load_window("hello-view.fxml");
            exit_button.getScene().getWindow().hide();
        });

        ResultSet tasks_result = dbHandler.getTasks();
        show_table_tasks(tasks_result);

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

    private void load_window(String url) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexNurseController.class.getResource(url));

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
