package cz.muni.fi.pv168.project.ui.model.Currency;

import com.google.inject.Singleton;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import javax.inject.Inject;
import java.util.List;

@Singleton
public class CurrencyListModel extends ListModel<Currency> {
    @Inject
    public CurrencyListModel(List<Currency> currencies) {
        super(currencies);
    }
}
