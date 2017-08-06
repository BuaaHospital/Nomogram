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
				Instance newInstance = new Instance(Constant.AttributeNum);
				System.out.println(instance);
				UnconfirmedData.add(newInstance);
				for (int i = 0; i < UnconfirmedData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
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
					DataSource dataSource = new DataSource(getCurrentUnconfirmedDataPath());
					UnconfirmedData = dataSource.getDataSet();
					UnconfirmedData.add(instance);
					break;
				}
			}
		}
		System.out.println(UnconfirmedData.toString());
		saveArff(UnconfirmedData, getCurrentUnconfirmedDataPath());
	}
	
	public void addToConfirmedData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(ConfirmedData.toString());
			if (ConfirmedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(Constant.AttributeNum);
				System.out.println(instance);
				ConfirmedData.add(newInstance);
				for (int i = 0; i < ConfirmedData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
						ConfirmedData.lastInstance().setValue(i, instance.value(i));
					}
					else {
						ConfirmedData.lastInstance().setValue(i, instance.stringValue(i));
					}
				}
				System.out.println(ConfirmedData.toString());
				break;
			}
			else {
				if (getNextConfirmedData() == null) {
					Constant.ConfirmedDataMaxIndex ++;
					ConfirmedDataIndex = Constant.ConfirmedDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentConfirmedDataPath(), false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(getCurrentConfirmedDataPath());
					ConfirmedData = dataSource.getDataSet();
					ConfirmedData.add(instance);
					break;
				}
			}
		}
		System.out.println(ConfirmedData.toString());
		saveArff(ConfirmedData, getCurrentConfirmedDataPath());
	}
	
	public void addToUntrainedData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(UntrainedData.toString());
			if (UntrainedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(Constant.AttributeNum);
				System.out.println(instance);
				UntrainedData.add(newInstance);
				for (int i = 0; i < UntrainedData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
						UntrainedData.lastInstance().setValue(i, instance.value(i));
					}
					else {
						UntrainedData.lastInstance().setValue(i, instance.stringValue(i));
					}
				}
				System.out.println(UntrainedData.toString());
				break;
			}
			else {
				if (getNextUntrainedData() == null) {
					Constant.UntrainedDataMaxIndex ++;
					UntrainedDataIndex = Constant.UntrainedDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentUntrainedDataPath(), false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(getCurrentUntrainedDataPath());
					UntrainedData = dataSource.getDataSet();
					UntrainedData.add(instance);
					break;
				}
			}
		}
		System.out.println(UntrainedData.toString());
		saveArff(UntrainedData, getCurrentUntrainedDataPath());
	}
	
	public void addToTrainedData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(TrainedData.toString());
			if (TrainedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(Constant.AttributeNum);
				System.out.println(instance);
				TrainedData.add(newInstance);
				for (int i = 0; i < TrainedData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
						TrainedData.lastInstance().setValue(i, instance.value(i));
					}
					else {
						TrainedData.lastInstance().setValue(i, instance.stringValue(i));
					}
				}
				System.out.println(TrainedData.toString());
				break;
			}
			else {
				if (getNextTrainedData() == null) {
					Constant.TrainedDataMaxIndex ++;
					TrainedDataIndex = Constant.TrainedDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentTrainedDataPath(), false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(getCurrentTrainedDataPath());
					TrainedData = dataSource.getDataSet();
					TrainedData.add(instance);
					break;
				}
			}
		}
		System.out.println(TrainedData.toString());
		saveArff(TrainedData, getCurrentTrainedDataPath());
	}
	
	public void addToTrainedData(Instances instances) throws Exception {
		Init();
		for (int k = 0; k < instances.numInstances(); k ++) {
			System.out.println(TrainedData.toString());
			if (TrainedData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(Constant.AttributeNum);
				TrainedData.add(newInstance);
				for (int i = 0; i < TrainedData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
						TrainedData.lastInstance().setValue(i, instances.instance(k).value(i));
					}
					else {
						TrainedData.lastInstance().setValue(i, instances.instance(k).stringValue(i));
					}
				}
			}
			else {
				saveArff(TrainedData, getCurrentTrainedDataPath());
				if (getNextTrainedData() == null) {
					Constant.TrainedDataMaxIndex ++;
					TrainedDataIndex = Constant.TrainedDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentTrainedDataPath(), false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(getCurrentTrainedDataPath());
					TrainedData = dataSource.getDataSet();
					Instance newInstance = new Instance(Constant.AttributeNum);
					TrainedData.add(newInstance);
					for (int i = 0; i < TrainedData.numAttributes(); i ++) {
						if (i != 1 && i != 29) {
							TrainedData.lastInstance().setValue(i, instances.instance(k).value(i));
						}
						else {
							TrainedData.lastInstance().setValue(i, instances.instance(k).stringValue(i));
						}
					}
				}
			}
		}
		saveArff(TrainedData, getCurrentTrainedDataPath());
	}
	
	public void addToBadData(Instance instance) throws Exception {
		Init();
		for (; ;) {
			System.out.println(BadData.toString());
			if (BadData.numInstances() < Constant.MaxInstanceItem) {
				Instance newInstance = new Instance(Constant.AttributeNum);
				System.out.println(instance);
				BadData.add(newInstance);
				for (int i = 0; i < BadData.numAttributes(); i ++) {
					if (i != 1 && i != 29) {
						BadData.lastInstance().setValue(i, instance.value(i));
					}
					else {
						BadData.lastInstance().setValue(i, instance.stringValue(i));
					}
				}
				System.out.println(BadData.toString());
				break;
			}
			else {
				if (getNextBadData() == null) {
					Constant.BadDataMaxIndex ++;
					BadDataIndex = Constant.BadDataMaxIndex;
					BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentBadDataPath(), false)));
					bWriter.write(Constant.ArffFileHead);
					bWriter.flush();
					bWriter.close();
					DataSource dataSource = new DataSource(getCurrentBadDataPath());
					BadData = dataSource.getDataSet();
					BadData.add(instance);
					break;
				}
			}
		}
		System.out.println(BadData.toString());
		saveArff(BadData, getCurrentBadDataPath());
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
	
	public void deleteInstance(QueryResult queryResult) throws IOException {
		Instances instances = queryResult.getInstances();
		instances.delete(queryResult.getIndex());
		saveArff(instances, queryResult.getFilePath());
	}
	
	public void modifyInstance(QueryResult queryResult) throws Exception {
		if (queryResult.isTypeChanged()) {
			deleteInstance(queryResult);
			if (queryResult.getDataType() == DataType.Unconfirmed) {
				addToUnconfirmedData(queryResult.getInstance());
			}
			else if (queryResult.getDataType() == DataType.Confirmed) {
				addToConfirmedData(queryResult.getInstance());
			}
			else if (queryResult.getDataType() == DataType.Untrained) {
				addToUntrainedData(queryResult.getInstance());
			}
			else if (queryResult.getDataType() == DataType.Trained) {
				addToTrainedData(queryResult.getInstance());
			}
			else {
				addToBadData(queryResult.getInstance());
			}
		}
		else {
			System.out.println(queryResult.getInstance().toString());
			saveArff(queryResult.getInstances(), queryResult.getFilePath());
		}
	}
	
	public Instances[] getAllNeedTrainedData() throws Exception {
		Init();
		Instances AllInstances = Attribute.GenEmptyInstances();
		Instances OSInstances = Attribute.GenEmptyInstances();
		Instances ODInstances = Attribute.GenEmptyInstances();
		Instances[] instances = {AllInstances, OSInstances, ODInstances};
		
		for (; ;) {
			for (int i = 0; i < UntrainedData.numInstances(); i ++) {
				Instance instance = new Instance(Constant.AttributeNum);
				AllInstances.add(instance);
				for (int j = 0; j < AllInstances.numAttributes(); j ++) {
					if (j != 1 && j != 29) {
						AllInstances.lastInstance().setValue(j, UntrainedData.instance(i).value(j));
					}
					else {
						AllInstances.lastInstance().setValue(j, UntrainedData.instance(i).stringValue(j));
					}
				}
				if (UntrainedData.instance(i).value(4) == 0) {
					OSInstances.add(instance);
					for (int j = 0; j < OSInstances.numAttributes(); j ++) {
						if (j != 1 && j != 29) {
							OSInstances.lastInstance().setValue(j, UntrainedData.instance(i).value(j));
						}
						else {
							OSInstances.lastInstance().setValue(j, UntrainedData.instance(i).stringValue(j));
						}
					}
				}
				else {
					ODInstances.add(instance);
					for (int j = 0; j < ODInstances.numAttributes(); j ++) {
						if (j != 1 && j != 29) {
							ODInstances.lastInstance().setValue(j, UntrainedData.instance(i).value(j));
						}
						else {
							ODInstances.lastInstance().setValue(j, UntrainedData.instance(i).stringValue(j));
						}
					}
				}
			}
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		for (; ;) {
			for (int i = 0; i < TrainedData.numInstances(); i ++) {
				Instance instance = new Instance(Constant.AttributeNum);
				AllInstances.add(instance);
				for (int j = 0; j < AllInstances.numAttributes(); j ++) {
					if (j != 1 && j != 29) {
						AllInstances.lastInstance().setValue(j, TrainedData.instance(i).value(j));
					}
					else {
						AllInstances.lastInstance().setValue(j, TrainedData.instance(i).stringValue(j));
					}
				}
				if (TrainedData.instance(i).value(4) == 0) {
					OSInstances.add(instance);
					for (int j = 0; j < OSInstances.numAttributes(); j ++) {
						if (j != 1 && j != 29) {
							OSInstances.lastInstance().setValue(j, TrainedData.instance(i).value(j));
						}
						else {
							OSInstances.lastInstance().setValue(j, TrainedData.instance(i).stringValue(j));
						}
					}
				}
				else {
					ODInstances.add(instance);
					for (int j = 0; j < ODInstances.numAttributes(); j ++) {
						if (j != 1 && j != 29) {
							ODInstances.lastInstance().setValue(j, TrainedData.instance(i).value(j));
						}
						else {
							ODInstances.lastInstance().setValue(j, TrainedData.instance(i).stringValue(j));
						}
					}
				}
			}
			if (getNextTrainedData() == null) {
				break;
			}
		}
		return instances;	
	}
	
	public Instances getAllUnTrainedData() throws Exception {
		Init();
		Instances AllUntrainedInstances = Attribute.GenEmptyInstances();
		
		for (; ;) {
			for (int i = 0; i < UntrainedData.numInstances(); i ++) {
				Instance instance = new Instance(Constant.AttributeNum);
				AllUntrainedInstances.add(instance);
				for (int j = 0; j < AllUntrainedInstances.numAttributes(); j ++) {
					if (j != 1 && j != 29) {
						AllUntrainedInstances.lastInstance().setValue(j, UntrainedData.instance(i).value(j));
					}
					else {
						AllUntrainedInstances.lastInstance().setValue(j, UntrainedData.instance(i).stringValue(j));
					}
				}
			}
			if (getNextUntrainedData() == null) {
				break;
			}
		}
		return AllUntrainedInstances;	
	}

}
