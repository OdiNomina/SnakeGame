package snakeGame;

import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class ZZZnake {
//	Auf dem Bildschirm gibt es den Spieler, eine Schlange, Gold und eine Tür.
//	Die Tür und das Gold sind fest, der Spieler kann bewegt werden und die Schlange bewegt sich selbstständig auf den Spieler zu.
//	Der Spieler muss zum Gold und dann zur Tür. Wenn die Schlange den Spieler vorher erwischt, ist das Spiel verloren.
	
	private static final int XMAX = 30, YMAX = 20, SNAKELENGTH = 8;
	
	
	public static void main(String[] args) {
		final Player player = new Player();
		final Gold gold1 = new Gold();
		final Gold gold2 = new Gold();
		final Door door = new Door();
		final Point[] points = new Point[] {player, gold1, gold2, door};
		final Snake[] snake1Positions = new Snake[SNAKELENGTH];
		final Snake[] snake2Positions = new Snake[SNAKELENGTH];
		
		for(int i = 0; i < SNAKELENGTH; i++) {
			snake1Positions[i] = new Snake();
			snake2Positions[i] = new Snake();
		}
		int snakeHead = 0;
		
		initializeXY(snake1Positions[snakeHead]);
		initializeXY(snake2Positions[snakeHead]);
		for(Point p : points)
			initializeXY(p);
		
		Point[] allPoints = concat(points, snake1Positions, snake2Positions);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			printScreen(allPoints);
			
			if(gold1.isCollected && gold2.isCollected && player.equals(door)) {
				System.out.println( "\nGewonnen!" );
				sc.close();
				return;
			}
			if(Arrays.asList(snake1Positions).contains(player) || Arrays.asList(snake2Positions).contains(player)) {
				System.out.println( "\nZZZZZZZ - Die Schlange hat dich!" );
				sc.close();
				return;
			}
			if(player.equals(gold1)) {
				gold1.setLocation(-1, -1);
				gold1.isCollected = true;
			}
			if(player.equals(gold2)) {
				gold2.setLocation(-1, -1);
				gold2.isCollected = true;
			}
			
			player.movePlayerOverBorders(sc, XMAX, YMAX);
			if(!player.validInput) {
				sc.close();
				return;
			}
			player.moves++;
			
			if(player.moves > 5) { // head start for player
				int oldHead = snakeHead;
						
				snakeHead = ++snakeHead % SNAKELENGTH;
								
				snake1Positions[snakeHead].x = snake1Positions[oldHead].x;
				snake1Positions[snakeHead].y = snake1Positions[oldHead].y;;
				
				snake2Positions[snakeHead].x = snake2Positions[oldHead].x;
				snake2Positions[snakeHead].y = snake2Positions[oldHead].y;
				
				snake1Positions[snakeHead].moveSnake(player);
				snake2Positions[snakeHead].moveSnake(player);
			}
		}
	}
	
	
	private static void initializeXY(final Point p) {
		if(p == null)
			throw new IllegalArgumentException("Point is null");
		
		int randomX = (int)(Math.random() * XMAX);
		int randomY = (int)(Math.random() * YMAX);
		p.setLocation(randomX, randomY);
	}
	
	
	public static Point[] concat( Point[] first, Point[] second, Point[] third ) {
		Point[] result = Arrays.copyOf( first, first.length + second.length + third.length);
		
		System.arraycopy(second, 0, result, first.length, second.length );
		System.arraycopy(third, 0, result, first.length + second.length, third.length );
		
		return result;
	}
	
	
	private static void printScreen(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		for(int y = 0; y < YMAX; y++) {
			for(int x = 0; x < XMAX; x++) {
				currentPosition.setLocation(x, y);
				if(Arrays.asList(points).contains(currentPosition)) {
					for(Point p : points) {
						if(currentPosition.equals(p)) {
							if(p instanceof Snake) {
								System.out.print( " S " ); 
								break;
							}
							if(p instanceof Player) {
								System.out.print( "°O°" );
								break;
							}
							if(p instanceof Gold) {
								System.out.print( "|$|" );
								break;
							}
							if(p instanceof Door) {
								System.out.print( "###" );
								break;
							}
						}
					}
				}
				else
					System.out.print( "..." );
			}
			System.out.println();
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void printScreen(final Point player, final Point snake1, final Point snake2, final Point gold1, final Point gold2, final Point door){
		final Point currentPosition = new Point(0, 0);
		
		for(int y = 0; y < YMAX; y++) {
			for(int x = 0; x < XMAX; x++) {
				currentPosition.setLocation(x, y);
				
				if(currentPosition.equals(player))
					System.out.print( "P" );
				else if(currentPosition.equals(snake1) || currentPosition.equals(snake2))
					System.out.print( "^" );
					else if(currentPosition.equals(gold1) || currentPosition.equals(gold2))
						System.out.print( "$" );
						else if(currentPosition.equals(door))
							System.out.print( "#" );
							else
								System.out.print( "." );
			}
			System.out.println();
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void printScreenFlag(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		boolean isPrinted;
		for(int y = 0; y < YMAX; y++) {
			for(int x = 0; x < XMAX; x++) {
				currentPosition.setLocation(x, y);
				isPrinted = false;
				for(Point p : points) {
					if(currentPosition.equals(p)) {
						if(p instanceof Player) {
							System.out.print( "P" );
							isPrinted = true;
							break;
						}
						if(p instanceof Snake) {
							System.out.print( "^" );
							isPrinted = true;
							break;
						}
						if(p instanceof Gold) {
							System.out.print( "$" );
							isPrinted = true;
							break;
						}
						if(p instanceof Door) {
							System.out.print( "#" );
							isPrinted = true;
							break;
						}
					}
				}
				if(!isPrinted)
					System.out.print( "." );
			}
			System.out.println();
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void printScreenLabel(final Point... points){
		if(points == null || points.length == 0)
			throw new IllegalArgumentException("Array is null or empty");
		
		final Point currentPosition = new Point(0, 0);
		for(int y = 0; y < YMAX; y++) {
			for(int x = 0; x < XMAX; x++) {
				currentPosition.setLocation(x, y);
				nextPosition:{
					for(Point p : points) {
						if(currentPosition.equals(p)) {
							if(p instanceof Player) {
								System.out.print( "P" );
								break nextPosition;
							}
							if(p instanceof Snake) {
								System.out.print( "^" ); 
								break nextPosition;
							}
							if(p instanceof Gold) {
								System.out.print( "$" );
								break nextPosition;
							}
							if(p instanceof Door) {
								System.out.print( "#" );
								break nextPosition;
							}
						}
					}
					System.out.print( "." );
				}
			}
			System.out.println();
		}
	}
	
	
}
