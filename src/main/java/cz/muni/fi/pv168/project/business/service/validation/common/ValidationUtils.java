package cz.muni.fi.pv168.project.business.service.validation.common;

class ValidationUtils {
    static boolean validateDouble(String text){
        try{
            Double.parseDouble(text);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    static boolean validateInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
