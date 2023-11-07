package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.*;
import java.util.List;

public class ComboBoxPanel<T> extends FilterComponentPanel {
    private final JComboBox<T> comboBox = new JComboBox<>();

    public ComboBoxPanel(String labelName) {
        super(labelName);
        this.add(comboBox);
    }

    public void setComboBoxItems(List<T> comboBoxItems) {
        comboBox.addItem(null);
        for (T item : comboBoxItems) {
            comboBox.addItem(item);
        }
    }

    public JComboBox<T> getComboBox() {
        return comboBox;
    }
}
