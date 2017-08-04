package com.buaa.hospital.nomogram;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.buaa.hospital.nomogram.PredictInterface.LogAreamouseRightClickListener;

import java.awt.Font;
import java.awt.Color;

public class ModifyInterface {

	private JFrame frame;
	private DataBase dataBase;
	private QueryInterface queryInterface;
	private QueryResult queryResult;
	private ArrayList<String> OriginData;
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
	private JComboBox OpticalZoneBox;
	private JTextField K1Text;
	private JTextField K2Text;
	private JTextField KmText;
	private JTextField CCTText;
	private JComboBox LeadEyeBox;
	private JComboBox RealNomogramBox;
	private JTextField RSTText;
	private JTextField HumidityText;
	private JTextField TemperatureText;
	private JComboBox FirstEyeBox;
	private JComboBox EnergyBox;
	private JComboBox OBLBox;
	private JComboBox ThicknessBox;
	private JComboBox PositionBox;
	private JTextField SDAfterOneDayText;
	private JTextField SDAfterThreeMonthsText;
	private JTextField SDAfterSixMonthsText;
	private JTextArea LogArea;
	private String[] SexLabels = {"Male", "Female"};
	private String[] EyeLabels = {"OS", "OD"};
	private String[] BCVALabels = {"0.8", "0.9", "1.0", "1.1", "1.2", "1.3", "1.4", "1.5"};
	private String[] OpticalZoneLabels = {"6.0", "6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7", "6.8", "6.9", "7.0"};
	private String[] RealNomogramLabels = {"未确定", "与预测值相同"};
	private String[] EnergyLabels = {"25", "26", "27", "28", "29", "30", "31", "32", "33"};
	private String[] OBLLabels = {"未确定", "是", "否"};
	private String[] ThicknessLabels = {"110", "120", "130", "140", "150", "160"};
	private String[] PositionLabels = {"90", "95", "100", "105", "110", "115", "120", "125", "130", "135", "140", "145", "150", "155", "160", "165", "170", "175", "180"};
	
	
 	

