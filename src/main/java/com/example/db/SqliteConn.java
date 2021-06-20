package com.example.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConn extends DatabaseConn {
    private static Connection conn;
    private static final Logger log = Logger.getLogger(SqliteConn.class.getName());

    public static Connection createConnection() throws SQLException {

        String url = "jdbc:sqlite:/Users/sumityadav/IdeaProjects/multicloud-infra-management/data/database.db";
        if (conn == null) {
            conn = DriverManager.getConnection(url);
            log.info("Sqlite database connection is created!!");
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
