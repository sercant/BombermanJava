package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.gui.states.Play;
import game.models.Cell;
import game.models.ElementType;
import game.models.Explosion;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;

public class ExplosionController implements GeneralController{
	
	private LinkedList<Explosion> explosions;
	private StateBasedGame game;
	
	public ExplosionController(StateBasedGame sbg){
		explosions = new LinkedList<Explosion>();
		this.game = sbg;
	}
	@Override
	public void update(int delta) {
		if(explosions.isEmpty()){
			return;
		}
		Iterator<Explosion> iterator = explosions.listIterator();
		LinkedList<Explosion> explosionsToRemove = new LinkedList<Explosion>();
		while(iterator.hasNext()){
			Explosion e = (Explosion) iterator.next();
			if(e != null && e.isExpired(delta)){
				Cell cell = ((Play)game.getCurrentState()).getMapController().getCellAt(e.getX(), e.getY());
				explosionsToRemove.add(e);
				if(cell.isContains(ElementType.Enemy)){
					((Play)game.getCurrentState()).getPlayerController().addScore(100);
				}
				cell.explode();
				cell.deleteElement(e);
			}
		}
		iterator = explosionsToRemove.listIterator();
		while(iterator.hasNext()){
			Explosion e = (Explosion) iterator.next();
			explosions.remove(e);
		}
	}
	public void spawnExplosion(int x, int y){
		Explosion e = new Explosion(x, y);
		explosions.add(e);
		((Play)game.getCurrentState()).getMapController().addMapElement(e);
	}
}
