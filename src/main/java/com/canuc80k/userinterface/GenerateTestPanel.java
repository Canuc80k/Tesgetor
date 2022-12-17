package com.canuc80k.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import com.canuc80k.generator.Generator;

class GenerateTestPanel extends JPanel {
    private JLabel topLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel backtoHome;

    private JTextField beginTestcaseIndexTextField;
    private JTextField endTestcaseIndexTextField;

    private JButton generateButton;

    GenerateTestPanel() {
        buildUI();
    }

    private void buildUI() {
        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(Color.decode("#d7def8"));
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        topLabel = new JLabel("Testcase index:");
        topLabel.setMinimumSize(new Dimension(100, 50));
        topLabel.setMaximumSize(new Dimension(100, 50));
        topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(topLabel);

        fromLabel = new JLabel("From");
        fromLabel.setMinimumSize(new Dimension(100, 50));
        fromLabel.setMaximumSize(new Dimension(100, 50));
        fromLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(fromLabel);

        beginTestcaseIndexTextField = new JTextField();
        beginTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(beginTestcaseIndexTextField);

        toLabel = new JLabel("To");
        toLabel.setMinimumSize(new Dimension(100, 50));
        toLabel.setMaximumSize(new Dimension(100, 50));
        toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(toLabel);

        endTestcaseIndexTextField = new JTextField();
        endTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(endTestcaseIndexTextField);

        add(Box.createRigidArea(new Dimension(0, 30)));

        generateButton = new JButton();
        generateButton.setMinimumSize(new Dimension(100, 50));
        generateButton.setMaximumSize(new Dimension(100, 50));
        generateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateButton.addActionListener(e -> {
            String beginIndex = beginTestcaseIndexTextField.getText().trim(); 
            String endIndex = endTestcaseIndexTextField.getText().trim();

            try {
                new Generator().generate(Integer.parseInt(beginIndex), Integer.parseInt(endIndex));
            } catch (NumberFormatException | IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        add(generateButton);

        backtoHome = new JLabel("Back to Home");
        backtoHome.setMinimumSize(new Dimension(100, 50));
        backtoHome.setMaximumSize(new Dimension(100, 50));
        backtoHome.setAlignmentX(Component.LEFT_ALIGNMENT);
        backtoHome.addMouseListener(new MouseInputListener() {
            public void mouseClicked(MouseEvent e) {
                HomeFrame currentFrame = (HomeFrame) SwingUtilities.windowForComponent(backtoHome);
                currentFrame.setTopPanel(new InitPanel());
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseDragged(MouseEvent e) {}
            public void mouseMoved(MouseEvent e) {}
        });
        add(backtoHome);
    }
}