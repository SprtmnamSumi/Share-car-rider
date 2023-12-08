package cz.muni.fi.pv168.project.storage.sql.db;

import javax.inject.Inject;
import java.util.Objects;
import java.util.function.Supplier;

public class TransactionConnectionSupplier implements Supplier<ConnectionHandler> {
    private final TransactionManager transactionManager;
    private final DatabaseManager databaseManager;

    @Inject
    public TransactionConnectionSupplier(TransactionManager transactionManager, DatabaseManager databaseManager) {
        this.transactionManager = Objects.requireNonNull(transactionManager);
        this.databaseManager = Objects.requireNonNull(databaseManager);
    }

    @Override
    public ConnectionHandler get() {
        if (transactionManager.hasActiveTransaction()) {
            return transactionManager.getConnectionHandler();
        }

        return databaseManager.getConnectionHandler();
    }
}
