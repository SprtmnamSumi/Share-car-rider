package cz.muni.fi.pv168.project.ui;

import com.google.inject.AbstractModule;


public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainWindow.class).to(MainWindowImpl.class);
    }
}
