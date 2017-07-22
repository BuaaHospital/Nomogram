package nomogram;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.awt.Frame;
import java.awt.geom.Ellipse2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

public class Main {

	protected Shell shell;
	private Text Input_name;
	private Text Input_Age;
	private Text Name;
	private Text Age;
	private Text Input_SE;
	private Text SE;
	private Text UCVApre;
	private Text Input_UCVApre;
	private Text SD;
	private Text Input_SD;
	private Text CD;
	private Text Input_CD;
	private Text A;
	private Text Input_A;
	private Text BCVA;
	private Text Input_BCVA;
	private Text CornealRadius;
	private Text Input_CornealRadius;
	private Text Opticalzone;
	private Text Input_Opticalzone;
	private Text K1;
	private Text Input_K1;
	private Text K2;
	private Text Input_K2;
	private Text Km;
	private Text Input_Km;
	private Text CCT;
	private Text Input_CCT;
	private Group Eye;
	private Text nomogram;
	private Text Output_nomogram;
	private Button Clear;
	private Text Hint;
	private MenuItem Export;
	private MenuItem Run;
	private Menu SubRun;
	private MenuItem Predict;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		ArrayList<Attribute> attributes = new ArrayList<>();
		shell = new Shell();
		shell.setSize(900, 720);
		shell.setText("Nomogram Prediction");
		
		Name = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Name.setBounds(240, 100, 50, 20);
		Name.setText("Name");
		Name.setEditable(false);
		
		Input_name = new Text(shell, SWT.BORDER);
		Input_name.setBounds(300, 100, 100, 20);
		
		
		Age = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Age.setBounds(540, 100, 50, 20);
		Age.setText("Age");
		Age.setEditable(false);
		
		Input_Age = new Text(shell, SWT.BORDER);
		Input_Age.setBounds(600, 100, 100, 20);
		
		Group Sex = new Group(shell, SWT.NONE);
		Sex.setBounds(240, 126, 160, 66);
		
		Button Sex_F = new Button(Sex, SWT.RADIO);
		Sex_F.setBounds(100, 25, 50, 20);
		Sex_F.setText("女");
		
		Button Sex_M = new Button(Sex, SWT.RADIO);
		Sex_M.setBounds(10, 25, 50, 20);
		Sex_M.setText("男");
		
		Eye = new Group(shell, SWT.NONE);
		Eye.setBounds(540, 126, 160, 66);
		
		
		Button Eye_OS = new Button(Eye, SWT.RADIO);
		Eye_OS.setBounds(10, 25, 50, 20);
		Eye_OS.setText("OS");
		
		Button Eye_OD = new Button(Eye, SWT.RADIO);
		Eye_OD.setBounds(100, 25, 50, 20);
		Eye_OD.setText("OD");
		
		SE = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		SE.setBounds(240, 200, 50, 20);
		SE.setText("SE");
		SE.setEditable(false);
		
		Input_SE = new Text(shell, SWT.BORDER);
		Input_SE.setBounds(300, 200, 100, 20);
		
		UCVApre = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		UCVApre.setBounds(510, 200, 80, 20);
		UCVApre.setEditable(false);
		UCVApre.setText("UCVApre");
		
		Input_UCVApre = new Text(shell, SWT.BORDER);
		Input_UCVApre.setBounds(600, 200, 100, 20);
		
		SD = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		SD.setBounds(240, 250, 50, 20);
		SD.setEditable(false);
		SD.setText("SD");
		
		Input_SD = new Text(shell, SWT.BORDER);
		Input_SD.setBounds(300, 250, 100, 20);
		
		CD = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		CD.setBounds(540, 250, 50, 20);
		CD.setEditable(false);
		CD.setText("CD");
		
		Input_CD = new Text(shell, SWT.BORDER);
		Input_CD.setBounds(600, 250, 100, 20);
		
		A = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		A.setBounds(240, 300, 50, 20);
		A.setEditable(false);
		A.setText("A");
		
		Input_A = new Text(shell, SWT.BORDER);
		Input_A.setBounds(300, 300, 100, 20);
		
		BCVA = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		BCVA.setBounds(540, 300, 50, 20);
		BCVA.setEditable(false);
		BCVA.setText("BCVA");
		
