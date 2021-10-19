package aplication;


import java.util.Scanner;
import entities.Card;
import entities.Deck;
import entities.Hand;
import entities.Player;
import entities.Table;

public class Program {
    
    public static void main(String[] args) {   	
      
      inicialMenu();
    
    }
    
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
				  round();	
			  }
			  else {
				  System.out.printf("\nPlease, digit a valid Letter (In Capslock)");
				
			  }
		  }
	}


/*
    public static void round() {

        int playerNumber = 3;
        Player[] players = new Player[playerNumber];

        for (int i = 0; i < playerNumber; i++) {
            String[] cardsOnHand = {"9s", "Ac"};
            players[i] = new Player(i, new Hand(cardsOnHand));
        }

    }
*/
}
