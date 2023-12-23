package com.example.demo4;

import java.sql.*;

public class FileReader {
    public final static int TASK_LIMIT = 50;
    static String query;

    // Returns a 2D array of string elements containing the data from the database
    public static String[][] AllTasks()
    {
        try
        {
            // Get connection with database
            Class.forName("org.sqlite.JDBC");
            System.out.println("yes\n\n");
            Connection c = DriverManager.getConnection("jdbc:sqlite:E:\\repos\\repos_Java\\demo4\\src\\main\\java\\com\\example\\demo4\\todos.db");
            Statement stmt = c.createStatement();

            // Execute Query
            ResultSet rs = stmt.executeQuery(query);

            int row = 0, column = 0;
            String[][] allTasks = new String[TASK_LIMIT][6];

            // Populate the 'allTasks' array
            while(rs.next())
            {
                allTasks[row][column++] = String.valueOf(rs.getInt("id"));
                allTasks[row][column++] = rs.getString("subject");
                allTasks[row][column++] = rs.getString("description");
                allTasks[row][column++] = rs.getString("location");
                allTasks[row][column++] = rs.getString("date");
                allTasks[row][column] = rs.getString("time");

                row++;
                column = 0;
            }

            stmt.close();
            c.close();
            return allTasks;
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Return the data requested to be searched for
    public static String[][] AllTasks(String search_text)
    {
        query = String.format("SELECT * FROM tasks WHERE subject Like \"%s\";", search_text);
        return AllTasks();
    }
}