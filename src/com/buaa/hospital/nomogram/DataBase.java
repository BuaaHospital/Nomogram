package com.buaa.hospital.nomogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		UnconfirmedData = multilayer.loaddata(getCurrentUnconfirmedDataPath());
		ConfirmedDataIndex = 1;
		ConfirmedData = multilayer.loaddata(getCurrentConfirmedDataPath());
		UntrainedDataIndex = 1;
		UntrainedData = multilayer.loaddata(getCurrentUntrainedDataPath());
		UntrainedDataIndex = 1;
		TrainedData = multilayer.loaddata(getCurrentTrainedDataPath());
		BadDataIndex = 1;
		BadData = multilayer.loaddata(getCurrentBadDataPath());
	}
	
	
	public Instances getNextUnconfirmedData() throws Exception {
		if (UnconfirmedDataIndex < Constant.UnconfirmedDataMaxIndex) {
			UnconfirmedDataIndex ++;
			UnconfirmedData = multilayer.loaddata(getCurrentUnconfirmedDataPath());
			return UnconfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextConfirmedData() throws Exception {
		if (ConfirmedDataIndex < Constant.ConfirmedDataMaxIndex) {
			ConfirmedDataIndex ++;
			ConfirmedData = multilayer.loaddata(getCurrentConfirmedDataPath());
			return ConfirmedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextUntrainedData() throws Exception {
		if (UntrainedDataIndex < Constant.UntrainedDataMaxIndex) {
			UntrainedDataIndex ++;
			UntrainedData = multilayer.loaddata(getCurrentUntrainedDataPath());
			return UntrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextTrainedData() throws Exception {
		if (TrainedDataIndex < Constant.TrainedDataMaxIndex) {
			TrainedDataIndex ++;
			TrainedData = multilayer.loaddata(getCurrentTrainedDataPath());
			return TrainedData;
		}
		else {
			return null;
		}
		
	}
	
	public Instances getNextBadData() throws Exception {
		if (BadDataIndex < Constant.BadDataMaxIndex) {
			BadDataIndex ++;
			BadData = multilayer.loaddata(getCurrentBadDataPath());
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
//		for (int i = 0; i < 19; i ++) {
//			string += strings[i];
//		}
		string = strings[0] + strings[4];
		return string;
	}
	
	public void addToUnconfirmedData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(UnconfirmedData.toString());
			if (UnconfirmedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(33);
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
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentUnconfirmedDataPath(), false)));
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
		saveArff(UnconfirmedData, getCurrentUnconfirmedDataPath());
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
	
	public ArrayList<QueryResult> QuerybyID(String ID) throws Exception {
		ArrayList<QueryResult> queryResults = new ArrayList();
		Init();
		for (; ;) {
			queryResults.addAll(QuerybyIDfromInstances(UnconfirmedData, ID, getCurrentUnconfirmedDataPath()));
			if (getNextUnconfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyIDfromInstances(ConfirmedData, ID, getCurrentConfirmedDataPath()));
			if (getNextConfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyIDfromInstances(UntrainedData, ID, getCurrentUntrainedDataPath()));
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyIDfromInstances(TrainedData, ID, getCurrentTrainedDataPath()));
			if (getNextTrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyIDfromInstances(BadData, ID, getCurrentBadDataPath()));
			if (getNextBadData() == null) {
				break;
			}
		}
		return queryResults;
	}
	
	public ArrayList<QueryResult> QuerybyName(String Name) throws Exception {
		ArrayList<QueryResult> queryResults = new ArrayList();
		Init();
		for (; ;) {
			queryResults.addAll(QuerybyNamefromInstances(UnconfirmedData, Name, getCurrentUnconfirmedDataPath()));
			if (getNextUnconfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyNamefromInstances(ConfirmedData, Name, getCurrentConfirmedDataPath()));
			if (getNextConfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyNamefromInstances(UntrainedData, Name, getCurrentUntrainedDataPath()));
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyNamefromInstances(TrainedData, Name, getCurrentTrainedDataPath()));
			if (getNextTrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyNamefromInstances(BadData, Name, getCurrentBadDataPath()));
			if (getNextBadData() == null) {
				break;
			}
		}
		return queryResults;
	}
	
	public ArrayList<QueryResult> QuerybyDate(Date date) throws Exception {
		ArrayList<QueryResult> queryResults = new ArrayList();
		Init();
		for (; ;) {
			queryResults.addAll(QuerybyDatefromInstances(UnconfirmedData, date, getCurrentUnconfirmedDataPath()));
			if (getNextUnconfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyDatefromInstances(ConfirmedData, date, getCurrentConfirmedDataPath()));
			if (getNextConfirmedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyDatefromInstances(UntrainedData, date, getCurrentUntrainedDataPath()));
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyDatefromInstances(TrainedData, date, getCurrentTrainedDataPath()));
			if (getNextTrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			queryResults.addAll(QuerybyDatefromInstances(BadData, date, getCurrentBadDataPath()));
			if (getNextBadData() == null) {
				break;
			}
		}
		return queryResults;
	}
	
	
	
	public ArrayList<QueryResult> QuerybyIDfromInstances(Instances instances, String ID, String FilePath) {
		ArrayList<QueryResult> queryResults = new ArrayList();
		for (int i = 0; i < instances.numInstances(); i ++) {
			if (instances.instance(i).value(0) == Double.parseDouble(ID)) {
				QueryResult queryResult = new QueryResult(instances, instances.instance(i), FilePath, i);
				queryResults.add(queryResult);
			}
		}
		return queryResults;
	}
	
	public ArrayList<QueryResult> QuerybyNamefromInstances(Instances instances, String Name, String FilePath) {
		ArrayList<QueryResult> queryResults = new ArrayList();
		for (int i = 0; i < instances.numInstances(); i ++) {
			if (instances.instance(i).stringValue(1).equals(Name)) {
				QueryResult queryResult = new QueryResult(instances, instances.instance(i), FilePath, i);
				queryResults.add(queryResult);
			}
		}
		return queryResults;
	}
	
	public ArrayList<QueryResult> QuerybyDatefromInstances(Instances instances, Date date, String FilePath) {
		ArrayList<QueryResult> queryResults = new ArrayList();
		for (int i = 0; i < instances.numInstances(); i ++) {
			DateFormat df = DateFormat.getDateInstance();
			Date tempDate = new Date((long)(instances.instance(i).value(20)));
			if (df.format(tempDate).equals(df.format(date))) {
				QueryResult queryResult = new QueryResult(instances, instances.instance(i), FilePath, i);
				queryResults.add(queryResult);
			}
		}
		return queryResults;
	}
	
	public String getCurrentUnconfirmedDataPath() {
		return Constant.UnconfirmedDataPath + "\\" + Constant.UnconfirmedDataName + "_" + UnconfirmedDataIndex + ".arff";
	}
	
	public String getCurrentConfirmedDataPath() {
		return Constant.ConfirmedDataPath + "\\" + Constant.ConfirmedDataName + "_" + ConfirmedDataIndex + ".arff";
	}
	
	public String getCurrentUntrainedDataPath() {
		return Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_" + UntrainedDataIndex + ".arff";
	}
	
	public String getCurrentTrainedDataPath() {
		return Constant.TrainedDataPath + "\\" + Constant.TrainedDataName + "_" + TrainedDataIndex + ".arff";
	}
	
	public String getCurrentBadDataPath() {
		return Constant.BadDataPath + "\\" + Constant.BadDataName + "_" + BadDataIndex + ".arff";
	}

}
