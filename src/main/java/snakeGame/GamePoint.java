package snakeGame;

import java.awt.Point;
import java.util.ArrayList;

class GamePoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	protected static final int XMAX = 30, YMAX = 20;
	
	
	protected void initializeRandomXY() {
		setLocation((int)(Math.random() * XMAX), (int)(Math.random() * YMAX));
	}
	
	
	protected static ArrayList<GamePoint> collectGamePoints(ArrayList<SnakeSection> list1, ArrayList<SnakeSection> list2) {
		ArrayList<GamePoint> snakePoints = new ArrayList<GamePoint>();
		
		snakePoints.addAll(list1);
		snakePoints.addAll(list2);
		
		return snakePoints;
	}

/*
 * Type safety: Potential heap pollution via varargs parameter ???
 * 
	protected static ArrayList<GamePoint> collectGamePoints(ArrayList<SnakeSection>... snakeSectionLists) {
		ArrayList<GamePoint> snakePoints = new ArrayList<GamePoint>();
		
		for(ArrayList<SnakeSection> points : snakeSectionLists)
			snakePoints.addAll(points);
		
		return snakePoints;
	}
*/
}
