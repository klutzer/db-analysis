package com.softexpert.db.analysis.op;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;

public class DropTable implements DatabaseRunnable {

	@Override
	public void beforeRun(AbstractConnectionManager manager) {
		manager.beginTransaction();
	}

	@Override
	public void run(AbstractConnectionManager manager) throws Exception {
		manager.executeSQL("drop table customers");
	}

	@Override
	public void afterRun(AbstractConnectionManager manager) {
		manager.commitTransaction();
	}

	@Override
	public void onError(AbstractConnectionManager manager, Exception exception) {
		manager.rollbackTransaction();
	}

}
