package com.buaa.hospital.nomogram;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;
import javax.swing.JProgressBar;

public class Main {

	private JFrame frame;
	private DataBase dataBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Main() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		dataBase = new DataBase();
		int ScreenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
		int ScreenWeight=Toolkit.getDefaultToolkit().getScreenSize().width;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu FileMenu = new JMenu("文件");
		menuBar.add(FileMenu);
		
		JMenuItem Import = new JMenuItem("导入...");
		FileMenu.add(Import);
		
		JMenuItem Export = new JMenuItem("导出...");
		FileMenu.add(Export);
		
		JMenu EditMenu = new JMenu("编辑");
		menuBar.add(EditMenu);
		
		JMenuItem PreInput = new JMenuItem("信息录入与预测");
		PreInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PredictInterface predictInterface = new PredictInterface(dataBase);
				predictInterface.startRun();
			}
		});
		EditMenu.add(PreInput);
		
		JMenuItem AfterInput = new JMenuItem("信息查询与修改");
		AfterInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QueryInterface queryInterface = new QueryInterface(dataBase);
				queryInterface.startRun();
			}
		});
		EditMenu.add(AfterInput);
		
		JMenu TrainMenu = new JMenu("训练");
		menuBar.add(TrainMenu);
		
		JMenuItem Retrain = new JMenuItem("重新训练");
		TrainMenu.add(Retrain);
		
		JProgressBar ProgressBar = new JProgressBar();
		ProgressBar.setBounds(0, 235, 200, 15);
		ProgressBar.setStringPainted(true);
		
		
		addSkin();
		ImageIcon imageIcon = new ImageIcon(Constant.BackgroundPicturePath);
		JLabel BackgroundImgLabel = new JLabel(imageIcon);
		frame.getContentPane().add(BackgroundImgLabel, new Integer(Integer.MIN_VALUE));
		BackgroundImgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		Container contain = frame.getContentPane();
        ((JPanel) contain).setOpaque(false); 
	}
	
	private void addSkin() {
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
        	SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
//			System.setProperty("Quaqua.tabLayoutPolicy","wrap");
//	         // set the Quaqua Look and Feel in the UIManager
//	         try { 
//	              UIManager.setLookAndFeel(
//	                  ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel()
//	              );
//	         // set UI manager properties here that affect Quaqua
//	         } catch (Exception e) {
//	             // take an appropriate action here
//	         }
//	         // insert your application initialization code here
	}
}
