package cz.muni.fi.pv168.project.ui;

import com.google.inject.AbstractModule;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.UuidGuidProvider;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainWindow.class).to(MainWindowImpl.class);
    }
}
