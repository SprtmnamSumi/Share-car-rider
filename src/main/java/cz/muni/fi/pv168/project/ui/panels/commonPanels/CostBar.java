package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.entities.Currency;
import cz.muni.fi.pv168.project.ui.panels.CarRide.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CostBar extends JPanel {
    private final List<String> currencyModel = Arrays.stream(Currency.values()).map(Currency::name).toList();
    private final List<String> categories;

    public CostBar() {
        super(new FlowLayout(FlowLayout.CENTER));
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        categories = testDataGenerator.createTestCategories(10).stream().map(Category::getName).toList();

        //        // Set look
//                this.setMaximumSize(dimension);
        var x = new JTextField();


        var tf = new PlaceholderTextField();
        tf.setPlaceholder("Amount");
        tf.setPreferredSize(new Dimension(270, 30));
        this.add(tf);


        JComboBox<String> comboBox = new JComboBox<>();
        for (String item : currencyModel) {
            comboBox.addItem(item);
        }
        this.add(comboBox);

        var tf1 = new PlaceholderTextField();
        tf1.setPlaceholder("Rate");
        tf1.setPreferredSize(new Dimension(270, 30));
        this.add(tf1);


    }
}
