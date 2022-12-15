package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public HomeFrame() {        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#6487db"));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.CENTER);
		topPanel.setLayout(new BorderLayout(0, 0));

        JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.CENTER);
		bottomPanel.setLayout(new BorderLayout(0, 0));
	
		JButton controller = new JButton("New button");
		controller.setPreferredSize(new Dimension(Integer.MIN_VALUE, 500));
		topPanel.add(controller, BorderLayout.NORTH);
	}

}
