package ru.javalab.homework7.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        try {
            FileInputStream fis = new FileInputStream(new File("db.properties"));
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String password = properties.getProperty("db.password");
            String login = properties.getProperty("db.user");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Cant get connection.");
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Cant find driver class");
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            System.out.println("Cant get access to properties file");
            throw new IllegalStateException(e);
        }
    }


    public Connection getConnection() {
        return connection;
    }

}
