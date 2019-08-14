package com.softexpert.db.analysis.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractConnectionManager {

    private Connection connection;

    public abstract String getDriverName();

    public abstract String getURL();

    public abstract String getUsr();

    public abstract String getPwd();

    public abstract String getCreateLargeTableSQL();

    public abstract String getCreateSimpleTableSQL();

    public abstract String getTestSQL();

    public AbstractConnectionManager() {
        try {
            Class.forName(getDriverName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        loadConnectionIfNeeded();
        return connection;
    }

    public void beginTransaction() {
        try {
            loadConnectionIfNeeded();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitTransaction() {
        try {
            if (connection == null) {
                return;
            }
            connection.commit();
            System.out.println("Commit " + getClass().getSimpleName());
        } catch (SQLException e) {
            e.printStackTrace();
            rollbackTransaction();
        }
    }

    public void rollbackTransaction() {
        try {
            if (connection == null) {
                return;
            }
            connection.rollback();
            System.out.println("Rollback " + getClass().getSimpleName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection == null) {
                return;
            }
            connection.close();
            System.out.println("Close " + getClass().getSimpleName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeSQL(String sql) throws SQLException {
        try (PreparedStatement ppst = getConnection().prepareStatement(sql)) {
            return ppst.executeUpdate();
        }
    }

    private void loadConnectionIfNeeded() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            connection = DriverManager.getConnection(getURL(), getUsr(), getPwd());
            System.out.println("Create connection for " + getClass().getSimpleName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
