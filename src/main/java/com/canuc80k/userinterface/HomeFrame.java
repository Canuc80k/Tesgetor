package com.canuc80k.userinterface;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel topLabel = new JLabel("Dont_generatetest_me_pls");
        topLabel.setFont(new Font("Open Sans Bold", Font.PLAIN, 24));
		contentPane.add(topLabel, BorderLayout.NORTH);
	}

}
