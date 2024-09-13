package snakeGame;

import java.awt.Point;

public class SnakeSection  extends GamePoint {

	private static final long serialVersionUID = 1L;
	
	public SnakeSection() {
		this.x = 0;
		this.y = 0;
	}
	
	public SnakeSection(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
}
