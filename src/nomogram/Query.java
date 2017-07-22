package nomogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Query {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query window = new Query();
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
	public Query() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(72, 5, 93, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(170, 5, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(268, 5, 93, 23);
		frame.getContentPane().add(btnNewButton);
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
	}
}
