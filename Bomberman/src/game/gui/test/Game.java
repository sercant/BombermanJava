package game.gui.test;

import game.gui.states.EnterCode;
import game.gui.states.GameOver;
import game.gui.states.Highscores;
import game.gui.states.Menu;
import game.gui.states.Play;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	public static final int SCALE = 5;
	public static final int TILESIZE = 16 * SCALE;
	public static final String gameName = "Bomberman";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int enterCode = 2;
	public static final int highScores = 3;
	public static final int gameOver = 4;
	
	public Game(String name) {
		super(name);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new EnterCode(enterCode));
		this.addState(new Highscores(highScores));
		this.addState(new GameOver(gameOver));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(enterCode).init(gc, this);
		this.getState(highScores).init(gc, this);
		this.getState(gameOver).init(gc, this);
		
		this.enterState(menu);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gameName));
			//appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);
			appgc.setDisplayMode(1024, 768, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}