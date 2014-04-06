package game.controllers;

import game.controllers.interfaces.GeneralController;
import game.models.Map;
import game.models.MapElement;
import game.models.SolidWall;

import org.newdawn.slick.state.StateBasedGame;

public class MapController implements GeneralController{
	private Map map;
	@SuppressWarnings("unused")
	private StateBasedGame game;
	
	public MapController(Map map, StateBasedGame sbg){
		this.map = map;
		this.game = sbg;
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void addMapElement(MapElement e) {
		map.addMapElement(e);
	}

	public void init() {
		for (int i = 0; i < map.getTileCountY(); i++)
		{
			for (int j = 0; j < map.getTileCountX(); j++)
			{
				if(		i == 0 || i == map.getTileCountY()-1 
					||	j == 0 || j == map.getTileCountX() - 1 
					||	(i % 2 == 0 && j % 2 == 0))
					map.addMapElement(new SolidWall(j, i));
			}
		}
	}
}
