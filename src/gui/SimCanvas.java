package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.SimDrawable;
import engine.SimNode;
import engine.SimRunnable;
import engine.SimSpring;

public class SimCanvas extends Canvas
{
	private ArrayList<SimDrawable> drawables = new ArrayList<SimDrawable>();
	
	public void init() {
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				SimNode input = new SimNode(100 * c + 100, 100 * r + 100);
				input.setStatic(true);
				input.contain(this);
				drawables.add(input);
			}
		}
		for(int i = 0; i < 5; i++) {
			SimNode output = new SimNode(800, 75 * i + 100);
			output.setStatic(true);
			output.contain(this);
			drawables.add(output);
		}
		for(int i = 0; i < 4; i++) {
			SimSpring link = new SimSpring(
					(SimNode) drawables.get((int) (Math.random() * 16)),
					(SimNode) drawables.get((int) (Math.random() * 4 + 16)),
					100, 0.5);
			drawables.add(link);
		}
		for(int i = 0; i < 4; i++) {
			SimNode node = new SimNode(Math.random() * 400 + 400, Math.random() * 300 + 100);
			for(int j = 0; j < Math.random() * 4; j++) {
				SimSpring in = new SimSpring(
						(SimNode) drawables.get((int) (Math.random() * 16)),
						node, 100, 0.01);
				drawables.add(in);
			}
			SimSpring out = new SimSpring(node,
					(SimNode) drawables.get((int) (Math.random() * 4 + 16)),
					100, 0.1);
			drawables.add(node);
			drawables.add(out);
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
