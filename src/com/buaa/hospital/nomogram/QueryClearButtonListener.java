package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryClearButtonListener implements ActionListener{
	
	private QueryInterface queryInterface;
	
	public QueryClearButtonListener(QueryInterface queryInterface) {
		// TODO Auto-generated constructor stub
		this.queryInterface = queryInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		queryInterface.reset();
	}

}
