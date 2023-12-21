package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    Button quit;

    @FXML
    TextField updatedData;

    @FXML
    Button back;

    @FXML
    private TableView<Table> table;

    @FXML
    private TableColumn<Table, String> date;

    @FXML
    private TableColumn<Table, String> description;

    @FXML
    private TableColumn<Table, String> location;

    @FXML
    private TableColumn<Table, String> sr_no;

    @FXML
    private TableColumn<Table, String> subject;

    @FXML
    private TableColumn<Table, String> time;



    public void quit(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ImageView i = new ImageView("cat.png");
        i.setFitHeight(90);
        i.setPreserveRatio(true);
        alert.setGraphic(i);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage s = (Stage) quit.getScene().getWindow();
            s.close();
        }
    }

    public void deleteButton(){

        int id = Integer.parseInt(table.getSelectionModel().getSelectedItems().get(0).getSr_no());

        // Statement borrowed from StackOverFlow
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());

        // Remove from the database
        ToDoList.remove(id);

        // Update the ids of the following tasks
        for (int i=id, j=0; FileReader.AllTasks()[j][0] != null && i < FileReader.AllTasks().length; id++,j++)
            ToDoList.update(++i, String.valueOf(id), "id");

        // Refresh
        items.clear();
        loadTasks();
    }

    public void updateButton(){
        table.getSelectionModel().setCellSelectionEnabled(true);

        int id = table.getFocusModel().getFocusedCell().getRow();
        String columnName = table.getFocusModel().getFocusedCell().getTableColumn().getId();
        String newData = updatedData.getText();

        // sr_no column is immutable
        if(!columnName.equals("sr_no")) {
            ToDoList.update(++id, newData, columnName);
            items.clear();
            loadTasks();
        }
    }

    public void switch1(ActionEvent e) throws IOException {
        Stage s = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view1.fxml"));

        Scene scene = new Scene(root,app.WIDTH,app.HEIGHT);
        s.setScene(scene);
        s.show();
    }

    public void switchInsertPage(ActionEvent e) throws IOException {
        Stage s = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("insert.fxml"));

        Scene scene = new Scene(root,app.WIDTH,app.HEIGHT);
        s.setScene(scene);
        s.show();
    }

    ObservableList<Table> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadTasks();

        sr_no.setCellValueFactory(new PropertyValueFactory<Table, String>("sr_no"));
        subject.setCellValueFactory(new PropertyValueFactory<Table, String>("subject"));
        date.setCellValueFactory(new PropertyValueFactory<Table, String>("date"));
        location.setCellValueFactory(new PropertyValueFactory<Table, String>("location"));
        time.setCellValueFactory(new PropertyValueFactory<Table, String>("time"));
        description.setCellValueFactory(new PropertyValueFactory<Table, String>("description"));

        // Show all the tasks added
        table.setItems(items);
    }

    public void loadTasks(){
        // Add all tasks or specific tasks to 'items' based on whether some text was entered in the field or not
        if(!Controller1.search)
            for (int i = 0; FileReader.AllTasks()[i][0] != null && i < FileReader.AllTasks().length; i++)
                items.add(new Table(FileReader.AllTasks()[i]));
        else
            for (int i = 0; FileReader.AllTasks(Controller1.txt)[i][0] != null && i < FileReader.AllTasks(Controller1.txt).length; i++)
                items.add(new Table(FileReader.AllTasks(Controller1.txt)[i]));
    }
}