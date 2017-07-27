package com.buaa.hospital.nomogram;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class QueryInterface {

	private JFrame frame;
	private DataBase dataBase;

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
		initialize();
		this.dataBase = dataBase;
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
	}

}
