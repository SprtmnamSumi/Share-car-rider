package cz.muni.fi.pv168.project.storage.sql.db;

import java.util.function.Supplier;
import javax.inject.Inject;

public class TransactionExecutorImpl implements TransactionExecutor {

    private final Supplier<Transaction> transactions;

    @Inject
    public TransactionExecutorImpl(DatabaseManager databaseManager) {
        this.transactions = databaseManager::getTransactionHandler;
    }

    @Override
    public void executeInTransaction(Runnable operation) {
        try (var transaction = transactions.get()) {
            operation.run();
            transaction.commit();
        }
    }
}
