package game.controllers;

import game.controllers.interfaces.GeneralController;
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
		Input input = game.getContainer().getInput();
		
		if(input.isKeyPressed(Input.KEY_SPACE)){//TEMPORARY
			door.open();
		}
	}
}
