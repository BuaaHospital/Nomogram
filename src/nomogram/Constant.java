package nomogram;

public class Constant {
	public static String RunPath = System.getProperty("user.dir");
	public static String ArffFileHead = "@relation QueryResult-weka.filters.unsupervised.attribute.Remove-R1-weka.filters.unsupervised.attribute.Remove-R2-weka.filters.unsupervised.attribute.Remove-R2-weka.filters.unsupervised.attribute.Remove-R8-9-weka.filters.unsupervised.attribute.Remove-R18-21-weka.filters.unsupervised.attribute.Remove-R17-weka.filters.unsupervised.attribute.Remove-R14-weka.filters.unsupervised.attribute.Remove-R12-weka.filters.unsupervised.attribute.Remove-R10-11-weka.filters.unsupervised.attribute.Remove-R11-weka.filters.unsupervised.attribute.Remove-R10\n\n@attribute Age numeric\n@attribute SE numeric\n@attribute UCVApre numeric\n@attribute SD numeric\n@attribute CD numeric\n@attribute A numeric\n@attribute BCVA numeric\n@attribute nomogram numeric\n@attribute CornealRadius numeric\n@attribute Opticalzone numeric\n@attribute K1 numeric\n@attribute K2 numeric\n@attribute Km numeric\n@attribute CCT numeric\n\n@data\n";
	public static String TempInstanceFilePath = RunPath + "\\Temp\\temp.arff";
	public static String DataPath = RunPath + "\\Data";
	public static String OSDataPath = DataPath + "\\OS.arff";
	public static String ODDataPath = DataPath + "\\OD.arff";
	public static String ModelPath = RunPath + "\\Model";
	public static String OSModelpath = ModelPath + "\\OS";
	public static String ODModelpath = ModelPath + "\\OD";
	public static String TrainPath = RunPath + "\\Train";
	public static String NewDataPath = RunPath + "\\NewData";
	
	
	
}
