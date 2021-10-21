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
    	System.out.println("Selecione uma Função");
    	System.out.printf("N - Novo Jogo \nS - Sair do Programa\n");
    	
    	try (Scanner entry = new Scanner(System.in)) {
			  String resposta = entry.next();
			
			  if("S".equals(resposta)) {

				  System.out.println("Programa Encerrado");
				  System.exit(0);
			  }
			  if("N".equals(resposta)) {
				  round();	
			  }
			  else {
				  System.out.printf("\nDigite uma letra válida (em maiúsculo)");
				
			  }
		}
	}



    public static void round() {
    	
    	System.out.printf("------- Poker Scores ------- \n\n");
    	System.out.printf("Digite o número de jogadores: ");
    	Scanner sc = new Scanner(System.in);
    	
        int playerNumber = sc.nextInt();
        Player[] players = new Player[playerNumber];

        for (int i = 0; i < playerNumber; i++) {
            String[] cardsOnHand = {};
            players[i] = new Player(i, new Hand(cardsOnHand));
        }

    }

}
