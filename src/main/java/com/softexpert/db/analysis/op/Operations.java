package com.softexpert.db.analysis.op;

import com.softexpert.db.analysis.main.DatabaseRunnable;

public enum Operations {

    TEST_CONNECTION ("Testar conexão", TestConnectionOperation.class),
    INSERT_LARGE_DATA ("Inserir dados grandes", InsertLargeOperation.class),
    SELECT_LARGE_DATA ("Buscar dados grandes", LoadLargeOperation.class),
    INSERT_MASSIVE_DATA ("Inserir grande quantidade de dados", InsertMassiveOperation.class),
    SELECT_MASSIVE_DATA ("Buscar grande quantidade de dados", LoadMassiveOperation.class);
    
    private String operationName;
    private final Class<? extends DatabaseRunnable> runnableClass;
    
    Operations(String operationName, Class<? extends DatabaseRunnable> runnableClass) {
        this.operationName = operationName;
        this.runnableClass = runnableClass;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public Class<? extends DatabaseRunnable> getRunnableClass() {
        return this.runnableClass;
    }

    public DatabaseRunnable createDatabaseRunnable() {
        try {
            return this.runnableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
