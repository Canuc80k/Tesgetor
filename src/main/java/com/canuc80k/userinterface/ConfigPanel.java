package com.canuc80k.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings("unchecked")

public class ConfigPanel extends JPanel {
    private static final File CONFIG_FILE = new File("config/config.cfg");
    private static List<String> configData;

    private JButton inputGeneratorFileButton;
    private JButton outputGeneratorFileButton;
    private JButton testcaseFolderButton;

    private JLabel inputGeneratorFileLabel;
    private JLabel outputGeneratorFileLabel;
    private JLabel testcaseFolderLabel;
    private JLabel backtoHome;

    ConfigPanel() {
        if (configData == null) {
            deserializeConfigData();
        }
        
        buildUI();
    }

    private void buildUI() {
        setPreferredSize(new Dimension(HomeFrame.APP_WIDTH, HomeFrame.TOPPANEL_HEIGHT));
        setBackground(Color.decode("#282a36"));
        setBorder(new EmptyBorder(0, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        inputGeneratorFileLabel = new JLabel("Input Generator File");
        inputGeneratorFileLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        inputGeneratorFileLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        inputGeneratorFileLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(inputGeneratorFileLabel);

        inputGeneratorFileButton = new JButton(configData.get(0));
        inputGeneratorFileButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        inputGeneratorFileButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        inputGeneratorFileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputGeneratorFileButton.addActionListener(e -> {
            chooseFile(e);
            configData.set(0, inputGeneratorFileButton.getText());
            serializeConfigData();
        });
        add(inputGeneratorFileButton);

        outputGeneratorFileLabel = new JLabel("Output Generator File");
        outputGeneratorFileLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        outputGeneratorFileLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        outputGeneratorFileLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(outputGeneratorFileLabel);

        outputGeneratorFileButton = new JButton(configData.get(1));
        outputGeneratorFileButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        outputGeneratorFileButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        outputGeneratorFileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        outputGeneratorFileButton.addActionListener(e -> {
            chooseFile(e);
            configData.set(1, outputGeneratorFileButton.getText());
            serializeConfigData();
        });
        add(outputGeneratorFileButton);

        testcaseFolderLabel = new JLabel("Testcase Output Folder");
        testcaseFolderLabel.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        testcaseFolderLabel.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        testcaseFolderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(testcaseFolderLabel);

        testcaseFolderButton = new JButton(configData.get(2));
        testcaseFolderButton.setMinimumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        testcaseFolderButton.setMaximumSize(new Dimension(HomeFrame.APP_WIDTH / 3 * 2, 50));
        testcaseFolderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        testcaseFolderButton.addActionListener(e -> {
            chooseFolder(e);
            configData.set(2, testcaseFolderButton.getText());
            serializeConfigData();
        });
        add(testcaseFolderButton);
        
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

    private void chooseFile(ActionEvent e) {
        JButton sourceObject = (JButton) e.getSource();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Testcase Generator File", "cpp"));
        fileChooser.setAcceptAllFileFilterUsed(false);

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

        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            sourceObject.setText(file.getAbsolutePath());
        }
    }

    private void serializeConfigData() {
        try {
            ObjectOutputStream configFileObjectOutputStream = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE));
            configFileObjectOutputStream.writeObject(configData);
            configFileObjectOutputStream.flush();
            configFileObjectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeConfigData() {
        try {
            ObjectInputStream configFileObjectInputStream = new ObjectInputStream(new FileInputStream(CONFIG_FILE));
            configData = (List<String>) configFileObjectInputStream.readObject();
            configFileObjectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            configData = new ArrayList<String>();
            for (int i = 0; i < 3; i ++) configData.add("");
        }
    }

    public static List<String> getConfigData() {
        if (configData == null) deserializeConfigData();
        return configData;
    }
}
