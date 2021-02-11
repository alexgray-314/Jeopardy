package com.jaguarplugins.jeopardy.util;

import java.util.ArrayList;

import com.jaguarplugins.jeopardy.input.button.Button;
import com.jaguarplugins.jeopardy.input.button.ScoreButton;
import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.questions.Team;
import com.jaguarplugins.jeopardy.states.State;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Handler {

	private Stage stage;
	private Question[][] questions;
	private State currentState, boardState;
	private Team[] teams;
	private int currentTeam = 0;
	private ArrayList<ScoreButton> scoreButtons;
	
	private Button[] buttons;
	private Rectangle nextButton;
	
	public Handler(Stage stage, Question[][] questions, Team[] teams) {
		
//		NOTE: Whenever class used, setupStates must also be run!
		this.stage = stage;
		this.questions = questions;
		this.teams = teams;
		
	}
	
	public void setupStates(State currentState, State boardState) {
		this.currentState = currentState;
		this.boardState = boardState;
	}
	
//	TEAM
	public Team getCurrentTeam() {
		return teams[currentTeam];
	}
	
	public void nextTeam() {
		
		teams[currentTeam].setActive(false);
		if (currentTeam < teams.length - 1) {
			currentTeam++;
		} else {
			currentTeam = 0;
		}
		teams[currentTeam].setActive(true);
		
	}
	
	public int teamsLength() {
		
//		NOTE THE -1
		return teams.length - 1;
		
	}
	
//	LAYOUT
	
	public double getHGrid(int x) {
		
		return ((x*getWidth())/6 + getWidth()/12);
		
	}
	
	public double getVGrid(int y) {
		
		if (y >= 1) {
			return ((2*getHeight()*y)/15 + (2*getHeight())/15);
		} else {
			return (getHeight()/15) + 1;
		}
		
		
	}
	
	public double getGridWidth() {
		
		return getWidth()/6;
		
	}
	
	public double getGridHeight() {
		
		return (2*getHeight())/15;
		
	}

//	GETTERS AND SETTERS
	
	public double getWidth() {
		return stage.getWidth() - 16;
	}

	public double getHeight() {
		return stage.getHeight() - 39;
	}

	public Stage getStage() {
		return stage;
	}
	
	public Question[][] getQuestions() {
		return questions;
	}

	public State getCurrentState() {
		return currentState;
	}

	public State getBoardState() {
		return boardState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public Team[] getTeams() {
		return teams;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

	public Rectangle getNextButton() {
		return nextButton;
	}

	public void setNextButton(Rectangle nextButton) {
		this.nextButton = nextButton;
	}

	public ArrayList<ScoreButton> getScoreButtons() {
		return scoreButtons;
	}

	public void setScoreButtons(ArrayList<ScoreButton> scoreButtons) {
		this.scoreButtons = scoreButtons;
	}
	
}
