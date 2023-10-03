package cz.muni.fi.pv168.project;

import cz.muni.fi.pv168.project.gui.MainFrame;

import javax.swing.*;

/**
 * The entry point of the application.
 */
public class Main {
    private Main() {
        throw new AssertionError("This class is not intended for instantiation.");
    }

    public static void main(String[] args){
        setUIStyle();
        JFrame frame = new MainFrame();
        frame.setTitle("Share Car Rider");
        frame.setVisible(true);
    }

    private static void setUIStyle(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(UnsupportedLookAndFeelException| ClassNotFoundException| InstantiationException|  IllegalAccessException e){
            System.err.println("Unsupported Look and Feel");
        }
    }
}