		Input_BCVA = new Text(shell, SWT.BORDER);
		Input_BCVA.setBounds(600, 300, 100, 20);
		
		CornealRadius = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		CornealRadius.setBounds(190, 350, 100, 20);
		CornealRadius.setEditable(false);
		CornealRadius.setText("CornealRadius");
		
		Input_CornealRadius = new Text(shell, SWT.BORDER);
		Input_CornealRadius.setBounds(300, 350, 100, 20);
		
		Opticalzone = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Opticalzone.setBounds(490, 350, 100, 20);
		Opticalzone.setEditable(false);
		Opticalzone.setText("Opticalzone");
		
		Input_Opticalzone = new Text(shell, SWT.BORDER);
		Input_Opticalzone.setBounds(600, 350, 100, 20);
		
		K1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		K1.setBounds(240, 400, 50, 20);
		K1.setEditable(false);
		K1.setText("K1");
		
		Input_K1 = new Text(shell, SWT.BORDER);
		Input_K1.setBounds(300, 400, 100, 20);
		
		K2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		K2.setBounds(540, 400, 50, 20);
		K2.setEditable(false);
		K2.setText("K2");
		
		Input_K2 = new Text(shell, SWT.BORDER);
		Input_K2.setBounds(600, 400, 100, 20);
		
		Km = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Km.setBounds(240, 450, 50, 20);
		Km.setEditable(false);
		Km.setText("Km");
		
		Input_Km = new Text(shell, SWT.BORDER);
		Input_Km.setBounds(300, 450, 100, 20);
		
		CCT = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		CCT.setBounds(540, 450, 50, 20);
		CCT.setEditable(false);
		CCT.setText("CCT");
		
		Input_CCT = new Text(shell, SWT.BORDER);
		Input_CCT.setBounds(600, 450, 100, 20);
		
		nomogram = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		nomogram.setBounds(390, 550, 100, 20);
		nomogram.setEditable(false);
		nomogram.setText("nomogram");
		
		Output_nomogram = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Output_nomogram.setBounds(500, 550, 100, 20);
		Output_nomogram.setEditable(false);
		Output_nomogram.setText("0");
		
		Button Ensure = new Button(shell, SWT.NONE);
		Ensure.setBounds(400, 500, 80, 30);
		Ensure.setText("确定");
		
		Clear = new Button(shell, SWT.NONE);
		Clear.setBounds(500, 500, 80, 30);
		Clear.setText("清空");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem Files = new MenuItem(menu, SWT.CASCADE);
		Files.setText("文件");
		
		Menu SubFiles = new Menu(Files);
		Files.setMenu(SubFiles);
		
		MenuItem Import = new MenuItem(SubFiles, SWT.NONE);
		Import.setText("从文件导入");
		
		Export = new MenuItem(SubFiles, SWT.NONE);
		Export.setText("导出到文件");
		
		Run = new MenuItem(menu, SWT.CASCADE);
		Run.setText("运行");
		
		SubRun = new Menu(Run);
		Run.setMenu(SubRun);
		
		Predict = new MenuItem(SubRun, SWT.NONE);
		Predict.setText("从文件中预测");
		
		Hint = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		Hint.setBounds(0, 640, 500, 20);
		
