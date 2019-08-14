package com.softexpert.db.analysis.op;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;

public class LoadLargeOperation implements DatabaseRunnable {

	@Override
	public void beforeRun(AbstractConnectionManager manager) {
		manager.beginTransaction();
	}

	@Override
	public void run(AbstractConnectionManager manager) throws Exception {

		try {
			PreparedStatement ppst = manager.getConnection().prepareStatement("select * from customers");
			ResultSet rs = ppst.executeQuery();
			System.out.println("ResultSet loaded. Sleeping to start iterating...");
			Thread.sleep(5000);
			while (rs.next()) {
				System.out.println("Load " + rs.getString("namevo"));
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

}
