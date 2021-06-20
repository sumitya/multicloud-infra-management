package com.example.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConn extends DatabaseConn {
    private static Connection conn;
    private static final Logger log = Logger.getLogger(OracleConn.class.getName());

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        if (conn == null) {
            conn = DriverManager.getConnection("");
            log.info("Oracle database connection is created!!");
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
