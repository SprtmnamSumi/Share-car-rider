package cz.muni.fi.pv168.project.ui.model.corversions;

import cz.muni.fi.pv168.project.business.model.CurrencyConversion;
import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.List;

public class CorversionListModel extends ListModel<CurrencyConversion> {

    public CorversionListModel(List<CurrencyConversion> currencies) {
        super(currencies);
    }
}
