package com.jaguarplugins.jeopardy.questions;

public class Team implements Comparable<Team> {

	private String name;
	private int score = 0;
	private boolean active = false;
	
	public Team(String name) {
		
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void changeScore(int change) {
		score = score + change;
	}

	@Override
	public int compareTo(Team t) {
		if (score < t.getScore()) {
			return -1;
		} else if (score > t.getScore()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
