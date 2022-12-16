package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeFrame extends JFrame {
    public static final int APP_WIDTH = 350;
    public static final int APP_HEIGHT = 550;
    private final int TOPPANEL_HEIGHT = APP_HEIGHT / 7 * 5;
	
    private JPanel contentPane; 
    protected MainPanel topPanel;

    public HomeFrame() {        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.decode("#6588dc"));
        contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		topPanel = new MainPanel();
        topPanel.setPreferredSize(new Dimension(APP_WIDTH, TOPPANEL_HEIGHT));
		topPanel.setBackground(Color.decode("#d7def8"));
		contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#6588dc"));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
	}

}
