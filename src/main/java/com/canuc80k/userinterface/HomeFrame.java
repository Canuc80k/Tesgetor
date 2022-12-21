package com.canuc80k.userinterface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.canuc80k.theme.ThemeProperty;

public class HomeFrame extends JFrame {
    protected static final int APP_WIDTH = 350;
    protected static final int APP_HEIGHT = 550;
    protected static final int TOPPANEL_HEIGHT = APP_HEIGHT / 7 * 5;

    private JPanel contentPane;
    private JPanel topPanel = new InitPanel();

    public HomeFrame() {
        setTitle("dont_generatetest_me_pls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_WIDTH, APP_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(ThemeProperty.getBackgroundColor());
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
