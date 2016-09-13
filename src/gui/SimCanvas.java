package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.SimDrawable;
import engine.SimNode;
import engine.SimRunnable;

public class SimCanvas extends Canvas
{
	private ArrayList<SimDrawable> drawables = new ArrayList<SimDrawable>();
	
	public void init() {
		for(int i = 0; i < 10; i++) {
			SimNode comp = new SimNode(100 * i + 10, 500, 1, -0.5);
System.out.println(getWidth() + " " + getHeight());
			comp.contain(this);
			drawables.add(comp);
		}
		Thread simThread = new Thread(new SimRunnable(this));
		simThread.start();
	}
	
	public void tick(double delta) {
//System.out.println("SimCanvas ticked. delta: " + delta);
		for(SimDrawable comp: drawables) {
			comp.tick(delta);
		}
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0xdddddd));
		for(SimDrawable comp: drawables) {
			g2.draw(comp);
		}
		//System.out.println("SimCanvas painted.");
	}
}
