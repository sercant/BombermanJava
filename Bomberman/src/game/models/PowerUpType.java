package game.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PowerUpType {
	Speed, 
	BombCount,
	ExplosionRange;

	private static final List<PowerUpType> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
		  
	public static PowerUpType getRandomType() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
