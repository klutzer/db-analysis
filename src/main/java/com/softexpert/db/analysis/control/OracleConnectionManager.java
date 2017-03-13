package com.softexpert.db.analysis.control;

public class OracleConnectionManager extends AbstractConnectionManager {

	@Override
	public String getURL() {
		return "jdbc:oracle:thin:@192.168.1.245:1521:orcl";
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
		return "create table customers (idvo integer, namevo varchar(100), textvo varchar(4000), randomdata blob,"
				+ "constraint customers_pk PRIMARY KEY (idvo))";
	}

	@Override
	public String getDriverName() {
		return "oracle.jdbc.OracleDriver";
	}

}
