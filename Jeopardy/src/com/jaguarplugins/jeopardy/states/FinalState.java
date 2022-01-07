package com.jaguarplugins.jeopardy.states;

import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FinalState extends State {

	private Team[] teams;
	
	public FinalState(GraphicsContext g, Handler handler) {
		super(g, handler);
		this.teams = handler.getTeams();
	}

	@Override
	public void render() {
		
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		g.setFont(new Font("broadway", handler.getGridWidth()/3));
		g.setFill(Color.WHITE);
		g.fillText("FINAL JEOPARDY", handler.getWidth()/2, handler.getVGrid(0));
		
		betChoice();
		
//		Controls help
		double bWidth = handler.getGridWidth()/8;
		double y = handler.getVGrid(6) + (handler.getHeight() - handler.getVGrid(6))/2;
		double x = handler.getGridWidth()/6;
		
		g.setFill(Color.WHITE);
		g.setStroke(Color.WHITE);
		g.setFont(new Font("calibri", handler.getGridWidth()/12));
		g.strokeRoundRect(handler.getHGrid(0) + x - bWidth/2, y-bWidth/2, bWidth, bWidth, handler.getGridHeight()/10, handler.getGridHeight()/10);
		g.fillText("Esc", handler.getHGrid(0) + x, y);
		
		g.setFont(new Font("calibri", handler.getGridWidth()/16));
		g.setTextAlign(TextAlignment.LEFT);
		g.fillText("Back to questions", handler.getHGrid(0) + 1.5*x, y);
		
	}
	
	private void betChoice() {
		
//		Calculates max width depending on number of teams
		double width = handler.getGridWidth();
		if (teams.length > 6) {
			width = width * 6/teams.length;
		}
		
		g.setFont(new Font("impact", handler.getGridWidth()/5));
		for (int x = 0; x < teams.length; x++) {
//			Background
			g.setStroke(Color.WHITE);
			g.strokeRect(handler.getWidth()/2 - 0.5*width*(teams.length - 1) + x*width - width/2, handler.getVGrid(1) - g.getFont().getSize()/2, width, g.getFont().getSize());
			
//			Team Names
			g.fillText(teams[x].getName(), handler.getWidth()/2 - 0.5*width*(teams.length - 1) + x*width, handler.getVGrid(1), width);
 		}
		
	}

}
