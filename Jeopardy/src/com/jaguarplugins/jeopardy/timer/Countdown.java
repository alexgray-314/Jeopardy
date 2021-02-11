package com.jaguarplugins.jeopardy.timer;

import com.jaguarplugins.jeopardy.util.Handler;

import javafx.application.Platform;

public class Countdown extends Thread {

	public static final double STARTTIME = 60;
	private double time;
	private Handler handler;
	private boolean running;
	
	public Countdown(Handler handler) {
		
		this.handler = handler;
		running = true;
		time = STARTTIME;
	}

	private synchronized void action() {
		
		if (time >= 0.02) {
			time -= 0.02;
			
		} else {
			time = 0;
			running = false;
		}
		
		Platform.runLater(() -> {
			handler.getCurrentState().render();
		});
		
	}
	
	@Override
	public void run() {
		time = STARTTIME;
		
		int fps = 50;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta > 1) {
				action();
				delta --;
			}
			
			if(timer >= 1_000_000_000) {
				timer = 0;
			}
			
		}
		
	}

	@Override
	public synchronized void interrupt() {
		running = false;
	}
	
	public double getTime() {
		return time;
	}

}
