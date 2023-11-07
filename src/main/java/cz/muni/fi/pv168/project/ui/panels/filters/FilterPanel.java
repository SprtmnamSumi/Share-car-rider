package cz.muni.fi.pv168.project.ui.panels.filters;

import javax.swing.*;

public class FilterPanel extends JPanel {

    public void reset(){
    }

    public void updateValues(){

    }
    protected boolean isIntInputValid(JTextField texfField) {
        try {
            int value = Integer.parseInt(texfField.getText());
            return value >= 0;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }
}
