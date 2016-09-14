package engine;

import java.awt.geom.Line2D;

public class SimSpring extends Line2D.Double implements SimDrawable
{
	private SimNode n1, n2;
	private double length, strength;
	
	public SimSpring(SimNode n1, SimNode n2, double length, double strength) {
		this.n1 = n1;
		this.n2 = n2;
		this.length = length;
		this.strength = strength;
	}
	public SimSpring(SimNode n1, SimNode n2, double length) {
		this(n1, n2, length, 1.0);
	}
	public SimSpring(SimNode n1, SimNode n2) {
		this(n1, n2, SimSpring.nodeDistance(n1, n2));
	}
	
	public void tick(double delta) {
		double actualLen = nodeDistance();
		double hori = (n2.x - n1.x) / actualLen;
		double vert = (n2.y - n1.y) / actualLen;
		double stretch = strength * delta * (length - actualLen) / 2;
		
		n1.move(-stretch * hori, -stretch * vert);
		n2.move(stretch * hori, stretch * vert);
		
		x1 = n1.getCenterX();
		y1 = n1.getCenterY();
		x2 = n2.getCenterX();
		y2 = n2.getCenterY();
	}
	
	private double nodeDistance() {
		return nodeDistance(n1, n2);
	}
	private static double nodeDistance(SimNode n1, SimNode n2) {
		double dX = n1.x - n2.x;
		double dY = n1.y - n2.y;
		return Math.sqrt(dX*dX + dY*dY);
	}
}