	/**
	 * Launch the application.
	 */
	public void startRun() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ModifyInterface(DataBase dataBase, QueryInterface queryInterface, QueryResult queryResult) {
		this.dataBase = dataBase;
		this.queryInterface = queryInterface;
		this.queryResult = queryResult;
		OriginData = (ArrayList<String>)queryResult.getAttribute().toArrayList().clone();
		initialize();
		DataInit();
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
		
		OpticalZoneBox = new JComboBox(OpticalZoneLabels);
		OpticalZoneBox.setSelectedIndex(6);
		OpticalZoneBox.setEditable(true);
		OpticalZoneBox.setBounds(200, 400, 100, 30);
		frame.getContentPane().add(OpticalZoneBox);
		
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
		
		JButton ResetButton = new JButton("还原");
		ResetButton.setBounds(200, 600, 100, 30);
		frame.getContentPane().add(ResetButton);
		ResetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DataInit();
			}
		});
		
		JButton DeleteButton = new JButton("删除");
		DeleteButton.setBounds(350, 600, 100, 30);
		frame.getContentPane().add(DeleteButton);
		DeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					dataBase.deleteInstance(queryResult);
					frame.dispose();
					queryInterface.reset();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton SaveButton = new JButton("保存");
		SaveButton.setBounds(500, 600, 100, 30);
		frame.getContentPane().add(SaveButton);
		SaveButton.addActionListener(new ModifySaveButtonListener(this, dataBase));
		
		JLabel PreLabel = new JLabel("术前参数");
		PreLabel.setForeground(Color.GRAY);
		PreLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		PreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PreLabel.setBounds(350, 50, 100, 30);
		frame.getContentPane().add(PreLabel);
		
		JLabel RealNomogramLabel = new JLabel("实际球镜调整值");
		RealNomogramLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RealNomogramLabel.setBounds(750, 100, 100, 30);
		frame.getContentPane().add(RealNomogramLabel);
		
		RealNomogramBox = new JComboBox(RealNomogramLabels);
		RealNomogramBox.setEditable(true);
		RealNomogramBox.setSelectedIndex(0);
		RealNomogramBox.setBounds(850, 100, 100, 30);
		frame.getContentPane().add(RealNomogramBox);
		
		JLabel RSTLabel = new JLabel("残余基质厚度");
		RSTLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RSTLabel.setBounds(1050, 100, 100, 30);
		frame.getContentPane().add(RSTLabel);
		
		RSTText = new JTextField();
		RSTText.setColumns(10);
		RSTText.setBounds(1150, 100, 100, 30);
		frame.getContentPane().add(RSTText);
		
		JLabel HumidityLabel = new JLabel("湿度");
		HumidityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HumidityLabel.setBounds(750, 150, 100, 30);
		frame.getContentPane().add(HumidityLabel);
		
		HumidityText = new JTextField();
		HumidityText.setColumns(10);
		HumidityText.setBounds(850, 150, 100, 30);
		frame.getContentPane().add(HumidityText);
		
		JLabel TemperatureLabel = new JLabel("温度");
		TemperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TemperatureLabel.setBounds(1050, 150, 100, 30);
		frame.getContentPane().add(TemperatureLabel);
		
		TemperatureText = new JTextField();
		TemperatureText.setColumns(10);
		TemperatureText.setBounds(1150, 150, 100, 30);
		frame.getContentPane().add(TemperatureText);
		
		JLabel FirstEyeLabel = new JLabel("First Eye");
		FirstEyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		FirstEyeLabel.setBounds(750, 200, 100, 30);
		frame.getContentPane().add(FirstEyeLabel);
		
		FirstEyeBox = new JComboBox(EyeLabels);
		FirstEyeBox.setEditable(false);
		FirstEyeBox.setBounds(850, 200, 100, 30);
		frame.getContentPane().add(FirstEyeBox);
		
		JLabel EnergyLabel = new JLabel("能量");
		EnergyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EnergyLabel.setBounds(1050, 200, 100, 30);
		frame.getContentPane().add(EnergyLabel);
		
		EnergyBox = new JComboBox(EnergyLabels);
		EnergyBox.setSelectedIndex(2);
		EnergyBox.setEditable(true);
		EnergyBox.setBounds(1150, 200, 100, 30);
		frame.getContentPane().add(EnergyBox);
		
		JLabel OBLLabel = new JLabel("是否出现OBL");
		OBLLabel.setHorizontalAlignment(SwingConstants.CENTER);
		OBLLabel.setBounds(750, 250, 100, 30);
		frame.getContentPane().add(OBLLabel);	
		
		OBLBox = new JComboBox(OBLLabels);
		OBLBox.setEditable(false);
		OBLBox.setBounds(850, 250, 100, 30);
		frame.getContentPane().add(OBLBox);
		
		JLabel ThicknessLabel = new JLabel("角膜帽厚度");
		ThicknessLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ThicknessLabel.setBounds(1050, 250, 100, 30);
		frame.getContentPane().add(ThicknessLabel);
		
		ThicknessBox = new JComboBox(ThicknessLabels);
		ThicknessBox.setSelectedIndex(1);
		ThicknessBox.setEditable(true);
		ThicknessBox.setBounds(1150, 250, 100, 30);
		frame.getContentPane().add(ThicknessBox);
		
		JLabel PositionLabel = new JLabel("切口位置");
		PositionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PositionLabel.setBounds(750, 300, 100, 30);
		frame.getContentPane().add(PositionLabel);
				
		PositionBox = new JComboBox(PositionLabels);
		PositionBox.setSelectedIndex(0);
		PositionBox.setEditable(true);
		PositionBox.setBounds(850, 300, 100, 30);
		frame.getContentPane().add(PositionBox);
		
		JLabel SDAfterOneDayLabel = new JLabel("术后一天验光度");
		SDAfterOneDayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SDAfterOneDayLabel.setBounds(1050, 300, 100, 30);
		frame.getContentPane().add(SDAfterOneDayLabel);
		
		SDAfterOneDayText = new JTextField();
		SDAfterOneDayText.setColumns(10);
		SDAfterOneDayText.setBounds(1150, 300, 100, 30);
		frame.getContentPane().add(SDAfterOneDayText);
		
		JLabel SDAfterThreeMonthsLabel = new JLabel("术后三月验光度");
		SDAfterThreeMonthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SDAfterThreeMonthsLabel.setBounds(750, 350, 100, 30);
		frame.getContentPane().add(SDAfterThreeMonthsLabel);
		
		SDAfterThreeMonthsText = new JTextField();
		SDAfterThreeMonthsText.setColumns(10);
		SDAfterThreeMonthsText.setBounds(850, 350, 100, 30);
		frame.getContentPane().add(SDAfterThreeMonthsText);
		
		JLabel SDAfterSixMonthsLabel = new JLabel("术后六月验光度");
		SDAfterSixMonthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SDAfterSixMonthsLabel.setBounds(1050, 350, 100, 30);
		frame.getContentPane().add(SDAfterSixMonthsLabel);
		
		SDAfterSixMonthsText = new JTextField();
		SDAfterSixMonthsText.setColumns(10);
		SDAfterSixMonthsText.setBounds(1150, 350, 100, 30);
		frame.getContentPane().add(SDAfterSixMonthsText);
		
		JLabel AfterLabel = new JLabel("术后参数");
		AfterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AfterLabel.setForeground(Color.GRAY);
		AfterLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		AfterLabel.setBounds(1000, 50, 100, 30);
		frame.getContentPane().add(AfterLabel);
		
		LogArea = new JTextArea();
		LogArea.setEditable(false);
		JScrollPane LogScrollPane = new JScrollPane(LogArea);
		LogScrollPane.setBounds(700, 400, 600, 200);
