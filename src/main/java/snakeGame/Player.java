package snakeGame;

import java.util.Scanner;

public class Player extends GamePoint {
	
	private static final long serialVersionUID = 1L;
	
	private static String validKeys = "\nDer Spieler kann mit folgenden Tasten bewegt werden:\n"
			+ "Hoch: w, W, h, H\n"
			+ "Tief: s, S, t, T\n"
			+ "Links: a, A, l, L\n"
			+ "Rechts: d, D, r, R";
	protected int moves = 0;
	protected boolean validInput = true;
	
	
	protected boolean isRich(Gold gold) {
		if(this.equals(gold)) {
			gold.setLocation(-1, -1);
			gold.isCollected = true;
			return true;
		}
		return false;
	}
	
	protected void movePlayer(final Scanner sc) {
		if(sc == null)
			throw new IllegalArgumentException("Scanner is null");
		
		moved:{
			for(int i = 0; i < 10; i++) {
				switch(sc.next().charAt(0)) {
					case 'w', 'W', 'h', 'H' -> {
						y = Math.max(0, y - 1);
						moves++;
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = Math.min((GamePoint.YMAX - 1), y + 1);
						moves++;
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = Math.max(0, x - 1);
						moves++;
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = Math.min((GamePoint.XMAX - 1), x + 1);
						moves++;
						break moved;
					}
					default -> {
						System.out.println(validKeys);
					}
				}
			}
			System.out.println("\nZuviele ungültige Eingaben -> Spiel wird beendet!");
			validInput = false;
		}
	}
	
	
	protected void movePlayerOverBorders(final Scanner sc) {
		if(sc == null)
			throw new IllegalArgumentException("Scanner is null");
		
		moved:{
			for(int i = 0; i < 10; i++) {
				switch(sc.next().charAt(0)) {
					case 'w', 'W', 'h', 'H' -> {
						y = (y - 1) < 0 ? (GamePoint.YMAX - 1) : ( y - 1 );
						moves++;
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = (y + 1) > (GamePoint.YMAX - 1) ? 0 : (y + 1);
						moves++;
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = (x - 1) < 0 ? (GamePoint.XMAX - 1) : (x - 1);
						moves++;
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = (x + 1) > (GamePoint.XMAX - 1) ? 0 : (x + 1);
						moves++;
						break moved;
					}
					default -> {
						System.out.println(validKeys);
					}
				}
			}
			System.out.println("\nZuviele ungültige Eingaben -> Spiel wird beendet!");
			validInput = false;
		}
	}
}
