package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.gui.states.Play;
import game.models.BrickWall;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;

public class BrickWallController implements GeneralController{
	private StateBasedGame game;
	private LinkedList<BrickWall> brickWalls;
	
	public BrickWallController(StateBasedGame sbg){
		this.game = sbg;
		brickWalls = new LinkedList<BrickWall>();
		
	}

	@Override
	public void update(int delta) {
		if(brickWalls.isEmpty()){
			return;
		}
		Iterator<BrickWall> iterator = brickWalls.listIterator();
		LinkedList<BrickWall> brickWallsToRemove = new LinkedList<BrickWall>();
		while(iterator.hasNext()){
			BrickWall bw = (BrickWall) iterator.next();
			if(bw != null && bw.isDestroyed()){
				brickWallsToRemove.add(bw);
				((Play)game.getCurrentState()).getMapController().getCellAt(bw.getX(), bw.getY()).deleteElement(bw);
			}
		}
		iterator = brickWallsToRemove.listIterator();
		while(iterator.hasNext()){
			BrickWall bw = (BrickWall) iterator.next();
			brickWalls.remove(bw);
		}
	}
	
	public void addBrickWall(int x, int y){
		BrickWall bw = new BrickWall(x, y);
		brickWalls.add(bw);
		((Play)game.getCurrentState()).getMapController().getCellAt(x, y).addElement(bw);
	}

	public void setBrickWalls(LinkedList<BrickWall> brickWalls) {
		this.brickWalls = brickWalls;
	}
}
