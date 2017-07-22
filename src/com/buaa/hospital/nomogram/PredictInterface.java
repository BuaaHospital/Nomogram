package com.buaa.hospital.nomogram;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import contrib.com.blogofbug.swing.SwingBugUtilities;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PredictInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void startRun() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public PredictInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int ScreenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
		int ScreenWeight=Toolkit.getDefaultToolkit().getScreenSize().width;
		frame = new JFrame();
		frame.setBounds(0, 0, ScreenWeight, ScreenHeight-40);
		frame.getContentPane().setLayout(null);
		
		String[] SexLabels = {"Male", "Female"};		
		JComboBox SexBox = new JComboBox(SexLabels);
		SexBox.setBounds(200, 100, 100, 30);
		frame.getContentPane().add(SexBox);
		SexBox.setEditable(false);
		
		JLabel SexLabel = new JLabel("性别");
		SexLabel.setBounds(100, 100, 100, 30);
		frame.getContentPane().add(SexLabel);
		SexLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] AlgorithmLabels = {"综合算法", "神经网络算法", "分类算法"};
		JComboBox AlogrithmBox = new JComboBox(AlgorithmLabels);
		AlogrithmBox.setBounds(200, 50, 100, 30);
		frame.getContentPane().add(AlogrithmBox);
		
		JLabel AlogrithmLabel = new JLabel("算法选择");
		AlogrithmLabel.setBounds(100, 50, 100, 30);
		frame.getContentPane().add(AlogrithmLabel);
		AlogrithmLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] ModelLabels = {"最近的模型"};
		JComboBox ModelBox = new JComboBox(ModelLabels);
		ModelBox.setBounds(500, 50, 100, 30);
		frame.getContentPane().add(ModelBox);
		
		JLabel ModelLabel = new JLabel("模型选择");
		ModelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ModelLabel.setBounds(400, 50, 100, 30);
		frame.getContentPane().add(ModelLabel);
	}
}
