package cz.muni.fi.pv168.project.storage.sql.dao;

public interface ISetUp<I, E extends Throwable> {
    void apply(I input) throws E;
}