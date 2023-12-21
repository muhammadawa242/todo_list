package com.example.demo4;

import java.sql.*;


public class FileWriter
{
    public static void save(String Save)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:E:\\repos\\repos_Java\\demo4\\src\\main\\java\\com\\example\\demo4\\todos.db");
            Statement stmt = c.createStatement();

            stmt.execute(Save);

            stmt.close();
            c.close();
        }
        catch (Exception e)
        {
            if(e.getMessage().substring(0, 62).equals("[SQLITE_ERROR] SQL error or missing database (no such column: "))
                System.err.println("Specified column does not exist!");
            else
                System.out.println(e.getMessage());
        }
    }
}