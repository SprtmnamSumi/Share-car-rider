package cz.muni.fi.pv168.project.ui.model;

import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;

public abstract class ListModel<T> extends AbstractListModel<T> {

    private final List<T> items;

    public ListModel(List<T> Items) {
        this.items = Items;
    }

    public void addElem(T elem) {
        items.add(elem);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public T getElementAt(int index) {
        return items.get(index);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }
}
