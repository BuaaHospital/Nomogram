package com.buaa.hospital.nomogram;

import weka.core.Instance;
import weka.core.Instances;

public class QueryResult {
	
	private Instances instances;
	private Instance instance;
	private String FilePath;
	private Attribute attribute;
	private int index;
	
	public QueryResult(Instances instances, Instance instance, String FilePath, int index) {
		this.instances = instances;
		this.instance = instance;
		this.FilePath = FilePath;
		attribute = new Attribute(instance);
		this.index = index;
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
