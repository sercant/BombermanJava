package game.factories;

import java.util.List;


public class HighScoreHandler {
	// Abstraction to online database high score insertion and getting data
	
	private static HighscoreGet highscoreGetter;
	
	public static void setHighscoreGetter(HighscoreGet highscoreGetter){
		HighScoreHandler.highscoreGetter = highscoreGetter;
	}
	
	public static List<String> getAllTimeScores(){
		if(highscoreGetter != null)
			return highscoreGetter.getAllTimeScores();
		else
			return null;
	}
	
	public static boolean insertScore(String name, int score){
		if(highscoreGetter != null)
			return highscoreGetter.insertScore(name, score);
		else
			return false;
	}
}
