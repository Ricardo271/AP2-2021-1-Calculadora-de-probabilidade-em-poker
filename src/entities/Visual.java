package entities;

import java.util.Scanner;

public class Visual {

	public static void inicialMenu() {
		
    	System.out.printf("------- Poker Scores ------- \n\n");
    	System.out.println("Select a function:");
    	System.out.printf("N - New Game \nL - Leave Program\n");
    	
    	try (Scanner entry = new Scanner(System.in)) {
			String resposta = entry.next();
			
			if("L".equals(resposta)) {

				System.out.println("Program ended");
				System.exit(0);
			}
			if("N".equals(resposta)) {
				rounds();	
			}
			else {
				System.out.printf("\nPlease, digit a valid Letter (In Capslock)");
				
			}
		}
	}
	
	public static void rounds() {
		
		System.out.printf("------- Poker Scores ------- \n\n");
		System.out.printf("Numbers of players: ");
		try (Scanner nPlayers = new Scanner(System.in)){
		
			
		}
	}	
}