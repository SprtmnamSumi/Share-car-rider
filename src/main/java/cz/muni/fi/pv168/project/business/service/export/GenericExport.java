package cz.muni.fi.pv168.project.business.service.export;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.business.service.export.batch.Batch;
import cz.muni.fi.pv168.project.business.service.export.batch.BatchExporter;
import cz.muni.fi.pv168.project.business.service.export.batch.BatchOperationException;
import cz.muni.fi.pv168.project.business.service.export.format.Format;
import cz.muni.fi.pv168.project.business.service.export.format.FormatMapping;

import java.util.Collection;


public class GenericExport implements Export{

    private final ICrudService<CarRide> carRideICrudService;
    private final ICrudService<Category> categoryICrudService;
    private final ICrudService<Currency> currencyICrudService;
    private final FormatMapping<BatchExporter> exporters;


    public GenericExport(
            ICrudService<CarRide> carRideICrudService,
            ICrudService<Category> categoryICrudService,
            ICrudService <Currency> currencyICrudService,
            Collection<BatchExporter> exporters
    ) {
        this.carRideICrudService = carRideICrudService;
        this.categoryICrudService = categoryICrudService;
        this.currencyICrudService = currencyICrudService;
        this.exporters = new FormatMapping<>(exporters);
    }
    @Override
    public void exportData(String filePath) {
        var exporter = getExporter(filePath);
        var batch = new Batch(carRideICrudService.findAll(),
                categoryICrudService.findAll(),
                currencyICrudService.findAll());
        exporter.exportBatch(batch, filePath);
    }

    @Override
    public Collection<Format> getFormats() {
        return exporters.getFormats();
    }

    private BatchExporter getExporter(String filePath) {
        var extension = filePath.substring(filePath.lastIndexOf('.') + 1);
        var importer = exporters.findByExtension(extension);
        if (importer == null)
            throw new BatchOperationException("Extension %s has no registered formatter".formatted(extension));
        return importer;
    }
}
