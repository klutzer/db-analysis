package com.softexpert.db.analysis.control;

public class OracleConnectionManager extends AbstractConnectionManager {

    @Override
    public String getURL() {
        return "jdbc:oracle:thin:@192.168.1.245:1521:orcl";
    }

    @Override
    public String getUsr() {
        return "orcl";
    }

    @Override
    public String getPwd() {
        return "XXXXXX";
    }

    @Override
    public String getCreateLargeTableSQL() {
        return "create table customers (idvo integer, namevo varchar(100), textvo varchar(4000), randomdata blob, constraint customers_pk PRIMARY KEY (idvo))";
    }

    @Override
    public String getDriverName() {
        return "oracle.jdbc.OracleDriver";
    }

    @Override
    public String getCreateSimpleTableSQL() {
        return "create table notes (idnotes integer, description varchar(4000), constraint notes_pk primary key (idnotes))";
    }

    @Override
    public String getTestSQL() {
        return "select 1 from dual";
    }

}
