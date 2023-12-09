package cz.muni.fi.pv168.project;

import com.google.inject.Injector;
import cz.muni.fi.pv168.project.business.service.properties.Config;
import cz.muni.fi.pv168.project.data.Initializer;
import cz.muni.fi.pv168.project.ui.MainWindow;
import cz.muni.fi.pv168.project.ui.action.NuclearQuitAction;
import cz.muni.fi.pv168.project.ui.action.QuitAction;
import cz.muni.fi.pv168.project.ui.theme.ColorTheme;
import java.awt.EventQueue;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.tinylog.Logger;

import static cz.muni.fi.pv168.project.wiring.Injector.getInjector;

public class Main {
    private static Injector _injector;

    public static void main(String[] args) {
        _injector = getInjector();
        Config.tryCreateProperties();
        initLookAndFeel();
//        fillDatabaseWithTestData();

        EventQueue.invokeLater(() -> {
            try {
                EventQueue.invokeLater(() -> _injector.getInstance(MainWindow.class).show());
            } catch (Exception ex) {
                Logger.error("Application crashed on startup. Reason: " + ex);
                showInitializationFailedDialog(ex);
            }
        });
    }

    public static void initLookAndFeel() {
        Properties properties = Config.loadProperties();
        String lookAndFeelsClassName = properties.getProperty(
                Config.PropertiesEnum.COLOR_THEME_PROPERY.toString()).equals(ColorTheme.LIGHT.name())
                ? "javax.swing.plaf.nimbus.NimbusLookAndFeel" : "com.jtattoo.plaf.noire.NoireLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeelsClassName);
        } catch (Exception ex) {
            Logger.error(lookAndFeelsClassName + " layout initialization failed. Reason: " + ex);
        }
    }

    private static void fillDatabaseWithTestData() {
        try {
            _injector.getInstance(Initializer.class).initialize(15);
        } catch (Exception ex) {
            Logger.warn("Database filling failed. Reason: " + ex);
        }
    }

    private static void showInitializationFailedDialog(Exception ex) {
        EventQueue.invokeLater(() -> {
            Object[] options = {
                    new JButton(new QuitAction()),
                    new JButton(new NuclearQuitAction())
            };
            JOptionPane.showOptionDialog(
                    null,
                    "Application initialization failed.\nWhat do you want to do?",
                    "Initialization Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]
            );
        });
    }
}
