package com.buaa.hospital.nomogram;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ibm.icu.text.DisplayContext.Type;

public class RadioButtonAction implements Action{
	
	private int Type;
	private JRadioButton jRadioButton;
	private Object Box;
	
	public RadioButtonAction(int Type, JRadioButton jRadioButton, Object Box) {
		// TODO Auto-generated constructor stub
		this.Type = Type;
		this.jRadioButton = jRadioButton;
		this.Box = Box;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Type == 0) {
			if (jRadioButton.isSelected()) {
				((JTextField)Box).setEditable(true);
				((JTextField)Box).setEnabled(true);
			}
			else {
				((JTextField)Box).setEditable(false);
				((JTextField)Box).setEnabled(false);
			}
		}
		else {
			if (jRadioButton.isSelected()) {
				((DateChooser)Box).setEnabled(true);
			}
			else {
				((DateChooser)Box).setEnabled(false);
			}
		}
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
