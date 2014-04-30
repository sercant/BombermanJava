package game.controllers;

import game.constants.Constants;
import game.controllers.interfaces.GeneralController;
import game.gui.main.Game;
import game.gui.states.Play;
import game.models.PowerUpElement;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;


public class PowerUpController implements GeneralController{
	private StateBasedGame game;
	private LinkedList<PowerUpElement> powerUpElements;
	
	public PowerUpController(StateBasedGame sbg){
		this.game = sbg;
	}
	
	@Override
	public void update(int delta) {
		if(powerUpElements.isEmpty()){
			return;
		}
		Iterator<PowerUpElement> iterator = powerUpElements.listIterator();
		Play play = (Play) game.getState(Game.play);
		while(iterator.hasNext()){
			PowerUpElement pue = (PowerUpElement) iterator.next();
			if(pue != null && pue.isTaken()){
				play.getMapController().getCellAt(pue.getX(), pue.getY()).deleteElement(pue);
				play.getPlayerController().addScore(Constants.SCORE_POWERUP);
				iterator.remove();
			}
		}
	}

	public LinkedList<PowerUpElement> getPowerUpElements() {
		return powerUpElements;
	}

	public void setPowerUpElements(LinkedList<PowerUpElement> powerUpElements) {
		this.powerUpElements = powerUpElements;
	}

}
