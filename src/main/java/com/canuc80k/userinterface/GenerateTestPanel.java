package com.canuc80k.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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

import com.canuc80k.constant.TestcaseFileNameType;
import com.canuc80k.generator.Generator;
import com.canuc80k.theme.ThemeProperty;
import com.canuc80k.validator.DirectoryValidator;
import com.canuc80k.validator.TestcaseIndexValidator;

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
        setBackground(ThemeProperty.getTopPanelColor());
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        topLabel = new JLabel("Testcase index");
        topLabel.setFont(new Font("Open Sans Bold", Font.PLAIN, 14));
        topLabel.setForeground(ThemeProperty.getFontColor());
        topLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        topLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(topLabel);

        fromLabel = new JLabel("From");
        fromLabel.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
        fromLabel.setForeground(ThemeProperty.getFontColor());
        fromLabel.setMinimumSize(new Dimension(100, 50));
        fromLabel.setMaximumSize(new Dimension(100, 50));
        fromLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(fromLabel);

        beginTestcaseIndexTextField = new JTextField();
        beginTestcaseIndexTextField.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
        beginTestcaseIndexTextField.setForeground(ThemeProperty.getInverseFontColor());
        beginTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(beginTestcaseIndexTextField);

        toLabel = new JLabel("To");
        toLabel.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
        toLabel.setForeground(ThemeProperty.getFontColor());
        toLabel.setMinimumSize(new Dimension(100, 50));
        toLabel.setMaximumSize(new Dimension(100, 50));
        toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(toLabel);

        endTestcaseIndexTextField = new JTextField();
        endTestcaseIndexTextField.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
        endTestcaseIndexTextField.setForeground(ThemeProperty.getInverseFontColor());
        endTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(endTestcaseIndexTextField);

        add(Box.createRigidArea(new Dimension(0, 30)));

        generateButton = new JButton("Run");
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.setBackground(ThemeProperty.getBackgroundColor());
        generateButton.setFont(new Font("Open Sans Bold", Font.PLAIN, 12));
        generateButton.setForeground(ThemeProperty.getFontHighLightColor());
        generateButton.setMinimumSize(new Dimension(100, 50));
        generateButton.setMaximumSize(new Dimension(100, 50));
        generateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateButton.addActionListener(e -> {
            TestcaseFileNameType type = TestcaseFileNameType.NORMAL;
            String beginIndex = beginTestcaseIndexTextField.getText().trim();
            String endIndex = endTestcaseIndexTextField.getText().trim();

            if (!DirectoryValidator.validateConfigFiles()) return;
            if (!TestcaseIndexValidator.validate(beginIndex, endIndex)) return;

            if (beginIndex.length() == endIndex.length()) {
                type = TestcaseFileNameType.LEXICOGRAPHICAL_ORDER;
            }

            try {
                new Generator().generate(Integer.parseInt(beginIndex), Integer.parseInt(endIndex), type, endIndex.length());
            } catch (NumberFormatException | IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        add(generateButton);

        add(Box.createRigidArea(new Dimension(0, 20)));

        backtoHome = new JLabel("<html><u><b>Back to Home</b></u></html>");
        backtoHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backtoHome.setForeground(Color.BLUE.darker());
        backtoHome.setFont(new Font("Open Sans Bold", Font.PLAIN, 12));
        backtoHome.setMinimumSize(new Dimension(100, 45));
        backtoHome.setMaximumSize(new Dimension(100, 45));
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