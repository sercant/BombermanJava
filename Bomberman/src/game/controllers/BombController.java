package game.controllers;

import game.constants.Constants;
import game.controllers.interfaces.GeneralController;
import game.gui.states.Play;
import game.models.Bomb;
import game.models.Cell;
import game.models.Direction;
import game.models.ElementType;
import game.models.Player;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.state.StateBasedGame;

public class BombController implements GeneralController {

	private LinkedList<Bomb> bombs;
	private StateBasedGame game;

	public BombController(StateBasedGame sbg) {
		this.game = sbg;
		bombs = new LinkedList<Bomb>();
	}

	@Override
	public void update(int delta) {
		if (bombs.isEmpty()) {
			return;
		}
		Iterator<Bomb> iterator = bombs.listIterator();
		while (iterator.hasNext()) {
			Bomb b = (Bomb) iterator.next();
			if (b != null && b.isExploded(delta)) {
				((Play) game.getCurrentState()).getMapController()
						.getCellAt(b.getX(), b.getY()).deleteElement(b);
				PlayerController pc = ((Play) game.getCurrentState())
						.getPlayerController();
				explode(b);
				pc.bombExploded();
				iterator.remove();
			}
		}
	}

	private void explode(Bomb b) {
		int explosionRange = b.getExplosionRange();
		int bombX = b.getX();
		int bombY = b.getY();

		ExplosionController ec = ((Play) game.getCurrentState())
				.getExplosionController();

		ec.spawnExplosion(bombX, bombY);
		handleExplosion(bombX, bombY, Direction.Up, explosionRange);
		handleExplosion(bombX, bombY, Direction.Down, explosionRange);
		handleExplosion(bombX, bombY, Direction.Left, explosionRange);
		handleExplosion(bombX, bombY, Direction.Right, explosionRange);
	}

	private void handleExplosion(int bombX, int bombY, Direction dir,
			int explosionRange) {
		int x = 0, y = 0;
		ExplosionController ec = ((Play) game.getCurrentState())
				.getExplosionController();
		MapController mc = ((Play) game.getCurrentState()).getMapController();

		explosionRange--; // don't touch this

		while (true) {
			if (dir == Direction.Left || dir == Direction.Down) {
				if (dir == Direction.Left) {
					x--;
				} else {
					y--;
				}
				if (x < -explosionRange || y < -explosionRange)
					break;
			} else {

				if (dir == Direction.Up) {
					x++;
				} else {
					y++;
				}
				if (x > explosionRange || y > explosionRange)
					break;
			}

			Cell cell = mc.getCellAt(bombX + x, bombY + y);

			if (cell != null) {
				if (cell.isContains(ElementType.SolidWall)) {
					break;
				}

				ec.spawnExplosion(bombX + x, bombY + y);

				if (cell.isContains(ElementType.BrickWall)) {
					break;
				}
			} else {
				break;
			}
		}
	}

	public void spawnBomb(int x, int y, Player player) {
		Bomb b = new Bomb(x, y, player.getExplosionRange(),
				Constants.DURATION_TIME_BOMB);
		bombs.add(b);
		((Play) game.getCurrentState()).getMapController().addMapElement(b);
	}
}
