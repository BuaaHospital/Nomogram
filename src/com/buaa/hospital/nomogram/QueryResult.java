package com.buaa.hospital.nomogram;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import weka.core.Instance;
import weka.core.Instances;

public class QueryResult {
	
	private Instances instances;
	private Instance instance;
	private String FilePath;
	private Attribute attribute;
	private int index;
	private DataType dataType;
	
	public QueryResult(Instances instances, Instance instance, String FilePath, int index) {
		this.instances = instances;
		this.instance = instance;
		this.FilePath = FilePath;
		attribute = new Attribute(instance);
		this.index = index;
		File file = new File(FilePath);
		String PreFileName = file.getName().split("_")[0];
		if (PreFileName.equals(Constant.UnconfirmedDataName)) {
			this.dataType = DataType.Unconfirmed;
		}
		else if (PreFileName.equals(Constant.ConfirmedDataName)) {
			this.dataType = DataType.Confirmed;
		}
		else if (PreFileName.equals(Constant.UntrainedDataName)) {
			this.dataType = DataType.Untrained;
		}
		else if (PreFileName.equals(Constant.TrainedDataName)) {
			this.dataType = DataType.Trained;
		}
		else {
			this.dataType = DataType.BadData;
		}
		
	}
	
	public Instances getInstances() {
		return instances;
	}
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public Instance getInstance() {
		return instance;
	}
	
	public String getFilePath() {
		return FilePath;
	}
	
	public boolean equals(QueryResult queryResult) {
		if (instance.toString().equals(queryResult.getInstance().toString())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public DataType getDataType() {
		return dataType;
	}
	
	public boolean isTypeChanged() {
		File file = new File(FilePath);
		if (file.getName().split(" ")[0].equals(dataType.toString())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void modifyInstance(ArrayList<String> Data) throws ParseException {
		instance.setValue(0, Double.parseDouble(Data.get(0)));
		instance.setValue(1, Data.get(1));
		instance.setValue(2, Double.parseDouble(Data.get(2)));
		if (Data.get(3).equals("Male")) {
			instance.setValue(3, 0);
		}
		else {
			instance.setValue(3, 1);
		}
		if (Data.get(4).equals("OS")) {
			instance.setValue(4, 0);
		}
		else {
			instance.setValue(4, 1);
		}
		instance.setValue(5, Double.parseDouble(Data.get(5)));
		instance.setValue(6, Double.parseDouble(Data.get(6)));
		instance.setValue(7, Double.parseDouble(Data.get(7)));
		instance.setValue(8, Double.parseDouble(Data.get(8)));
		instance.setValue(9, Double.parseDouble(Data.get(9)));
		instance.setValue(10, Double.parseDouble(Data.get(10)));
		instance.setValue(11, Double.parseDouble(Data.get(11)));
		instance.setValue(12, Double.parseDouble(Data.get(12)));
		instance.setValue(13, Double.parseDouble(Data.get(13)));
		instance.setValue(14, Double.parseDouble(Data.get(14)));
		instance.setValue(15, Double.parseDouble(Data.get(15)));
		instance.setValue(16, Double.parseDouble(Data.get(16)));
		if (Data.get(17).equals("OS")) {
			instance.setValue(17, 0);
		}
		else {
			instance.setValue(17, 1);
		}
		instance.setValue(18, Double.parseDouble(Data.get(18)));
		instance.setValue(19, Double.parseDouble(Data.get(19)));
		DateFormat df = DateFormat.getDateTimeInstance();
		Date date = df.parse(Data.get(20));
		instance.setValue(20, date.getTime());
		instance.setValue(21, Double.parseDouble(Data.get(21)));
		instance.setValue(22, Double.parseDouble(Data.get(22)));
		if (Data.get(23).equals("OS")) {
			instance.setValue(23, 0);
		}
		else {
			instance.setValue(23, 1);
		}
		instance.setValue(24, Double.parseDouble(Data.get(24)));
		instance.setValue(25, Double.parseDouble(Data.get(25)));
		instance.setValue(26, Double.parseDouble(Data.get(26)));
		instance.setValue(27, Double.parseDouble(Data.get(27)));
		instance.setValue(28, Double.parseDouble(Data.get(28)));
		if (Data.get(28).equals(Double.toString(Constant.InitDataNum))) {
			instance.setValue(29, "0");
		}
		else {
			instance.setValue(29, Data.get(28));
		}
		instance.setValue(30, Double.parseDouble(Data.get(29)));
		instance.setValue(31, Double.parseDouble(Data.get(30)));
		instance.setValue(32, Double.parseDouble(Data.get(31)));
		
	}

}
