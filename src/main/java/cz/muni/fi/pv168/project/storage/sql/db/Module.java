package cz.muni.fi.pv168.project.storage.sql.db;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<TransactionExecutor>() {
        }).to(TransactionExecutorImpl.class);

    }
}
