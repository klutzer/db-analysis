package com.softexpert.db.analysis.main;

import com.softexpert.db.analysis.control.AbstractConnectionManager;

public class DatabaseExecutor {
	
	public static void execute(AbstractConnectionManager manager, DatabaseRunnable test) {
		test.beforeRun(manager);
		try {
			test.run(manager);
		}catch (Exception e) {
			e.printStackTrace();
			test.onError(manager, e);
		}finally {
			test.afterRun(manager);
			manager.closeConnection();
		}
	}

}
