package cz.muni.fi.pv168.project.ui.panels.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.renderers.CurrencyRenderer;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;


public class CurrencyListPanel extends JPanel {

    public CurrencyListPanel(ListModel<Currency> currencyListModel) {
        var list = new JList<>(currencyListModel);
        list.setCellRenderer(new CurrencyRenderer());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
