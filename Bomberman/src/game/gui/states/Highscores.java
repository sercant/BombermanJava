package game.gui.states;

import game.database.DatabaseLayer;
import game.factories.HighScoreHandler;
import game.gui.main.Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Highscores extends BasicGameState {
	private int ID;
	private ArrayList<String> highscores;

	public Highscores(int state) {
		ID = state;
		HighScoreHandler.setHighscoreGetter(new DatabaseLayer());
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		highscores = (ArrayList<String>) HighScoreHandler.getAllTimeScores();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);

		if (highscores == null) {
			g.drawString("Can't receive highscores!!", gc.getWidth() / 2 - 100, 100);
		} else {
			g.drawString("Name - Score - Date", gc.getWidth() / 2 - 100 , 50);
			for (int i = 0, j = 100; i < 10; i++, j += 50) {
				if (i == highscores.size()) {
					break;
				}
				g.drawString(highscores.get(i), gc.getWidth() / 2 - 100, j);
			}
		}
		
		g.drawString("Press ESC to go back.", gc.getWidth() / 2 - 100 , gc.getHeight() - 100);
		g.drawString("Press F5 to refresh.", gc.getWidth() / 2 - 100, gc.getHeight() - 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		input.disableKeyRepeat();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Game.menu);
		}
		if (input.isKeyPressed(Input.KEY_F5)) {
			highscores = (ArrayList<String>) HighScoreHandler
					.getAllTimeScores();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
