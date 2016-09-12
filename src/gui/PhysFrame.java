package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhysFrame extends JFrame {
	public static void main(String[] args) {
		PhysFrame frame = new PhysFrame();
	}
	
	public PhysFrame() {
		init();
		setVisible(true);
	}
	
	private void init() {
		//TODO: add components
		JPanel content = new PhysPane();
		setContentPane(content);
		setMinimumSize(new Dimension(400, 300));
		setSize(new Dimension(1024, 678));
	}
}
