package cz.muni.fi.pv168.project.storage.sql.dao;

public interface panda<I, O, E extends Throwable> {
    O apply(I input) throws E;
}