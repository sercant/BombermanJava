package game.factories;

import java.util.List;

public interface HighscoreGet {
	public List<String> getAllTimeScores();
	public boolean insertScore(String name, int score);
//	public List<String> getMontlyScores(); //TODO implement this later
}
