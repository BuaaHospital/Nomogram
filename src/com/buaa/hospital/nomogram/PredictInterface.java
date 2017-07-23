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
	private JTextField IDText;
	private JTextField NameText;
	private JTextField AgeText;
	private JTextField SEText;

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
				
		JLabel AlogrithmLabel = new JLabel("算法选择");
		AlogrithmLabel.setBounds(100, 50, 100, 30);
		frame.getContentPane().add(AlogrithmLabel);
		AlogrithmLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] AlgorithmLabels = {"综合算法", "神经网络算法", "分类算法"};
		JComboBox AlogrithmBox = new JComboBox(AlgorithmLabels);
		AlogrithmBox.setBounds(200, 50, 100, 30);
		frame.getContentPane().add(AlogrithmBox);
		
		JLabel ModelLabel = new JLabel("模型选择");
		ModelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ModelLabel.setBounds(400, 50, 100, 30);
		frame.getContentPane().add(ModelLabel);
		
		String[] ModelLabels = {"最近的模型"};
		JComboBox ModelBox = new JComboBox(ModelLabels);
		ModelBox.setBounds(500, 50, 100, 30);
		frame.getContentPane().add(ModelBox);	
		
		JLabel IDLabel = new JLabel("病历号");
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IDLabel.setBounds(100, 100, 100, 30);
		frame.getContentPane().add(IDLabel);
		
		IDText = new JTextField();
		IDText.setBounds(200, 100, 100, 30);
		frame.getContentPane().add(IDText);
		IDText.setColumns(10);		
		
		JLabel NameLabel = new JLabel("姓名");
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(400, 100, 100, 30);
		frame.getContentPane().add(NameLabel);
		
		NameText = new JTextField();
		NameText.setBounds(500, 100, 100, 30);
		frame.getContentPane().add(NameText);
		NameText.setColumns(10);
		
		JLabel AgeLabel = new JLabel("年龄");
		AgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AgeLabel.setBounds(100, 150, 100, 30);
		frame.getContentPane().add(AgeLabel);
		
		AgeText = new JTextField();
		AgeText.setBounds(200, 150, 100, 30);
		frame.getContentPane().add(AgeText);
		AgeText.setColumns(10);
		
		JLabel SexLabel = new JLabel("性别");
		SexLabel.setBounds(400, 150, 100, 30);
		frame.getContentPane().add(SexLabel);
		SexLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] SexLabels = {"Male", "Female"};		
		JComboBox SexBox = new JComboBox(SexLabels);
		SexBox.setBounds(500, 150, 100, 30);
		frame.getContentPane().add(SexBox);
		SexBox.setEditable(false);
		
		JLabel EyeLabel = new JLabel("眼别");
		EyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EyeLabel.setBounds(100, 200, 100, 30);
		frame.getContentPane().add(EyeLabel);
		
		String[] EyeLabels = {"OS", "OD"};
		JComboBox EyeBox = new JComboBox(EyeLabels);
		EyeBox.setEditable(false);
		EyeBox.setBounds(200, 200, 100, 30);
		frame.getContentPane().add(EyeBox);
		
		JLabel SELabel = new JLabel("等效球镜度");
		SELabel.setHorizontalAlignment(SwingConstants.CENTER);
		SELabel.setBounds(400, 200, 100, 30);
		frame.getContentPane().add(SELabel);
		
		SEText = new JTextField();
		SEText.setBounds(500, 200, 100, 30);
		frame.getContentPane().add(SEText);
		SEText.setColumns(10);
		
		
	}
}
