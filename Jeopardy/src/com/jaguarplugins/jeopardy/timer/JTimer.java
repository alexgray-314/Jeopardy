package com.jaguarplugins.jeopardy.timer;

import com.jaguarplugins.jeopardy.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class JTimer {

	private Countdown t;
	private Handler handler;
	
	public JTimer(Handler handler) {
		
		this.handler = handler;
		
		t = new Countdown(handler);
		t.start();
		
	}
	
	public void render(GraphicsContext g) {
		
		g.setFill(Color.LIGHTBLUE);
		g.fillRoundRect(10, handler.getHeight()*0.95 - 10, (t.getTime()/Countdown.STARTTIME)*(handler.getWidth() - 20), handler.getHeight()/20, 10, 10);

		if (t.getTime() == 0) {
			g.setFill(Color.INDIANRED);
			g.fillRoundRect(10, handler.getHeight()*0.95 - 10, (handler.getWidth() - 20), handler.getHeight()/20, 10, 10);
		}
		
		g.setFill(Color.BLACK);
		g.strokeRoundRect(10, handler.getHeight()*0.95 - 11, (handler.getWidth() - 20), handler.getHeight()/20 + 2, 10, 10);		
		
	}

	public void stopThread() {
		t.interrupt();
	}
	
}
