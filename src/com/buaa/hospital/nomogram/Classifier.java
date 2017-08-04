package com.buaa.hospital.nomogram;

import java.io.File;

import weka.classifiers.meta.Bagging;
import weka.core.Instance;
import weka.core.SerializationHelper;

public class Classifier {
	private static Bagging baggingClassifier;
	public static void buildClassfier(){
		
	}
	public static double predict(String modelPath,Instance instance) throws Exception{
		if("".equals(modelPath)||(instance==null)){
			throw new Exception("Path empty or instance null");
		}
		baggingClassifier = (Bagging)SerializationHelper.read(modelPath);  //get serialized classifier from a model path
		return ((double)(baggingClassifier.classifyInstance(instance)))/20;  //get result
	}
	
	public static double predictByModelFile(String FilePath, Instance instance) throws Exception {
		File file = new File(FilePath);
		File[] files = file.listFiles();
		return predict(files[0].getAbsolutePath(), instance);
	}
	
	public static String GenLabel(double nomogram) {
		if (nomogram < 0.05) {
			return "0";
		}
		else if (nomogram < 0.1) {
			return "0.05";
		}
		else if (nomogram < 0.15) {
			return "0.1";
		}
		else if (nomogram < 0.2) {
			return "0.15";
		}
		else if (nomogram < 0.25) {
			return "0.2";
		}
		else if (nomogram < 0.3) {
			return "0.25";
		}
		else {
			return "0.3";
		}
	}
}
