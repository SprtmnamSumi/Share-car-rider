package cz.muni.fi.pv168.project.wiring;


import cz.muni.fi.pv168.project.storage.sql.db.DatabaseManager;
import javax.inject.Singleton;
import org.tinylog.Logger;

/**
 * Dependency provider for production environment.
 */
@Singleton
public final class ProductionDatabaseProvider implements IProductionDatabaseProvider {
    private static DatabaseManager databaseManager = null;

    public static DatabaseManager getDatabaseManager() {
        if (databaseManager == null) {
            createDatabaseManager();
        }
        assert databaseManager != null;

        return databaseManager;
    }


    private static void createDatabaseManager() {
        try {
            ProductionDatabaseProvider.databaseManager = DatabaseManager.createProductionInstance();
        } catch (Exception e) {
            Logger.error("Database schema initialization failed. Reason: " + e);
        }

    }
}
