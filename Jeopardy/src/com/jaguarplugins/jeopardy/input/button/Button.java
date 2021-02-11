package com.jaguarplugins.jeopardy.input.button;

import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Button {

	private final int SMOOTH = 80;
	
	private Rectangle rectangle;
	private Handler handler;
	private int winnings;
	private Team team;
	private boolean skip;
	private boolean clicked = false;
	
	public Button(Handler handler, int winnings, Team team, Rectangle rectangle, boolean skip) {
		this.rectangle = rectangle;
		this.handler = handler;
		this.winnings = winnings;
		this.team = team;
		this.skip = skip;
	}

	public void render(GraphicsContext g) {
		
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridWidth()/8));
		
		g.setFill(getColor());
		g.fillRoundRect(rectangle.getX() + 1, rectangle.getY() + 1, rectangle.getWidth() - 2, rectangle.getHeight() - 1, handler.getHeight()/SMOOTH, handler.getHeight()/SMOOTH);
		g.setFill(Color.WHITESMOKE);
		g.fillText(team.getName(), rectangle.getX() + rectangle.getWidth()/2, rectangle.getY() + rectangle.getHeight()/2, rectangle.getWidth()*0.9);
		
	}
	
	public void click(double x, double y) {
		
		if (rectangle.intersects(x, y, 1, 1)) {
			
//			TODO don't lose score when active teams gets q wrong
			
			if (!clicked) {
				team.changeScore(winnings);
			} else {
				team.changeScore(-winnings);
			}
			clicked = !clicked;
			
			handler.getCurrentState().render();
			
			for (Team t : handler.getTeams()) {
				System.out.println(t.getName() + ": " + t.getScore());
			}
			System.out.println();
			
		}
		
	}
	
	private Color getColor() {
		
		if (!clicked) {
			if (winnings > 0) {
				if (skip) {
					return Color.MEDIUMSEAGREEN;
				} else {
					return Color.DARKSEAGREEN;
				}
			} else {
				if (skip) {
					return Color.FIREBRICK;
				} else {
					return Color.INDIANRED;
				}
			}
		} else {
			if (winnings > 0) {
				if (skip) {
					return Color.MEDIUMSEAGREEN.darker();
				} else {
					return Color.DARKSEAGREEN.darker();
				}
			} else {
				if (skip) {
					return Color.FIREBRICK.darker();
				} else {
					return Color.INDIANRED.darker();
				}
			}
		}
		
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
}
