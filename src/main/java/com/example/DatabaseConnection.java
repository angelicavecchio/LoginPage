package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection databaseLink;

    public Connection getConnection() {
        String databaseUsername = "yourUsername"; 
        String password = "yourPassword";         
        String url = "jdbc:mysql://localhost:3306/yourdbname"; 

        try {
            Class.forName("org.postgresql.Driver"); 
            databaseLink = DriverManager.getConnection(url, databaseUsername, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL non trovato.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al database.");
            e.printStackTrace();
        }

        return databaseLink;
    }
}

