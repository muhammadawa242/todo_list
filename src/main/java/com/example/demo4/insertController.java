package com.example.demo4;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class insertController {

    @FXML
    private Label insertLabel;

    @FXML
    private Button insert;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField desField;

    @FXML
    private TextField locField;

    @FXML
    private TextField subField;

    @FXML
    private TextField timeField;

    public void switch2(ActionEvent e) throws IOException {

        // Load all the tasks to be shown before moving to the next scene
        FileReader.query = "SELECT * FROM tasks;";

        Stage s = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view2.fxml"));
        Scene scene = new Scene(root,app.WIDTH,app.HEIGHT);
        s.setScene(scene);
        s.show();
    }

    public void insertTask() throws IOException, InterruptedException {
        String sub = subField.getText();
        String des = desField.getText();
        String loc = locField.getText();
        String date = String.valueOf(dateField.getValue());
        String time = timeField.getText();

        boolean illegalCharacters = (sub + des + loc + date + time).matches(".*[%<>&;'-+=*].*");
        boolean EmptyField = sub.isEmpty() || des.isEmpty() || loc.isEmpty() || date.isEmpty() || time.isEmpty();

        if(!illegalCharacters && !EmptyField) {
            ToDoList task = new ToDoList(sub, des, loc, date, time);
            task.insert();

            // Clear all the input fields
            subField.undo();
            desField.undo();
            locField.undo();
            dateField.setValue(null);
            timeField.undo();


            // The following text appears when no illegal chars are used
            insertLabel.setTextFill(Paint.valueOf("GREEN"));
            insertLabel.setText("Task Inserted successfully!");

        }
        else if(illegalCharacters){
            insertLabel.setTextFill(Paint.valueOf("RED"));
            insertLabel.setText("No characters are allowed\n \tin input fields!");
        }
        else{
            insertLabel.setTextFill(Paint.valueOf("RED"));
            insertLabel.setText("No empty fields are allowed\n \t\tas input!");
        }

        PauseTransition p = new PauseTransition(Duration.seconds(1.5));
        p.setOnFinished(e -> insertLabel.setText(null));
        p.play();
    }
}