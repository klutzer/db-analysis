package com.softexpert.db.analysis.control;

public class SQLServerConnectionManager extends AbstractConnectionManager {

    @Override
    public String getURL() {
        return "jdbc:jtds:sqlserver://VMDATABASE2017:1433/cotia_erico;instance=MSSQLSERVER2017";
    }

    @Override
    public String getUsr() {
        return "sa";
    }

    @Override
    public String getPwd() {
        return "XXXXXX";
    }

    @Override
    public String getCreateLargeTableSQL() {
        return "create table customers (idvo integer, namevo varchar(100), textvo varchar(4000), randomdata image, constraint customers_pk PRIMARY KEY (idvo))";
    }

    @Override
    public String getDriverName() {
        return "net.sourceforge.jtds.jdbc.Driver";
    }

    @Override
    public String getCreateSimpleTableSQL() {
        return "create table notes (idnotes bigint primary key, description varchar(4000))";
    }

    @Override
    public String getTestSQL() {
        return "select 1";
    }

}
