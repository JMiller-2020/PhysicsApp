package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PhysFrame extends JFrame {
	public static void main(String[] args) {
		PhysFrame frame = new PhysFrame();
		frame.init();
	}
	
	public PhysFrame() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					createGUI();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			System.err.println("GUI creation failed.");
		}
	}
	
	private void createGUI() {
		JPanel content = new PhysPane();
		setContentPane(content);
		setMinimumSize(new Dimension(400, 300));
		setSize(new Dimension(1024, 678));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void init() {
		((PhysPane) getContentPane()).init();
	}
}
