package cz.muni.fi.pv168.project.business.model;

import com.google.inject.Singleton;
import java.util.UUID;

@Singleton
public class UuidGuidProvider implements GuidProvider {

    @Override
    public String newGuid() {
        return UUID.randomUUID().toString(); // TODO can be error
    }
}

