package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.gui.main.Game;
import game.gui.states.Play;
import game.models.Enemy;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;

public class EnemyController implements GeneralController{
	
	private StateBasedGame game;
	private LinkedList<Enemy> enemies;
	private LinkedList<Float> moveTimers;
	
	public EnemyController(StateBasedGame game){
		this.game = game;
		this.enemies = new LinkedList<Enemy>();
		this.moveTimers = new LinkedList<Float>();
	}
	
	@Override
	public void update(int delta) {
		if(enemies.isEmpty()){
			return;
		}
		
		Iterator<Enemy> iterator = enemies.listIterator();
		Iterator<Float> moveTimerIterator = moveTimers.listIterator();
		Play play = ((Play)game.getState(Game.play));
		while(iterator.hasNext()){
			Enemy e = (Enemy) iterator.next();
			Float mt = moveTimerIterator.next();
			if(e == null)
				continue;
			if(!e.isAlive()){
				play.getMapController().getCellAt(e.getX(), e.getY()).deleteElement(e);
				play.getPlayerController().addScore(e.getReward());
				iterator.remove();
				moveTimerIterator.remove();
			}else{
				updateEnemy(delta, e, mt);
			}
		}
		
	}
	
	

	private void updateEnemy(int delta, Enemy e, Float mt) {
		//TODO enemy AI and move algorithm goes here.
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(LinkedList<Enemy> enemies) {
		this.enemies = enemies;
		
		//for()
	}
}
