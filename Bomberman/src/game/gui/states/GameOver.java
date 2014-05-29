package game.gui.states;

import game.constants.Constants;
import game.factories.HighScoreHandler;
import game.gui.main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
	private int ID;
	private int score;
	private int levelCode;
	private boolean scoreSent;
	private String scoreMessage;

	public GameOver(int state) {
		this.ID = state;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		scoreSent = false;
		scoreMessage = "Press ENTER to sent highscore.";
	}

	/**
	 * Render part of the state. This is where the graphics printed on the
	 * screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(Color.black);
		g.drawString("Score: " + score + "!", gc.getWidth() / 2 - 100,
				gc.getHeight() / 2);
		g.drawString("Level Code: " + levelCode + "", gc.getWidth() / 2 - 100,
				gc.getHeight() / 2 + 50);
		g.drawString("Press ESC to return Main Menu.", gc.getWidth() / 2 - 100,
				gc.getHeight() / 2 + 100);
		g.drawString(scoreMessage, gc.getWidth() / 2 - 100,
				gc.getHeight() / 2 + 150);
	}

	/**
	 * Update part of the state. This is where all the changes made.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		input.disableKeyRepeat();
		if (scoreSent) {
			scoreMessage = "Score sent!";
		} else {
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				scoreSent = HighScoreHandler.insertScore(Constants.PLAYER_NAME,
						score);
				if(!scoreSent)
					scoreMessage = "There was an Error :( Press ENTER to retry.";
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.initStatesList(gc);
			sbg.enterState(Game.menu);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(int levelCode) {
		this.levelCode = levelCode;
	}
}
