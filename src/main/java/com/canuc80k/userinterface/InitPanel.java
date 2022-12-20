package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import org.zeroturnaround.zip.ZipUtil;

import com.canuc80k.generator.Generator;
import com.canuc80k.theme.ThemeProperty;

public class InitPanel extends JPanel {
    private final int OPTION_NUMBERS = 4;

    private final int GENERATE_OPTION_INDEX = 0;
    private final int ZIP_OPTION_INDEX = 1;
    private final int CLEAR_OPTION_INDEX = 2;
    private final int CONFIG_OPTION_INDEX = 3;

    private JLabel topLabel;
    private List<String> options = new ArrayList<String>();

    InitPanel() {
        options.add("Generate test");
        options.add("Zip");
        options.add("Clear");
        options.add("Config");

        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(Color.decode("#94959b"));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        topLabel = new JLabel("What do you want to do?");
        topLabel.setAlignmentX(CENTER_ALIGNMENT);
        topLabel.setForeground(ThemeProperty.getFontColor());
        topLabel.setFont(new Font("Open Sans Bold", Font.PLAIN, 16));
        topLabel.setSize(new Dimension(HomeFrame.APP_WIDTH, 50));
        add(topLabel);

        add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(ThemeProperty.getTopPanelColor());
        optionsPanel.setLayout(new GridLayout(2, OPTION_NUMBERS / 2, 10, 10));
        add(optionsPanel);

        options.forEach(option -> {
            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new BorderLayout());
            optionPanel.setBackground(ThemeProperty.getSingleOptionPannelColor());
            optionsPanel.add(optionPanel);

            JLabel optionDescription = new JLabel(option, SwingConstants.CENTER);
            optionPanel.add(optionDescription, BorderLayout.CENTER);
            optionDescription.setAlignmentX(CENTER_ALIGNMENT);
            optionDescription.setFont(new Font("Open Sans Medium", Font.PLAIN, 14));
            optionDescription.setForeground(ThemeProperty.getFontColor());
            optionDescription.addMouseListener(new MouseInputListener() {
                public void mouseClicked(MouseEvent e) {
                    String choosedOption = ((JLabel) e.getSource()).getText();

                    HomeFrame currentFrame = (HomeFrame) SwingUtilities.windowForComponent(optionDescription);
                    if (choosedOption.equals(options.get(GENERATE_OPTION_INDEX))) {
                        currentFrame.setTopPanel(new GenerateTestPanel());
                    }
                    if (choosedOption.equals(options.get(ZIP_OPTION_INDEX))) {
                        File testcaseFile = new File(ConfigPanel.getConfigData().get(2));
                        File testcaseZipFile = new File(testcaseFile.getAbsolutePath() + ".zip");
                        ZipUtil.pack(testcaseFile, testcaseZipFile);
                    }
                    if (choosedOption.equals(options.get(CLEAR_OPTION_INDEX))) {
                        new Generator().clear();
                    }
                    if (choosedOption.equals(options.get(CONFIG_OPTION_INDEX))) {
                        currentFrame.setTopPanel(new ConfigPanel());
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