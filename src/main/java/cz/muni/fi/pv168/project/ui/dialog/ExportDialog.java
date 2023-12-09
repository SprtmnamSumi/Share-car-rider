package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.export.BatchExporterCarRideJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCategoryJSON;
import cz.muni.fi.pv168.project.export.BatchExporterCurrencyJSON;
import cz.muni.fi.pv168.project.export.BatchExporterTemplateJSON;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import org.tinylog.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;
import java.util.Objects;


public class ExportDialog extends IODialog{
    private final static String EXPORT = "Export";
    private final static String CANCEL = "Cancel";
    private final TableModel<Template> templates;
    private final TableModel<Currency> currencies;
    private final TableModel<Category> categories;
    private final ICarRideTableFilter carRideTableFilter;

    ExportDialog(ICarRideTableFilter carRideTableFilter,
                 TableModel<Template> templates,
                 TableModel<Currency> currencies,
                 TableModel<Category> categories) {
        super(EXPORT, CANCEL);
        this.carRideTableFilter = carRideTableFilter;
        this.templates = templates;
        this.currencies = currencies;
        this.categories = categories;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE); // Reset the JOptionPane's value
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (EXPORT.equals(optionPane.getValue()) && getSelectedFile() != null) {
                    performExport(getSelectedEntity(), getSelectedFile());
                }
            }
        });
    }

    private void performExport(String selectedExportOption, File file) {
        Logger.info("Performing Export of "+selectedExportOption+" data to file: "+file.getAbsolutePath());
        switch (selectedExportOption) {
            case "Car Rides" -> {
                List<CarRide> carRideList = carRideTableFilter.getRideCompoundMatcher().getData();
                new BatchExporterCarRideJSON().exportData(carRideList, file.getAbsolutePath());
            }
            case "Currency" -> {
                new BatchExporterCurrencyJSON()
                        .exportData(currencies.getAll(), file.getAbsolutePath());
            }
            case "Category" -> {
                new BatchExporterCategoryJSON()
                        .exportData(categories.getAll(), file.getAbsolutePath());
            }
            case "Template" -> {
                new BatchExporterTemplateJSON()
                        .exportData(templates.getAll(), file.getAbsolutePath());
            }
            default -> {
                Logger.error("Selected unsupported export action.");
                throw new IllegalStateException("You shouldn't be here, how did you even get here?");
            }
        }
    }
}