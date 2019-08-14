package com.softexpert.db.analysis.op;

import java.sql.PreparedStatement;

import org.apache.commons.lang3.RandomStringUtils;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;

public class InsertMassiveOperation implements DatabaseRunnable {

	@Override
	public void beforeRun(AbstractConnectionManager manager) {
		manager.beginTransaction();
	}

	@Override
	public void run(AbstractConnectionManager manager) throws Exception {
	    manager.executeSQL(manager.getCreateSimpleTableSQL());
	    try (PreparedStatement ppst = manager.getConnection().prepareStatement("insert into notes values (?,?)")) {
	        System.out.println("Storing data...");
	        for (int i=1; i<=10_000_000; i++) {
	            String longStr = createRandomString();
	            ppst.setInt(1, i);
	            ppst.setString(2, longStr);
	            ppst.addBatch();

	            if (i > 0 && i % 10000 == 0) {
	                ppst.executeBatch();
	                manager.commitTransaction();
	                //manager.getConnection().prepareStatement("dbcc shrinkfile (ctc_log, truncateonly)").executeQuery();
	                System.out.println(i+" rows inserted.");
	            }
	        }
	        ppst.executeBatch();
	    }
	    System.out.println("Done!");
	}

	@Override
	public void afterRun(AbstractConnectionManager manager) {
		manager.commitTransaction();
	}

	@Override
	public void onError(AbstractConnectionManager manager, Exception exception) {
		manager.rollbackTransaction();
	}

	private String createRandomString() {
		return RandomStringUtils.randomAlphanumeric(4000);
	}
	
}
