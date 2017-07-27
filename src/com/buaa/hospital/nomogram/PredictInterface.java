package com.buaa.hospital.nomogram;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import contrib.com.blogofbug.swing.SwingBugUtilities;
import weka.clusterers.forOPTICSAndDBScan.Databases.Database;
import weka.core.Check;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PredictInterface {

	private DataBase dataBase;
	private JFrame frame;
	private JComboBox AlogrithmBox;
	private JComboBox ModelBox;
	private JTextField IDText;
	private JTextField NameText;
	private JTextField AgeText;
	private JComboBox SexBox;
	private JComboBox EyeBox;
	private JTextField SEText;
	private JTextField UCVAText;
	private JTextField SDText;
	private JTextField CDText;
	private JTextField AxisText;
	private JComboBox BCVABox;
	private JTextField CornealRadiusText;
	private JTextField OpticalZoneText;
	private JTextField K1Text;
	private JTextField K2Text;
	private JTextField KmText;
	private JTextField CCTText;
	private JComboBox LeadEyeBox;
	private HistoryTabelModel historyTabelModel = new HistoryTabelModel();
	private JTable HistoryTable;
	private JTextArea LogArea;
	private String[] AlgorithmLabels = {"综合算法", "神经网络算法", "分类算法"};
	private String[] ModelLabels = {"最近的模型"};
	private String[] SexLabels = {"Male", "Female"};
	private String[] EyeLabels = {"OS", "OD"};
	private String[] BCVALabels = {"0.8", "0.9", "1.0", "1.1", "1.2", "1.3", "1.4", "1.5"};

	/**
	 * Launch the application.
	 */
	public void startRun() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public PredictInterface(DataBase dataBase) {
		this.dataBase = dataBase;
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
		
		
		AlogrithmBox = new JComboBox(AlgorithmLabels);
		AlogrithmBox.setBounds(200, 50, 100, 30);
		frame.getContentPane().add(AlogrithmBox);
		
		JLabel ModelLabel = new JLabel("模型选择");
		ModelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ModelLabel.setBounds(400, 50, 100, 30);
		frame.getContentPane().add(ModelLabel);
		
		
		ModelBox = new JComboBox(ModelLabels);
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
		
				
		SexBox = new JComboBox(SexLabels);
		SexBox.setBounds(500, 150, 100, 30);
		frame.getContentPane().add(SexBox);
		SexBox.setEditable(false);
		
		JLabel EyeLabel = new JLabel("眼别");
		EyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EyeLabel.setBounds(100, 200, 100, 30);
		frame.getContentPane().add(EyeLabel);
		
		
		EyeBox = new JComboBox(EyeLabels);
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
		
		JLabel UCVALabel = new JLabel("术前裸眼视力");
		UCVALabel.setHorizontalAlignment(SwingConstants.CENTER);
		UCVALabel.setBounds(100, 250, 100, 30);
		frame.getContentPane().add(UCVALabel);
		
		UCVAText = new JTextField();
		UCVAText.setColumns(10);
		UCVAText.setBounds(200, 250, 100, 30);
		frame.getContentPane().add(UCVAText);
		
		JLabel SDLabel = new JLabel("球镜度");
		SDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SDLabel.setBounds(400, 250, 100, 30);
		frame.getContentPane().add(SDLabel);
		
		SDText = new JTextField();
		SDText.setColumns(10);
		SDText.setBounds(500, 250, 100, 30);
		frame.getContentPane().add(SDText);
		
		JLabel CDLabel = new JLabel("柱镜度");
		CDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CDLabel.setBounds(100, 300, 100, 30);
		frame.getContentPane().add(CDLabel);
		
		CDText = new JTextField();
		CDText.setColumns(10);
		CDText.setBounds(200, 300, 100, 30);
		frame.getContentPane().add(CDText);
		
		JLabel AxisLabel = new JLabel("轴");
		AxisLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AxisLabel.setBounds(400, 300, 100, 30);
		frame.getContentPane().add(AxisLabel);
		
		AxisText = new JTextField();
		AxisText.setColumns(10);
		AxisText.setBounds(500, 300, 100, 30);
		frame.getContentPane().add(AxisText);
		
		JLabel BCVALabel = new JLabel("最佳矫正视力");
		BCVALabel.setHorizontalAlignment(SwingConstants.CENTER);
		BCVALabel.setBounds(100, 350, 100, 30);
		frame.getContentPane().add(BCVALabel);
		
		
		BCVABox = new JComboBox(BCVALabels);
		BCVABox.setSelectedIndex(2);
		BCVABox.setEditable(true);
		BCVABox.setBounds(200, 350, 100, 30);
		frame.getContentPane().add(BCVABox);
		
		JLabel CornealRadiusLabel = new JLabel("角膜半径");
		CornealRadiusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CornealRadiusLabel.setBounds(400, 350, 100, 30);
		frame.getContentPane().add(CornealRadiusLabel);
		
		CornealRadiusText = new JTextField();
		CornealRadiusText.setColumns(10);
		CornealRadiusText.setBounds(500, 350, 100, 30);
		frame.getContentPane().add(CornealRadiusText);
		
		JLabel OpticalZoneLabel = new JLabel("光学区");
		OpticalZoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OpticalZoneLabel.setBounds(100, 400, 100, 30);
		frame.getContentPane().add(OpticalZoneLabel);
		
		OpticalZoneText = new JTextField();
		OpticalZoneText.setColumns(10);
		OpticalZoneText.setBounds(200, 400, 100, 30);
		frame.getContentPane().add(OpticalZoneText);
		
		JLabel K1Label = new JLabel("K1");
		K1Label.setHorizontalAlignment(SwingConstants.CENTER);
		K1Label.setBounds(400, 400, 100, 30);
		frame.getContentPane().add(K1Label);
		
		K1Text = new JTextField();
		K1Text.setColumns(10);
		K1Text.setBounds(500, 400, 100, 30);
		frame.getContentPane().add(K1Text);
		
		JLabel K2Label = new JLabel("K2");
		K2Label.setHorizontalAlignment(SwingConstants.CENTER);
		K2Label.setBounds(100, 450, 100, 30);
		frame.getContentPane().add(K2Label);
		
		K2Text = new JTextField();
		K2Text.setColumns(10);
		K2Text.setBounds(200, 450, 100, 30);
		frame.getContentPane().add(K2Text);
		
		JLabel KmLabel = new JLabel("Km");
		KmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		KmLabel.setBounds(400, 450, 100, 30);
		frame.getContentPane().add(KmLabel);
		
		KmText = new JTextField();
		KmText.setColumns(10);
		KmText.setBounds(500, 450, 100, 30);
		frame.getContentPane().add(KmText);
		
		JLabel CCTLabel = new JLabel("角膜中央厚度");
		CCTLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CCTLabel.setBounds(100, 500, 100, 30);
		frame.getContentPane().add(CCTLabel);
		
		CCTText = new JTextField();
		CCTText.setColumns(10);
		CCTText.setBounds(200, 500, 100, 30);
		frame.getContentPane().add(CCTText);
		
		JLabel LeadEyeLabel = new JLabel("主导眼");
		LeadEyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LeadEyeLabel.setBounds(400, 500, 100, 30);
		frame.getContentPane().add(LeadEyeLabel);
		
		LeadEyeBox = new JComboBox(EyeLabels);
		LeadEyeBox.setEditable(false);
		LeadEyeBox.setBounds(500, 500, 100, 30);
		frame.getContentPane().add(LeadEyeBox);
		
		JButton PredictButton = new JButton("预测");
		PredictButton.setBounds(200, 600, 100, 30);
		frame.getContentPane().add(PredictButton);
		PredictButton.addActionListener(new PredictButtonListener(this));
		
		JButton ClearButton = new JButton("清除");
		ClearButton.setBounds(350, 600, 100, 30);
		frame.getContentPane().add(ClearButton);
		ClearButton.addActionListener(new ClearButtonListener(this));
		
		JButton SaveButton = new JButton("保存");
		SaveButton.setBounds(500, 600, 100, 30);
		frame.getContentPane().add(SaveButton);
		SaveButton.addActionListener(new SaveButtonListener(this));
		
		SaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		HistoryTable = new JTable(historyTabelModel);
		HistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		HistoryTable.setDefaultRenderer(Object.class, tcr);
		JScrollPane HistoryScrollPane = new JScrollPane(HistoryTable);
		HistoryScrollPane.setBounds(700, 50, 600, 400);
		frame.getContentPane().add(HistoryScrollPane);
		
		LogArea = new JTextArea();
		LogArea.setEditable(false);
		JScrollPane LogScrollPane = new JScrollPane(LogArea);
		LogScrollPane.setBounds(700, 500, 600, 100);
		frame.getContentPane().add(LogScrollPane);
		
		
		
		
	}
	
	public boolean CheckData() {
		return true;
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> AttributeData = new ArrayList();
		AttributeData.add(IDText.getText());
		AttributeData.add(NameText.getText());
		AttributeData.add(AgeText.getText());
		AttributeData.add(SexLabels[SexBox.getSelectedIndex()]);
		AttributeData.add(EyeLabels[EyeBox.getSelectedIndex()]);
		AttributeData.add(SEText.getText());
		AttributeData.add(UCVAText.getText());
		AttributeData.add(SDText.getText());
		AttributeData.add(CDText.getText());
		AttributeData.add(AxisText.getText());
		if (BCVABox.getSelectedIndex() == -1) {
			AttributeData.add((String)BCVABox.getSelectedItem());
		}
		else {
			AttributeData.add(BCVALabels[BCVABox.getSelectedIndex()]);
		}
		AttributeData.add(CornealRadiusText.getText());
		AttributeData.add(OpticalZoneText.getText());
		AttributeData.add(K1Text.getText());
		AttributeData.add(K2Text.getText());
		AttributeData.add(KmText.getText());
		AttributeData.add(CCTText.getText());
		AttributeData.add(EyeLabels[LeadEyeBox.getSelectedIndex()]);	
		return AttributeData;
	}
	
	public String getAlogrithmName() {
		return AlgorithmLabels[AlogrithmBox.getSelectedIndex()];
	}
	
	public int getAlogrithmNum() {
		return AlogrithmBox.getSelectedIndex();
	}
	
	public int getModelNum() {
		return ModelBox.getSelectedIndex();
	}
	
	public String getModelName() {
		return ModelLabels[ModelBox.getSelectedIndex()];
	}
	
	public void setLogText(String text) {
		LogArea.setText(LogArea.getText() + "\n" + text);
	}
	
	public void addHistory(Attribute attribute) {
		historyTabelModel.addHistoryItem(attribute);
	}
	
	public void clear() {
		AlogrithmBox.setSelectedIndex(0);
		ModelBox.setSelectedIndex(0);
		IDText.setText("");
		NameText.setText("");
		AgeText.setText("");
		SexBox.setSelectedIndex(0);
		EyeBox.setSelectedIndex(0);
		SEText.setText("");
		UCVAText.setText("");
		SDText.setText("");
		CDText.setText("");
		AxisText.setText("");
		BCVABox.setSelectedIndex(2);
		CornealRadiusText.setText("");
		OpticalZoneText.setText("");
		K1Text.setText("");
		K2Text.setText("");
		KmText.setText("");
		CCTText.setText("");
		LeadEyeBox.setSelectedIndex(0);
	}
	
	public void RefreshHistoryTable() {
//		HistoryTable.validate();
		HistoryTable.updateUI();
//		HistoryTable.repaint();
	}
	
	public DataBase getDataBase() {
		return dataBase;
	}
}
