package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.canuc80k.launcher.GlobalResource;

public class HomeFrame extends JFrame {
    protected static final int APP_WIDTH = 400;
    protected static final int APP_HEIGHT = 650;
    protected static final int TOPPANEL_HEIGHT = APP_HEIGHT / 7 * 5;

    private JPanel contentPane;
    private JPanel topPanel = GlobalResource.getInitPanel();

    public HomeFrame() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(HomeFrame.class.getResourceAsStream("/image/icon.png")));
            setIconImage(icon.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        setTitle("tesgetor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_WIDTH, APP_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(GlobalResource.getTheme().getBackgroundColor());
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new BorderLayout(0, 0));
    }

    public void setTopPanel(JPanel newTopPanel) {
        remove(topPanel);
        contentPane.add(newTopPanel, BorderLayout.NORTH);
        validate();
        repaint();

        topPanel = newTopPanel;
    }
}
