package com.uncodigo.vwallet.models.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb implements IConnectionEnvs {
    // Implements singleton pattern
    private static ConnectionDb instance;
    private static Connection connection;

    private ConnectionDb() {
        try {
            Class.forName(DRIVER_DB);
            connection = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ConnectionDb getInstance() {
        if (instance == null) {
            instance = new ConnectionDb();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
