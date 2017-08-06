package com.buaa.hospital.nomogram;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class QueryTableMouseListener implements MouseListener{
	
	private QueryInterface queryInterface;
	private JPopupMenu jPopupMenu;
	private JMenuItem editMenuItem;
	private JMenuItem delMenuItem;
	private EditActionListener editActionListener = new EditActionListener(0);
	private DeleteActionListener deleteActionListener = new DeleteActionListener(0);
	
	public QueryTableMouseListener(QueryInterface queryInterface) {
		// TODO Auto-generated constructor stub
		this.queryInterface = queryInterface;
		jPopupMenu = new JPopupMenu();
		editMenuItem = new JMenuItem("编辑");
		editMenuItem.addActionListener(editActionListener);
		delMenuItem = new JMenuItem("删除");
		delMenuItem.addActionListener(deleteActionListener);
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
				editActionListener.setfocusedRowIndex(focusedRowIndex);
				deleteActionListener.setfocusedRowIndex(focusedRowIndex);
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
	
	public class EditActionListener implements ActionListener {
		
		public int focusedRowIndex;
		
		public EditActionListener(int focusedRowIndex) {
			// TODO Auto-generated constructor stub
			this.focusedRowIndex = focusedRowIndex;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ModifyInterface modifyInterface = new ModifyInterface(QueryTableMouseListener.this.queryInterface.getDataBase(), queryInterface, queryInterface.getQueryTabelModel().getQueryResultAt(focusedRowIndex));
			modifyInterface.startRun();
		}
		
		public void setfocusedRowIndex(int focusedRowIndex) {
			this.focusedRowIndex = focusedRowIndex;
		}
		
	}
	
	public class DeleteActionListener implements ActionListener {
		
		private int focusedRowIndex;
		
		public DeleteActionListener(int focusedRowIndex) {
			// TODO Auto-generated constructor stub
			this.focusedRowIndex = focusedRowIndex;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QueryResult queryResult = queryInterface.getQueryTabelModel().getQueryResultAt(focusedRowIndex);
			try {
				queryInterface.getDataBase().deleteInstance(queryResult);
				queryInterface.getQueryTabelModel().removeQueryResultAt(focusedRowIndex);
				queryInterface.RefreshQueryTable();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		public void setfocusedRowIndex(int focusedRowIndex) {
			this.focusedRowIndex = focusedRowIndex;
		}
		
	}

}
