package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.business.model.Model;

import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;

public class TableUtils {
    public static <T extends Model> List<T> getSelectedData(JTable table){
        TableModel<T> tableModel = (TableModel<T>) table.getModel();
        List<T> selectedData = new ArrayList<>();
        List<T> data = tableModel.getAllEntities();
        for(int index = 0; index < data.size(); index++){
            if(table.getSelectionModel().isSelectedIndex(index)){
                selectedData.add(data.get(table.convertRowIndexToModel(index)));
            }
        }
        return selectedData;
    }
}
