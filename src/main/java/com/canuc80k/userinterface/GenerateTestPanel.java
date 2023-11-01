package com.canuc80k.userinterface;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
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

    private JPanel testcaseDataPanel, advancedSettingPanel;
    private JLabel topLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel backtoHome;
    private JLabel advancedSettingLabel;
    private JLabel cplusplusVersionLabel;
    private JLabel timeOutLabel;

    private JTextField beginTestcaseIndexTextField;
    private JTextField endTestcaseIndexTextField;
    private JTextField timeOutTextField;

    private JButton generateButton;
    private JComboBox<String> cplusplusVersionComboBox;

    public GenerateTestPanel() {
        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(GlobalResource.getTheme().getTopPanelColor());
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BorderLayout());

        testcaseDataPanel = new JPanel();
        testcaseDataPanel.setPreferredSize(new Dimension(HomeFrame.APP_WIDTH / 2 - 20, HomeFrame.TOPPANEL_HEIGHT));
        testcaseDataPanel.setBackground(GlobalResource.getTheme().getTopPanelColor());
        testcaseDataPanel.setLayout(new BoxLayout(testcaseDataPanel, BoxLayout.Y_AXIS));
        add(testcaseDataPanel, BorderLayout.WEST);
        
        advancedSettingPanel = new JPanel();
        advancedSettingPanel.setPreferredSize(new Dimension(HomeFrame.APP_WIDTH / 2 - 20, HomeFrame.TOPPANEL_HEIGHT));
        advancedSettingPanel.setBackground(GlobalResource.getTheme().getTopPanelColor());
        advancedSettingPanel.setLayout(new BoxLayout(advancedSettingPanel, BoxLayout.Y_AXIS));
        add(advancedSettingPanel, BorderLayout.EAST);

        topLabel = new JLabel("Testcase index");
        topLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        topLabel.setForeground(GlobalResource.getTheme().getFontColor());
        topLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.APP_HEIGHT / 100 * 9));
        topLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.APP_HEIGHT / 100 * 9));
        topLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseDataPanel.add(topLabel);

        testcaseDataPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        fromLabel = new JLabel("From");
        fromLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        fromLabel.setForeground(GlobalResource.getTheme().getFontColor());
        fromLabel.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        fromLabel.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        fromLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseDataPanel.add(fromLabel);

        beginTestcaseIndexTextField = new JTextField();
        beginTestcaseIndexTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        beginTestcaseIndexTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        beginTestcaseIndexTextField.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        beginTestcaseIndexTextField.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        beginTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseDataPanel.add(beginTestcaseIndexTextField);

        toLabel = new JLabel("To");
        toLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        toLabel.setForeground(GlobalResource.getTheme().getFontColor());
        toLabel.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        toLabel.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseDataPanel.add(toLabel);

        endTestcaseIndexTextField = new JTextField();
        endTestcaseIndexTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        endTestcaseIndexTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        endTestcaseIndexTextField.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        endTestcaseIndexTextField.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        endTestcaseIndexTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseDataPanel.add(endTestcaseIndexTextField);

        testcaseDataPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        generateButton = new JButton("Run");
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.setBackground(GlobalResource.getTheme().getBackgroundColor());
        generateButton.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.SMALL));
        generateButton.setForeground(GlobalResource.getTheme().getFontHighLightColor());
        generateButton.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        generateButton.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
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
        testcaseDataPanel.add(generateButton);

        testcaseDataPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        backtoHome = new JLabel("<html><u><b>Back to Home</b></u></html>");
        backtoHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backtoHome.setForeground(Color.BLUE.darker().darker());
        backtoHome.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.SMALL));
        backtoHome.setMinimumSize(new Dimension(200, 45));
        backtoHome.setMaximumSize(new Dimension(200, 45));
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
        testcaseDataPanel.add(backtoHome);

        advancedSettingLabel = new JLabel("Advanced Setting");
        advancedSettingLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        advancedSettingLabel.setForeground(GlobalResource.getTheme().getFontColor());
        advancedSettingLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        advancedSettingLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        advancedSettingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(advancedSettingLabel);

        cplusplusVersionLabel = new JLabel("C++ Version");
        cplusplusVersionLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        cplusplusVersionLabel.setForeground(GlobalResource.getTheme().getFontColor());
        cplusplusVersionLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        cplusplusVersionLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        cplusplusVersionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(cplusplusVersionLabel);

        cplusplusVersionComboBox = new JComboBox<String>();
        cplusplusVersionComboBox.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        cplusplusVersionComboBox.setBackground(GlobalResource.getTheme().getBackgroundColor());
        cplusplusVersionComboBox.setForeground(GlobalResource.getTheme().getInverseFontColor());
        cplusplusVersionComboBox.setMinimumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        cplusplusVersionComboBox.setMaximumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        cplusplusVersionComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        cplusplusVersionComboBox.addItem("C++");
        cplusplusVersionComboBox.addItem("C++ 11");
        cplusplusVersionComboBox.addItem("C++ 14");
        cplusplusVersionComboBox.addItem("C++ 17");
        cplusplusVersionComboBox.addItem("C++ 20");
        cplusplusVersionComboBox.addItem("C++ 2a");
        advancedSettingPanel.add(cplusplusVersionComboBox);

        timeOutLabel = new JLabel("Timeout");
        timeOutLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        timeOutLabel.setForeground(GlobalResource.getTheme().getFontColor());
        timeOutLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        timeOutLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        timeOutLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(timeOutLabel);
   
        timeOutTextField = new JTextField();
        timeOutTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        timeOutTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        timeOutTextField.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        timeOutTextField.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        timeOutTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(timeOutTextField);
    }

    public void increaseDoneTestcase() {
        doneTestcase ++;
        if (totalTestcase == doneTestcase) {
            doneTestcase = totalTestcase = 0;
            generateButton.setText("RUN");
            
            String errorInformation = GlobalResource.getGenerator().getErrorInformation();
            if (errorInformation.length() == 0) {
                JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
                    "Mission accomplished",
                    "It's done, sir",
                    JOptionPane.NO_OPTION
                );
            } else {
                JOptionPane.showMessageDialog(
                    GlobalResource.getTopDialog(),
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