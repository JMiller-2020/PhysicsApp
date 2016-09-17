package engine;

import java.awt.Component;
import java.awt.Container;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class SimNode extends Ellipse2D.Double implements SimDrawable
{
	private static final double RADIUS = 5;
	
	private double lastX, lastY;
	private boolean isStatic;
	private boolean isContained;
	private Component container;
	
	public SimNode(double x, double y) {
		this(x, y, 0, 0);
	}
	public SimNode(double x, double y, double xVel, double yVel) {
		super(x - RADIUS, y - RADIUS, RADIUS + RADIUS, RADIUS + RADIUS);
		lastX = (x - RADIUS) - xVel;
		lastY = (y - RADIUS) - yVel;
		isStatic = false;
	}
	public void tick(double delta) {
		double xVel = x - lastX;
		double yVel = y - lastY;
		lastX = x;
		lastY = y;
		
System.out.println("x: " + x + ", xVel: " + xVel);
		x += xVel * delta;
System.out.println("y: " + y + ", yVel: " + yVel);
		y += yVel * delta;
		
		if(isContained) {
			int minX = (int) (container.getX() - RADIUS);
			int minY = (int) (container.getY() - RADIUS);
			int maxX = minX + (int) (container.getWidth());
			int maxY = minY + (int) (container.getHeight());
			if(x < minX) {
				x = minX + (minX - x);
				lastX = minX + (minX - lastX);
			}
			if(y < minY) {
				y = minY + (minY - y);
				lastY = minY + (minY - lastY);
			}
			if(x > maxX) {
				x = maxX + (maxX - x);
				lastX = maxX + (maxX - lastX);
			}
			if(y > maxY) {
				y = maxY + (maxY - y);
				lastY = maxY + (maxY - lastY);
			}
		}
	}
	public void move(double moveX, double moveY) {
		if(!isStatic) {
			x += moveX;
			y += moveY;
		}
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public void contain(Component container) {
		isContained = true;
		this.container = container;
	}
	public void unbound() {
		isContained = false;
	}
}
