package cz.muni.fi.pv168.project.ui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sabrina Orálková, 525089
 */
public class InfoDialog extends JDialog {
    private final String title = "Info";

    private String btnString1 = "Hmm, interesting";

    public InfoDialog(Frame aFrame, String aWord) {
        super(aFrame, true);
        setTitle(title);

        String msgString = "This is just a test, don't bite me";
        JLabel messageLabel = new JLabel(msgString);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = new JButton(btnString1);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        okButton.setBounds(120,150,60,30);

        //add(messageLabel, BorderLayout.CENTER);
        //add(okButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void clearAndHide() {
        setVisible(false);
    }
}
