package com.canuc80k.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import com.canuc80k.font.FontSize;
import com.canuc80k.font.FontType;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;
import com.canuc80k.validator.DirectoryValidator;
import com.canuc80k.validator.TestcaseIndexValidator;

public class GenerateTestPanel extends JPanel {
    private int doneTestcase = 0;
    private int totalTestcase = 0;

    private JLabel topLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel backtoHome;

    private JTextField beginTestcaseIndexTextField;
    private JTextField endTestcaseIndexTextField;

    private JButton generateButton;

    public GenerateTestPanel() {
        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(GlobalResource.getTheme().getTopPanelColor());
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        topLabel = new JLabel("Testcase index");
        topLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        topLabel.setForeground(GlobalResource.getTheme().getFontColor());
        topLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        topLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(topLabel);

        fromLabel = new JLabel("From");
        fromLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        fromLabel.setForeground(GlobalResource.getTheme().getFontColor());
        fromLabel.setMinimumSize(new Dimension(100, 50));
        fromLabel.setMaximumSize(new Dimension(100, 50));
        fromLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(fromLabel);

        beginTestcaseIndexTextField = new JTextField();
        beginTestcaseIndexTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        beginTestcaseIndexTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        beginTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        beginTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(beginTestcaseIndexTextField);

        toLabel = new JLabel("To");
        toLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        toLabel.setForeground(GlobalResource.getTheme().getFontColor());
        toLabel.setMinimumSize(new Dimension(100, 50));
        toLabel.setMaximumSize(new Dimension(100, 50));
        toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(toLabel);

        endTestcaseIndexTextField = new JTextField();
        endTestcaseIndexTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        endTestcaseIndexTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        endTestcaseIndexTextField.setMinimumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setMaximumSize(new Dimension(100, 50));
        endTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(endTestcaseIndexTextField);

        add(Box.createRigidArea(new Dimension(0, 30)));

        generateButton = new JButton("Run");
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.setBackground(GlobalResource.getTheme().getBackgroundColor());
        generateButton.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.SMALL));
        generateButton.setForeground(GlobalResource.getTheme().getFontHighLightColor());
        generateButton.setMinimumSize(new Dimension(100, 50));
        generateButton.setMaximumSize(new Dimension(100, 50));
        generateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doneTestcase == totalTestcase) {
                    TestcaseFileNameType type = TestcaseFileNameType.NORMAL;
                    String beginIndex = beginTestcaseIndexTextField.getText().trim();
                    String endIndex = endTestcaseIndexTextField.getText().trim();

                    if (!DirectoryValidator.validateConfigFiles())
                        return;
                    if (!TestcaseIndexValidator.validate(beginIndex, endIndex))
                        return;

                    if (beginIndex.length() == endIndex.length()) {
                        type = TestcaseFileNameType.LEXICOGRAPHICAL_ORDER;
                    }

                    try {
                        GlobalResource.getGenerator().generate(Integer.parseInt(beginIndex), Integer.parseInt(endIndex), type,
                                endIndex.length());
                    } catch (NumberFormatException | IOException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        add(generateButton);

        add(Box.createRigidArea(new Dimension(0, 20)));

        backtoHome = new JLabel("<html><u><b>Back to Home</b></u></html>");
        backtoHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backtoHome.setForeground(Color.BLUE.darker());
        backtoHome.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.SMALL));
        backtoHome.setMinimumSize(new Dimension(100, 45));
        backtoHome.setMaximumSize(new Dimension(100, 45));
        backtoHome.setAlignmentX(Component.LEFT_ALIGNMENT);
        backtoHome.addMouseListener(new MouseInputListener() {
            public void mouseClicked(MouseEvent e) {
                HomeFrame currentFrame = (HomeFrame) SwingUtilities.windowForComponent(backtoHome);
                currentFrame.setTopPanel(GlobalResource.getInitPanel());
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

    public void increaseDoneTestcase() {
        doneTestcase ++;
        if (totalTestcase == doneTestcase) {
            doneTestcase = totalTestcase = 0;
            generateButton.setText("RUN");
            
            String errorInformation = GlobalResource.getGenerator().getErrorInformation();
            if (errorInformation.length() == 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Mission accomplished",
                    "It's done, sir",
                    JOptionPane.NO_OPTION
                );
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    errorInformation + " has occured in generate test process, error testcases have been deleted, check your testcase folder",
                    "Check your testcase generator files",
                    JOptionPane.NO_OPTION
                );
            }
            return;
        }
        generateButton.setText(doneTestcase + "/" + totalTestcase);
    }

    public void setTotalTestcase(int newTotalTestcase) {
        totalTestcase = newTotalTestcase;
        generateButton.setText(0 + "/" + totalTestcase);
    }

    public synchronized void setGenerateButtonText(String text) {
        generateButton.setText(text);
    }
}