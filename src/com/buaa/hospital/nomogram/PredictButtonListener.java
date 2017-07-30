package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredictButtonListener implements ActionListener{
	
	private PredictInterface predictInterface;
	public PredictButtonListener(PredictInterface predictInterface) {
		// TODO Auto-generated constructor stub
		this.predictInterface = predictInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (predictInterface.CheckData()) {
			Attribute attribute = new Attribute(predictInterface);
			double Nomogram = 0;
			attribute.GenTime();
			attribute.GenRST();
			if (predictInterface.getAlogrithmNum() == 0) {
				//综合算法
			}
			else if (predictInterface.getAlogrithmNum() == 1) {
				//神经网络算法
				try {
					Nomogram = attribute.PredictbyMultiPreception(predictInterface.getModelNum());	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				//分类算法
				try {
					Nomogram = attribute.PredictbyClassifier(predictInterface.getModelNum());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attribute.setPredictNomogram(Nomogram);
			predictInterface.setLogText("病历号为 " + attribute.getID() + " 的病人 " + attribute.getName() + " 使用  " + predictInterface.getAlogrithmName() + " 的预测球镜调整值为 " + Nomogram);
			predictInterface.addHistory(attribute);
			predictInterface.RefreshHistoryTable();
		}
	}

}
