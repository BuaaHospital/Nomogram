package com.buaa.hospital.nomogram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

enum DataType {
	Unconfirmed,
	Confirmed,
	Untrained,
	Trained,
	BadData
}

public class Constant {
	public static String RunPath = System.getProperty("user.dir");
	public static String DataPath = RunPath + "\\Data";
	public static String OSDataPath = DataPath + "\\OS.arff";
	public static String ODDataPath = DataPath + "\\OD.arff";
	public static String ModelPath = RunPath + "\\Model";
	public static String OSModelpath = ModelPath + "\\OS";
	public static String ODModelpath = ModelPath + "\\OD";
	public static String TrainPath = RunPath + "\\Train";
	public static String NewDataPath = RunPath + "\\NewData";
	
	public static boolean Debug = true;
	public static String ArffFileHead = "@relation \'\'\n\n@attribute ID numeric\n@attribute Name string\n@attribute Age numeric\n@attribute Sex numeric\n@attribute Eye numeric\n@attribute SE numeric\n@attribute UCVA numeric\n@attribute SD numeric\n@attribute CD numeric\n@attribute Axis numeric\n@attribute BCVA numeric\n@attribute CornealRadius numeric\n@attribute OpticalZone numeric\n@attribute K1 numeric\n@attribute K2 numeric\n@attribute Km numeric\n@attribute CCT numeric\n@attribute LeadEye numeric\n@attribute PredictNomogram numeric\n@attribute RST numeric\n@attribute Time numeric\n@attribute Humidity numeric\n@attribute Temperature numeric\n@attribute FirstEyeToTreat numeric\n@attribute Energy numeric\n@attribute OBL numeric\n@attribute Thickness numeric\n@attribute Position numeric\n@attribute RealNomogram numeric\n@attribute RealNomogramLabel {0,0.05,0.1,0.15,0.2,0.25,0.3}\n@attribute SDAfterOneDay numeric\n@attribute SDAfterThreeMonths numeric\n@attribute SDAfterSixMonths numeric\n@attribute CDAfterOneDay numeric\n@attribute CDAfterThreeMonths numeric\n@attribute CDAfterSixMonths numeric\n@attribute AxisAfterOneDay numeric\n@attribute AxisAfterThreeMonths numeric\n@attribute AxisAfterSixMonths numeric\n\n@data\n";
	public static String TempInstanceFilePath = RunPath + "\\Temp\\Temp.arff";
	public static String ModelsPath = RunPath + "\\Models";
	public static String ClassifyModelsPath = ModelsPath + "\\Classify";
	public static String MultiPreceptionModelsPath = ModelsPath + "\\MultiPreception";
	public static String LatestClassifyModelsPath = ClassifyModelsPath + "\\Latest";
	public static String HistoryClassifyModelsPath = ClassifyModelsPath + "\\History";
	public static String LatestMultiPreceptionModelsPath = MultiPreceptionModelsPath + "\\Latest";
	public static String HistoryMultiPreceptionModelsPath = MultiPreceptionModelsPath + "\\History";
	public static String LatestMultiPreceptionOSModelsPath = LatestMultiPreceptionModelsPath + "\\OS";
	public static String LatestMultiPreceptionODModelsPath = LatestMultiPreceptionModelsPath + "\\OD";
	public static String HistoryMultiPreceptionOSModelsPath = HistoryMultiPreceptionModelsPath + "\\OS";
	public static String HistoryMultiPreceptionODModelsPath = HistoryMultiPreceptionModelsPath + "\\OD";
	public static String DataBasePath = RunPath + "\\DataBase";
	public static String UnconfirmedDataPath = DataBasePath + "\\Unconfirmed";
	public static String ConfirmedDataPath = DataBasePath + "\\Confirmed";
	public static String UntrainedDataPath = DataBasePath + "\\Untrained";
	public static String TrainedDataPath = DataBasePath + "\\Trained";
	public static String BadDataPath = DataBasePath + "\\BadData";
	public static int UnconfirmedDataMaxIndex = 1;
	public static int ConfirmedDataMaxIndex = 1;
	public static int UntrainedDataMaxIndex = 1;
	public static int TrainedDataMaxIndex = 1;
	public static int BadDataMaxIndex = 1;
	public static int TrainModelMaxIndex = 1;
	public static String UnconfirmedDataName = "Unconfirmed";
	public static String ConfirmedDataName = "Confirmed";
	public static String UntrainedDataName = "Untrained";
	public static String TrainedDataName = "Trained";
	public static String BadDataName = "BadData";
	public static int MaxInstanceItem = 10000;
	public static double InitDataNum = Integer.MIN_VALUE;
	public static String UnfilledHint = "未确定";
	public static double BadDataBound = 0.5;
	public static String ImagePath = RunPath + "\\Image";
	public static String BackgroundPicturePath = ImagePath + "\\Background_4.jpg";
	public static String TrainFileName = "Train.arff";
	public static String TrainOSPath = TrainPath + "\\OS";
	public static String TrainODPath = TrainPath + "\\OD";
	public static String TrainOSFilePath = TrainOSPath + "\\" + TrainFileName;
	public static String TrainODFilePath = TrainODPath + "\\" + TrainFileName;
	public static int AttributeNum = 39;
	public static int MultiPreceptionOSModelIndex = 1;
	public static int MultiPreceptionODModelIndex = 1;
	public static int ClassifyModelIndex = 1;
	public static String LatestMultiPreceptionOSModelName = "MultiPreceptionOSModel_" + (MultiPreceptionOSModelIndex + 1) + ".model";
	public static String LatestMultiPreceptionODModelName = "MultiPreceptionODModel_" + (MultiPreceptionODModelIndex + 1) + ".model";
	public static String ClassifyModelName = "ClassifyModel_" + (ClassifyModelIndex + 1) + ".model";
	public static int MultipreceptionAttributeNum = 14;
	public static String ConfigureFilePath = RunPath + "\\Config.ini";
	
