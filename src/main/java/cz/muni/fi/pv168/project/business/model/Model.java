package cz.muni.fi.pv168.project.business.model;

import java.util.Objects;

public abstract class Model {

    protected String guid;

    protected Model(String guid) {
        this.guid = guid;
    }

    /**
     * Returns globally unique identifier of the given entity.
     */
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(guid, model.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
