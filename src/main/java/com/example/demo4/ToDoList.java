package com.example.demo4;

import javafx.scene.control.Alert;

public class ToDoList
{
    // Properties
    private String subject, description, location, date, time;

    // Constructors
    ToDoList(String subject, String description, String location, String date, String time){
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    // Instance Methods
    public void insert()
    {
        // Save only if the max limit of tasks has not exceeded
        if (FileReader.AllTasks()[FileReader.TASK_LIMIT - 1][0] == null) {
            String insert = String.format(
                    "INSERT INTO tasks (subject, description, location, date, time) " +
                            "VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
                    subject, description, location, date, time);

            FileWriter.save(insert);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have exceeded the limit of tasks as a free user!");
            alert.show();
        }
    }

    // Static methods

    // Update
    public static void update(int id, String newData, String column)
    {
        String update = String.format("UPDATE tasks SET %s = \"%s\" WHERE id == %d;", column, newData, id);
        FileWriter.save(update);
    }

    // Remove
    public static void remove(int id)
    {
        String remove = String.format("DELETE FROM tasks WHERE id == %d;", id);
        FileWriter.save(remove);
    }
}
