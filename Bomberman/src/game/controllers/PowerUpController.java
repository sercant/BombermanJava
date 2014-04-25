package game.controllers;

import game.controllers.interfaces.GeneralController;
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
		LinkedList<PowerUpElement> powerUpElementsToRemove = new LinkedList<PowerUpElement>();
		while(iterator.hasNext()){
			PowerUpElement pue = (PowerUpElement) iterator.next();
			if(pue != null && pue.isTaken()){
				powerUpElementsToRemove.add(pue);
				((Play)game.getCurrentState()).getMapController().getCellAt(pue.getX(), pue.getY()).deleteElement(pue);
			}
		}
		iterator = powerUpElementsToRemove.listIterator();
		while(iterator.hasNext()){
			PowerUpElement pue = (PowerUpElement) iterator.next();
			powerUpElements.remove(pue);
		}
	}

	public LinkedList<PowerUpElement> getPowerUpElements() {
		return powerUpElements;
	}

	public void setPowerUpElements(LinkedList<PowerUpElement> powerUpElements) {
		this.powerUpElements = powerUpElements;
	}

}
