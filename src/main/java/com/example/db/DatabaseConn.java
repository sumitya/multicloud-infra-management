package com.example.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseConn {

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        return null;
    }

    public abstract void closeConnection();

}
