package cz.muni.fi.pv168.project.ui.action;

import javax.swing.Action;
import javax.swing.JTable;

public interface DefaultActionFactory<T> {
    Action getAddAction(JTable table);

    Action getDeleteAction(JTable table);

    Action getEditAction(JTable table);
}
