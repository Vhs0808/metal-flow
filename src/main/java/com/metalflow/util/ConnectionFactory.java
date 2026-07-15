package com.metalflow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL=
            "jdbc:mysql://localhost:3306/MetalFlow_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="root";

    private ConnectionFactory(){}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado" + e);
        }

        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
