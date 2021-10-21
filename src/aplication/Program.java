package aplication;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Card;
import entities.Deck;
import entities.Hand;
import entities.Player;
import entities.Table;

public class Program {
	
	static Deck deck = new Deck();
    
    public static void main(String[] args) {   	
      
    	
    	deck.shuffle();
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

        List<Card> tableCards = new ArrayList<Card>();
        for (int i = 0; i < 5; i++) 
        	tableCards.add(deck.removeCard());
        
        for (int i = 0; i < playerNumber; i++) {
            List<Card> playerHand = new ArrayList<Card>();
            for (int j = 0; j < 2; j++)
            	playerHand.add(deck.removeCard());
            
            players[i] = new Player(i, new Hand(merge(playerHand, tableCards)));
            players[i] = new Player(i, tableCards, playerHand);
            
            System.out.printf("\nMão do Jogador %d: %s\n", i+1, playerHand.toString());
            System.out.println("Quanto ele pagou? (digite 0 se ele saiu da mesa) $");
            
        }
        
    }
    
    public static<T> List<T> merge(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>();
     
        list.addAll(list1);
        list.addAll(list2);
     
        return list;
    }

}
