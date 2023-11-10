package cz.muni.fi.pv168.project.ui.renderers;


import cz.muni.fi.pv168.project.business.model.CurrencyConversion;

import javax.swing.*;

public final class ConversionRenderer extends AbstractRenderer<CurrencyConversion> {

    public ConversionRenderer() {
        super(CurrencyConversion.class);
    }

    @Override
    protected void updateLabel(JLabel label, CurrencyConversion value) {
        if (value != null) {
            label.setText(value.toString());
        }
    }
}
