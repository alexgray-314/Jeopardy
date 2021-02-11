package com.jaguarplugins.jeopardy.states;

import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.timer.JTimer;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class QuestionState extends State {
	
	private static JTimer t;
	
	public QuestionState(GraphicsContext g, Handler handler, Question question, int winnings) {

		super(g, handler);
		this.question = question;
		this.winnings = winnings;
		
		t = new JTimer(handler);
		
	}

	@Override
	public void render() {

		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridWidth()/5));
		g.setFill(Color.BLACK);
		
		question.render(handler, g);
		t.render(g);
		
	}

	public static JTimer getJTimer() {
		return t;
	}

}
