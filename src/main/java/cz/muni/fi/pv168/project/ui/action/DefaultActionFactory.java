package cz.muni.fi.pv168.project.ui.action;

import javax.swing.*;

public interface DefaultActionFactory<T> {
    public Action getAddAction(JTable table);
    public Action getDeleteAction(JTable table);
    public Action getEditAction(JTable table);
}
