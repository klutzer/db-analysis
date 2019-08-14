package com.softexpert.db.analysis.control;

public class PostgreSQLConnectionManager extends AbstractConnectionManager {

    @Override
    public String getURL() {
        return "jdbc:postgresql://localhost:5432/sesuitedev?defaultRowFetchSize=100";
    }

    @Override
    public String getUsr() {
        return "postgres";
    }

    @Override
    public String getPwd() {
        return "XXXXXX";
    }

    @Override
    public String getCreateLargeTableSQL() {
        return "create table customers (idvo integer primary key, namevo varchar(100), textvo varchar(4000), randomdata bytea)";
    }

    @Override
    public String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getCreateSimpleTableSQL() {
        return "create table notes (idnotes integer primary key, description varchar(4000))";
    }

    @Override
    public String getTestSQL() {
        return "select 1";
    }

}
