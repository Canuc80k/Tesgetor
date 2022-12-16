package com.canuc80k.userinterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

public class MainPanel extends JPanel {
    private JLabel topLabel;
    private List<String> options = new ArrayList<>();

    MainPanel() {
        options.add("Generate test");
        options.add("Zip");
        options.add("Clear");

		setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        topLabel = new JLabel("What do you want to do?");
        topLabel.setFont(new Font("Open Sans Bold", Font.PLAIN, 14));
        topLabel.setSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        add(topLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        options.forEach(option -> {
            JLabel optionDescription = new JLabel(option);
            optionDescription.setFont(new Font("Open Sans Regular", Font.PLAIN, 14));
            optionDescription.setSize(new Dimension(HomeFrame.APP_WIDTH, 50));
            optionDescription.addMouseListener(new MouseInputListener() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getSource());
                }
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                public void mouseDragged(MouseEvent e) {}
                public void mouseMoved(MouseEvent e) {}
                
            });
            this.add(optionDescription);
        });
    }
}