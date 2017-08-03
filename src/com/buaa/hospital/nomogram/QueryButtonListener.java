package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import weka.core.Instance;

public class QueryButtonListener implements ActionListener{
	
	private DataBase dataBase;
	private QueryInterface queryInterface;
	private ArrayList<QueryResult> QueryResults;
	
	public QueryButtonListener(DataBase dataBase, QueryInterface queryInterface) {
		// TODO Auto-generated constructor stub
		this.dataBase = dataBase;
		this.queryInterface = queryInterface;
		QueryResults = new ArrayList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clearQueryResult();
		try {
			if (queryInterface.checkData()) {
				if (queryInterface.isIdSelected()) {
					QueryResults = dataBase.QuerybyID(queryInterface.getIDText());
					if (queryInterface.isNameSelected()) {
						QuerybyName(QueryResults, queryInterface.getNameText());
					}
					else if (queryInterface.isDateSelected()) {
						QuerybyDate(QueryResults, queryInterface.getDate());
					}
				}
				else if (queryInterface.isNameSelected()) {
					QueryResults = dataBase.QuerybyName(queryInterface.getNameText());
					if (queryInterface.isDateSelected()) {
						QuerybyDate(QueryResults, queryInterface.getDate());
					}
				}
				else if (queryInterface.isDateSelected()){
					QueryResults = dataBase.QuerybyDate(queryInterface.getDate());
				}
				else {
					
				}
			}
			queryInterface.setQueryResult(QueryResults);
			queryInterface.RefreshQueryTable();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void clearQueryResult() {
		QueryResults.clear();
	}
	
	public void QuerybyName(ArrayList<QueryResult> queryResults, String Name) {
		for (int i = queryResults.size() - 1; i >= 0; i --) {
			if (!queryResults.get(i).getInstance().stringValue(1).equals(Name)) {
				queryResults.remove(i);
			}
		}
	}
	
	public void QuerybyDate(ArrayList<QueryResult> queryResults, Date date) {
		for (int i = queryResults.size() - 1; i >= 0; i --) {
			DateFormat df = DateFormat.getDateInstance();
			Date tempdate = new Date((long)(queryResults.get(i).getInstance().value(20)));		
			if (!df.format(tempdate).equals(df.format(date))) {
				queryResults.remove(i);
			}
		}
	}
	
	public ArrayList<QueryResult> getQueryResults() {
		return QueryResults;
	}

}
