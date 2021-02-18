package com.jaguarplugins.jeopardy.input.button;

import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreButton {

	private static final int SMOOTH = 40, HDIV = 3;
	
	private Rectangle r;
	private Handler handler;
	private Team team;
	private int x, y;
	private int scoreChange;
	private boolean hover = false;
	
	public ScoreButton(Handler handler, Team team, int x, int y, int scoreChange) {
		
		this.handler = handler;
		this.team = team;
		this.x = x; // X should be 0 or 1 depending on if you want button on left or right
		this.y = y;
		this.scoreChange = scoreChange;
		
	}
	
	public void render(GraphicsContext g) {


		double height = (handler.getHeight() - handler.getHeight()/HDIV)/4;
		if (handler.teamsLength() > 4) {
			height = (handler.getHeight() - handler.getHeight()/HDIV)/handler.teamsLength();
		}
		double rawY = handler.getHeight()/(2*HDIV) + y*height;
		
		r = new Rectangle(handler.getWidth()/2 + x*height/2 + 1, rawY - height/4 + 1, height/2 - 2, height/2 - 2);
		g.setFill(getColor());
		g.fillRoundRect(r.getX(), r.getY(), r.getWidth(), r.getHeight(), handler.getHeight()/SMOOTH, handler.getHeight()/SMOOTH);
		
		String displayScore = "+";
		if (scoreChange > 0) {
			displayScore += scoreChange;
		} else {
			displayScore = "" + scoreChange;
		}
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridHeight()/5));
		g.setFill(Color.BLACK);
		g.fillText(displayScore, handler.getWidth()/2 + height/4 + x*height/2, rawY);
		
	}

	public void click(double xPos, double yPos) {
		if (r.intersects(xPos, yPos, 1, 1)) {

			team.changeScore(scoreChange);
			for (Team t : handler.getTeams()) {
				System.out.println(t.getName() + ": " + t.getScore());
			}
			System.out.println();
			hover = false;

		}
	}
	
	public void startHover(double xPos, double yPos) {
		if (r.intersects(xPos, yPos, 1, 1)) {
			hover = true;
		}
	}
	
	private Color getColor() {
		
		Color color;
		if (scoreChange > 0) {
			color = Color.GREEN;
		} else {
			color = Color.RED;
		}
		
		if (hover) {
			color = color.darker();
		}
		return color;
		
	}
	
	public static double getYPos(Handler handler, double yGrid) {
		double height = (handler.getHeight() - handler.getHeight()/HDIV)/4;
		if (handler.teamsLength() > 4) {
			height = (handler.getHeight() - handler.getHeight()/HDIV)/handler.teamsLength();
		}
		return handler.getHeight()/(2*HDIV) + yGrid*height;
	}
	
}
