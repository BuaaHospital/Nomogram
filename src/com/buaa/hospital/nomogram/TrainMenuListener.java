package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class TrainMenuListener implements ActionListener {
	
	private DataBase dataBase;
	private JFrame frame;
	private JProgressBar jProgressBar;
	
	public TrainMenuListener(DataBase dataBase, JFrame frame, JProgressBar jProgressBar) {
		// TODO Auto-generated constructor stub
		this.dataBase = dataBase;
		this.frame = frame;
		this.jProgressBar = jProgressBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				jProgressBar.setMinimum(1);
				jProgressBar.setMaximum(100);
				jProgressBar.setToolTipText("正在初始化设置...");
				File LatestMultiPreceptionOSModelsPath = new File(Constant.LatestMultiPreceptionOSModelsPath);
				File LatestMultiPreceptionODModelsPath = new File(Constant.HistoryMultiPreceptionODModelsPath);
				
				frame.getContentPane().add(jProgressBar);
			}
			
		}.start();
	}
	
	public void MultiPreceptionTrain() {
		
	}

}
