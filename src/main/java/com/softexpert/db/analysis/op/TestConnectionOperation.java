package com.softexpert.db.analysis.op;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.softexpert.db.analysis.control.AbstractConnectionManager;
import com.softexpert.db.analysis.main.DatabaseRunnable;

public class TestConnectionOperation implements DatabaseRunnable {

	@Override
	public void beforeRun(AbstractConnectionManager manager) {
	}

	@Override
	public void run(AbstractConnectionManager manager) throws Exception {
	    try (PreparedStatement statement = manager.getConnection().prepareStatement(manager.getTestSQL())) {
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            System.out.println("Query returned " + rs.getInt(1));		    
	        } else {
	            System.err.println("Query returned nothing");
	        }
	    }
	}

	@Override
	public void afterRun(AbstractConnectionManager manager) {
	}
	
	@Override
	public void onError(AbstractConnectionManager manager, Exception exception) {
	}

}
