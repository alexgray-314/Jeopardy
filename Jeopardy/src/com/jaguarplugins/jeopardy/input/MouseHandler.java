package com.jaguarplugins.jeopardy.input;

import com.jaguarplugins.jeopardy.input.button.Button;
import com.jaguarplugins.jeopardy.input.button.ScoreButton;
import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.states.AdjustState;
import com.jaguarplugins.jeopardy.states.AnswerState;
import com.jaguarplugins.jeopardy.states.BoardState;
import com.jaguarplugins.jeopardy.states.QuestionState;
import com.jaguarplugins.jeopardy.states.ResultState;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {

	private Handler handler;
	private int lastX = -1, lastY = -1;
	private int pointX, pointY;

	public MouseHandler(Handler handler) {
		super();
		this.handler = handler;
	}
	
	@Override
	public void handle(MouseEvent e) {

		if (handler.getCurrentState() instanceof BoardState) {

			if (e.getEventType().equals(MouseEvent.MOUSE_MOVED)) {

				pointX = -1;
				pointY = -1;

				if (e.getSceneX() <= handler.getHGrid(5) && e.getSceneY() <= handler.getVGrid(6)) {

					for (Question[] category : handler.getQuestions()) {
						for(Question question : category) {

							if (e.getSceneX() >= handler.getHGrid(question.getX()) && e.getSceneY() >= handler.getVGrid(question.getY())) {

								pointX = question.getX();
								pointY = question.getY() - 1;

							}		
						}
					}
				}

				if (lastX != -1 && lastY != -1) {

					handler.getQuestions()[lastX][lastY].setHover(false);
					handler.getCurrentState().render();
					lastX = -1;
					lastY = -1;

				}

				if (pointX != -1 && pointY != -1) {

					lastX = pointX;
					lastY = pointY;

					handler.getQuestions()[pointX][pointY].setHover(true);
					handler.getCurrentState().render();

				}

			}

			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				
				if (pointX != -1 && pointY != -1) {

					if (!handler.getQuestions()[pointX][pointY].isAnswered()) {

						handler.getQuestions()[pointX][pointY].setAnswered(true);
						handler.setCurrentState(new QuestionState(handler.getCurrentState().getG(), handler, handler.getQuestions()[pointX][pointY], handler.getQuestions()[pointX][pointY].getY()*10));
						handler.getCurrentState().render();
					}

				}

			}
			
		} else if (handler.getCurrentState() instanceof QuestionState) {
			
			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				
				handler.setCurrentState(new AnswerState(handler.getCurrentState().getG(), handler, handler.getCurrentState().getQuestion(), handler.getCurrentState().getWinnings()));
				handler.getCurrentState().render();
				QuestionState.getJTimer().stopThread();
				
			}
			
		} else if (handler.getCurrentState() instanceof AnswerState) {

			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {

				if (handler.getNextButton().intersects(e.getSceneX(), e.getSceneY(), 1, 1)) {
					AnswerState.moveOn(handler);
				} else {
					
					for (Button b : handler.getButtons()) {
						b.click(e.getSceneX(), e.getSceneY());
					}
					
				}

			}

		} else if (handler.getCurrentState() instanceof ResultState) {
			
			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				ResultState.nextStage(handler);
			}
			
		} else if (handler.getCurrentState() instanceof AdjustState) {
			
			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				
				for (ScoreButton b : handler.getScoreButtons()) {
					b.click(e.getSceneX(), e.getSceneY());
				}
				handler.getCurrentState().render();
				
			} 
			
			if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				
				for (ScoreButton b : handler.getScoreButtons()) {
					b.startHover(e.getSceneX(), e.getSceneY());
				}
				handler.getCurrentState().render();
				
			}
			
		}
		
	}

}
