package nomogram;

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
	
	public static Attribute GenAttr(Shell shell, Text Input_name, Text Input_Age, Button Sex_M, Button Sex_F, Button Eye_OS, Button Eye_OD, Text Input_SE, Text Input_UCVApre, Text Input_SD, Text Input_CD, Text Input_A, Text Input_BCVA, Text Input_CornealRadius, Text Input_Opticalzone, Text Input_K1, Text Input_K2, Text Input_Km, Text Input_CCT) {
		Attribute attribute = new Attribute();
		Shell shell2 = new Shell();
		shell2.setVisible(false);
		shell2.setSize(300, 200);
		Button okButton = new Button(shell2, SWT.NONE);
		okButton.setBounds(100, 100, 80, 20);
		okButton.setText("确定");
		Text Hint = new Text(shell2, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Hint.setBounds(50, 50, 200, 20);
		Hint.setText("Please Input Correct Attribute!");
		okButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				shell2.setVisible(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		attribute.Name = Input_name.getText();
		try {
			attribute.Age = Double.parseDouble(Input_Age.getText());
			if (Sex_M.getSelection()) {
				attribute.Sex = SexEnum.Male;
			}
			else {
				attribute.Sex = SexEnum.Female;
			}
			if (Eye_OS.getSelection()) {
				attribute.Eye = EyeEnum.OS;
			}
			else {
				attribute.Eye = EyeEnum.OD;
			}
			attribute.SE = Double.parseDouble(Input_SE.getText());
			attribute.UCVA = Double.parseDouble(Input_UCVApre.getText());
			attribute.SD = Double.parseDouble(Input_SD.getText());
			attribute.CD = Double.parseDouble(Input_CD.getText());
			attribute.Axis = Double.parseDouble(Input_A.getText());
			attribute.BCVA = Double.parseDouble(Input_BCVA.getText());
			attribute.CornealRadius = Double.parseDouble(Input_CornealRadius.getText());
			attribute.OpticalZone = Double.parseDouble(Input_Opticalzone.getText());
			attribute.K1 = Double.parseDouble(Input_K1.getText());
			attribute.K2 = Double.parseDouble(Input_K2.getText());
			attribute.Km = Double.parseDouble(Input_Km.getText());
			attribute.CCT = Double.parseDouble(Input_CCT.getText());
			
		} catch(Exception e) {
			System.out.println("Please Input Complete Attribute Value.");
			shell2.setVisible(true);
		}
		return attribute;
	}
	
	public void FillBlank(Shell shell, Text Input_name, Text Input_Age, Button Sex_M, Button Sex_F, Button Eye_OS, Button Eye_OD, Text Input_SE, Text Input_UCVApre, Text Input_SD, Text Input_CD, Text Input_A, Text Input_BCVA, Text Input_CornealRadius, Text Input_Opticalzone, Text Input_K1, Text Input_K2, Text Input_Km, Text Input_CCT) {
		Input_name.setText(Name);
		Input_Age.setText(Double.toString(Age));
		if (Sex == SexEnum.Female) {
			Sex_F.setSelection(true);
		}
		else {
			Sex_M.setSelection(true);
		}
		if (Eye == EyeEnum.OD) {
			Eye_OD.setSelection(true);
		}
		else {
			Eye_OS.setSelection(true);
		}
		Input_SE.setText(Double.toString(SE));
		Input_UCVApre.setText(Double.toString(UCVA));
		Input_SD.setText(Double.toString(SD));
		Input_CD.setText(Double.toString(CD));
		Input_A.setText(Double.toString(Axis));
		Input_BCVA.setText(Double.toString(BCVA));
		Input_CornealRadius.setText(Double.toString(CornealRadius));
		Input_Opticalzone.setText(Double.toString(OpticalZone));
		Input_K1.setText(Double.toString(K1));
		Input_K2.setText(Double.toString(K2));
		Input_Km.setText(Double.toString(Km));
		Input_CCT.setText(Double.toString(CCT));
	}
	
	public void FillBook(XSSFRow xssfRow) {
		for (int i = 0; i < 17; i ++) {
			xssfRow.createCell(i);
		}
		xssfRow.getCell(0).setCellValue(Name);
		xssfRow.getCell(1).setCellValue(Age);
		if (Sex == SexEnum.Male) {
			xssfRow.getCell(2).setCellValue(1);
		}
		else {
			xssfRow.getCell(2).setCellValue(2);
		}
		if (Eye == EyeEnum.OS) {
			xssfRow.getCell(2).setCellValue(1);
		}
		else {
			xssfRow.getCell(2).setCellValue(2);
		}
		xssfRow.getCell(4).setCellValue(SE);
		xssfRow.getCell(5).setCellValue(UCVA);
		xssfRow.getCell(6).setCellValue(SD);
		xssfRow.getCell(7).setCellValue(CD);
		xssfRow.getCell(8).setCellValue(Axis);
		xssfRow.getCell(9).setCellValue(BCVA);
		xssfRow.getCell(10).setCellValue(CornealRadius);
		xssfRow.getCell(11).setCellValue(OpticalZone);
		xssfRow.getCell(12).setCellValue(K1);
		xssfRow.getCell(13).setCellValue(K2);
		xssfRow.getCell(14).setCellValue(Km);
		xssfRow.getCell(15).setCellValue(CCT);
		xssfRow.getCell(16).setCellValue(PredictNomogram);
	}
	
	public static Attribute GenAttribute(XSSFRow xssfRow) {
		Attribute attribute = new Attribute();
		ArrayList<String> Attributes = new ArrayList<>();
		for (int i = 0; i < xssfRow.getLastCellNum(); i ++) {
			Attributes.add(getValue(xssfRow.getCell(i)));
		}
		attribute.Name = Attributes.get(0);
		attribute.Age = Double.parseDouble(Attributes.get(1));
		if (Attributes.get(2).equals("F")) {
			attribute.Sex = SexEnum.Female;
		}
		else {
			attribute.Sex = SexEnum.Male;
		}
		if (Attributes.get(3).equals("OD")) {
			attribute.Eye = EyeEnum.OD;
		}
		else {
			attribute.Eye = EyeEnum.OS;
		}
		attribute.SE = Double.parseDouble(Attributes.get(4));
		attribute.UCVA = Double.parseDouble(Attributes.get(5));
		attribute.SD = Double.parseDouble(Attributes.get(6));
		attribute.CD = Double.parseDouble(Attributes.get(7));
		attribute.Axis = Double.parseDouble(Attributes.get(8));
		attribute.BCVA = Double.parseDouble(Attributes.get(9));
		attribute.CornealRadius = Double.parseDouble(Attributes.get(10));
		attribute.OpticalZone = Double.parseDouble(Attributes.get(11));
		attribute.K1 = Double.parseDouble(Attributes.get(12));
		attribute.K2 = Double.parseDouble(Attributes.get(13));
		attribute.Km = Double.parseDouble(Attributes.get(14));
		attribute.CCT = Double.parseDouble(Attributes.get(15));
		return attribute;
	}
	
	public static Attribute GenAttribute(HSSFRow hssfRow) {
		Attribute attribute = new Attribute();
		ArrayList<String> Attributes = new ArrayList<>();
		for (int i = 0; i < hssfRow.getRowNum(); i ++) {
			Attributes.add(getValue(hssfRow.getCell(i)));
		}
		attribute.Name = Attributes.get(0);
		attribute.Age = Double.parseDouble(Attributes.get(1));
		if (Attributes.get(2).equals("F")) {
			attribute.Sex = SexEnum.Female;
		}
		else {
			attribute.Sex = SexEnum.Male;
		}
		if (Attributes.get(3).equals("OD")) {
			attribute.Eye = EyeEnum.OD;
		}
		else {
			attribute.Eye = EyeEnum.OS;
		}
		attribute.SE = Double.parseDouble(Attributes.get(4));
		attribute.UCVA = Double.parseDouble(Attributes.get(5));
		attribute.SD = Double.parseDouble(Attributes.get(6));
		attribute.CD = Double.parseDouble(Attributes.get(7));
		attribute.Axis = Double.parseDouble(Attributes.get(8));
		attribute.BCVA = Double.parseDouble(Attributes.get(9));
		attribute.CornealRadius = Double.parseDouble(Attributes.get(10));
		attribute.OpticalZone = Double.parseDouble(Attributes.get(11));
		attribute.K1 = Double.parseDouble(Attributes.get(12));
		attribute.K2 = Double.parseDouble(Attributes.get(13));
		attribute.Km = Double.parseDouble(Attributes.get(14));
		attribute.CCT = Double.parseDouble(Attributes.get(15));
		return attribute;
	}
	
	public Instance GenInstance() throws Exception {
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.TempInstanceFilePath, false)));
		String string = Age + "," + SE + "," + UCVA + "," + SD + "," + CD + "," + Axis + "," + BCVA + "," + CornealRadius + "," + OpticalZone + "," + K1 + "," + K2 + "," + Km + "," + CCT + "," + "0";
		bWriter.write(Constant.ArffFileHead + string);
		bWriter.flush();
		bWriter.close();
		DataSource dataSource = new DataSource(Constant.TempInstanceFilePath);
		Instances structure = dataSource.getDataSet();
		return structure.instance(0);
	}
	
	public double Predict() throws Exception {
		String curModelPath = "";
		Instance instance = this.GenInstance();
		if (Eye == EyeEnum.OS) {
			curModelPath = Constant.OSModelpath;
		}
		else {
			curModelPath = Constant.ODModelpath;
		}
		return multilayer.Predict(curModelPath, instance);
	}
	
	public static String getValue(XSSFCell xssfRow) {
		if (xssfRow == null) {
			return "";
		}
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
	
	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "";
		}
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	public void setNomogram(double nomogram) {
		this.PredictNomogram = nomogram;
	}
	
	public String toString() {
		return Name + " " + Age + " " + Sex + " " + Eye;
	}

}
