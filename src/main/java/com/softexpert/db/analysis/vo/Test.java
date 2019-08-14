package com.softexpert.db.analysis.vo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.control.OracleConnectionManager;
import com.softexpert.db.analysis.control.PostgreSQLConnectionManager;
import com.softexpert.db.analysis.control.SQLServerConnectionManager;

public class Test {

    public static void main(String[] args) throws Exception {
        testStatement(new PostgreSQLConnectionManager());
        testStatement(new OracleConnectionManager());
        testStatement(new SQLServerConnectionManager());
    }
    
    private static void testStatement(AbstractConnectionManager manager) throws Exception {
        ResultSet rs = null;
        try (PreparedStatement statement = manager.getConnection().prepareStatement("select count(1) from aduser")) {
            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        }
        System.out.println("ResultSet está fechado para "+manager.getDriverName()+"? " + rs.isClosed());
    }
}
