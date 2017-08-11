package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import weka.core.Instances;

public class TrainMenuListener implements ActionListener {
	
	private DataBase dataBase;
	private JFrame frame;
	private JProgressBar jProgressBar;
	private Instances[] AllInstances;
	
	public TrainMenuListener(DataBase dataBase, JFrame frame, JProgressBar jProgressBar) throws Exception {
		// TODO Auto-generated constructor stub
		this.dataBase = dataBase;
		this.frame = frame;
		this.jProgressBar = jProgressBar;
		this.AllInstances = dataBase.getAllNeedTrainedData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				try {
					frame.getContentPane().add(jProgressBar);
					jProgressBar.setVisible(true);
					jProgressBar.setMinimum(0);
					jProgressBar.setMaximum(100);
					jProgressBar.setValue(0);
//					jProgressBar.setValue(jProgressBar.getValue() + 1);
					jProgressBar.setToolTipText("正在初始化设置...");
//					jProgressBar.setValue(jProgressBar.getValue() + 1);
					MultiPreceptionTrain();
//					jProgressBar.setValue(jProgressBar.getValue() + 1);
					ClassifyTrain();
					MoveInstancesToTrained();
					for (;jProgressBar.getValue() < jProgressBar.getMaximum();) {
						jProgressBar.setValue(jProgressBar.getValue() + 1);
						sleep(500);
					}
					frame.getContentPane().remove(jProgressBar);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}.start();
	}
	
	public void GenOSTrainFile() throws Exception {
		Instances instances = AllInstances[1];
		Attribute.GenMultiPreceptionInstances(instances);
		dataBase.saveArff(instances, Constant.TrainOSFilePath);
	}
	
	public void GenODTrainFile() throws Exception {
		Instances instances = AllInstances[2];
		Attribute.GenMultiPreceptionInstances(instances);
		dataBase.saveArff(instances, Constant.TrainODFilePath);
	}
	
	public void MultiPreceptionTrain() throws Exception {
		File LatestMultiPreceptionOSModels = new File(Constant.LatestMultiPreceptionOSModelsPath);
		File LastMultiPreceptionOSModel = LatestMultiPreceptionOSModels.listFiles()[0];
		Constant.FileCopy(LastMultiPreceptionOSModel.getAbsolutePath(), Constant.HistoryMultiPreceptionOSModelsPath + "\\" + LastMultiPreceptionOSModel.getName());
		File LatestMultiPreceptionODModels = new File(Constant.LatestMultiPreceptionODModelsPath);
		File LastMultiPreceptionODModel = LatestMultiPreceptionODModels.listFiles()[0];
		Constant.FileCopy(LastMultiPreceptionODModel.getAbsolutePath(), Constant.HistoryMultiPreceptionODModelsPath + "\\" + LastMultiPreceptionODModel.getName());
		File TrainOSFile = new File(Constant.TrainOSPath);
		//Train OS
//		jProgressBar.setValue(jProgressBar.getValue() + 1);
		jProgressBar.setToolTipText("正在生成可用的左眼训练数据...");
		if (!TrainOSFile.exists()) {
			TrainOSFile.mkdir();
		}
		else {
			Constant.FileDelete(TrainOSFile);
			TrainOSFile.mkdir();
		}
		GenOSTrainFile();
//		jProgressBar.setValue(jProgressBar.getValue() + 1);
		jProgressBar.setToolTipText("开始训练神经网络左眼模型...");
		Multilayer.crosstrain(Constant.TrainOSPath + "\\", Constant.TrainFileName, Constant.MultipreceptionAttributeNum - 1, 10, jProgressBar);
		String BestModelPath = Multilayer.chooseBest(Constant.TrainOSPath + "\\finalmodels\\1\\", Constant.TrainOSFilePath, Constant.MultipreceptionAttributeNum - 1);
		Constant.FileCopy(BestModelPath, Constant.LatestMultiPreceptionOSModelsPath + "\\" + Constant.LatestMultiPreceptionOSModelName);
		LastMultiPreceptionOSModel.delete();
		Constant.MultiPreceptionOSModelIndex ++;
		
		//Train OD
		File TrainODFile = new File(Constant.TrainODPath);
		jProgressBar.setToolTipText("正在生成可用的右眼训练数据...");
		if (!TrainODFile.exists()) {
			TrainODFile.mkdir();
		}
		else {
			Constant.FileDelete(TrainODFile);
			TrainODFile.mkdir();
		}
		GenODTrainFile();
		jProgressBar.setToolTipText("开始训练神经网络右眼模型...");
		Multilayer.crosstrain(Constant.TrainODPath + "\\", Constant.TrainFileName, Constant.MultipreceptionAttributeNum - 1, 10, jProgressBar);
		BestModelPath = Multilayer.chooseBest(Constant.TrainODPath + "\\finalmodels\\1\\", Constant.TrainODFilePath, Constant.MultipreceptionAttributeNum - 1);
		Constant.FileCopy(BestModelPath, Constant.LatestMultiPreceptionODModelsPath + "\\" + Constant.LatestMultiPreceptionODModelName);
		LastMultiPreceptionODModel.delete();
		Constant.MultiPreceptionODModelIndex ++;
	}
	
	public void ClassifyTrain(Instances instances, String SaveModelPath) throws Exception {
		Classifier.buildClassfier(SaveModelPath, instances);
	}
	
	public void ClassifyTrain() throws Exception {
		File LatestClassifyModels = new File(Constant.LatestClassifyModelsPath);
		File LastClassifyModel = LatestClassifyModels.listFiles()[0];
		Constant.FileCopy(LastClassifyModel.getAbsolutePath(), Constant.HistoryClassifyModelsPath + "\\" + LastClassifyModel.getName());
		Instances instances = AllInstances[0];
		Attribute.GenClassifyInstances(instances);
		ClassifyTrain(instances, Constant.LatestClassifyModelsPath + "\\" + Constant.ClassifyModelName);
		LastClassifyModel.delete();
		Constant.ClassifyModelIndex ++;
	}
	
	public void MoveInstancesToTrained() throws Exception {
		Instances instances = dataBase.getAllUnTrainedData();
		dataBase.addToTrainedData(instances);
		File UntrainedData = new File(Constant.UntrainedDataPath);
		Constant.FileDelete(UntrainedData);
		UntrainedData.mkdir();
		DataBase.saveArff(Attribute.GenEmptyInstances(), Constant.UntrainedDataPath + "\\" + Constant.UntrainedDataName + "_1.arff");
		Constant.UntrainedDataMaxIndex = 1;
		
	}

}
