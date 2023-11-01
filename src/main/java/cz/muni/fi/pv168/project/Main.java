package cz.muni.fi.pv168.project;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cz.muni.fi.pv168.project.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Injector _injector;

    public static void main(String[] args) {
        _injector = getInjector();
        initNimbusLookAndFeel();
        EventQueue.invokeLater(() -> _injector.getInstance(MainWindow.class).show());
    }

    private static Injector getInjector(){
        return Guice.createInjector(List.of(
                new cz.muni.fi.pv168.project.business.model.Module(),
                new cz.muni.fi.pv168.project.ui.Module(),
                new cz.muni.fi.pv168.project.business.service.validation.Module(),
                new cz.muni.fi.pv168.project.business.service.crud.Module(),
                new cz.muni.fi.pv168.project.business.repository.Module(),
                new cz.muni.fi.pv168.project.ui.model.Module(),
                new cz.muni.fi.pv168.project.ui.action.Module()));
    }

    private static void initNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Nimbus layout initialization failed", ex);
        }
    }
}
