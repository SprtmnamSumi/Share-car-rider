package cz.muni.fi.pv168.project;

import cz.muni.fi.pv168.project.gui.MainFrame;
import cz.muni.fi.pv168.project.gui.toolbar.MainToolBar;

import javax.swing.*;
import java.awt.*;

/**
 * The entry point of the application.
 */
public class Main {

    private Main() {
        throw new AssertionError("This class is not intended for instantiation.");
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        JFrame frame = new MainFrame();
        frame.setTitle("Share Car Rider");
        frame.setVisible(true);
    }
}
