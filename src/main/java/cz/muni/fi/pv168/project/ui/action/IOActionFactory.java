package cz.muni.fi.pv168.project.ui.action;

import javax.inject.Inject;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;

public class IOActionFactory {
    private final DialogFactory dialogFactory;

    @Inject
    IOActionFactory(DialogFactory dialogFactory) {
        this.dialogFactory = dialogFactory;
    }
    public ExportAction getExportAction(ICarRideTableFilter carRideFilterModel) {
        return new ExportAction(dialogFactory, carRideFilterModel);
    }

    public ImportAction getImportAction(ICarRideTableFilter carRideFilterModel) {
        return new ImportAction(dialogFactory, carRideFilterModel);
    }
}
