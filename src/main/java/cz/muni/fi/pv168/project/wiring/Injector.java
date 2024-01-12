package cz.muni.fi.pv168.project.wiring;

import com.google.inject.Guice;

import java.util.List;

public class Injector {
    public static com.google.inject.Injector getInjector() {
        return Guice.createInjector(List.of(
                new cz.muni.fi.pv168.project.business.model.Module(),
                new cz.muni.fi.pv168.project.ui.Module(),
                new cz.muni.fi.pv168.project.business.service.validation.Module(),
                new cz.muni.fi.pv168.project.business.service.crud.Module(),
                new cz.muni.fi.pv168.project.storage.sql.Module(),
                new cz.muni.fi.pv168.project.business.repository.Module(),
                new cz.muni.fi.pv168.project.ui.model.Module(),
                new cz.muni.fi.pv168.project.ui.action.Module(),
                new cz.muni.fi.pv168.project.business.service.currenies.Module(),
                new cz.muni.fi.pv168.project.business.service.statistics.Module(),
                new cz.muni.fi.pv168.project.ui.dialog.Module(),
                new cz.muni.fi.pv168.project.ui.icons.Module(),
                new cz.muni.fi.pv168.project.data.Module(),
                new cz.muni.fi.pv168.project.storage.sql.db.Module()
        ));
    }
}
