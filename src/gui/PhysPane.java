package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PhysPane extends JPanel
{
	public PhysPane() {
		super(new BorderLayout());
		setBackground(new Color(0x333333));
		add(new SimCanvas());
	}
	public void init() {
		((SimCanvas) getComponent(0)).init();
	}
}
