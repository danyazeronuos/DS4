package org.zero.ds4.utils;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class Database {
    private final Connection connection;
    private static final Database INSTANCE = new Database();

    public static Database getInstance() {
        return INSTANCE;
    }

    private Database() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/english",
                    "postgres",
                    "1111"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error creating connection with database");
        }

    }
}
