package com.jaguarplugins.jeopardy.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.jaguarplugins.jeopardy.questions.Question;
import com.jaguarplugins.jeopardy.questions.Team;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JFile {

	public static Question[][] load() {

		File file = new File("questions.txt");
		
//		Presence check
		if(!file.exists()) {
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			Alert a = new Alert(AlertType.INFORMATION, "Questions file created");
			a.showAndWait();
			System.exit(0);
			
		}
		
		Question[][] contents = new Question[5][5];
		int currentLine = 1;
		
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			
//			READING FILE
			String line = reader.readLine();
			String cat = line.split("=")[1];
			line = reader.readLine(); currentLine++;
			int category = 0, money = 0;
			while (line != null) {
				if (money >= 5 || line.contains("-=")) {
					cat = line.split("=")[1];
					category++;
					money = 0;
					line = reader.readLine(); currentLine++;
				}
				String[] data = line.split("~");
				contents[category][money] = new Question(cat, data[0], data[1], data[2], category, money + 1);
				money++;
				line = reader.readLine(); currentLine++;
			}

			reader.close();
			
			for (Question[] cats : contents) {
				for (Question q : cats) {
					if (q == null) {
						Alert a = new Alert(AlertType.ERROR, "Invalid formatting of questions.txt. Please visit "
								+ "https://github.com/JaguarPlugins/Jeopardy/wiki/Question-setup for instructions on how to set out questions properly.");
						a.showAndWait();
						System.exit(0);
					}
				}
			}
			
			return contents;
			
		} catch (FileNotFoundException e) {
			Alert a = new Alert(AlertType.ERROR, "File could not be found");
			a.showAndWait();
			return null;
		} catch (UnsupportedEncodingException e) {
			Alert a = new Alert(AlertType.ERROR, "Please change file encoding type to UTF-8 and try again");
			a.showAndWait();
			return null;
		} catch (IOException e) {
			Alert a = new Alert(AlertType.ERROR, "I/O exception");
			a.showAndWait();
			return null;
		} catch (Exception e ) {
			Alert a = new Alert(AlertType.ERROR, "Error on line " + currentLine + " of questions.txt. Please visit "
					+ "https://github.com/JaguarPlugins/Jeopardy/wiki/Question-setup for instructions on how to set out questions properly.");
			a.showAndWait();
			System.exit(0);
			return null;
		}
		
	}
	
	public static Team[] loadTeams() { 
		
		File file = new File("teams.txt");
		
//		Presence check
		if(!file.exists()) {
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			Alert a = new Alert(AlertType.INFORMATION, "Teams file created");
			a.showAndWait();
			System.exit(0);
			
		}
		
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			ArrayList<Team> contents = new ArrayList<Team>();
			
//			READING FILE
			String line = reader.readLine();
			while (line != null) {
				contents.add(new Team(line));
				line = reader.readLine();
			}

			reader.close();

			if (contents.size() <= 0) {
				Alert a  = new Alert(AlertType.ERROR, "teams.txt is empty. Please write your team names on each line of the file, leaving no blank lines, and try again.");
				a.showAndWait();
				System.exit(0);
			}
			
			return contents.toArray(new Team[contents.size()]);
			
		} catch (FileNotFoundException e) {
			Alert a = new Alert(AlertType.ERROR, "File could not be found");
			a.showAndWait();
			return null;
		} catch (UnsupportedEncodingException e) {
			Alert a = new Alert(AlertType.ERROR, "Please change file encoding type to UTF-8 and try again");
			a.showAndWait();
			return null;
		} catch (IOException e) {
			Alert a = new Alert(AlertType.ERROR, "I/O exception");
			a.showAndWait();
			return null;
		}
		
	}
	
}