//		LogArea.addMouseListener(new LogAreamouseRightClickListener());
		frame.getContentPane().add(LogScrollPane);
		
		
		
		
		
		
	}
	
	private void DataInit() {
		IDText.setText(OriginData.get(0));
		NameText.setText(OriginData.get(1));
		AgeText.setText(OriginData.get(2));
		if (OriginData.get(3).equals("0")) {
			SexBox.setSelectedIndex(0);
		}
		else {
			SexBox.setSelectedIndex(1);
		}
		if (OriginData.get(4).equals("0")) {
			EyeBox.setSelectedIndex(0);
		}
		else {
			EyeBox.setSelectedIndex(1);
		}
		SEText.setText(OriginData.get(5));
		UCVAText.setText(OriginData.get(6));
		SDText.setText(OriginData.get(7));
		CDText.setText(OriginData.get(8));
		AxisText.setText(OriginData.get(9));
		BCVABox.setSelectedItem(OriginData.get(10));
		CornealRadiusText.setText(OriginData.get(11));
		OpticalZoneBox.setSelectedItem(OriginData.get(12));
		K1Text.setText(OriginData.get(13));
		K2Text.setText(OriginData.get(14));
		KmText.setText(OriginData.get(15));
		CCTText.setText(OriginData.get(16));
		if (OriginData.get(17).equals("0")) {
			LeadEyeBox.setSelectedIndex(0);
		}
		else {
			LeadEyeBox.setSelectedIndex(1);
		}
		if (OriginData.get(19).equals(Double.toString(Constant.InitDataNum))) {
			RSTText.setText(Constant.UnfilledHint);
		}
		else {
			RSTText.setText(OriginData.get(19));
		}
		if (OriginData.get(21).equals(Double.toString(Constant.InitDataNum))) {
			HumidityText.setText(Constant.UnfilledHint);
		}
		else {
			HumidityText.setText(OriginData.get(21));
		}
		if (OriginData.get(22).equals(Double.toString(Constant.InitDataNum))) {
			TemperatureText.setText(Constant.UnfilledHint);
		}
		else {
			TemperatureText.setText(OriginData.get(22));
		}
		if (OriginData.get(23).equals("0")) {
			FirstEyeBox.setSelectedIndex(0);
		}
		else {
			FirstEyeBox.setSelectedIndex(1);
		}
		if (OriginData.get(24).equals(Double.toString(Constant.InitDataNum))) {
			EnergyBox.setSelectedIndex(2);
		}
		else {
			EnergyBox.setSelectedItem(OriginData.get(24));
		}
		if (OriginData.get(25).equals(Double.toString(Constant.InitDataNum))) {
			OBLBox.setSelectedIndex(0);
		}
		else if (OriginData.get(25).equals("0")){
			OBLBox.setSelectedIndex(1);
		}
		else {
			OBLBox.setSelectedIndex(2);
		}
		if (OriginData.get(26).equals(Double.toString(Constant.InitDataNum))) {
			ThicknessBox.setSelectedIndex(1);
		}
		else {
			ThicknessBox.setSelectedItem(OriginData.get(26));
		}
		if (OriginData.get(27).equals(Double.toString(Constant.InitDataNum))) {
			PositionBox.setSelectedIndex(0);
		}
		else {
			PositionBox.setSelectedItem(OriginData.get(27));
		}
		if (OriginData.get(28).equals(Double.toString(Constant.InitDataNum))) {
			RealNomogramBox.setSelectedIndex(0);
		}
		else {
			RealNomogramBox.setSelectedItem(OriginData.get(28));
		}
		if (OriginData.get(29).equals(Double.toString(Constant.InitDataNum))) {
			SDAfterOneDayText.setText(Constant.UnfilledHint);
		}
		else {
			SDAfterOneDayText.setText(OriginData.get(29));
		}
		if (OriginData.get(30).equals(Double.toString(Constant.InitDataNum))) {
			SDAfterThreeMonthsText.setText(Constant.UnfilledHint);
		}
		else {
			SDAfterThreeMonthsText.setText(OriginData.get(30));
		}
		if (OriginData.get(31).equals(Double.toString(Constant.InitDataNum))) {
			SDAfterSixMonthsText.setText(Constant.UnfilledHint);
		}
		else {
			SDAfterSixMonthsText.setText(OriginData.get(31));
		}
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
		if (OpticalZoneBox.getSelectedIndex() == -1) {
			AttributeData.add((String)OpticalZoneBox.getSelectedItem());
		}
		else {
			AttributeData.add(OpticalZoneLabels[OpticalZoneBox.getSelectedIndex()]);
		}
		AttributeData.add(K1Text.getText());
		AttributeData.add(K2Text.getText());
		AttributeData.add(KmText.getText());
		AttributeData.add(CCTText.getText());
		AttributeData.add(EyeLabels[LeadEyeBox.getSelectedIndex()]);
		AttributeData.add(OriginData.get(18));
		if (RSTText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(RSTText.getText());
		}
		AttributeData.add(OriginData.get(20));
		if (HumidityText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(HumidityText.getText());
		}
		if (TemperatureText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(TemperatureText.getText());
		}
		AttributeData.add(EyeLabels[FirstEyeBox.getSelectedIndex()]);
		AttributeData.add((String)EnergyBox.getSelectedItem());
		if (OBLBox.getSelectedIndex() == 0) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(Integer.toString(OBLBox.getSelectedIndex() - 1));
		}
		AttributeData.add((String)ThicknessBox.getSelectedItem());
		AttributeData.add((String)PositionBox.getSelectedItem());
		if (RealNomogramBox.getSelectedIndex() == 0) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else if (RealNomogramBox.getSelectedIndex() == 1) {
			AttributeData.add(OriginData.get(18));
		}
		else {
			AttributeData.add((String)RealNomogramBox.getSelectedItem());
		}
		if (SDAfterOneDayText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(SDAfterOneDayText.getText());
		}
		if (SDAfterThreeMonthsText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(SDAfterThreeMonthsText.getText());
		}
		if (SDAfterSixMonthsText.getText().equals(Constant.UnfilledHint)) {
			AttributeData.add(Double.toString(Constant.InitDataNum));
		}
		else {
			AttributeData.add(SDAfterSixMonthsText.getText());
		}
		return AttributeData;
	}
	
	public void SaveToQueryResult() throws ParseException {
		queryResult.modifyInstance(toArrayList());
	}
	
	public void SaveModify() throws Exception {
		SaveToQueryResult();
		dataBase.modifyInstance(queryResult);
		setLogText("病历号为 " + queryResult.getAttribute().toArrayList().get(0) + " 的病人 " + queryResult.getAttribute().toArrayList().get(1) + " 的信息修改完毕，已存入数据库");
		queryInterface.reset();
	}
	
	public boolean CheckData() {
		return true;
	}
	
	public void setLogText(String text) {
		LogArea.setText(LogArea.getText() + "\n>> " + text);
	}
	
	public JTextArea getLogArea() {
		return LogArea;
	}
	
	public class LogAreamouseRightClickListener implements MouseListener {
		private JPopupMenu jPopupMenu;
		private JMenuItem clearMenuItem;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			jPopupMenu = new JPopupMenu();
			clearMenuItem = new JMenuItem("清除");
			jPopupMenu.add(clearMenuItem);
			clearMenuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LogArea.setText("");
				}
			});
			if (e.getButton() == MouseEvent.BUTTON3) {
				jPopupMenu.show(LogArea, e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
