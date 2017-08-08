package com.buaa.hospital.nomogram;
import weka.classifiers.functions.LinearRegression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ibm.icu.util.BytesTrie.Entry;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class multilayer {
	
	public static void main(String[] args) throws Exception {
		
//		PrintStream ps=new PrintStream(new FileOutputStream("output.txt"));  
//        System.setOut(ps);
		
//		watchmean9model("F:\\医疗数据\\眼科数据\\finaldata.arff");
//		trainRun("F:\\医疗数据\\眼科数据\\crazydata\\crazydata.arff", "F:\\医疗数据\\眼科数据\\crazydata\\crazydata.model");
		
//		trainRun("F:\\医疗数据\\眼科数据\\divide\\div_9.arff", "F:\\医疗数据\\眼科数据\\divide\\div_9.model");
//		watchModel("F:\\医疗数据\\眼科数据\\OS_10_trainval\\finalmodels\\1\\85_0.03390717873434722.model", "F:\\医疗数据\\眼科数据\\OS_无术后验光度\\OS.arff", 7);
//		watchmeanmodels("F:\\医疗数据\\眼科数据\\OS_10_trainval\\finalmodels\\1\\", "F:\\医疗数据\\眼科数据\\OS_无术后验光度\\OS_new.arff", 7);
//		boostRun("F:\\医疗数据\\眼科数据\\eye2_1\\1\\", "eye2_1.arff", "F:\\医疗数据\\眼科数据\\eye2_1.arff", 8);
//		chooseBest("F:\\医疗数据\\眼科数据\\eye2_1\\1\\", "F:\\医疗数据\\眼科数据\\eye2_1_test.arff", 7);
//		initcross("F:\\医疗数据\\眼科数据\\cluster\\", "cluster1_result_new.arff", 3);
//		crosstrain("F:\\医疗数据\\眼科数据\\OD_10_finalmodel\\", "OD.arff", 13, 10);
//		avgModels("F:\\医疗数据\\眼科数据\\test\\finalmodels\\1\\", "F:\\医疗数据\\眼科数据\\test\\OD.arff", 7);
//		adaboost("F:\\医疗数据\\眼科数据\\ada_OD\\", "F:\\医疗数据\\眼科数据\\ada_OD\\eye_sign.arff", "F:\\医疗数据\\眼科数据\\ada_OD\\eye_sign.arff", 7, 10);
//		adaboostM1("F:\\医疗数据\\眼科数据\\ada_OD\\", "F:\\医疗数据\\眼科数据\\ada_OD\\eye_sign.arff", "F:\\医疗数据\\眼科数据\\ada_OD\\eye_sign.arff", 7, 10);
	}
	
	public static double watchModel(String modelpath, String testfilepath, int indexclass) throws Exception {
		
		DataSource test_data = new DataSource(testfilepath);
		Instances test_struture = test_data.getDataSet();
		Instances test_struture2 = test_data.getDataSet();
		double sumerr = 0;
		int count = 0, count2 = 0;
		
		MultilayerPerceptron readmodel = getmodel(modelpath);
		for (int n = 0; n < test_struture.numInstances(); n ++) {
			double real = getdatafrominstances(test_struture, n, indexclass);
			double predict = getmultilayerresult(readmodel, test_struture.instance(n));
//			System.out.println("NO = " + (n+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
			int k = (int)Math.floor((predict*100)/5);
			
			
			if (predict - k * 0.05 < 0.025) {
				predict = ((double)((int)(k * 5)))/100;
			}
			else {
				predict = ((double)((int)((k+1) * 5)))/100;
			}
			if (predict < 0) {
				predict = 0;
			}
			
			sumerr += Math.pow(real-predict, 2);
			if ((int)Math.floor((100 * Math.abs(predict - real))) > 5) {
				System.out.println("NO = " + (n+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
				System.out.println(test_struture2.instance(n).toString());
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 1) {
				count ++;
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 5) {
				count2 ++;
			}
		}
		System.out.println(readmodel.toString());
		System.out.println("avgErr = " + Math.sqrt(sumerr/test_struture.numInstances()));
		System.out.println("Err less than 0.01 = " + count + " Percent = " + ((double)count)/test_struture.numInstances());
		System.out.println("Err less than 0.05 = " + count2 + " Percent = " + ((double)count2)/test_struture.numInstances());
		return ((double)count2)/test_struture.numInstances();
	}
	
	public static void trainRun(String dataname, String savepath) throws Exception {
		String testfilepath = dataname;
		String savemodelpath = "F:\\医疗数据\\眼科数据\\";
		int indexclass = 9;
		DataSource test_data = new DataSource(testfilepath);
		Instances test_struture = test_data.getDataSet();
		double sumerr = 0;
		int count1 = 0, count2 = 0;
		Instances structure = loaddata(dataname);
		MultilayerPerceptron mp = trainmultilayer(structure, indexclass);		
		for (int i = 0; i < test_struture.numInstances(); i ++) {
			double real = getdatafrominstances(test_struture, i, indexclass);
			double predict = getmultilayerresult(mp, structure.instance(i));
			sumerr += Math.pow(real-predict, 2);
			if (Math.abs(predict - real) <= 0.01) {
				count1 ++;
			}
			if (Math.abs(predict - real) <= 0.05) {
				count2 ++;
			}
		}
		if (savepath.equals("")) {
			savemodelpath = savemodelpath + count1 + "_" + (Math.sqrt(sumerr/test_struture.numInstances()) + ".model");
		}
		else {
			savemodelpath = savepath;
		}
		savemodel(savemodelpath, mp);
		System.out.println(mp.toString());
		System.out.println("mean of square err = " + Math.sqrt(sumerr/test_struture.numInstances()));
		System.out.println("number of err less than 0.01 = " + count1);
		System.out.println("percent of err less than 0.01 = " + ((double)count1)/test_struture.numInstances());
		System.out.println("number of err less than 0.05 = " + count2);
		System.out.println("percent of err less than 0.05 = " + ((double)count2)/test_struture.numInstances());
	}
	
	public static void boostRun(String rootpath, String dataname, String filepath, int indexclass) throws Exception {
		String testfilepath = filepath;
		String modelpath = "";
		String savemodelpath = "";
		int max = 5;
		int maxCount = 0;
		double minErr = Integer.MAX_VALUE;
		
		initrunFile(rootpath, dataname, filepath);
		DataSource test_data = new DataSource(testfilepath);
		Instances test_struture = test_data.getDataSet();
		double sumerr = 0;
		int count = 0;
		
		MultilayerPerceptron readmodel = getmodel(modelpath);
//		for (int n = 0; n < test_struture.numInstances(); n ++) {
//			double real = getdatafrominstances(test_struture, n, indexclass);
//			double predict = getmultilayerresult(readmodel, test_struture.instance(n));
//			sumerr += Math.pow(real-predict, 2);
//			System.out.println("NO = " + (n+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
//			if (Math.abs(predict - real) <= 0.01) {
//				count ++;
//			}
//		}
//		System.out.println(readmodel.toString());
//		System.out.println("avgErr = " + Math.sqrt(sumerr/test_struture.numInstances()) + ", Count = " + count);
		
		for (int m = 0; m < max; m ++) {
			sumerr = 0;
			count = 0;
			Instances structure = loaddata(rootpath + dataname);
			MultilayerPerceptron mp = trainmultilayer(structure, indexclass);		
			for (int i = 0; i < test_struture.numInstances(); i ++) {
				double real = getdatafrominstances(test_struture, i, indexclass);
				double predict = getmultilayerresult(mp, structure.instance(i));
				sumerr += Math.pow(real-predict, 2);
//				System.out.println(Double.toString(real) + " " + Double.toString(predict));
				if (Math.abs(predict - real) <= 0.01) {
					count ++;
				}
				else {
					String s = "";
					for (int n = 0; n < test_struture.numAttributes(); n ++) {
						s += getdatafrominstances(test_struture, i, n);
						if (n != test_struture.numAttributes() - 1) {
							s += ",";
						}
						else {
							s += "\r\n";
						}
					}
//					System.out.println(s);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rootpath + dataname, true)));
					out.write(s);
					out.flush();
					out.close();
				}
			}
			System.out.println("Generate = " + (m + 1) + ", avgErr = " + Math.sqrt(sumerr/test_struture.numInstances()) + ", Count = " + count + ", Percent = " + ((double)count)/test_struture.numInstances());
//			if (count >= maxCount) {
//				savemodelpath = rootpath + count + "_" + (Math.sqrt(sumerr/test_struture.numInstances()) + ".model");
//				savemodel(savemodelpath, mp);
//				maxCount = count;
//			}
//			else if (sumerr <= minErr) {
//				savemodelpath = rootpath + count + "_" + (Math.sqrt(sumerr/test_struture.numInstances()) + ".model");
//				savemodel(savemodelpath, mp);
//				minErr = sumerr;
//			}
			savemodelpath = rootpath + count + "_" + (Math.sqrt(sumerr/test_struture.numInstances()) + ".model");
			savemodel(savemodelpath, mp);
			if (count == test_struture.numInstances() || m == max-1) {
				savemodelpath = rootpath + count + "_" + (Math.sqrt(sumerr/test_struture.numInstances()) + ".model");
				savemodel(savemodelpath, mp);
				break;
			}			
		}
	}
	
	public static Instances loaddata(String filepath) throws Exception {
//		DataSource train_data = new DataSource(filepath);
//		Instances structure = train_data.getDataSet();
//		return structure;
		FileReader fileReader = new FileReader(filepath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Instances structure = new Instances(bufferedReader);
		bufferedReader.close();
		fileReader.close();
		return structure;
	}
	
	public static MultilayerPerceptron trainmultilayer(Instances structure, int index) throws Exception {
		structure.setClassIndex(index);
		String[] options = weka.core.Utils.splitOptions("-L 0.01 -M 0.2 -N 5000 -V 0 -S 0 -E 20 -H \"5,8\"");
		MultilayerPerceptron mp = new MultilayerPerceptron();
		mp.setOptions(options);
		mp.buildClassifier(structure);	
		return mp;
	}
	
	public static double getdatafrominstances(Instances structure, int index, int attrpos) {
		return structure.instance(index).toDoubleArray()[attrpos];
	}
	
	public static Instance getinstance(Instances structure, int index) {
		return structure.instance(index);
	}
	
	public static Instance makeinstance(String s) {	
		String[] strings = s.split(" ");
		Instance instance = new weka.core.SparseInstance(strings.length);
		for (int i = 0; i < strings.length - 1; i ++) {
			instance.setValue(i, Double.parseDouble(strings[i]));
		}
		return instance;
	}
	
	public static double getmultilayerresult(MultilayerPerceptron mp, Instance ins) throws Exception {
		return mp.classifyInstance(ins);
	}
	
	public static void savemodel(String modelpath, MultilayerPerceptron mp) throws Exception {
		SerializationHelper.write(modelpath, mp);
	}
	
	public static MultilayerPerceptron getmodel(String modelpath) throws Exception {
		return (MultilayerPerceptron)weka.core.SerializationHelper.read(modelpath);
	}
	
	public static void initrunFile(String rootpath, String dataname, String filepath) {
		File rootFile = new File(rootpath);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}
		FileCopy(filepath, rootpath + dataname);
		System.out.println(rootpath + dataname);
	}
	
	public static void FileCopy(String readfile,String writeFile) {
		try {
			File writefile = new File(writeFile);
			if (!writefile.exists()) {
				writefile.createNewFile();
			}
			FileInputStream input = new FileInputStream(readfile);
			FileOutputStream output = new FileOutputStream(writeFile);
			int read = input.read();		
			while ( read != -1 ) {
				output.write(read);	
				read = input.read();
			}			
			input.close();
			output.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static String chooseBest(String rootpath, String testfilepath, int indexclass) throws Exception {
		File rootfile = new File(rootpath);
		File[] files = rootfile.listFiles();
//		DataSource test_data = new DataSource(testfilepath);
//		Instances test_struture = test_data.getDataSet();
		String bestModelPath = "";
		double bestPercent = 0, bestPercent2 = 0, bestavgErr = 0, bestCount = 0, bestCount2 = 0;
		for (int i = 0; i < files.length; i ++) {
			String filename = files[i].getName();
			double sumerr = 0;
			int count = 0, count2 = 0;
			if (filename.endsWith(".model")) {
				DataSource test_data = new DataSource(testfilepath);
				Instances test_struture = test_data.getDataSet();
				MultilayerPerceptron readmodel = getmodel(files[i].getAbsolutePath());
				for (int n = 0; n < test_struture.numInstances(); n ++) {
					double real = getdatafrominstances(test_struture, n, indexclass);
					double predict = getmultilayerresult(readmodel, test_struture.instance(n));
					int k = (int)Math.floor((predict*100)/5);
					
					
					if (predict - k * 0.05 < 0.025) {
						predict = ((double)((int)(k * 5)))/100;
					}
					else {
						predict = ((double)((int)((k+1) * 5)))/100;
					}
					if (predict < 0) {
						predict = 0;
					}
					
					sumerr += Math.pow(real-predict, 2);
					if ((int)Math.floor((100 * Math.abs(predict - real))) <= 1) {
						count ++;
					}
					if ((int)Math.floor((100 * Math.abs(predict - real))) <= 5) {
						count2 ++;
					}
				}
				double percent = ((double)count)/test_struture.numInstances();
				double percent2 = ((double)count2)/test_struture.numInstances();
				
				System.out.println("No = " + (i+1) + ", name = " + files[i].getName() + ", avgErr = " + Math.sqrt(sumerr/test_struture.numInstances()) + ", Err less than 0.01 = " + percent + ", Err less than 0.05 = " + percent2);
				if (percent2 > bestPercent2) {
					bestPercent = percent;
					bestPercent2 = percent2;
					bestModelPath = files[i].getAbsolutePath();
					bestavgErr = Math.sqrt(sumerr/test_struture.numInstances());
					bestCount = count;
					bestCount2 = count2;
				}
				else if (percent2 == bestPercent2) {
					if (percent > bestPercent) {
						bestPercent = percent;
						bestPercent2 = percent2;
						bestModelPath = files[i].getAbsolutePath();
						bestavgErr = Math.sqrt(sumerr/test_struture.numInstances());
						bestCount = count;
						bestCount2 = count2;
					}
				}
			}
		}
		System.out.println("\r\nBest Model Path = " + bestModelPath);
		System.out.println("avgErr = " +bestavgErr);
		System.out.println("Err less than 0.01 = " + bestCount + " Percent = " + bestPercent);
		System.out.println("Err less than 0.05 = " + bestCount2 + " Percent = " + bestPercent2);
		return bestModelPath;
	}
	
	public static void initcross(String rootpath, String filename, int crossnum) throws IOException {
		File file = new File(rootpath + filename);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		ArrayList<String> data = new ArrayList();
		String line = bf.readLine();
		String head = "";
		while (line != null) {
			if (line.startsWith("@") || line.equals("")) {
				head = head + line + "\n";
			}
			else {
				data.add(line);
			}
			line = bf.readLine();
		}
		Collections.shuffle(data);
		for (int i = 0; i < crossnum; i ++) {
			File newfile = new File(rootpath + filename.substring(0,  filename.length() - 5) + "_" + Integer.toString(i));
			if (!newfile.exists()) {
				newfile.mkdir();
			}
			BufferedWriter testout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile.getAbsolutePath() + "\\" + filename.substring(0,  filename.length() - 5) + "_" + Integer.toString(i) + "_test.arff", false)));
			BufferedWriter trainout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile.getAbsolutePath() + "\\" + filename.substring(0,  filename.length() - 5) + "_" + Integer.toString(i) + "_train.arff", false)));
			BufferedWriter valout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile.getAbsolutePath() + "\\" + filename.substring(0,  filename.length() - 5) + "_" + Integer.toString(i) + "_trainval.arff", false)));
			trainout.write(head);
			testout.write(head);
			valout.write(head);
			for (int j = 0; j < data.size(); j ++) {
				if(j % crossnum == i) {
					testout.write(data.get(j));
					testout.write("\n");
				}
				else {
					if (j % 9 == 0) {
						valout.write(data.get(j));
						valout.write("\n");
					}
					else {
						trainout.write(data.get(j));
						trainout.write("\n");
					}
				}
			}
			trainout.flush();
			trainout.close();
			testout.flush();
			testout.close();
			valout.flush();
			valout.close();
		}
	}
	
	public static void crosstrain(String rootpath, String filename, Integer indexnum, int crossnum) throws Exception {
		initcross(rootpath, filename, crossnum);
		File file = new File(rootpath);
		File[] files = file.listFiles();
		ArrayList<String> models = new ArrayList();
		int maxnum = 0;
		for (int i = 0; i < files.length; i ++) {
			if (files[i].isDirectory()) {
				File[] files2 = files[i].listFiles();
				int num = 0;
				for (int j = 0; j < files2.length; j ++) {
					if (files2[j].getName().endsWith(".arff")) {
						continue;
					}
					if (num < Integer.parseInt(files2[j].getName())) {
						num = Integer.parseInt(files2[j].getName());
					}
				}
				File newfile = new File(files[i].getAbsolutePath() + "\\" + (num + 1) + "\\");
				newfile.mkdir();
				if (!files[i].getName().equals("finalmodels")) {
					boostRun(files[i].getAbsolutePath() + "\\" + (num + 1) + "\\", files[i].getName() + "_train.arff", files[i].getAbsolutePath() + "\\" + files[i].getName() + "_train.arff", indexnum);
					models.add(chooseBest(files[i].getAbsolutePath() + "\\" + (num + 1) + "\\", files[i].getAbsolutePath() + "\\" + files[i].getName() + "_trainval.arff", indexnum));
				}
			}
		}
		File finalmodels = new File(rootpath + "finalmodels");
		if (!finalmodels.exists()) {
			finalmodels.mkdir();
		}
		File[] finalmodelsfiles = finalmodels.listFiles();
		for (int i = 0; i < finalmodelsfiles.length; i ++) {
			if (maxnum < Integer.parseInt(finalmodelsfiles[i].getName())) {
				maxnum = Integer.parseInt(finalmodelsfiles[i].getName());
			}
		}
		File newfinalmodels = new File(rootpath + "finalmodels\\" + (maxnum + 1));
		newfinalmodels.mkdir();
		double sumpercent = 0, allsumpercent = 0;
		for (int i = 0; i < models.size(); i ++) {
			System.out.println(models.get(i));
			String[] temp = models.get(i).split("\\\\");
			FileCopy(models.get(i), rootpath + "finalmodels\\" + (maxnum + 1) + "\\" + temp[temp.length-1]);
			String testfilepath = "";
			for (int j = 0; j < temp.length-2; j ++) {
				testfilepath += temp[j];
				testfilepath += "\\";
			}
			testfilepath += temp[temp.length-3] + "_test.arff";
			double percent = watchModel(models.get(i), testfilepath, indexnum);
			double allpercent = watchModel(models.get(i), rootpath + filename, indexnum);
			sumpercent += percent;
			allsumpercent += allpercent;
			System.out.println("percent of correct results in dataset " + crossnum + " = " + percent);
			System.out.println("percent of correct results in the whole dataset = " + allpercent);
		}
		System.out.println("Average percent of correct results in " + crossnum + " trainset = " + sumpercent/crossnum);
		System.out.println("Average percent of correct results in " + crossnum + " trainset where testset is equal to trainset = " + allsumpercent/crossnum);
	}
	
	public static void avgModels(String rootpath, String testfilepath, int indexclass) throws Exception {
		File file = new File(rootpath);
		File[] modelfiles = file.listFiles();
		DataSource test_data = new DataSource(testfilepath);
		Instances test_struture = test_data.getDataSet();
		Instances test_struture2 = test_data.getDataSet();
		ArrayList<MultilayerPerceptron> models = new ArrayList();
		double sumerr = 0;
		int count = 0, count2 = 0;
		for (int i = 0; i < modelfiles.length; i ++) {
			if (modelfiles[i].getName().endsWith(".model")) {
				models.add(getmodel(modelfiles[i].getAbsolutePath()));
			}
		}
		for (int i = 0; i < test_struture.numInstances(); i ++) {
			double real = test_struture.instance(i).value(indexclass);
			double sumresult = 0;
			for (int j = 0; j < models.size(); j ++) {
				test_struture = test_data.getDataSet();
				sumresult += getmultilayerresult(models.get(j), test_struture.instance(i));
			}
			double predict = sumresult/models.size();
			
			int k = (int)Math.floor((predict*100)/5);
			
			
			if (predict - k * 0.05 < 0.025) {
				predict = ((double)((int)(k * 5)))/100;
			}
			else {
				predict = ((double)((int)((k+1) * 5)))/100;
			}
			if (predict < 0) {
				predict = 0;
			}
//			System.out.println("NO = " + (i+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
			sumerr += Math.pow(real-predict, 2);
			if ((int)Math.floor((100 * Math.abs(predict - real))) > 5) {
				System.out.println("NO = " + (i+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
				System.out.println(test_struture2.instance(i).toString());
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 1) {
				count ++;
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 5) {
				count2 ++;
			}
		}
		System.out.println("avgErr = " + Math.sqrt(sumerr/test_struture.numInstances()));
		System.out.println("Err less than 0.01 = " + count + " Percent = " + ((double)count)/test_struture.numInstances());
		System.out.println("Err less than 0.05 = " + count2 + " Percent = " + ((double)count2)/test_struture.numInstances());
	}
	
	public static void adaboost(String rootpath, String trainfilepath, String testfilepath, int indexclass, int maxgen) throws Exception {
		String modelpath = "F:\\医疗数据\\眼科数据\\divide\\finaldata_avgnorm.model";
		String savemodelpath = "F:\\医疗数据\\眼科数据\\6\\";
		int maxCount = 0;
		double precision = 0.01;
		double minErr = Integer.MAX_VALUE;
		ArrayList<Double> weight = new ArrayList();
		ArrayList<MultilayerPerceptron> models = new ArrayList();
		ArrayList<Instances> instances = new ArrayList();
		ArrayList<Instances> instances2 = new ArrayList();
		ArrayList<Double> coefficient = new ArrayList();
		Instances test_structure = loaddata(testfilepath);
		double sumerr = 0;
		int count = 0, count2 = 0;
		
		for (int i = 0; i < maxgen; i ++) {
			instances.add(loaddata(trainfilepath));
			instances2.add(loaddata(trainfilepath));
		}
		
		for (int i = 0; i < instances.get(0).numInstances(); i ++) {
			weight.add(((double)1)/instances.get(0).numInstances());
//			weight.add((double)1);
		}
		
		for (int m = 0; m < maxgen; m ++) {
			sumerr = 0;
			count = 0;
			System.out.println(weight.toString());
			setweight(instances.get(m), weight);
			MultilayerPerceptron mp = trainmultilayer(instances.get(m), indexclass);
			double errRate = 0;
			double err = 0;
			for (int i = 0; i < instances.get(m).numInstances(); i ++) {
				double real = getdatafrominstances(instances.get(m), i, indexclass);
				double predict = getmultilayerresult(mp, instances.get(m).instance(i));
				int k = (int)Math.floor((predict*100)/5);
				if (predict - k * 0.05 < 0.025) {
					predict = ((double)((int)(k * 5)))/100;
				}
				else {
					predict = ((double)((int)((k+1) * 5)))/100;
				}
				if (predict < 0) {
					predict = 0;
				}
				if ((int)Math.floor((100 * Math.abs(predict - real))) > 0) {
					errRate += instances.get(m).instance(i).weight();
					err += Math.abs(predict - real);
				}
//				System.out.println("real = " + real + " predict = " + predict);
//				if (Math.abs(predict-real) > precision) {
//					errRate += instances.get(m).instance(i).weight();
//					err += Math.abs(predict - real);
//				}
			}
			System.out.println(errRate);
			double avgerr = err/instances.get(m).numInstances();
//			double alpha = 1.0/2 * Math.log((1-errRate)/errRate);
			double alpha = 1.0/2 * Math.exp(errRate);
			double beta = Math.pow(errRate, 2);
//			double beta = avgerr/(1-avgerr);
			System.out.println(beta);
			coefficient.add(Math.log(1.0/beta));
//			coefficient.add(1.0/2*Math.log(1.0/beta));
			for (int i = 0; i < instances2.get(m).numInstances(); i ++) {
				double real = getdatafrominstances(instances2.get(m), i, indexclass);
				double predict = getmultilayerresult(mp, instances2.get(m).instance(i));
				if (Math.abs(predict-real) > precision) {
//					weight.set(i, weight.get(i) * Math.exp(alpha));
//					weight.set(i, weight.get(i));
//					weight.set(i, Math.pow(beta, -Math.abs(predict-real)));
					weight.set(i, weight.get(i)*1.1);
				}
				else {
//					weight.set(i, weight.get(i) * Math.exp(-alpha));
//					weight.set(i, weight.get(i) * beta);
//					weight.set(i, Math.pow(beta, -Math.abs(predict-real)));
					weight.set(i, weight.get(i));
				}
			}
			System.out.println(weight);
			NormalizationArray(weight);
			System.out.println("sum of weight = " + checkNormalization(weight));
			models.add(mp);
			System.out.println("Generate = " + (m + 1));	
		}
		
		double sumcoff = 0;
		for (int i = 0; i < coefficient.size(); i ++) {
			sumcoff += coefficient.get(i);
		}
		for (int i = 0; i < coefficient.size(); i ++) {
			coefficient.set(i, coefficient.get(i)/sumcoff);
		}
		
		System.out.println(coefficient.toString());;
		for (int i = 0; i < test_structure.numInstances(); i ++) {
			double real = getdatafrominstances(test_structure, i, indexclass);
			double predict = weightmean(models, coefficient, test_structure.instance(i));
			int k = (int)Math.floor((predict*100)/5);
			if (predict - k * 0.05 < 0.025) {
				predict = ((double)((int)(k * 5)))/100;
			}
			else {
				predict = ((double)((int)((k+1) * 5)))/100;
			}
			if (predict < 0) {
				predict = 0;
			}
			sumerr += Math.pow(real-predict, 2);
			if ((int)Math.floor((100 * Math.abs(predict - real))) > 5) {
				System.out.println("NO = " + (i+1) + ", real = " + Double.toString(real) + ", predict = " + Double.toString(predict));
				System.out.println(test_structure.instance(i).toString());
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 1) {
				count ++;
			}
			if ((int)Math.floor((100 * Math.abs(predict - real))) <= 5) {
				count2 ++;
			}
		}
		for (int i = 0; i < models.size(); i ++) {
			savemodelpath = rootpath + (i + 1) + ".model";
			savemodel(savemodelpath, models.get(i));
		}
		System.out.println("avgErr = " + Math.sqrt(sumerr/test_structure.numInstances()));
		System.out.println("Err less than 0.01 = " + count + " Percent = " + ((double)count)/test_structure.numInstances());
		System.out.println("Err less than 0.05 = " + count2 + " Percent = " + ((double)count2)/test_structure.numInstances());
	}
	
	public static double weightmean(ArrayList<MultilayerPerceptron> models, ArrayList<Double> coefficient, Instance instance) throws Exception {
		double result = 0;
		ArrayList<Instance> instances = new ArrayList();
		for (int i = 0; i < models.size(); i ++) {
			instances.add((Instance)instance.copy());
		}
		for (int i = 0; i < models.size(); i ++) {
			result += coefficient.get(i) * getmultilayerresult(models.get(i), instances.get(i));
		}
		return result;
	}
	
	public static void setweight(Instances structure, ArrayList<Double> weight) {
		for (int i = 0; i < structure.numInstances(); i ++) {
			structure.instance(i).setWeight(weight.get(i));
		}
	}
	
	public static void NormalizationArray(ArrayList<Double> weight) {
		double sum = 0;
		for (int i = 0; i < weight.size(); i ++) {
			sum += weight.get(i);
		}
		for (int i = 0; i < weight.size(); i ++) {
			weight.set(i, weight.get(i)/sum);
		}
	}
	
	public static double checkNormalization(ArrayList<Double> weight) {
		double sum = 0;
		for (int i = 0; i < weight.size(); i ++) {
			sum += weight.get(i);
		}
		return sum;
	}
	
	public static void disDoubleArray(double[] array) {
		for (int i = 0; i < array.length; i ++) {
			System.out.print(array[i]);
			System.out.print(",");
			if (i == array.length - 1) {
				System.out.print("\r\n");
			}
		}
	}
	
	public static void watchmeanmodels(String rootpath, String testfilepath, int indexclass) throws Exception {
		File file = new File(rootpath);
		File[] files = file.listFiles();
		double result = 0;
		int modelnum = 0;
		for (int i = 0; i < files.length; i ++) {
			if (files[i].getName().endsWith(".model")) {
				String modelpath = files[i].getAbsolutePath();
				double percent = watchModel(modelpath, testfilepath, indexclass);
				System.out.println("Model " + (i + 1) + " : Err less than 0.05 Percent = " + percent);
				result += percent;
				modelnum ++;
			}
		}
		System.out.println("Average Models : Err less than 0.05 Percent = " + result/modelnum);
	}
	
	
	public static void adaboostM1(String rootpath, String trainfilepath, String testfilepath, int indexclass, int maxgen) throws Exception {
		AdaBoostM1 adaboost = new AdaBoostM1();
		String modelpath = "F:\\医疗数据\\眼科数据\\divide\\finaldata_avgnorm.model";
		String savemodelpath = "F:\\医疗数据\\眼科数据\\6\\";
		Instances train_structure = loaddata(trainfilepath);
		Instances test_structure = loaddata(testfilepath);
		
		adaboost = trainadaboost(train_structure, indexclass);
		
		System.out.println(adaboost.toString());
		
		for (int i = 0; i < test_structure.numInstances(); i ++) {
			double real = getdatafrominstances(test_structure, i, indexclass);
		}
		
	}
	
	public static AdaBoostM1 trainadaboost(Instances structure, int index) throws Exception {
		structure.setClassIndex(index);
		String[] options = weka.core.Utils.splitOptions("-P 100 -S 1 -I 10 -W weka.classifiers.functions.MultilayerPerceptron -- -L 0.01 -M 0.2 -N 5000 -V 20 -S 0 -E 20 -H \"5,8\" -D");
		AdaBoostM1 adaboost = new AdaBoostM1();
		adaboost.setOptions(options);
		adaboost.buildClassifier(structure);
		return adaboost;
	}
	
	public static double Predict(String Modelpath, Instance instance) throws Exception {
		File file = new File(Modelpath);
		File[] files = file.listFiles();
		HashMap<Double, Integer> nomograms = new HashMap<>();
		ArrayList<Double> MaxNomogram = new ArrayList<>();
		for (int i = 0; i < files.length; i ++) {
			String ModelPath = files[i].getAbsolutePath();
			MultilayerPerceptron mPerceptron = getmodel(ModelPath);
			double nomogram = getmultilayerresult(mPerceptron, instance);
//			nomogram = Transfrom(nomogram);
			System.out.println(nomogram);
			if (!nomograms.containsKey(nomogram)) {
				nomograms.put(nomogram, 1);
			}
			else {
				nomograms.put(nomogram, nomograms.get(nomogram) + 1);
			}
		}
		double MaxTimes = 0;
		double MaxTimesNomogram = 0;
		Iterator iterator = nomograms.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Double, Integer> entry= (Map.Entry<Double, Integer>)iterator.next();
			double nomogram = entry.getKey();
			double times = entry.getValue();
			if (times > MaxTimes) {
				MaxNomogram.clear();
				MaxNomogram.add(nomogram);
			}
			else if (times == MaxTimes) {
				MaxNomogram.add(nomogram);
			}
		}
		if (MaxNomogram.size() != 1) {
			return calAvgArray(MaxNomogram);
		}
		else {
			return MaxNomogram.get(0);
		}
	}
	
	public static double calAvgArray(ArrayList<Double> data) {
		Iterator<Double> iterator = data.iterator();
		double sum = 0;
		while (iterator.hasNext()) {
			sum += iterator.next();
		}
		double avg = sum/data.size();
		return Transfrom(avg);
	}
	
	public static double Transfrom(double predict) {
		int k = (int)Math.floor((predict*100)/5);
		if (predict - k * 0.05 < 0.025) {
			predict = ((double)((int)(k * 5)))/100;
		}
		else {
			predict = ((double)((int)((k+1) * 5)))/100;
		}
		if (predict < 0) {
			predict = 0;
		}
		return predict;
	}

}
