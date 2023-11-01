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

import com.canuc80k.constant.LanguageConstant;
import com.canuc80k.constant.OsConstant;
import com.canuc80k.font.FontSize;
import com.canuc80k.font.FontType;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.testcase.TestcaseFileNameType;
import com.canuc80k.validator.DirectoryValidator;
import com.canuc80k.validator.TestcaseDataValidator;

public class GenerateTestPanel extends JPanel {
    private int doneTestcase = 0;
    private int totalTestcase = 0;

    private JPanel testcaseDataPanel, advancedSettingPanel;
    private JLabel topLabel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel backtoHome;
    private JLabel advancedSettingLabel;
    private JLabel languageLabel;
    private JLabel timeoutLabel;
    private JLabel osLabel;

    private JTextField beginTestcaseIndexTextField;
    private JTextField endTestcaseIndexTextField;
    private JTextField timeoutTextField;

    private JComboBox<String> languageComboBox;
    private JComboBox<String> osComboBox;

    private JButton generateButton;
    
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
                if (doneTestcase == 0 && totalTestcase == 0) {
                    TestcaseFileNameType type = TestcaseFileNameType.NORMAL;
                    String beginIndex = beginTestcaseIndexTextField.getText().trim();
                    String endIndex = endTestcaseIndexTextField.getText().trim();
                    String os = (String) osComboBox.getSelectedItem();
                    String language = (String) languageComboBox.getSelectedItem();
                    String timeout = timeoutTextField.getText().trim();

                    if (!DirectoryValidator.validateConfigFiles()) return;
                    if (!TestcaseDataValidator.validateIndex(beginIndex, endIndex)) return;
                    if (!TestcaseDataValidator.validateTimeout(timeout)) return;

                    if (beginIndex.length() == endIndex.length()) 
                        type = TestcaseFileNameType.LEXICOGRAPHICAL_ORDER;

                    try {
                        GlobalResource.getGenerator().generate(
                            Integer.parseInt(beginIndex), 
                            Integer.parseInt(endIndex), 
                            type, 
                            endIndex.length(),
                            os,
                            language,
                            Integer.parseInt(timeout)
                        );
                    } catch (NumberFormatException | IOException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("Stop");
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

        languageLabel = new JLabel("Language");
        languageLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        languageLabel.setForeground(GlobalResource.getTheme().getFontColor());
        languageLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        languageLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        languageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(languageLabel);

        languageComboBox = new JComboBox<String>();
        languageComboBox.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        languageComboBox.setBackground(GlobalResource.getTheme().getBackgroundColor());
        languageComboBox.setForeground(GlobalResource.getTheme().getInverseFontColor());
        languageComboBox.setMinimumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        languageComboBox.setMaximumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        languageComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (int i = 0; i < LanguageConstant.LANGUAGE.length; i ++)
            languageComboBox.addItem(LanguageConstant.LANGUAGE[i]);
        advancedSettingPanel.add(languageComboBox);

        timeoutLabel = new JLabel("Timeout");
        timeoutLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        timeoutLabel.setForeground(GlobalResource.getTheme().getFontColor());
        timeoutLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        timeoutLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        timeoutLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(timeoutLabel);
   
        timeoutTextField = new JTextField();
        timeoutTextField.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        timeoutTextField.setForeground(GlobalResource.getTheme().getInverseFontColor());
        timeoutTextField.setMinimumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        timeoutTextField.setMaximumSize(new Dimension(100, HomeFrame.APP_HEIGHT / 100 * 9));
        timeoutTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(timeoutTextField);
        
        osLabel = new JLabel("OS");
        osLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        osLabel.setForeground(GlobalResource.getTheme().getFontColor());
        osLabel.setMinimumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        osLabel.setMaximumSize(new Dimension(200, HomeFrame.APP_HEIGHT / 100 * 9));
        osLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        advancedSettingPanel.add(osLabel);

        osComboBox = new JComboBox<String>();
        osComboBox.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, FontSize.MEDIUM));
        osComboBox.setBackground(GlobalResource.getTheme().getBackgroundColor());
        osComboBox.setForeground(GlobalResource.getTheme().getInverseFontColor());
        osComboBox.setMinimumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        osComboBox.setMaximumSize(new Dimension(120, HomeFrame.APP_HEIGHT / 100 * 9));
        osComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (int i = 0; i < OsConstant.OS.length; i ++)
            osComboBox.addItem(OsConstant.OS[i]);
        advancedSettingPanel.add(osComboBox);
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