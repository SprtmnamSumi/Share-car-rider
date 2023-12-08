package cz.muni.fi.pv168.project.ui.renderers;


import javax.swing.JLabel;
import java.util.Currency;

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
