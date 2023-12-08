package cz.muni.fi.pv168.project.ui.icons;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(IconLoader.class).to(CachedIconLoader.class);
    }
}
