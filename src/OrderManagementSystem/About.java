/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderManagementSystem;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Velasco
 */
public class About extends JDialog {

    private JLabel iconScreenshot, infoLbl;
    private JButton closeBtn;
    private JPanel panel1, panel2;

    public About(JFrame owner) {

        super(owner, "About", true);

        ImageIcon icon = new ImageIcon("media/screenshot.png");
        iconScreenshot = new JLabel("", icon, JLabel.CENTER);
        closeBtn = new JButton("Close");
        infoLbl = new JLabel("<html>Created by: Velasco Paola<br/>A.M: cs161020<br/>"
                + "Creation Date: 25.05.2020 - 01:00AM <br/> Completed Date: 28.05.2020 - 10:00AM </html>", SwingConstants.CENTER);
        infoLbl.setBorder(BorderFactory.createLoweredBevelBorder());
        infoLbl.setFont(new Font("Courier", Font.BOLD, 18));

    }

    public void prepareUI() {

        panel1 = new JPanel(new GridLayout(2, 1));
        panel1.add(iconScreenshot);
        panel1.add(infoLbl);
        this.add(panel1);

        panel2 = new JPanel();
        panel2.add(closeBtn);
        this.add(panel2, BorderLayout.SOUTH);

        // Event Handling for closeBtn - anonymous inner class
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // closing the JDialog without closing the main JFrame
            }
        });

    }

    public void initUI() {

        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
