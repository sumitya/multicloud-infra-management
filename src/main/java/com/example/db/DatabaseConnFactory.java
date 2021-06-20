package com.example.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnFactory {

    public synchronized Connection createDbConn(String dbType) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        if (dbType.equals("sqlite")) {
            conn = SqliteConn.createConnection();
        } else if (dbType.equals("oracle")) {
            conn = OracleConn.createConnection();
        }
        return conn;
    }
}
