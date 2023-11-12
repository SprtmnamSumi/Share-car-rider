package cz.muni.fi.pv168.project.ui.dialog;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(DialogFactory.class).to(ModalDialogFactory.class);
    }
}
