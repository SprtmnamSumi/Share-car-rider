package cz.muni.fi.pv168.project.ui.validation;

public class ValidationUtils {
    public static boolean validateDouble(String text){
        try{
            Double.parseDouble(text);
            return true;
        }
        catch(IllegalArgumentException e){
            return false;
        }
    }
    public static boolean validateInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
