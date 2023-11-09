package cz.muni.fi.pv168.project.ui.model.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class CurrencyListModel extends ListModel<Currency> {

    public CurrencyListModel(List<Currency> currencies) {
        super(currencies);
    }
}
