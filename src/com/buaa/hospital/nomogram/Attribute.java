package com.buaa.hospital.nomogram;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.WorkbenchLayoutSettingsTransfer;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

enum SexEnum {
	Male,
	Female
}

enum EyeEnum {
	OS,
	OD
}

public class Attribute {
	private double ID;
	private String Name;
	private double Age;
	private SexEnum Sex;
	private EyeEnum Eye;
	private double SE;
	private double UCVA;
	private double SD;
	private double CD;
	private double Axis;
	private double BCVA;
	private double CornealRadius;
	private double OpticalZone;
	private double K1;
	private double K2;
	private double Km;
	private double CCT;
	private EyeEnum LeadEye;
	private double PredictNomogram;
	private double RST;
	private double Time;
	private double Humidity;
	private double Temperature;
	private EyeEnum FirstEyeToTreat;
	private double Energy;
	private double OBL;
	private double Thickness;
	private double Position;
	private double RealNomogram;
	private double SDAfterOneDay;
	private double SDAfterThreeMonths;
	private double SDAfterSixMonths;
	
	
	public Attribute() {
		init();
	}
	
	public Attribute(PredictInterface predictInterface) {
		ArrayList<String> AttributeData = predictInterface.toArrayList();
		ID = Double.parseDouble(AttributeData.get(0));
		Name = AttributeData.get(1);
		Age = Double.parseDouble(AttributeData.get(2));
		if (AttributeData.get(3).equals("Male")) {
			Sex = SexEnum.Male;
		}
		else {
			Sex = SexEnum.Female;
		}
		if (AttributeData.get(4).equals("OS")) {
			Eye = EyeEnum.OS;
		}
		else {
			Eye = EyeEnum.OD;
		}
		SE = Double.parseDouble(AttributeData.get(5));
		UCVA = Double.parseDouble(AttributeData.get(6));
		SD = Double.parseDouble(AttributeData.get(7));
		CD = Double.parseDouble(AttributeData.get(8));
		Axis = Double.parseDouble(AttributeData.get(9));
		BCVA = Double.parseDouble(AttributeData.get(10));
		CornealRadius = Double.parseDouble(AttributeData.get(11));
		OpticalZone = Double.parseDouble(AttributeData.get(12));
		K1 = Double.parseDouble(AttributeData.get(13));
		K2 = Double.parseDouble(AttributeData.get(14));
		Km = Double.parseDouble(AttributeData.get(15));
		CCT = Double.parseDouble(AttributeData.get(16));
		if (AttributeData.get(17).equals("OS")) {
			LeadEye = EyeEnum.OS;
		}
		else {
			LeadEye = EyeEnum.OD;
		}
		PredictNomogram = 0;
		RST = 0;
		Time = 0;
		Humidity = 0;
		Temperature = 0;
		FirstEyeToTreat = EyeEnum.OS;
		Energy = 0;
		OBL = 0;
		Thickness = 0;
		Position = 0;
		RealNomogram = 0;
		SDAfterOneDay = 0;
		SDAfterThreeMonths = 0;
		SDAfterSixMonths = 0;
	}
	
	public void init() {
		ID = 0;
		Name = "";
		Sex = SexEnum.Male;
		Eye = EyeEnum.OS;
		Age = 0;
		SE = 0;
		UCVA = 0;
		SD = 0;
		CD = 0;
		Axis = 0;
		BCVA = 0;
		CornealRadius = 0;
		OpticalZone = 0;
		K1 = 0;
		K2 = 0;
		Km = 0;
		CCT = 0;
		LeadEye = EyeEnum.OS;
		PredictNomogram = 0;
		RST = 0;
		Time = 0;
		Humidity = 0;
		Temperature = 0;
		FirstEyeToTreat = EyeEnum.OS;
		Energy = 0;
		OBL = 0;
		Thickness = 0;
		Position = 0;
		RealNomogram = 0;
		SDAfterOneDay = 0;
		SDAfterThreeMonths = 0;
		SDAfterSixMonths = 0;
	}
	
	public Instance GenInstance() throws Exception {
		Instances structure = GenInstances();
		return structure.instance(0);
	}
	
	public double PredictbyMultiPreception(int ModelIndex) throws Exception {
		String curModelPath = "";
		Instance instance = this.GenMultiPreceptionInstance();
		if (Eye == EyeEnum.OS) {
			if (ModelIndex == 0) {
				curModelPath = Constant.LatestMultiPreceptionOSModelsPath;
			}
			else {
				curModelPath = Constant.HistoryMultiPreceptionOSModelsPath;
			}
		}
		else {
			if (ModelIndex == 0) {
				curModelPath = Constant.LatestMultiPreceptionODModelsPath;
			}
			else {
				curModelPath = Constant.HistoryMultiPreceptionODModelsPath;
			}
		}
		return multilayer.Predict(curModelPath, instance);
	}
	
	public Instance GenMultiPreceptionInstance() throws Exception {
		Instances structure = GenMultiPreceptionInstances();
		return structure.instance(0);
	}
	
	public void setNomogram(double nomogram) {
		this.PredictNomogram = nomogram;
	}
	
