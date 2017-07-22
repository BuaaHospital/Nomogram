package com.buaa.hospital.nomogram;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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

public class Main {

	private JFrame frame;

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
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int ScreenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
		int ScreenWeight=Toolkit.getDefaultToolkit().getScreenSize().width;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu FileMenu = new JMenu("文件");
		menuBar.add(FileMenu);
		
		JMenuItem Import = new JMenuItem("导入...");
		FileMenu.add(Import);
		
		JMenuItem Export = new JMenuItem("导出...");
		FileMenu.add(Export);
		
		JMenu InputMenu = new JMenu("信息录入");
		menuBar.add(InputMenu);
		
		JMenuItem PreInput = new JMenuItem("术前信息录入与预测");
		PreInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PredictInterface predictInterface = new PredictInterface();
				predictInterface.startRun();
			}
		});
		InputMenu.add(PreInput);
		
		JMenuItem AfterInput = new JMenuItem("术后信息录入与修改");
		InputMenu.add(AfterInput);
		
		addSkin();
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
