package com.softexpert.db.analysis.op;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;

public class LoadMassiveOperation implements DatabaseRunnable {

    @Override
    public void beforeRun(AbstractConnectionManager manager) {
        manager.beginTransaction();
    }

    @Override
    public void run(AbstractConnectionManager manager) throws Exception {

        try {
            PreparedStatement ppst = manager.getConnection().prepareStatement("select * from notes");
            System.out.println("Executing query...");
            ResultSet rs = ppst.executeQuery();
            System.out.println("ResultSet loaded. Sleeping to start iterating...");
            Thread.sleep(5000);
            long count = 0;
            getColumns(rs);
            while (rs.next()) {
                if (++count % 10000 == 0) {
                    System.out.println("Loaded record " + count + " (" + rs.getInt(1) + " - " + rs.getString(2) + ")");
                }

                //			    String description = rs.getString(2);
                //			    if (rs.getLong(1) % 10000 == 0) {
                //			        System.out.println("Load record " + rs.getLong(1) + ": " + description);
                //			    }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void afterRun(AbstractConnectionManager manager) {
    }

    @Override
    public void onError(AbstractConnectionManager manager, Exception exception) {
    }

    private static void getColumns(final ResultSet rs) throws Exception {
        final ResultSetMetaData metadata = rs.getMetaData();
        final int columnsCount = metadata.getColumnCount();
        for (int i = 1; i <= columnsCount; i++) {
            System.out.println("Column: " + metadata.getColumnName(i));
        }
    }

}
