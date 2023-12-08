package cz.muni.fi.pv168.project.business.service.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.business.service.export.batch.BatchImporter;
import cz.muni.fi.pv168.project.business.service.export.batch.BatchOperationException;
import cz.muni.fi.pv168.project.business.service.export.format.Format;
import cz.muni.fi.pv168.project.business.service.export.format.FormatMapping;

import java.util.Collection;


public class GenericImport implements Import{
    private final ICrudService<CarRide> carRideICrudService;
    private final ICrudService<Category> categoryICrudService;
    private final ICrudService<Currency> currencyICrudService;
    private final FormatMapping<BatchImporter> importers;

    public GenericImport(
            ICrudService<CarRide> carRideICrudService,
            ICrudService<Category> categoryICrudService,
            ICrudService <Currency> currencyICrudService,
            Collection<BatchImporter> importers
    ) {
        this.carRideICrudService = carRideICrudService;
        this.categoryICrudService = categoryICrudService;
        this.currencyICrudService = currencyICrudService;
        this.importers = new FormatMapping<>(importers);
    }

    @Override
    public void importData(String filePath) {
        carRideICrudService.deleteAll();
        categoryICrudService.deleteAll();
        currencyICrudService.deleteAll();

        var batch = getImporter(filePath).importBatch(filePath);

        batch.carRides().forEach(this::createCarRide);
        batch.categories().forEach(this::createCategory);
        batch.currencies().forEach(this::createCurrency);
    }

    private void createCarRide(CarRide carRide) {
        carRideICrudService.create(carRide)
                .intoException();
    }

    private void createCategory(Category category) {
        categoryICrudService.create(category)
                .intoException();
    }

    private void createCurrency(Currency currency) {
        currencyICrudService.create(currency)
                .intoException();
    }

    @Override
    public Collection<Format> getFormats() {
        return importers.getFormats();
    }

    private BatchImporter getImporter(String filePath) {
        var extension = filePath.substring(filePath.lastIndexOf('.') + 1);
        var importer = importers.findByExtension(extension);
        if (importer == null) {
            throw new BatchOperationException("Extension %s has no registered formatter".formatted(extension));
        }
        return importer;
    }
}