	public String toString() {
		return ID + "," + Name + "," + (Sex == SexEnum.Male? 0: 1) + "," + (Eye == EyeEnum.OS? 0: 1) + "," + Age + "," + SE + "," + UCVA + "," + SD + "," + CD + "," + Axis + "," + BCVA + "," + CornealRadius + "," + OpticalZone + "," + K1 + "," + K2 + "," + Km + "," + CCT + "," + (LeadEye == EyeEnum.OS? 0: 1) + "," + PredictNomogram + "," + RST + "," + Time + "," + Humidity + "," + Temperature + "," + (FirstEyeToTreat == EyeEnum.OS? 0: 1) + "," + Energy + "," + OBL + "," + Thickness + "," + Position + "," + RealNomogram + "," + SDAfterOneDay + "," + SDAfterThreeMonths + "," + SDAfterSixMonths;
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> AttributeArrayList = new ArrayList();
		AttributeArrayList.add(Double.toString(ID));
		AttributeArrayList.add(Name);
		AttributeArrayList.add(Double.toString(Age));
		AttributeArrayList.add(Sex.toString());
		AttributeArrayList.add(Eye.toString());
		AttributeArrayList.add(Double.toString(SE));
		AttributeArrayList.add(Double.toString(UCVA));
		AttributeArrayList.add(Double.toString(SD));
		AttributeArrayList.add(Double.toString(CD));
		AttributeArrayList.add(Double.toString(Axis));
		AttributeArrayList.add(Double.toString(BCVA));
		AttributeArrayList.add(Double.toString(CornealRadius));
		AttributeArrayList.add(Double.toString(OpticalZone));
		AttributeArrayList.add(Double.toString(K1));
		AttributeArrayList.add(Double.toString(K2));
		AttributeArrayList.add(Double.toString(Km));
		AttributeArrayList.add(Double.toString(CCT));
		AttributeArrayList.add(LeadEye.toString());
		AttributeArrayList.add(Double.toString(PredictNomogram));
		AttributeArrayList.add(Double.toString(RST));
		AttributeArrayList.add(Double.toString(Time));
		AttributeArrayList.add(Double.toString(Humidity));
		AttributeArrayList.add(Double.toString(Temperature));
		AttributeArrayList.add(FirstEyeToTreat.toString());
		AttributeArrayList.add(Double.toString(Energy));
		AttributeArrayList.add(Double.toString(OBL));
		AttributeArrayList.add(Double.toString(Thickness));
		AttributeArrayList.add(Double.toString(Position));
		AttributeArrayList.add(Double.toString(RealNomogram));
		AttributeArrayList.add(Double.toString(SDAfterOneDay));
		AttributeArrayList.add(Double.toString(SDAfterThreeMonths));
		AttributeArrayList.add(Double.toString(SDAfterSixMonths));
		return AttributeArrayList;
	}
	
	public void generateArffFile(Instances instances, String path) {
	    ArffSaver saver = new ArffSaver();
	    saver.setInstances(instances);
	    try {
	    	saver.setFile(new File(path));
	        saver.writeBatch();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public Instances GenMultiPreceptionInstances() throws Exception {
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.TempInstanceFilePath, false)));
		String string = ID + "," + Name + "," + (Sex == SexEnum.Male? 0: 1) + "," + (Eye == EyeEnum.OS? 0: 1) + "," + Age + "," + SE + "," + UCVA + "," + SD + "," + CD + "," + Axis + "," + BCVA + "," + CornealRadius + "," + OpticalZone + "," + K1 + "," + K2 + "," + Km + "," + CCT + "," + (LeadEye == EyeEnum.OS? 0: 1) + "," + PredictNomogram + "," + RST + "," + Time + "," + Humidity + "," + Temperature + "," + (FirstEyeToTreat == EyeEnum.OS? 0: 1) + "," + Energy + "," + OBL + "," + Thickness + "," + Position + "," + RealNomogram + "," + SDAfterOneDay + "," + SDAfterThreeMonths + "," + SDAfterSixMonths;
		bWriter.write(Constant.ArffFileHead + string);
		bWriter.flush();
		bWriter.close();
		DataSource dataSource = new DataSource(Constant.TempInstanceFilePath);
		Instances structure = dataSource.getDataSet();
		int[] DeleteAttributeIndex = {31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 17, 4, 3, 1, 0};
		for (int i = 0; i < DeleteAttributeIndex.length; i ++) {
			structure.deleteAttributeAt(DeleteAttributeIndex[i]);
		}
		return structure;
	}
	
	public Instances GenInstances() throws Exception {
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.TempInstanceFilePath, false)));
		String string = ID + "," + Name + "," + (Sex == SexEnum.Male? 0: 1) + "," + (Eye == EyeEnum.OS? 0: 1) + "," + Age + "," + SE + "," + UCVA + "," + SD + "," + CD + "," + Axis + "," + BCVA + "," + CornealRadius + "," + OpticalZone + "," + K1 + "," + K2 + "," + Km + "," + CCT + "," + (LeadEye == EyeEnum.OS? 0: 1) + "," + PredictNomogram + "," + RST + "," + Time + "," + Humidity + "," + Temperature + "," + (FirstEyeToTreat == EyeEnum.OS? 0: 1) + "," + Energy + "," + OBL + "," + Thickness + "," + Position + "," + RealNomogram + "," + SDAfterOneDay + "," + SDAfterThreeMonths + "," + SDAfterSixMonths;
		bWriter.write(Constant.ArffFileHead + string);
		bWriter.flush();
		bWriter.close();
		DataSource dataSource = new DataSource(Constant.TempInstanceFilePath);
		Instances structure = dataSource.getDataSet();
		return structure;
	}
	
	public double getID() {
		return ID;
	}
	
	public String getName() {
		return Name;
	}
}
