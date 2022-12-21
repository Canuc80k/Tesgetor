package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import org.zeroturnaround.zip.ZipUtil;

import com.canuc80k.generator.Generator;
import com.canuc80k.theme.ThemeProperty;
import com.canuc80k.validator.DirectoryValidator;

public class InitPanel {
    private static JPanel initPanel;
    private final static int OPTION_NUMBERS = 4;

    private final static int GENERATE_OPTION_INDEX = 0;
    private final static int ZIP_OPTION_INDEX = 1;
    private final static int CLEAR_OPTION_INDEX = 2;
    private final static int CONFIG_OPTION_INDEX = 3;

    private static JLabel topLabel;
    private static List<String> options = new ArrayList<String>();

    public static void createInitPanel() {
        initPanel = new JPanel();
        options.add("Generate test");
        options.add("Zip");
        options.add("Clear");
        options.add("Config");

        initPanel.setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        initPanel.setBackground(Color.decode("#94959b"));
        initPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        initPanel.setLayout(new BoxLayout(initPanel, BoxLayout.Y_AXIS));
        topLabel = new JLabel("What do u wanna do");
        topLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLabel.setForeground(ThemeProperty.getFontColor());
        topLabel.setFont(new Font("Open Sans Bold", Font.PLAIN, 16));
        topLabel.setSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        initPanel.add(topLabel);

        initPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(ThemeProperty.getTopPanelColor());
        optionsPanel.setLayout(new GridLayout(2, OPTION_NUMBERS / 2, 10, 10));
        initPanel.add(optionsPanel);

        options.forEach(option -> {
            JPanel optionPanel = new JPanel();
            optionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            optionPanel.setLayout(new BorderLayout());
            optionPanel.setBackground(ThemeProperty.getSingleOptionPannelColor());
            optionsPanel.add(optionPanel);

            JLabel optionDescription = new JLabel(option, SwingConstants.CENTER);
            optionPanel.add(optionDescription, BorderLayout.CENTER);
            optionDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
            optionDescription.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
            optionDescription.setForeground(ThemeProperty.getFontColor());
            optionDescription.addMouseListener(new MouseInputListener() {
                public void mouseClicked(MouseEvent e) {
                    String choosedOption = ((JLabel) e.getSource()).getText();

                    HomeFrame currentFrame = (HomeFrame) SwingUtilities.windowForComponent(optionDescription);
                    if (choosedOption.equals(options.get(GENERATE_OPTION_INDEX))) {
                        currentFrame.setTopPanel(GenerateTestPanel.getGenerateTestPanel());
                    }
                    if (choosedOption.equals(options.get(ZIP_OPTION_INDEX))) {
                        int answer = JOptionPane.showConfirmDialog(
                            currentFrame, 
                            "Wanna zip test folder?",
                            "Ask again to be sure .-.",
                            JOptionPane.YES_NO_CANCEL_OPTION
                        );
                        if (answer != JOptionPane.YES_OPTION) return;

                        if (!DirectoryValidator.validateTestcaseFiles()) return;
                        File testcaseFile = new File(ConfigPanel.getConfigData().get(2));
                        File testcaseZipFile = new File(testcaseFile.getAbsolutePath() + ".zip");
                        ZipUtil.pack(testcaseFile, testcaseZipFile);
                        JOptionPane.showMessageDialog(
                            null,
                            "Mission accomplished",
                            "It's done, sir",
                            JOptionPane.NO_OPTION
                        );
                    }
                    if (choosedOption.equals(options.get(CLEAR_OPTION_INDEX))) {
                        int answer = JOptionPane.showConfirmDialog(
                            null, 
                            "Wanna clear old tests in test folder and delete old zip testcase folder?",
                            "Ask again to be sure .-.",
                            JOptionPane.YES_NO_CANCEL_OPTION
                        );
                        if (answer != JOptionPane.YES_OPTION) return;
                        new Generator().clear();
                        JOptionPane.showMessageDialog(
                            null,
                            "Mission accomplished",
                            "It's done, sir",
                            JOptionPane.NO_OPTION
                        );
                    }
                    if (choosedOption.equals(options.get(CONFIG_OPTION_INDEX))) {
                        currentFrame.setTopPanel(ConfigPanel.getConfigPanel());
                    }
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseDragged(MouseEvent e) {
                }

                public void mouseMoved(MouseEvent e) {
                }

            });
        });
    }

    public static JPanel getInitPanel() {
        if (initPanel == null) createInitPanel();
        return initPanel;
    }
}