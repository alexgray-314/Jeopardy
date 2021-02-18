package com.jaguarplugins.jeopardy;

import com.jaguarplugins.jeopardy.input.JFile;
import com.jaguarplugins.jeopardy.input.KeyHandler;
import com.jaguarplugins.jeopardy.input.MouseHandler;
import com.jaguarplugins.jeopardy.states.BoardState;
import com.jaguarplugins.jeopardy.states.QuestionState;
import com.jaguarplugins.jeopardy.states.State;
import com.jaguarplugins.jeopardy.util.Handler;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image image = new Image("com/jaguarplugins/jeopardy/gfx/logo.png");
		
		Canvas canvas = new Canvas(5000,5000);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		Group root = new Group(canvas);
		Scene scene = new Scene(root);
		
		Handler handler = new Handler(primaryStage, JFile.load(), JFile.loadTeams());
		
		State board = new BoardState(g, handler);
		handler.setupStates(board, board);
		MouseHandler mouse = new MouseHandler(handler);
		KeyHandler keys = new KeyHandler(handler);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(1500);
		primaryStage.setHeight(800);
		primaryStage.setTitle("Jeopardy Quiz");
		primaryStage.getIcons().add(image);
		primaryStage.setOnCloseRequest(e -> {
			try {
				QuestionState.getJTimer().stopThread();
			} catch(NullPointerException ex) {}
			
		});
		
		ChangeListener<Number> resize = new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        
//		    	RESET BOARD ON RESIZE
		        handler.getCurrentState().render();
		    
		    }
		};
		
		scene.heightProperty().addListener(resize);
		scene.widthProperty().addListener(resize);
		scene.setOnMouseMoved(mouse);
		scene.setOnMouseClicked(mouse);
		scene.setOnMousePressed(mouse);
		scene.setOnKeyReleased(keys);
		
//		SETUPS
		handler.getCurrentState().render();
		
		primaryStage.show();
		
	}

}
