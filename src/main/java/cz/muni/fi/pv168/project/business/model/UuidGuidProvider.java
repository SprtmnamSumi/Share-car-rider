package cz.muni.fi.pv168.project.business.model;

import com.google.inject.Singleton;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton

class UuidGuidProvider implements GuidProvider {

    @Override
    public String newGuid() {
        return UUID.randomUUID().toString(); // TODO can be error
    }
}

