package cz.muni.fi.pv168.project.business.service.export;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.export.BatchExporterCarRideJSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static cz.muni.fi.pv168.project.business.service.statistics.CarRideStatisticsUnitTests.createCarRideThree;
import static cz.muni.fi.pv168.project.business.service.statistics.CarRideStatisticsUnitTests.createCarRideTwo;
import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("ALL")
class BatchExporterUnitTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));
    private final BatchExporterCarRideJSON batchExporterCarRideJSON = new BatchExporterCarRideJSON();
    private final Path exportFilePath = TEST_RESOURCES
            .resolve("output")
            .resolve(Instant.now().toString().replace(':', '_') + ".json");

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(exportFilePath)) {
            Files.delete(exportFilePath);
        }
    }

    @Test
    void exportEmpty() throws IOException {
        List<CarRide> carRides = new ArrayList<>();
        batchExporterCarRideJSON.exportData(carRides, exportFilePath.toString());

        assertExportedContent(
                """
                        {"carrides": []}
                        """
        );
    }

    @Test
    void exportSingleCarRide() throws IOException {
        List<CarRide> carRides = new ArrayList<>();
        carRides.add(createCarRideTwo("c-2"));

        batchExporterCarRideJSON.exportData(carRides, exportFilePath.toString());

        assertExportedContent(
                """
                        {"carrides": [{
                          "date": "2001-01-01T11:00:00",
                          "passengers": 2,
                          "cost_of_fuel_per_litre": 35,
                          "distance": 80,
                          "guid": "c-2",
                          "description": "skvela jizda",
                          "fuel_consumption": 20,
                          "commission": 0.1,
                          "currency": {
                            "symbol": "Kc",
                            "name": "Czech crown",
                            "guid": "2",
                            "rate_to_dollar": 22.3
                          },
                          "title": "Porsak",
                          "category": {
                            "color": 7,
                            "name": "Blue",
                            "guid": "1"
                          }
                        }]}
                            """
        );
    }

    @Test
    void exportMultipleCarRides() throws IOException {
        List<CarRide> carRides = new ArrayList<>();
        carRides.add(createCarRideTwo("c-2"));
        carRides.add(createCarRideThree("c-3"));

        batchExporterCarRideJSON.exportData(carRides, exportFilePath.toString());

        assertExportedContent(
                """
                        {"carrides": [
                          {
                            "date": "2001-01-01T11:00:00",
                            "passengers": 2,
                            "cost_of_fuel_per_litre": 35,
                            "distance": 80,
                            "guid": "c-2",
                            "description": "skvela jizda",
                            "fuel_consumption": 20,
                            "commission": 0.1,
                            "currency": {
                              "symbol": "Kc",
                              "name": "Czech crown",
                              "guid": "2",
                              "rate_to_dollar": 22.3
                            },
                            "title": "Porsak",
                            "category": {
                              "color": 7,
                              "name": "Blue",
                              "guid": "1"
                            }
                          },
                          {
                            "date": "2001-01-01T11:00:00",
                            "passengers": 2,
                            "cost_of_fuel_per_litre": 37,
                            "distance": 16,
                            "guid": "c-3",
                            "description": "skvela jizda",
                            "fuel_consumption": 6,
                            "commission": 0.17,
                            "currency": {
                              "symbol": "$",
                              "name": "USD",
                              "guid": "2",
                              "rate_to_dollar": 1
                            },
                            "title": "Skoda",
                            "category": {
                              "color": 7,
                              "name": "Blue",
                              "guid": "1"
                            }
                          }
                        ]}
                            """
        );
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}