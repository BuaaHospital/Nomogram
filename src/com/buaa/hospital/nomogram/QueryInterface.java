package com.buaa.hospital.nomogram;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.Component;

import javax.swing.JButton;

public class QueryInterface {

	private JFrame frame;
	private DataBase dataBase;
	private JLabel IDLabel;
	private JRadioButton IDRadioButton;
	private JTextField IDText;
	private JLabel NameLabel;
	private JRadioButton NameRadioButton;
	private JTextField NameText;
	private JLabel DateLabel;
	private JRadioButton DateRadioButton;
	private DateChooser dateChooser;
	private QueryTabelModel queryTabelModel = new QueryTabelModel();
	private JTable QueryTable;

	/**
	 * Launch the application.
	 */
	public void startRun() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public QueryInterface(DataBase dataBase) {
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
		
		IDLabel = new JLabel("病历号");
		IDLabel.setBounds(300, 100, 100, 30);
		IDLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(IDLabel);
		
		IDText = new JTextField();
		IDText.setBounds(400, 100, 100, 30);
		frame.getContentPane().add(IDText);
		IDText.setColumns(10);
		IDText.setEditable(false);
		IDText.setEnabled(false);
		
		NameLabel = new JLabel("姓名");
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(600, 100, 100, 30);
		frame.getContentPane().add(NameLabel);
		
		NameText = new JTextField();
		NameText.setColumns(10);
		NameText.setBounds(700, 100, 100, 30);
		frame.getContentPane().add(NameText);
		NameText.setEditable(false);
		NameText.setEnabled(false);
		
		DateLabel = new JLabel("日期");
		DateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DateLabel.setBounds(900, 100, 100, 30);
		frame.getContentPane().add(DateLabel);
		
		dateChooser = new DateChooser("yyyy-MM-dd");
		dateChooser.setBounds(1000, 100, 100, 30);
		frame.getContentPane().add(dateChooser);
		dateChooser.setEnabled(false);
		
		JPanel IDPanel = new JPanel();
		IDPanel.setBounds(300, 50, 200, 30);
		frame.getContentPane().add(IDPanel);
		
		IDRadioButton = new JRadioButton("");
		IDPanel.add(IDRadioButton);
		RadioButtonAction IDRadioButtonAction = new RadioButtonAction(0, IDRadioButton, IDText);
		IDRadioButton.setAction(IDRadioButtonAction);
		IDRadioButton.setEnabled(true);
		
		JPanel NamePanel = new JPanel();
		NamePanel.setBounds(600, 50, 200, 30);
		frame.getContentPane().add(NamePanel);
		
		NameRadioButton = new JRadioButton("");
		NamePanel.add(NameRadioButton);
		RadioButtonAction NameRadioButtonAction = new RadioButtonAction(0, NameRadioButton, NameText);
		NameRadioButton.setAction(NameRadioButtonAction);
		NameRadioButton.setEnabled(true);
		
		JPanel DatePanel = new JPanel();
		DatePanel.setBounds(900, 50, 200, 30);
		frame.getContentPane().add(DatePanel);
		
		DateRadioButton = new JRadioButton("");
		DatePanel.add(DateRadioButton);
		RadioButtonAction DateRadioButtonAction = new RadioButtonAction(1, DateRadioButton, dateChooser);
		DateRadioButton.setAction(DateRadioButtonAction);
		DateRadioButton.setEnabled(true);
		
		QueryTable = new JTable(queryTabelModel);
		QueryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		QueryTable.setDefaultRenderer(Object.class, tcr);
		JScrollPane QueryScrollPane = new JScrollPane(QueryTable);
		QueryScrollPane.setBounds(300, 200, 800, 400);
		frame.getContentPane().add(QueryScrollPane);
		QueryTable.addMouseListener(new QueryTableMouseListener(this));
		
		JButton QueryButton = new JButton("查询");
		QueryButton.setBounds(500, 150, 100, 30);
		frame.getContentPane().add(QueryButton);
		QueryButton.addActionListener(new QueryButtonListener(dataBase, this));
		
		JButton ClearButton = new JButton("清空");
		ClearButton.setBounds(800, 150, 100, 30);
		frame.getContentPane().add(ClearButton);
		ClearButton.addActionListener(new QueryClearButtonListener(this));
	}
	
	public boolean isIdSelected() {
		if (IDRadioButton.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getIDText() {
		return IDText.getText();
	}
	
	public boolean isNameSelected() {
		if (NameRadioButton.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getNameText() {
		return NameText.getText();
	}
	
	public boolean isDateSelected() {
		if (DateRadioButton.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Date getDate() {
		return dateChooser.getDate();
	}
	
	public boolean checkData() {
		return true;
	}
	
	public void setQueryResult(ArrayList<QueryResult> queryResults) {
		queryTabelModel.setQueryResult(queryResults);
	}
	
	public void reset() {
		IDRadioButton.setSelected(false);
		IDText.setEditable(false);
		IDText.setText("");
		NameRadioButton.setSelected(false);
		NameText.setEditable(false);
		NameText.setText("");
		DateRadioButton.setSelected(false);
		dateChooser.setEnabled(false);
		dateChooser.setSelectedNow();
		queryTabelModel.clearQueryResult();
		RefreshQueryTable();
	}
	
	public void RefreshQueryTable() {
		QueryTable.updateUI();
	}
	
	public JTable getQueryTable() {
		return QueryTable;
	}
	
	public DataBase getDataBase() {
		return dataBase;
	}
	
	public QueryTabelModel getQueryTabelModel() {
		return queryTabelModel;
	}
}
