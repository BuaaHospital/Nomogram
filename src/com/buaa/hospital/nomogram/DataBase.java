package com.buaa.hospital.nomogram;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DataBase {
	
	private Instances UnconfirmedData;
	private int UnconfirmedDataIndex = 1;
	private Instances ConfirmedData;
	private int ConfirmedDataIndex = 1;
	private Instances UntrainedData;
	private int UntrainedDataIndex = 1;
	private Instances TrainedData;
	private int TrainedDataIndex = 1;
	private Instances BadData;
	private int BadDataIndex = 1;
	
	public DataBase() throws Exception {
		Init();
	}
	
	public void Init() throws Exception {
		UnconfirmedDataIndex = 1;
		UnconfirmedData = getInstancesfromFile(Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff");
		ConfirmedDataIndex = 1;
		ConfirmedData = getInstancesfromFile(Constant.ConfirmedDataPath + "\\" + Constant.ConfirmedDataName + "_" + ConfirmedDataIndex + ".arff");
		UntrainedDataIndex = 1;
		UntrainedData = getInstancesfromFile(Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_" + UntrainedDataIndex + ".arff");
		UntrainedDataIndex = 1;
		TrainedData = getInstancesfromFile(Constant.TrainedDataPath + "\\" + Constant.TrainedDataName + "_" + TrainedDataIndex + ".arff");
		BadDataIndex = 1;
		BadData = getInstancesfromFile(Constant.BadDataPath + "\\" + Constant.BadDataName + "_" + BadDataIndex + ".arff");
	}
	
	public Instances getInstancesfromFile(String filepath) throws Exception {
		DataSource dataSource = new DataSource(filepath);
		Instances structure = dataSource.getDataSet();
		return structure;
	}
	
	public Instances NextUnconfirmedData() throws Exception {
		if (UnconfirmedDataIndex < Constant.UnconfirmedDataMaxIndex) {
			UnconfirmedDataIndex ++;
			UnconfirmedData = getInstancesfromFile(Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff");
			return UnconfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextConfirmedData() throws Exception {
		if (ConfirmedDataIndex < Constant.ConfirmedDataMaxIndex) {
			ConfirmedDataIndex ++;
			ConfirmedData = getInstancesfromFile(Constant.ConfirmedDataPath + "\\" + Constant.ConfirmedDataName + "_" + ConfirmedDataIndex + ".arff");
			return ConfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextUntrainedData() throws Exception {
		if (UntrainedDataIndex < Constant.UntrainedDataMaxIndex) {
			UntrainedDataIndex ++;
			UntrainedData = getInstancesfromFile(Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_" + UntrainedDataIndex + ".arff");
			return UntrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextTrainedData() throws Exception {
		if (TrainedDataIndex < Constant.TrainedDataMaxIndex) {
			TrainedDataIndex ++;
			TrainedData = getInstancesfromFile(Constant.TrainedDataPath + "\\" + Constant.TrainedDataName + "_" + TrainedDataIndex + ".arff");
			return TrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextBadData() throws Exception {
		if (BadDataIndex < Constant.BadDataMaxIndex) {
			BadDataIndex ++;
			BadData = getInstancesfromFile(Constant.BadDataPath + "\\" + Constant.BadDataName + "_" + BadDataIndex + ".arff");
			return BadData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getUnconfirmedData() {
		return UnconfirmedData;
	}
	
	public Instances getConfirmedData() {
		return ConfirmedData;
	}
	
	public Instances getUntrainedData() {
		return UntrainedData;
	}
	
	public Instances getTrainedData() {
		return TrainedData;
	}
	
	public Instances getBadData() {
		return BadData;
	}
	
	public boolean isInDataBase(Instance instance) throws Exception {
		Init();
		return false;
	}

}
