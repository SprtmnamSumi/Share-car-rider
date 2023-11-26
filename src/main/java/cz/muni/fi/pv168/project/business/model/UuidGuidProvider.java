package cz.muni.fi.pv168.project.business.model;

import com.google.inject.Singleton;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
class UuidGuidProvider implements GuidProvider {

    private final static AtomicInteger guidCounter = new AtomicInteger();

    @Override
    public String newGuid() {
        return String.valueOf(UUID.nameUUIDFromBytes(
                        Integer.toHexString(guidCounter.getAndIncrement()).getBytes()));
    }
}
