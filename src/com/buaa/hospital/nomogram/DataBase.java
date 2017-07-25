package com.buaa.hospital.nomogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
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
		UnconfirmedData = multilayer.loaddata(Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff");
		ConfirmedDataIndex = 1;
		ConfirmedData = multilayer.loaddata(Constant.ConfirmedDataPath + "\\" + Constant.ConfirmedDataName + "_" + ConfirmedDataIndex + ".arff");
		UntrainedDataIndex = 1;
		UntrainedData = multilayer.loaddata(Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_" + UntrainedDataIndex + ".arff");
		UntrainedDataIndex = 1;
		TrainedData = multilayer.loaddata(Constant.TrainedDataPath + "\\" + Constant.TrainedDataName + "_" + TrainedDataIndex + ".arff");
		BadDataIndex = 1;
		BadData = multilayer.loaddata(Constant.BadDataPath + "\\" + Constant.BadDataName + "_" + BadDataIndex + ".arff");
	}
	
	
	public Instances getNextUnconfirmedData() throws Exception {
		if (UnconfirmedDataIndex < Constant.UnconfirmedDataMaxIndex) {
			UnconfirmedDataIndex ++;
			UnconfirmedData = multilayer.loaddata(Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff");
			return UnconfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextConfirmedData() throws Exception {
		if (ConfirmedDataIndex < Constant.ConfirmedDataMaxIndex) {
			ConfirmedDataIndex ++;
			ConfirmedData = multilayer.loaddata(Constant.ConfirmedDataPath + "\\" + Constant.ConfirmedDataName + "_" + ConfirmedDataIndex + ".arff");
			return ConfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextUntrainedData() throws Exception {
		if (UntrainedDataIndex < Constant.UntrainedDataMaxIndex) {
			UntrainedDataIndex ++;
			UntrainedData = multilayer.loaddata(Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_" + UntrainedDataIndex + ".arff");
			return UntrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextTrainedData() throws Exception {
		if (TrainedDataIndex < Constant.TrainedDataMaxIndex) {
			TrainedDataIndex ++;
			TrainedData = multilayer.loaddata(Constant.TrainedDataPath + "\\" + Constant.TrainedDataName + "_" + TrainedDataIndex + ".arff");
			return TrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextBadData() throws Exception {
		if (BadDataIndex < Constant.BadDataMaxIndex) {
			BadDataIndex ++;
			BadData = multilayer.loaddata(Constant.BadDataPath + "\\" + Constant.BadDataName + "_" + BadDataIndex + ".arff");
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
		for (; ;) {
			if (isInInstances(UnconfirmedData, instance)) {
				return true;
			}
			if (getNextUnconfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			if (isInInstances(ConfirmedData, instance)) {
				return true;
			}
			if (getNextConfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			if (isInInstances(UntrainedData, instance)) {
				return true;
			}
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			if (isInInstances(TrainedData, instance)) {
				return true;
			}
			if (getNextTrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			if (isInInstances(BadData, instance)) {
				return true;
			}
			if (getNextBadData() == null) {
				break;
			}
		}
		return false;
	}
	
	public boolean isInInstances(Instances instances, Instance instance) {
		for (int i = 0; i < instances.numInstances(); i ++) {
			if (SplitInstance(instances.instance(i)).equals(SplitInstance(instance))) {
				return true;
			}
		}
		return false;
	}
	
	public String SplitInstance(Instance instance) {
		String[] strings = instance.toString().split(",");
		String string = "";
		for (int i = 0; i < 19; i ++) {
			string += strings[i];
		}
		return string;
	}
	
	public void addToUnconfirmedData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(UnconfirmedData.toString());
			if (UnconfirmedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(32);
				System.out.println(instance);
				UnconfirmedData.add(newInstance);
				for (int i = 0; i < UnconfirmedData.numAttributes(); i ++) {
					if (i != 1) {
						UnconfirmedData.lastInstance().setValue(i, instance.value(i));
					}
					else {
						UnconfirmedData.lastInstance().setValue(i, instance.stringValue(i));
					}
				}
				System.out.println(UnconfirmedData.toString());
				break;
			}
			else {
				if (getNextUnconfirmedData() == null) {
					Constant.UnconfirmedDataMaxIndex ++;
					UnconfirmedDataIndex = Constant.UnconfirmedDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff", false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(Constant.TempInstanceFilePath);
					UnconfirmedData = dataSource.getDataSet();
					UnconfirmedData.add(instance);
					break;
				}
			}
		}
		System.out.println(UnconfirmedData.toString());
		saveArff(UnconfirmedData, Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff");
	}
	
	public static void saveArff(Instances instances, String FileName) throws IOException {
		ArffSaver saver = new ArffSaver();  
        saver.setInstances(instances);
        File file = new File(FileName);
        System.out.println(file.getAbsolutePath());
        saver.setFile(file);  
        saver.writeBatch();
//		BufferedWriter writer = new BufferedWriter(new FileWriter(FileName));  
//		writer.write(instances.toString());  
//		writer.flush();  
//		writer.close();
	}

}
