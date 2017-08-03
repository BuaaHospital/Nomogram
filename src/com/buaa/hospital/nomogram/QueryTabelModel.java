package com.buaa.hospital.nomogram;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import weka.core.Instance;

public class QueryTabelModel extends AbstractTableModel{
	private ArrayList<QueryResult> QueryResult = new ArrayList();
	private String[] QueryTableColomnName = {"病历号", "姓名", "年龄", "性别", "眼别", "等效球镜度", "术前裸眼视力", "球镜度", "柱镜度", "轴", "最佳矫正视力", "角膜半径", "光学区", "K1", "K2", "Km", "角膜中央厚度", "主导眼", "预测球镜调整值", "残余基质厚度", "手术时间", "湿度", "温度", "First eye to treat", "能量", "OBL", "角膜帽厚度", "切口位置", "实际球镜调整值", "术后验光度（1天）", "术后验光度（1月）", "术后验光度（3月）"};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return QueryResult.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return QueryTableColomnName.length;
	}
	
	@Override
    public String getColumnName(int column) {
        return QueryTableColomnName[column];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return QueryResult.get(rowIndex).getAttribute().toArrayList().get(columnIndex);
	}
	
	public void addQueryItem(QueryResult queryResult) {
		QueryResult.add(queryResult);
	}
	
	public void setQueryResult(ArrayList<QueryResult> queryResults) {
		this.QueryResult = queryResults;
	}
	
	public void clearQueryResult() {
		this.QueryResult = new ArrayList();
	}
	
	public QueryResult getQueryResultAt(int i) {
		return QueryResult.get(i);
	}

}
