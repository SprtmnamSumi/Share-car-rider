package cz.muni.fi.pv168.project.storage.sql.db.actions;


import cz.muni.fi.pv168.project.data.IInitializator;
import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;

import static cz.muni.fi.pv168.project.wiring.Injector.getInjector;

public final class ReCreateDatabase {

    public static void main(String[] args) {
        var injector = getInjector();
        var dbManager = injector.getInstance(DatabaseManager.class);
//        var dbManager = injector.getInstance(DatabaseManager.class);
        var initializator = injector.getInstance(IInitializator.class);

        dbManager.destroySchema();
        dbManager.initSchema();
        initializator.initialize(150);

        System.out.println("Database created...");
        System.out.println("Database connection string: " + dbManager.getDatabaseConnectionString());
    }
}
