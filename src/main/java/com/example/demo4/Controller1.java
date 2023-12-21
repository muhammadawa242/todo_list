package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller1 {
    @FXML
    Button quit;

    @FXML
    static Button showAllTasks;

    @FXML
    TextField searchText;

    static boolean search;
    static String txt;

    public void quit(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ImageView i = new ImageView("cat2.png");
        i.setFitHeight(90);
        i.setPreserveRatio(true);
        alert.setGraphic(i);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage s = (Stage) quit.getScene().getWindow();
            s.close();
        }
    }

    public void switch2(ActionEvent e) throws IOException {
        search();
        Stage s = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view2.fxml"));
        Scene scene = new Scene(root,app.WIDTH,app.HEIGHT);
        s.setScene(scene);
        s.show();
    }

    public void search(){
        txt = searchText.getText();

        if(txt.isEmpty()){
            // Load all the tasks to be shown before moving to the next scene
            FileReader.query = "SELECT * FROM tasks;";

            search = false;
        }
        else
            search = true;
    }

}