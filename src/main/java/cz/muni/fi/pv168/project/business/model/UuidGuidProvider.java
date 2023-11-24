package cz.muni.fi.pv168.project.business.model;

import java.util.UUID;

class UuidGuidProvider implements GuidProvider {

    @Override
    public String newGuid() {
        return UUID.randomUUID().toString();
    }
}
