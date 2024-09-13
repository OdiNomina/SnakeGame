package snakeGame;

import java.awt.Point;

class GamePoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	protected static final int XMAX = 30, YMAX = 20;
	
	
	protected void initializeRandomXY() {
		setLocation((int)(Math.random() * XMAX), (int)(Math.random() * YMAX));
	}
}
