package com.softexpert.db.analysis.control;

public class SQLServerConnectionManager extends AbstractConnectionManager {

	@Override
	public String getURL() {
		return "jdbc:jtds:sqlserver://VMDATABASE2012:1433/ERICO_DEV;instance=SS2016";
	}

	@Override
	public String getUsr() {
		return "ERICO_DEV";
	}

	@Override
	public String getPwd() {
		return "XXXXXX";
	}
	
	@Override
	public String getCreateTableSQL() {
		return "create table customers (idvo integer, namevo varchar(100), textvo varchar(4000), randomdata image, "
				+ "constraint customers_pk PRIMARY KEY (idvo))";
	}

	@Override
	public String getDriverName() {
		return "net.sourceforge.jtds.jdbc.Driver";
	}

}
