package snakeGame;

import java.awt.Point;
import java.util.Scanner;

public class Player extends Point  {
	
	private static final long serialVersionUID = 1L;
	private static String validKeys = "\nDer Spieler kann mit folgenden Tasten bewegt werden:\n"
			+ "Hoch: w, W, h, H\n"
			+ "Tief: s, S, t, T\n"
			+ "Links: a, A, l, L\n"
			+ "Rechts: d, D, r, R";
	
	protected int moves = 0;
	
	
	protected boolean movePlayer(final Scanner sc, final int maxX, final int maxY) {
		if(sc == null)
			throw new IllegalArgumentException("Scanner is null");
		
		moved:
		{
			for(int i = 0; i < 10; i++) {
				switch(sc.next().charAt(0)) {
					case 'w', 'W', 'h', 'H' -> {
						y = Math.max(0, y - 1);
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = Math.min((maxY - 1), y + 1);
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = Math.max(0, x - 1); 
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = Math.min((maxX - 1), x + 1); 
						break moved;
					}
					default -> {
						System.out.println(validKeys);
					}
				}
			}
			System.out.println("Zuviele ung�ltige Eingaben -> Spiel wird beendet!");
			return false;
		}
		return true;
	}
	
	protected boolean movePlayerOverBorders(final Scanner sc, final int maxX, final int maxY) {
		if(sc == null)
			throw new IllegalArgumentException("Scanner is null");
		
		moved:
		{
			for(int i = 0; i < 10; i++) {
				switch(sc.next().charAt(0)) {
					case 'w', 'W', 'h', 'H' -> {
						y = (y - 1) < 0 ? (maxY - 1) : ( y - 1 );
						break moved;
					}
					case 's', 'S', 't', 'T' -> {
						y = (y + 1) > (maxY - 1) ? 0 : (y + 1);
						break moved;
					}
					case 'a', 'A', 'l', 'L' -> {
						x = (x - 1) < 0 ? (maxX - 1) : (x - 1) ;
						break moved;
					}
					case 'd', 'D', 'r', 'R' -> {
						x = (x + 1) > (maxX - 1) ? 0 : (x + 1);
						break moved;
					}
					default -> {
						System.out.println(validKeys);
					}
				}
			}
			System.out.println("Zuviele ung�ltige Eingaben -> Spiel wird beendet!");
			return false;
		}
		return true;
	}
}
