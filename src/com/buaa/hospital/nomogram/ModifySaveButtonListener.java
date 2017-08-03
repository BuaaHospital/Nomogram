package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifySaveButtonListener implements ActionListener{
	
	private DataBase dataBase;
	private ModifyInterface modifyInterface;
	
	public ModifySaveButtonListener(ModifyInterface modifyInterface, DataBase dataBase) {
		// TODO Auto-generated constructor stub
		this.modifyInterface = modifyInterface;
		this.dataBase = dataBase;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			modifyInterface.SaveModify();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
