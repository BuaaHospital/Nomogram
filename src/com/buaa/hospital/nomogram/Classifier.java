package com.buaa.hospital.nomogram;

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
		return baggingClassifier.classifyInstance(instance);  //get result
	}
}
