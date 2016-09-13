package engine;

import gui.SimCanvas;

public class SimRunnable implements Runnable
{
	private static final int TARGET_FPS = 60;
	private static final int ONE_SECOND = 1000;
	private static final double TARGET_FRAME_LENGTH = ONE_SECOND / TARGET_FPS;
	private boolean isRunning;
	private SimCanvas sim;
	
	public SimRunnable(SimCanvas sim) {
		isRunning = false;
		this.sim = sim;
	}
	public void run() {
		isRunning = true;
		long now = System.currentTimeMillis();
		long then = now;
		long lastFrame = now;
		long lastSec = now;
		int frames = 0, count = 0;
		while(isRunning) {
			then = now;
			now = System.currentTimeMillis();
//System.out.println("Running...");
			
			count++;
			if(now - lastFrame >= TARGET_FRAME_LENGTH) {
				sim.tick((now - lastFrame) / TARGET_FRAME_LENGTH);
				sim.repaint();
				lastFrame = now;
				frames++;
			}
			if(now - lastSec >= ONE_SECOND) {
System.out.println("FPS: " + frames);
System.out.println("TPF: " + (count * 1.0) / frames);
				frames = 0;
				count = 0;
				lastSec = now;
			}
			
		}
	}
}
