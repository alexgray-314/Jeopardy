package com.jaguarplugins.jeopardy.input;

import com.jaguarplugins.jeopardy.states.AdjustState;
import com.jaguarplugins.jeopardy.states.BoardState;
import com.jaguarplugins.jeopardy.states.QuestionState;
import com.jaguarplugins.jeopardy.states.ResultState;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private Handler handler;
	
	public KeyHandler(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void handle(KeyEvent e) {
		
		if (e.getCode().equals(KeyCode.S)) {

			if (handler.getCurrentState() instanceof BoardState) {

				handler.setCurrentState(new ResultState(handler.getCurrentState().getG(), handler));
				handler.getCurrentState().render();

			}
			
		} 
		
		if (e.getCode().equals(KeyCode.A)) {
			
			if (handler.getCurrentState() instanceof BoardState) {
				
				handler.setCurrentState(new AdjustState(handler.getCurrentState().getG(), handler));
				handler.getCurrentState().render();
				
			}
			
		}
		
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			
			if (!(handler.getCurrentState() instanceof BoardState)) {
				
				try {
				QuestionState.getJTimer().stopThread();
				} catch (NullPointerException ex) {}
				
				handler.setCurrentState(handler.getBoardState());
				handler.getCurrentState().render();
				handler.getMouseHandler().moveMouse(-1.0, -1.0);
				
			}
			
		}

	}

}
