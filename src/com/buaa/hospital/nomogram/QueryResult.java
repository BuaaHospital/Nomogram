package com.buaa.hospital.nomogram;

import java.io.File;

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
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public Instance getInstance() {
		return instance;
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

}
