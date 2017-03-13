package com.softexpert.db.analysis.main;

import com.softexpert.db.analysis.control.AbstractConnectionManager;

public interface DatabaseRunnable {

	public void beforeRun(AbstractConnectionManager manager);
	public void run(AbstractConnectionManager manager) throws Exception;
	public void afterRun(AbstractConnectionManager manager);
	public void onError(AbstractConnectionManager manager, Exception exception);
}
