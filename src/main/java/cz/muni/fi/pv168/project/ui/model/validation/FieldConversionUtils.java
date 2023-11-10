package cz.muni.fi.pv168.project.ui.model.validation;

import javax.swing.*;

public class FieldConversionUtils {
    public static boolean validateDouble(JTextField field){
        try{
            Double.parseDouble(field.getText());
            return true;
        }
        catch(IllegalArgumentException e){
            return false;
        }
    }
}
