package cz.muni.fi.pv168.project.business.service.properties;

import cz.muni.fi.pv168.project.Main;
import cz.muni.fi.pv168.project.ui.theme.ColorTheme;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private final static String PROPERTIES_FILE = "src/main/resources/properties/config.properties";

    public static void tryCreateProperties() {
        File f = new File(PROPERTIES_FILE);
        if(f.exists() && !f.isDirectory()) {
           return;
        }

        Properties properties = new Properties();
        properties.setProperty("mode", ColorTheme.LIGHT.toString());

        saveProperties(properties);
    }

    public static void saveProperties(Properties properties) {
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(output, null);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, "Failed to save properties", ex);
        }
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, "Failed to load properties", ex);
        }

        return properties;
    }

    public static void changeColorThemeProperty() {
        Properties properties = loadProperties();

        if (properties.getProperty("mode").equals(ColorTheme.LIGHT.name())) {
            properties.setProperty("mode", ColorTheme.DARK.name());
        } else {
            properties.setProperty("mode", ColorTheme.LIGHT.name());
        }

        saveProperties(properties);
    }
}
