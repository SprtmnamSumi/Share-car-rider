package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;

public class NameValuePanel extends JPanel {
    private final static Font boldFont = new JLabel().getFont().deriveFont(Font.BOLD);
    JLabel name = new JLabel();
    JLabel value = new JLabel();

    public NameValuePanel(String name) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.name.setText(name);
        value.setFont(boldFont);
        this.add(this.name);
        this.add(value);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }
}
