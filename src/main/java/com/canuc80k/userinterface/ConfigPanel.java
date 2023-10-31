package com.canuc80k.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.canuc80k.font.FontSize;
import com.canuc80k.font.FontType;
import com.canuc80k.launcher.GlobalResource;

public class ConfigPanel extends JPanel {
    private JButton inputGeneratorFileButton;
    private JButton outputGeneratorFileButton;
    private JButton testcaseFolderButton;

    private JLabel inputGeneratorFileLabel;
    private JLabel outputGeneratorFileLabel;
    private JLabel testcaseFolderLabel;
    private JLabel backtoHome;

    public ConfigPanel() {
        if (GlobalResource.getConfigData() == null) {
            GlobalResource.deserializeConfigData();
        }

        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(GlobalResource.getTheme().getTopPanelColor());
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));

        inputGeneratorFileLabel = new JLabel("Input Generator File");
        inputGeneratorFileLabel.setForeground(GlobalResource.getTheme().getFontColor());
        inputGeneratorFileLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        inputGeneratorFileLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        inputGeneratorFileLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        inputGeneratorFileLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(inputGeneratorFileLabel);

        inputGeneratorFileButton = new JButton(GlobalResource.getConfigData().get(0));
        inputGeneratorFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inputGeneratorFileButton.setForeground(GlobalResource.getTheme().getFontHighLightColor());
        inputGeneratorFileButton.setFont(GlobalResource.getExtendedFont().getFont(FontType.SEMI_BOLD, FontSize.SMALL));
        inputGeneratorFileButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        inputGeneratorFileButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        inputGeneratorFileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputGeneratorFileButton.addActionListener(e -> {
            chooseFile(e);
            GlobalResource.getConfigData().set(0, inputGeneratorFileButton.getText());
            GlobalResource.serializeConfigData();
        });
        add(inputGeneratorFileButton);

        add(Box.createRigidArea(new Dimension(0, 20)));

        outputGeneratorFileLabel = new JLabel("Output Generator File");    
        outputGeneratorFileLabel.setForeground(GlobalResource.getTheme().getFontColor());
        outputGeneratorFileLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        outputGeneratorFileLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        outputGeneratorFileLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        outputGeneratorFileLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(outputGeneratorFileLabel);

        outputGeneratorFileButton = new JButton(GlobalResource.getConfigData().get(1));
        outputGeneratorFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        outputGeneratorFileButton.setForeground(GlobalResource.getTheme().getFontHighLightColor());
        outputGeneratorFileButton.setFont(GlobalResource.getExtendedFont().getFont(FontType.SEMI_BOLD, FontSize.SMALL));
        outputGeneratorFileButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        outputGeneratorFileButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        outputGeneratorFileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        outputGeneratorFileButton.addActionListener(e -> {
            chooseFile(e);
            GlobalResource.getConfigData().set(1, outputGeneratorFileButton.getText());
            GlobalResource.serializeConfigData();
        });
        add(outputGeneratorFileButton);

        add(Box.createRigidArea(new Dimension(0, 20)));

        testcaseFolderLabel = new JLabel("Testcase Output Folder");
        testcaseFolderLabel.setForeground(GlobalResource.getTheme().getFontColor());
        testcaseFolderLabel.setFont(GlobalResource.getExtendedFont().getFont(FontType.BOLD, FontSize.MEDIUM));
        testcaseFolderLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        testcaseFolderLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        testcaseFolderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(testcaseFolderLabel);

        testcaseFolderButton = new JButton(GlobalResource.getConfigData().get(2));
        testcaseFolderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        testcaseFolderButton.setForeground(GlobalResource.getTheme().getFontHighLightColor());
        testcaseFolderButton.setFont(GlobalResource.getExtendedFont().getFont(FontType.SEMI_BOLD, FontSize.SMALL));
        testcaseFolderButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        testcaseFolderButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 5 * 4, 45));
        testcaseFolderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseFolderButton.addActionListener(e -> {
            chooseFolder(e);
            GlobalResource.getConfigData().set(2, testcaseFolderButton.getText());
            GlobalResource.serializeConfigData();
        });
        add(testcaseFolderButton);
        
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

    private void chooseFile(ActionEvent e) {
        JButton sourceObject = (JButton) e.getSource();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Testcase Generator File", "cpp"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(sourceObject.getText()));

        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            sourceObject.setText(file.getAbsolutePath());
        }
    }

    private void chooseFolder(ActionEvent e) {
        JButton sourceObject = (JButton) e.getSource();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(sourceObject.getText()));

        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            sourceObject.setText(file.getAbsolutePath());
        }
    }
}
