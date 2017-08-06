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
		try {
			frame.getContentPane().add(jProgressBar);
			jProgressBar.setMinimum(1);
			jProgressBar.setMaximum(100);
			jProgressBar.setToolTipText("正在初始化设置...");
			MultiPreceptionTrain();
			ClassifyTrain();
			MoveInstancesToTrained();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
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
		LastMultiPreceptionOSModel.delete();
		File LatestMultiPreceptionODModels = new File(Constant.LatestMultiPreceptionODModelsPath);
		File LastMultiPreceptionODModel = LatestMultiPreceptionODModels.listFiles()[0];
		Constant.FileCopy(LastMultiPreceptionODModel.getAbsolutePath(), Constant.HistoryMultiPreceptionODModelsPath + "\\" + LastMultiPreceptionODModel.getName());
		LastMultiPreceptionODModel.delete();
		File TrainOSFile = new File(Constant.TrainOSPath);
		//Train OS
		jProgressBar.setToolTipText("正在生成可用的左眼训练数据...");
		if (!TrainOSFile.exists()) {
			TrainOSFile.mkdir();
		}
		else {
			Constant.FileDelete(TrainOSFile);
			TrainOSFile.mkdir();
		}
		GenOSTrainFile();
		jProgressBar.setToolTipText("开始训练神经网络左眼模型...");
		multilayer.crosstrain(Constant.TrainOSPath + "\\", Constant.TrainFileName, Constant.MultipreceptionAttributeNum - 1, 10);
		String BestModelPath = multilayer.chooseBest(Constant.TrainOSPath + "\\finalmodels\\1\\", Constant.TrainOSFilePath, Constant.MultipreceptionAttributeNum - 1);
		Constant.FileCopy(BestModelPath, Constant.LatestMultiPreceptionOSModelsPath + "\\" + Constant.LatestMultiPreceptionOSModelName);
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
		multilayer.crosstrain(Constant.TrainODPath + "\\", Constant.TrainFileName, Constant.MultipreceptionAttributeNum - 1, 10);
		BestModelPath = multilayer.chooseBest(Constant.TrainODPath + "\\finalmodels\\1\\", Constant.TrainODFilePath, Constant.MultipreceptionAttributeNum - 1);
		Constant.FileCopy(BestModelPath, Constant.LatestMultiPreceptionODModelsPath + "\\" + Constant.LatestMultiPreceptionODModelName);
		Constant.MultiPreceptionODModelIndex ++;
	}
	
	public void ClassifyTrain(Instances instances, String SaveModelPath, String SaveModelName) {
		
	}
	
	public void ClassifyTrain() throws Exception {
		File LatestClassifyModels = new File(Constant.LatestClassifyModelsPath);
		File LastClassifyModel = LatestClassifyModels.listFiles()[0];
		Constant.FileCopy(LastClassifyModel.getAbsolutePath(), Constant.HistoryClassifyModelsPath + "\\" + LastClassifyModel.getName());
		Instances instances = AllInstances[0];
		ClassifyTrain(instances, Constant.LatestClassifyModelsPath, Constant.ClassifyModelName);
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
