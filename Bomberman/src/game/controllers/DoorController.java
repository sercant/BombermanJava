package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.gui.states.Play;
import game.models.Door;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class DoorController implements GeneralController{
	private StateBasedGame game;
	private Door door;
	
	public DoorController(Door door, StateBasedGame game){
		this.door = door;
		this.game = game;
	}
	
	public void update(int delta){
		if(door != null && door.isDestroyed()){
			((Play)game.getCurrentState()).getMapController().getCellAt(door.getX(), door.getY()).deleteElement(door);
			door = null;
		}
		if(door == null){
			return;
		}
		Input input = game.getContainer().getInput();
		
		if(input.isKeyPressed(Input.KEY_SPACE)){//TEMPORARY
			door.open();
		}
		
	}
}
