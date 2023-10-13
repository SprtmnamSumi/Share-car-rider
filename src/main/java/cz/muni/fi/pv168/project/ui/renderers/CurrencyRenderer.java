package cz.muni.fi.pv168.project.ui.renderers;

import cz.muni.fi.pv168.project.entities.old.Gender;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.util.Currency;
import java.util.Map;

public final class CurrencyRenderer extends AbstractRenderer<Currency> {

    public CurrencyRenderer() {
        super(Currency.class);
    }

        @Override
    protected void updateLabel(JLabel label, Currency value) {
        if (value != null) {
            label.setText(value.toString());
        }
    }
}
