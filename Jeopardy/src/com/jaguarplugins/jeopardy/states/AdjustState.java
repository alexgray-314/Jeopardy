package com.jaguarplugins.jeopardy.states;

import java.util.ArrayList;

import com.jaguarplugins.jeopardy.input.button.ScoreButton;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class AdjustState extends State {

	private ArrayList<ScoreButton> buttons = new ArrayList<ScoreButton>();
	
	public AdjustState(GraphicsContext g, Handler handler) {
		super(g, handler);

		for (int i = 0; i <= handler.teamsLength(); i++) {
			for (int x = 0; x <= 1; x++) {
				buttons.add(new ScoreButton(handler, handler.getTeams()[i], x, i, -10 + 20*x));
			}
		}
		
		handler.setScoreButtons(buttons);
		
	}

	@Override
	public void render() {
		
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setTextAlign(TextAlignment.RIGHT);
		g.setTextBaseline(VPos.CENTER);
		g.setFont(new Font("calibri", handler.getGridHeight()/3));
		g.setFill(Color.BLACK);
		
		for (int i = 0; i <= handler.teamsLength(); i++) {
			g.fillText(handler.getTeams()[i].getName() + ":  ", handler.getWidth()/2, ScoreButton.getYPos(handler, i), handler.getWidth()/2);
		}
		
		for (ScoreButton b : buttons) {
			b.render(g);
		}
		
//		Controls help
		double bWidth = handler.getGridWidth()/8;
		double y = handler.getVGrid(6) + (handler.getHeight() - handler.getVGrid(6))/2;
		double x = handler.getGridWidth()/6;
		
		g.setFill(Color.DIMGRAY);
		g.setStroke(Color.DIMGRAY);
		g.setFont(new Font("calibri", handler.getGridWidth()/12));
		g.strokeRoundRect(handler.getHGrid(0) + x - bWidth/2, y-bWidth/2, bWidth, bWidth, handler.getGridHeight()/10, handler.getGridHeight()/10);
		g.fillText("Esc", handler.getHGrid(0) + x, y);
		
		g.setFont(new Font("calibri", handler.getGridWidth()/16));
		g.setTextAlign(TextAlignment.LEFT);
		g.fillText("Back to questions", handler.getHGrid(0) + 1.5*x, y);
		
	}

}
