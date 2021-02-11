package com.jaguarplugins.jeopardy.states;

import java.util.ArrayList;

import com.jaguarplugins.jeopardy.input.button.Button;
import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class AnswerState extends State {

	private final int SMOOTH = 80;
	
	public AnswerState(GraphicsContext g, Handler handler, Question question, int winnings) {
		super(g, handler);
		this.question = question;
		this.winnings = winnings;
		
		ArrayList<Button> buttons = new ArrayList<Button>();
		Rectangle r;
		
		Team[] otherTeams = new Team[handler.teamsLength()];
		int i = 0;
		for (Team t : handler.getTeams()) {
			
			if (!t.equals(handler.getCurrentTeam())) {
				otherTeams[i] = t;
				i++;
			}
			
		}
		
		double width = handler.getHGrid(3) + handler.getGridWidth() - handler.getHGrid(1);
		for (int x = 0; x < handler.teamsLength(); x++) {
//			OTHER TEAMS
			r = new Rectangle(handler.getHGrid(1) + x*(width/handler.teamsLength()) + 1, handler.getVGrid(5) + 1, width/handler.teamsLength() - 2, handler.getGridHeight()/2 - 2);
			buttons.add(new Button(handler, winnings, otherTeams[x], r, false));
			r = new Rectangle(handler.getHGrid(1) + x*(width/handler.teamsLength()) + 1, handler.getVGrid(5) + handler.getGridHeight()/2 + 1, width/handler.teamsLength() - 2, handler.getGridHeight()/2 - 2);
			buttons.add(new Button(handler, -winnings, otherTeams[x], r, false));
		}
		
//		CURRENT TEAM
		r = new Rectangle(handler.getHGrid(2) - handler.getGridWidth()/4 + 1, handler.getVGrid(4) + 1, handler.getGridWidth()*1.5 - 2, handler.getGridHeight()*0.75 - 2);
		buttons.add(new Button(handler, winnings, handler.getCurrentTeam(), r, true));
		
		for (Button b : buttons) {
			b.render(g);
		}
		
		handler.setButtons(buttons.toArray(new Button[buttons.size()]));
		
	}

	@Override
	public void render() {
		
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridWidth()/5));
		g.setFill(Color.BLACK);
		
		g.fillText(question.getAnswer(), handler.getWidth()/2, handler.getHeight()/2.5);
		
		int bi = 0;
		double width = handler.getHGrid(3) + handler.getGridWidth() - handler.getHGrid(1);
		for (int x = 0; x < handler.teamsLength(); x++) {
//			OTHER TEAMS
			handler.getButtons()[bi].setRectangle(new Rectangle(handler.getHGrid(1) + x*(width/handler.teamsLength()) + 1, handler.getVGrid(5) + 1, width/handler.teamsLength() - 2, handler.getGridHeight()/2 - 2));
			bi++;
			handler.getButtons()[bi].setRectangle(new Rectangle(handler.getHGrid(1) + x*(width/handler.teamsLength()) + 1, handler.getVGrid(5) + handler.getGridHeight()/2 + 1, width/handler.teamsLength() - 2, handler.getGridHeight()/2 - 2));
			bi++;
		}
		
//		CURRENT TEAM
		handler.getButtons()[bi].setRectangle(new Rectangle(handler.getHGrid(2) - handler.getGridWidth()/4 + 1, handler.getVGrid(4) + 1, handler.getGridWidth()*1.5 - 2, handler.getGridHeight()*0.75 - 2));
		bi++;
		
		for (Button b : handler.getButtons()) {
			b.render(g);
		}
		
//		NEXTBUTTON
		g.setFill(Color.CORNFLOWERBLUE);
		g.fillRoundRect(handler.getHGrid(4) + handler.getGridWidth()/2 + 1, handler.getVGrid(5) + handler.getGridHeight()/4 + 1,
				handler.getGridWidth()*0.6 - 2, handler.getGridHeight() - handler.getGridHeight()/2 - 2,
				handler.getHeight()/SMOOTH, handler.getHeight()/SMOOTH);
		
		handler.setNextButton(new Rectangle(handler.getHGrid(4) + handler.getGridWidth()/2, handler.getVGrid(5) + handler.getGridHeight()/4,
				handler.getGridWidth()*0.6, handler.getGridHeight() - handler.getGridHeight()/2));
		
		g.setFill(Color.WHITESMOKE);
		g.fillText("→", handler.getHGrid(4) + handler.getGridWidth()/2 + handler.getGridWidth()*0.3, 
				handler.getVGrid(5) + handler.getGridHeight()/2);

	}
	
	public static void moveOn(Handler handler) {
		
		handler.nextTeam();
		handler.setCurrentState(handler.getBoardState());
		handler.getCurrentState().render();
		
	}

}