	public static void writeConfigure(String FilePath) throws IOException {
		BufferedWriter ConfigureFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FilePath, false)));
		String str = "";
		str = str + "RunPath = " + RunPath + "\r\n";
		str = str + "UnconfirmedDataMaxIndex = " + UnconfirmedDataMaxIndex + "\r\n";
		str = str + "ConfirmedDataMaxIndex = " + ConfirmedDataMaxIndex + "\r\n";
		str = str + "UntrainedDataMaxIndex = " + UntrainedDataMaxIndex + "\r\n";
		str = str + "TrainedDataMaxIndex = " + TrainedDataMaxIndex + "\r\n";
		str = str + "BadDataMaxIndex = " + BadDataMaxIndex + "\r\n";
		str = str + "TrainModelMaxIndex = " + TrainModelMaxIndex + "\r\n";
		str = str + "UnconfirmedDataName = " + UnconfirmedDataName + "\r\n";
		str = str + "ConfirmedDataName = " + ConfirmedDataName + "\r\n";
		str = str + "UntrainedDataName = " + UntrainedDataName + "\r\n";
		str = str + "TrainedDataName = " + TrainedDataName + "\r\n";
		str = str + "BadDataName = " + BadDataName + "\r\n";
		str = str + "MaxInstanceItem = " + MaxInstanceItem + "\r\n";
		str = str + "InitDataNum = " + InitDataNum + "\r\n";
		str = str + "UnfilledHint = " + UnfilledHint + "\r\n";
		str = str + "BadDataBound = " + BadDataBound + "\r\n";
		str = str + "MultiPreceptionOSModelIndex = " + MultiPreceptionOSModelIndex + "\r\n";
		str = str + "MultiPreceptionODModelIndex = " + MultiPreceptionODModelIndex + "\r\n";
		str = str + "ClassifyModelIndex = " + ClassifyModelIndex + "\r\n";
		ConfigureFile.write(str);
		ConfigureFile.flush();
		ConfigureFile.close();
	}
	
	public static void readConfigure(String FilePath) throws IOException {
		BufferedReader ConfigureFile = new BufferedReader(new FileReader(new File(FilePath)));
		String Line = "";
		while ((Line = ConfigureFile.readLine()) == null) {
			Line = Line.replace(" ", "");
			String Key = Line.split("=")[0];
			String Value = Line.split("=")[1];
			if (Key.equals("RunPath")) {
				RunPath = Value;
			}
			else if (Key.equals("UnconfirmedDataMaxIndex")) {
				UnconfirmedDataMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("ConfirmedDataMaxIndex")) {
				ConfirmedDataMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("UntrainedDataMaxIndex")) {
				UntrainedDataMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("TrainedDataMaxIndex")) {
				TrainedDataMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("BadDataMaxIndex")) {
				BadDataMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("TrainModelMaxIndex")) {
				TrainModelMaxIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("UnconfirmedDataName")) {
				UnconfirmedDataName = Value;
			}
			else if (Key.equals("ConfirmedDataName")) {
				ConfirmedDataName = Value;
			}
			else if (Key.equals("UntrainedDataName")) {
				UntrainedDataName = Value;
			}
			else if (Key.equals("TrainedDataName")) {
				TrainedDataName = Value;
			}
			else if (Key.equals("BadDataName")) {
				BadDataName = Value;
			}
			else if (Key.equals("MaxInstanceItem")) {
				MaxInstanceItem = Integer.parseInt(Value);
			}
			else if (Key.equals("InitDataNum")) {
				InitDataNum = Integer.parseInt(Value);
			}
			else if (Key.equals("UnfilledHint")) {
				UnfilledHint = Value;
			}
			else if (Key.equals("BadDataBound")) {
				BadDataBound = Double.parseDouble(Value);
			}
			else if (Key.equals("MultiPreceptionOSModelIndex")) {
				MultiPreceptionOSModelIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("MultiPreceptionODModelIndex")) {
				MultiPreceptionODModelIndex = Integer.parseInt(Value);
			}
			else if (Key.equals("ClassifyModelIndex")) {
				ClassifyModelIndex = Integer.parseInt(Value);
			}
		}
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
	
	
	public static void FileDelete(File file) {
		if (file.isFile()) {
			file.delete();
		}
		else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i ++) {
				FileDelete(files[i]);
			}
			file.delete();
		}
	}
}
