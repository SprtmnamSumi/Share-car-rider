package cz.muni.fi.pv168.project.ui.model.corversions;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.CurrencyConversion;
import cz.muni.fi.pv168.project.business.service.crud.IConversionCrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class ConversionTableModel extends TableModel<CurrencyConversion> {
    @Inject
    public ConversionTableModel(IConversionCrudService currencyCrudService) {
        super(currencyCrudService, List.of(
                Column.readonly("Name", String.class, CurrencyConversion::getGuid))
        );
    }
}
