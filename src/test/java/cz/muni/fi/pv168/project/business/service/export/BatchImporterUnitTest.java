package cz.muni.fi.pv168.project.business.service.export;

import cz.muni.fi.pv168.project.export.BatchImporterCarRideJSON;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

import static cz.muni.fi.pv168.project.business.service.statistics.CarRideStatisticsUnitTests.createCarRideThree;
import static cz.muni.fi.pv168.project.business.service.statistics.CarRideStatisticsUnitTests.createCarRideTwo;
import static org.assertj.core.api.Assertions.assertThat;


class BatchImporterUnitTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    private final BatchImporterCarRideJSON batchImporterCarRideJSON = new BatchImporterCarRideJSON();

    @Test
    void importNoCarRides() {
        Path importFilePath = TEST_RESOURCES.resolve("empty.json");
        var carRides = batchImporterCarRideJSON.importData(importFilePath);

        assertThat(carRides).isEmpty();
    }

    @Test
    void singleCarRide() {
        Path importFilePath = TEST_RESOURCES.resolve("single-carride.json");
        var carRides = batchImporterCarRideJSON.importData(importFilePath);

        assertThat(carRides)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        createCarRideTwo("124")
                );
    }

    @Test
    void multipleCarRides() {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-carrides.json");
        var carRides = batchImporterCarRideJSON.importData(importFilePath);

        assertThat(carRides)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        createCarRideTwo("c-2"),
                        createCarRideThree("c-3")
                );
    }
}