		Import.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setFilterPath(Constant.RunPath);
				fileDialog.setFilterExtensions(new String[]{"*.*", "*.txt", ".xls", ".xlsx"});  
				fileDialog.setFilterNames(new String[]{"All Files(*.*)", "Text Files(*.txt)", "xls Files(*.xls)", "xlsx Files(*.xlsx)"});
				String file=fileDialog.open();  
				if(file!=null) {	
					File path=new File(file);  
					System.out.println(path.getPath());
					String FilePath = path.getAbsolutePath();
					Hint.setText(FilePath);
					try {
						InputStream inputStream = new FileInputStream(path);
						if (FilePath.endsWith(".xlsx")) {	
							XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
							XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
							Attribute attribute = Attribute.GenAttribute(xssfSheet.getRow(0));
							System.out.println(attribute.toString());
							attribute.FillBlank(shell, Input_name, Input_Age, Sex_M, Sex_F, Eye_OS, Eye_OD, Input_SE, Input_UCVApre, Input_SD, Input_CD, Input_A, Input_BCVA, Input_CornealRadius, Input_Opticalzone, Input_K1, Input_K2, Input_Km, Input_CCT);								
						}
						else if (FilePath.endsWith(".xls")){
							HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);		
							HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Export.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (Hint.getText().equals("")) {
						MessageBox messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.YES);
						messageBox.setMessage("Please Import The File First!");
						messageBox.setText("Warning");
						messageBox.open();
					}
					else if (Hint.getText().endsWith(".xlsx")) {
						DirectoryDialog directoryDialog = new DirectoryDialog(shell);
						directoryDialog.setFilterPath(Constant.RunPath);
						String saveDirectoryPath = directoryDialog.open();
						if (saveDirectoryPath != null) {
							String[] strings = Hint.getText().split("\\\\");
							String saveFilePath = saveDirectoryPath + "\\" + strings[strings.length-1];
							System.out.println(saveFilePath);
							File saveFile = new File(saveFilePath);
							if (saveFile.exists()) {
								MessageBox messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK | SWT.CANCEL);
								messageBox.setMessage("There Is A Same Name File Here, Please Ensure If Covering It!");
								messageBox.setText("Warning");
								if (messageBox.open() == SWT.OK) {
									saveFile.delete();
									saveFile.createNewFile();
									InputStream inputStream = new FileInputStream(saveFile);
									BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
									XSSFWorkbook xssfWorkbook = new XSSFWorkbook(bufferedInputStream);
									xssfWorkbook.createSheet();
									XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
									for (int i = 0; i < attributes.size(); i ++) {
										xssfSheet.createRow(i);
										attributes.get(i).FillBook(xssfSheet.getRow(i));
									}
									bufferedInputStream.close();
									inputStream.close();
									System.out.println("close!");
								}
								else {
									widgetSelected(arg0);
								}
							}
							else {
								saveFile.createNewFile();
								InputStream inputStream = new FileInputStream(saveFile);
								BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
								XSSFWorkbook xssfWorkbook = new XSSFWorkbook(bufferedInputStream);
								XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
								for (int i = 0; i < attributes.size(); i ++) {
									attributes.get(i).FillBook(xssfSheet.getRow(i));
								}
								bufferedInputStream.close();
								inputStream.close();
								System.out.println("close!");
							}
						}
					}
					else {
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Predict.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				try {	
					InputStream inputStream = new FileInputStream(new File(Hint.getText()));
					XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
					XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
					attributes.clear();
					for (int i = 0; i < xssfSheet.getLastRowNum(); i ++) {
						Attribute attribute = Attribute.GenAttribute(xssfSheet.getRow(i));
						attribute.setNomogram(attribute.Predict());
						attributes.add(attribute);
					}
					MessageBox messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.YES);
					messageBox.setMessage("Running Finished!");
					messageBox.setText("Congratulations");
					messageBox.open();
					inputStream.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Ensure.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Attribute attribute = Attribute.GenAttr(shell, Input_name, Input_Age, Sex_M, Sex_F, Eye_OS, Eye_OD, Input_SE, Input_UCVApre, Input_SD, Input_CD, Input_A, Input_BCVA, Input_CornealRadius, Input_Opticalzone, Input_K1, Input_K2, Input_Km, Input_CCT);
				try {
					Output_nomogram.setText(Double.toString(attribute.Predict()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Clear.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Input_name.setText("");
				Input_Age.setText("");
				Sex_M.setSelection(false);
				Sex_F.setSelection(false);
				Eye_OS.setSelection(false);
				Eye_OD.setSelection(false);
				Input_SE.setText("");
				Input_UCVApre.setText("");
				Input_SD.setText("");
				Input_CD.setText("");
				Input_A.setText("");
				Input_BCVA.setText("");
				Input_CornealRadius.setText("");
				Input_Opticalzone.setText("");
				Input_K1.setText("");
				Input_K2.setText("");
				Input_Km.setText("");
				Input_CCT.setText("");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println(Constant.RunPath);
		JFileChooser jFileChooser = new JFileChooser();
		
		
		try {
			
			UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        JFrame.setDefaultLookAndFeelDecorated(true); 
        try {
        	SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());  
        	SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
        	SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBinaryWatermark());  
        	SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter()); 
        	SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
        	SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());  
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
