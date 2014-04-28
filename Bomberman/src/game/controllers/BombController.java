package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.gui.states.Play;
import game.models.Bomb;
import game.models.Cell;
import game.models.ElementType;
import game.models.Player;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;

public class BombController implements GeneralController{
	
	private LinkedList<Bomb> bombs;
	private StateBasedGame game;
	
	public BombController(StateBasedGame sbg){
		this.game = sbg;
		bombs = new LinkedList<Bomb>();
	}
	
	@Override
	public void update(int delta) {
		if(bombs.isEmpty()){
			return;
		}
		Iterator<Bomb> iterator = bombs.listIterator();
		LinkedList<Bomb> bombsToRemove = new LinkedList<Bomb>();
		while(iterator.hasNext()){
			Bomb b = (Bomb) iterator.next();
			if(b != null && b.isExploded(delta)){
				bombsToRemove.add(b);
				((Play)game.getCurrentState()).getMapController().getCellAt(b.getX(), b.getY()).deleteElement(b);
				explode(b);
			}
		}
		if(bombsToRemove.isEmpty()){
			return;
		}
		PlayerController pc = ((Play)game.getCurrentState()).getPlayerController();
		
		iterator = bombsToRemove.listIterator();
		while(iterator.hasNext()){
			Bomb b = (Bomb) iterator.next();
			bombs.remove(b);
			pc.bombExploded();
		}
	}

	private void explode(Bomb b) {
		int explosionRange = b.getExplosionRange();
		int bombX = b.getX();
		int bombY = b.getY();
		
		ExplosionController ec =  ((Play)game.getCurrentState()).getExplosionController();
		MapController mc = ((Play)game.getCurrentState()).getMapController();
		
		ec.spawnExplosion(bombX, bombY);
		
		for(int x = 1; x < explosionRange; x++){
			Cell cell = mc.getCellAt(bombX + x, bombY);
			if(cell != null){
				if(cell.isContains(ElementType.BrickWall)){
					ec.spawnExplosion(bombX + x, bombY);
					break;
				}
				if(cell.isContains(ElementType.SolidWall)){
					break;
				}
				ec.spawnExplosion(bombX + x, bombY);
			}else{
				break;
			}
		}
		for(int x = -1; x > -explosionRange; x--){
			Cell cell = mc.getCellAt(bombX + x, bombY);
			if(cell != null){
				if(cell.isContains(ElementType.BrickWall)){
					ec.spawnExplosion(bombX + x, bombY);
					break;
				}
				if(cell.isContains(ElementType.SolidWall)){
					break;
				}
				ec.spawnExplosion(bombX + x, bombY);
			}else{
				break;
			}
		}
		for(int y = 1; y < explosionRange; y++){
			Cell cell = mc.getCellAt(bombX, bombY + y);
			if(cell != null){
				if(cell.isContains(ElementType.BrickWall)){
					ec.spawnExplosion(bombX, bombY + y);
					break;
				}
				if(cell.isContains(ElementType.SolidWall)){
					break;
				}
				ec.spawnExplosion(bombX, bombY + y);
			}else{
				break;
			}
		}
		for(int y = -1; y > -explosionRange; y--){
			Cell cell = mc.getCellAt(bombX, bombY + y);
			if(cell != null){
				if(cell.isContains(ElementType.BrickWall)){
					ec.spawnExplosion(bombX, bombY + y);
					break;
				}
				if(cell.isContains(ElementType.SolidWall)){
					break;
				}
				ec.spawnExplosion(bombX, bombY + y);
			}else{
				break;
			}
		}
	}

	public void spawnBomb(int x, int y, Player player){
		Bomb b = new Bomb(x, y, player.getExplosionRange(), 3000);
		bombs.add(b);
		((Play)game.getCurrentState()).getMapController().addMapElement(b);
	}
}
