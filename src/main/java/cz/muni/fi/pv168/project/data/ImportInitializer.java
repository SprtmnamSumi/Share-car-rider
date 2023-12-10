package cz.muni.fi.pv168.project.data;

import com.google.inject.Inject;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.storage.sql.db.TransactionExecutor;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import java.util.List;

public class ImportInitializer implements IImportInitializer {
    private final TableModel<Category> categoryTableModel;
    private final TableModel<CarRide> carRideTableModel;
    private final TableModel<Currency> currencyTableModel;
    private final TableModel<Template> templateTableModel;
    private final TransactionExecutor transactionExecutor;
    private final ICrudService<Currency> currencyICrudService;
    private final ICrudService<Category> categoryICrudService;
    private final ICrudService<Template> templateICrudService;
    private final ICrudService<CarRide> carRideICrudService;

    @Inject
    ImportInitializer(TableModel<Category> categoryTable, TableModel<CarRide> rideTable, TableModel<Currency> currencyTable, TableModel<Template> templateTable, TransactionExecutor transactionExecutor,
                      ICrudService<Currency> currencyICrudService, ICrudService<Category> categoryICrudService, ICrudService<Template> templateICrudService, ICrudService<CarRide> carRideICrudService
    ) {
        this.categoryTableModel = categoryTable;
        this.carRideTableModel = rideTable;
        this.currencyTableModel = currencyTable;
        this.templateTableModel = templateTable;
        this.transactionExecutor = transactionExecutor;
        this.currencyICrudService = currencyICrudService;
        this.categoryICrudService = categoryICrudService;
        this.templateICrudService = templateICrudService;
        this.carRideICrudService = carRideICrudService;
    }


    @Override
    public void initializeCarRide(List<CarRide> rides, boolean rewrite) {
        transactionExecutor.executeInTransaction(() -> {
            if (rewrite) {
                carRideICrudService.deleteAll();
            }
            rides.forEach(carRideICrudService::create);
        });
        carRideTableModel.refresh();
    }


    @Override
    public void initializeCategory(List<Category> categories, boolean rewrite) {
        transactionExecutor.executeInTransaction(() -> {
            if (rewrite) {
                categoryICrudService.deleteAll();
            }
            categories.forEach(categoryICrudService::create);
        });
        categoryTableModel.refresh();
    }


    @Override
    public void initializeCurrency(List<Currency> currencies, boolean rewrite) {
        transactionExecutor.executeInTransaction(() -> {
            if (rewrite) {
                currencyICrudService.deleteAll();
            }
            currencies.forEach(currencyICrudService::create);
        });
        currencyTableModel.refresh();
    }


    @Override
    public void initializeTemplate(List<Template> templates, boolean rewrite) {
        transactionExecutor.executeInTransaction(() -> {
            if (rewrite) {
                currencyICrudService.deleteAll();
            }
            templates.forEach(templateICrudService::create);
        });
        templateTableModel.refresh();
    }

}
