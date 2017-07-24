package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButtonListener implements ActionListener {
	
	private PredictInterface predictInterface;
	public SaveButtonListener(PredictInterface predictInterface) {
		// TODO Auto-generated constructor stub
		this.predictInterface = predictInterface;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (predictInterface.CheckData()) {
			Attribute attribute = new Attribute(predictInterface);
			double Nomogram = 0;
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
			}
			attribute.setNomogram(Nomogram);
			attribute.GenTime();
			attribute.GenRST();
			predictInterface.setLogText(">> 病历号为 " + attribute.getID() + " 的病人 " + attribute.getName() + " 使用" + predictInterface.getAlogrithmName() + "的预测球镜调整值为 " + Nomogram);
			predictInterface.setLogText("正在检查数据库中是否有相同的病人信息...");
			predictInterface.setLogText("病人信息和预测结果正在存入数据库...");
			predictInterface.setLogText("保存完毕！");
			predictInterface.addHistory(attribute);
			predictInterface.RefreshHistoryTable();
			
		}
	}
	
}
