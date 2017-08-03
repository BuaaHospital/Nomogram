package com.buaa.hospital.nomogram;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class QueryTableMouseListener implements MouseListener{
	
	private QueryInterface queryInterface;
	private JPopupMenu jPopupMenu;
	private JMenuItem editMenuItem;
	private JMenuItem delMenuItem;
	
	public QueryTableMouseListener(QueryInterface queryInterface) {
		// TODO Auto-generated constructor stub
		this.queryInterface = queryInterface;
		jPopupMenu = new JPopupMenu();
		editMenuItem = new JMenuItem("编辑");
		delMenuItem = new JMenuItem("删除");
		jPopupMenu.add(editMenuItem);
		jPopupMenu.add(delMenuItem);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			int focusedRowIndex = queryInterface.getQueryTable().rowAtPoint(e.getPoint());
			int focusedColomnIndex = queryInterface.getQueryTable().columnAtPoint(e.getPoint());
			if (focusedRowIndex == -1) {
				return;
			}
			else {
				queryInterface.getQueryTable().setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
				queryInterface.getQueryTable().setColumnSelectionInterval(focusedColomnIndex, focusedColomnIndex);
				jPopupMenu.show(queryInterface.getQueryTable(), e.getX(), e.getY());
				editMenuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ModifyInterface modifyInterface = new ModifyInterface(QueryTableMouseListener.this.queryInterface.getDataBase(), queryInterface.getQueryTabelModel().getQueryResultAt(focusedRowIndex));
						modifyInterface.startRun();
					}
				});
				delMenuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
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
