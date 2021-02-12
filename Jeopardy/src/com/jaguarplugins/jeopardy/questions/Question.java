package com.jaguarplugins.jeopardy.questions;

import com.jaguarplugins.jeopardy.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Question {
	
	
	private String question, answer, category;
	private Image image;
	private boolean answered = false;
	
	private int x, y;
	private boolean hover = false;;
	
	public Question(String category, String question, String answer, String url, int x, int y) {

		this.category = category;
		this.question = question;
		this.answer = answer;
		this.x = x;
		this.y = y;
		
		if (url.equalsIgnoreCase("none") || url.equalsIgnoreCase("null")) {
			this.image = null;
		} else {
			try {
				this.image = new Image(url);
			} catch (Exception e) {			
				Alert a = new Alert(AlertType.ERROR, url + " could not be found.");
				a.showAndWait();
			}
		}
		
	}
	
	public void render(Handler handler, GraphicsContext g) {
		
		double y = handler.getHeight()/2;
		if (image != null) {
			y += handler.getHeight()/4;
			double imgWidth = (image.getWidth()/image.getHeight())*(4.5*handler.getGridHeight());
			g.drawImage(image, handler.getWidth()/2 - imgWidth/2, handler.getVGrid(0), imgWidth, 4.5*handler.getGridHeight());
		}
		
		g.fillText(question, handler.getWidth()/2, y, handler.getWidth());
		
	}

//	GETTERS AND SETTERS
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public Image getImage() {
		return image;
	}

	public boolean isAnswered() {
		return answered;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	public String getCategory() {
		return category;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

}