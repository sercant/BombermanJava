package game.controllers;

import game.constants.Constants;
import game.controllers.interfaces.GeneralController;
import game.gui.main.Game;
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
		Play play = ((Play)game.getState(Game.play));
		while(iterator.hasNext()){
			BrickWall bw = (BrickWall) iterator.next();
			if(bw != null && bw.isDestroyed()){
				play.getMapController().getCellAt(bw.getX(), bw.getY()).deleteElement(bw);
				play.getPlayerController().addScore(Constants.SCORE_BRICKWALL);
				iterator.remove();
			}
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
