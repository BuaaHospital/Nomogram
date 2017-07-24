package com.buaa.hospital.nomogram;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class HistoryTabelModel extends AbstractTableModel{

	private ArrayList<Attribute> HistoryData = new ArrayList();
	String[] HistoryTableColomnName = {"病历号", "姓名", "年龄", "性别", "眼别", "等效球镜度", "术前裸眼视力", "球镜度", "柱镜度", "轴", "最佳矫正视力", "角膜半径", "光学区", "K1", "K2", "Km", "角膜中央厚度", "主导眼", "预测球镜调整值"};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return HistoryData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return HistoryTableColomnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return HistoryData.get(rowIndex).toArrayList().get(columnIndex);
	}
	
	@Override
    public String getColumnName(int column) {
        return HistoryTableColomnName[column];
    }
	
	public void addHistoryItem(Attribute attribute) {
		if (!isExists(attribute)) {
			HistoryData.add(attribute);
		}
	}
	
	public boolean isExists(Attribute attribute) {
		Iterator<Attribute> iterator= HistoryData.iterator();
		while (iterator.hasNext()) {
			Attribute attr = iterator.next();
			if (attr.toString().equals(attribute.toString())) {
				return true;
			}
		}
		return false;
	}

}
