package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.export.BatchExporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchExporterTemplateJSON;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.workers.WorkerProvider;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;
import org.tinylog.Logger;


public class ExportDialog extends IODialog {
    private final static String EXPORT = "Export";
    private final static String CANCEL = "Cancel";
    private final WorkerProvider workerProvider;
    private TableModel<Template> templates;
    private TableModel<Currency> currencies;
    private TableModel<Category> categories;
    private ICarRideTableFilter carRideTableFilter;

    ExportDialog(WorkerProvider workerProvider, List<Model> data) {
        super("Export Selection", true, EXPORT, CANCEL);
        this.workerProvider = workerProvider;
        this.forceSelectEntity(getSupportedEntity(data));
        initActions(() -> export(getSelectedEntity(), getSelectedFile(), data));
    }

    ExportDialog(WorkerProvider workerProvider,
                 ICarRideTableFilter carRideTableFilter,
                 TableModel<Template> templates,
                 TableModel<Currency> currencies,
                 TableModel<Category> categories) {
        super("Export data", true, EXPORT, CANCEL);
        this.workerProvider = workerProvider;
        this.carRideTableFilter = carRideTableFilter;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        initActions(() -> export(getSelectedEntity(), getSelectedFile()));
    }

    private void initActions(Runnable exportButtonAction) {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE); // Reset the JOptionPane's value
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (EXPORT.equals(optionPane.getValue()) && getSelectedFile() != null) {
                    exportButtonAction.run();
                }
            }
        });
    }

    private void export(String selectedExportOption, File file) {
        Logger.info("Performing Export of " + selectedExportOption + " data to file: " + file.getAbsolutePath());

        Function<Void, Boolean> exportFunction = switch (selectedExportOption) {
            case "Car Rides" -> (x) -> new BatchExporterCarRideJSON()
                    .exportData(carRideTableFilter.getRideCompoundMatcher().getData(), file.getAbsolutePath());
            case "Currency" -> (x) -> new BatchExporterCurrencyJSON()
                    .exportData(currencies.getAll(), file.getAbsolutePath());
            case "Category" -> (x) -> new BatchExporterCategoryJSON()
                    .exportData(categories.getAll(), file.getAbsolutePath());
            case "Template" ->
                    (x) -> new BatchExporterTemplateJSON().exportData(templates.getAll(), file.getAbsolutePath());
            default -> {
                Logger.error("Selected unsupported export action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        };
        performExport(exportFunction);
    }

    private void export(String selectedExportOption, File file, List<Model> data) {
        Logger.info("Performing Export of " + selectedExportOption + " data to file: " + file.getAbsolutePath());
        Function<Void, Boolean> exportFunction = switch (selectedExportOption) {
            case "Car Rides" -> (x) -> new BatchExporterCarRideJSON()
                    .exportData(data.stream().map(model -> (CarRide) model).toList(), file.getAbsolutePath());
            case "Currency" -> (x) -> new BatchExporterCurrencyJSON()
                    .exportData(data.stream().map(model -> (Currency) model).toList(), file.getAbsolutePath());
            case "Category" -> (x) -> new BatchExporterCategoryJSON()
                    .exportData(data.stream().map(model -> (Category) model).toList(), file.getAbsolutePath());
            case "Template" -> (x) -> new BatchExporterTemplateJSON()
                    .exportData(data.stream().map(model -> (Template) model).toList(), file.getAbsolutePath());
            default -> {
                Logger.error("Selected unsupported export action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        };
        performExport(exportFunction);
    }

    private void performExport(Function<Void, Boolean> exportFunction) {
        boolean success = workerProvider.submitTask(exportFunction,
                () -> JOptionPane.showMessageDialog(this, "Export has NOT successfully finished."),
                "Export");
        if (!success) {
            Logger.info("Export did not start, because another IO action is in progress");
            JOptionPane.showMessageDialog(this, "Export did not start, because another IO action is in progress");
        }
    }

    private String getSupportedEntity(List<Model> data) {
        if (data.get(0) instanceof CarRide) {
            return "Car Rides";
        }
        if (data.get(0) instanceof Category) {
            return "Category";
        }
        if (data.get(0) instanceof Template) {
            return "Template";
        }
        return "Currency";
    }
}