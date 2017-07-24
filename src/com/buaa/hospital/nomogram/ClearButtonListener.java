package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener{
	
	private PredictInterface predictInterface;
	public ClearButtonListener(PredictInterface predictInterface) {
		// TODO Auto-generated constructor stub
		this.predictInterface = predictInterface;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		predictInterface.clear();
	}

}
