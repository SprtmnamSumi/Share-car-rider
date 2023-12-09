package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;

import javax.inject.Inject;

public class IOActionFactory {
    private final DialogFactory dialogFactory;

    @Inject
    IOActionFactory(DialogFactory dialogFactory) {
        this.dialogFactory = dialogFactory;
    }
    public ExportAction getExportAction(ICarRideTableFilter carRideFilterModel) {
        return new ExportAction(dialogFactory, carRideFilterModel);
    }

    public ImportAction getImportAction() {
        return new ImportAction(dialogFactory);
    }
}
