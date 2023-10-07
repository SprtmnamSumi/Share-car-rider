package cz.muni.fi.pv168.project.gui.filterbar.panels;

import javax.swing.*;
import java.util.List;

public class ComboBoxPanel extends FilterComponentPanel{
    private final JComboBox<String> comboBox = new JComboBox<>();

    public ComboBoxPanel(String labelName) {
        super(labelName);
        this.add(comboBox);
    }

    public void setComboBoxItems(List<String> comboBoxItems) {
        comboBox.removeAllItems();
        for(String item : comboBoxItems){
            comboBox.addItem(item);
        }
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
