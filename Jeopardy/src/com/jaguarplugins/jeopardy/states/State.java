package com.jaguarplugins.jeopardy.states;

import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.scene.canvas.GraphicsContext;

public abstract class State {

	protected GraphicsContext g;
	protected Handler handler;
	protected Question question;
	protected int winnings;
	
	public State(GraphicsContext g, Handler handler) {
		this.g = g;
		this.handler = handler;
	}
	
	public abstract void render();

	public GraphicsContext getG() {
		return g;
	}

	public Question getQuestion() {
		return question;
	}
	
	public int getWinnings() {
		return winnings;
	}
	
}
