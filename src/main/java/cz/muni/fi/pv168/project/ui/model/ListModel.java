package cz.muni.fi.pv168.project.ui.model;

import javax.swing.*;
import java.util.List;

public abstract class ListModel<T> extends AbstractListModel<T> {

    private final List<T> items;

    public ListModel(List<T> Items) {
        this.items = Items;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public T getElementAt(int index) {
        return items.get(index);
    }
}
