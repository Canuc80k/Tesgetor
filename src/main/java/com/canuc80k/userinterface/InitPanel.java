package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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

import com.canuc80k.font.FontSize;
import com.canuc80k.font.FontType;
import com.canuc80k.generator.CPPGenerator;
import com.canuc80k.launcher.GlobalResource;
import com.canuc80k.validator.DirectoryValidator;

public class InitPanel extends JPanel {
    private final int OPTION_NUMBERS = 4;

    private final int GENERATE_OPTION_INDEX = 0;
    private final int ZIP_OPTION_INDEX = 1;
    private final int CLEAR_OPTION_INDEX = 2;
    private final int CONFIG_OPTION_INDEX = 3;

    private JLabel topLabel;
    private List<String> options = new ArrayList<String>();

    public InitPanel() {
        options.add("Generate test");
        options.add("Zip");
        options.add("Clear");
        options.add("Config");

        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(Color.decode("#94959b"));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        topLabel = new JLabel("What do u wanna do");
        topLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLabel.setForeground(GlobalResource.getTheme().getFontColor());
        topLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.LARGE));
        topLabel.setSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        add(topLabel);

        add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(GlobalResource.getTheme().getTopPanelColor());
        optionsPanel.setLayout(new GridLayout(2, OPTION_NUMBERS / 2, 10, 10));
        add(optionsPanel);

        options.forEach(option -> {
            JPanel optionPanel = new JPanel();
            optionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            optionPanel.setLayout(new BorderLayout());
            optionPanel.setBackground(GlobalResource.getTheme().getSingleOptionPannelColor());
            optionsPanel.add(optionPanel);

            JLabel optionDescription = new JLabel(option, SwingConstants.CENTER);
            optionPanel.add(optionDescription, BorderLayout.CENTER);
            optionDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
            optionDescription.setFont(GlobalResource.getExtendedFont().getFont(FontType.MEDIUM, 20));
            optionDescription.setForeground(GlobalResource.getTheme().getFontColor());
            optionDescription.addMouseListener(new MouseInputListener() {
                public void mouseClicked(MouseEvent e) {
                    String choosedOption = ((JLabel) e.getSource()).getText();

                    HomeFrame currentFrame = (HomeFrame) SwingUtilities.windowForComponent(optionDescription);
                    if (choosedOption.equals(options.get(GENERATE_OPTION_INDEX))) {
                        currentFrame.setTopPanel(GlobalResource.getGenerateTestPanel());
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
                        File testcaseFile = new File(GlobalResource.getConfigData().get(GlobalResource.TESTCASE_FOLDER_INDEX));
                        File testcaseZipFile = new File(testcaseFile.getAbsolutePath() + ".zip");
                        ZipUtil.pack(testcaseFile, testcaseZipFile);
                        JOptionPane.showMessageDialog(
                            GlobalResource.getTopDialog(),
                            "Mission accomplished",
                            "It's done, sir",
                            JOptionPane.NO_OPTION
                        );
                    }
                    if (choosedOption.equals(options.get(CLEAR_OPTION_INDEX))) {
                        int answer = JOptionPane.showConfirmDialog(
                            GlobalResource.getTopDialog(),
                            "Wanna clear old tests in test folder and delete old zip testcase folder?",
                            "Ask again to be sure .-.",
                            JOptionPane.YES_NO_CANCEL_OPTION
                        );
                        if (answer != JOptionPane.YES_OPTION) return;
                        new CPPGenerator().clear();
                        JOptionPane.showMessageDialog(
                            GlobalResource.getTopDialog(),
                            "Mission accomplished",
                            "It's done, sir",
                            JOptionPane.NO_OPTION
                        );
                    }
                    if (choosedOption.equals(options.get(CONFIG_OPTION_INDEX))) {
                        currentFrame.setTopPanel(GlobalResource.getConfigPanel());
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
}