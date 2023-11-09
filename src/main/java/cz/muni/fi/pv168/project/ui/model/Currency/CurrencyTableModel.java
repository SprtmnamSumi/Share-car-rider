package cz.muni.fi.pv168.project.ui.model.Currency;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.crud.ICurrencyCrudService;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
@Singleton
public class CurrencyTableModel extends TableModel<Currency> {
    @Inject
    public CurrencyTableModel(ICurrencyCrudService currencyCrudService) {
        super(currencyCrudService, List.of(
                Column.readonly("Name", String.class, Currency::getName)));
    }
}
