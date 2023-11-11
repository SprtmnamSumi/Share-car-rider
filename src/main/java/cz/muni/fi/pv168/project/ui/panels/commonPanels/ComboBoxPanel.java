package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComboBoxPanel<T> extends FilterComponentPanel {
    private final JComboBox<T> comboBox = new JComboBox<>();

    public ComboBoxPanel(String labelName) {
        super(labelName);
        this.add(comboBox);
        comboBox.setRenderer((list, value, index, isSelected, cellHasFocus) ->
                new DefaultListCellRenderer()
                        .getListCellRendererComponent(list, value == null ? "All" : value, index, isSelected, cellHasFocus));
    }


    public void setComboBoxItems(List<T> comboBoxItems) {
        comboBox.removeAllItems();
        comboBox.addItem(null);
        for (T item : comboBoxItems) {
            comboBox.addItem(item);
        }
    }

    public JComboBox<T> getComboBox() {
        return comboBox;
    }
}
