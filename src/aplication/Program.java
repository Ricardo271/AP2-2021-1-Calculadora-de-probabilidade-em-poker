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
    	System.out.printf("Selecione uma Função\n\n");
    	System.out.printf("N - Novo Jogo \nS - Sair do Programa\n");
    	
    	try (Scanner entry = new Scanner(System.in)) {
			  String response = entry.next().toUpperCase();
			
			  if("S".equals(response)) {

				  System.out.println("Programa Encerrado");
				  System.exit(0);
			  }
			  if("N".equals(response)) {
				  System.out.printf("------- Poker Scores ------- \n\n");
				  System.out.printf("Selecione uma opção de jogo: \n\n");
				  System.out.println("T - Texas Holdem Poker");
				  System.out.println("F - Five Card Draw Poker");
				  System.out.println("L - 2-7 Low Ball Poker");
				  
				  Scanner sc = new Scanner(System.in);
				  String gameType = sc.next().toUpperCase();
				  
				  switch (gameType) {
				  
				  case "T":
					  roundTexasHoldem();
					  break;
				  case "F":
					  //roundFiveCardDraw();
					  break;
				  case "L": 
					  //roundLowBallPoker();
					  break;
				  }
			  }
			
		}
	}



    public static void roundTexasHoldem() {
    	
    	System.out.printf("\n------- Poker Scores ------- \n\n");
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
            
            players[i] = new Player(i, tableCards, playerHand);
            
            System.out.printf("\nMão do Jogador %d: %s\n", i+1, playerHand.toString());
            System.out.printf("Quanto ele pagou? (digite 0 se ele saiu da mesa) $");
            players[i].addBet(sc.nextDouble());
            
        }
        
        System.out.printf("\nCartas da mesa: ");
        System.out.println(tableCards.subList(0,3).toString());
        
        //---------------------------------Next round of bets---------------------------------
    	System.out.printf("\n\n------- Poker Scores ------- \n\n");
        System.out.printf("Segunda rodada de apostas\n");
        for (int i = 0; i < playerNumber; i++) {
        	 System.out.printf("\nQuanto o jogador %d pagou? (digite 0 se ele saiu da mesa) $", i+1);
             players[i].addBet(sc.nextDouble());
        }
        
        System.out.printf("\nCartas da mesa: ");
        System.out.println(tableCards.subList(0,4).toString());
        
        //---------------------------------Next round of bets---------------------------------
    	System.out.printf("\n\n------- Poker Scores ------- \n\n");
        System.out.printf("Terceira rodada de apostas\n");
        for (int i = 0; i < playerNumber; i++) {
        	 System.out.printf("\nQuanto o jogador %d pagou? (digite 0 se ele saiu da mesa) $", i+1);
             players[i].addBet(sc.nextDouble());
        }
        
        System.out.printf("\nCartas da mesa: ");
        System.out.println(tableCards.subList(0,5).toString());
        
        //-------------------------------------End of Round-----------------------------------
        int score=0;
        List<Player> biggestScore = new ArrayList<Player>();
        for (Player player : players) {
        	if (player.getFullHand().getPowerOfHand() > score) {
        		biggestScore.clear();
        		biggestScore.add(player);
        		score = player.getFullHand().getPowerOfHand();
        	}
        	else if (player.getFullHand().getPowerOfHand() == score) {
        		if (biggestScore.size() == 0) {
        			biggestScore.add(player);
        		}
        		else {
        			int response = player.getFullHand().tieBreak(biggestScore.get(0).getFullHand());
        			switch (response) {
        			case 0:
        				biggestScore.add(player);
        				break;
        			case 1:
        				biggestScore.clear();
        				biggestScore.add(player);
        				break;
        			}
        		}
        	}
        }
        System.out.printf("\n\n------- Poker Scores ------- \n\n");
        System.out.println("# Round 1 #\n");
        System.out.printf("Cartas da mesa: ");
        System.out.println(tableCards.subList(0,5).toString());
        
        for (int i = 0; playerNumber > i; i++) {
        	System.out.printf("\nJogador %d: %s", i+1, players[i].getFullHand().getStrongestHand().toString());
        	if(biggestScore.size() == 1) {
        		if (players[i].equals(biggestScore.get(0))) {
        			System.out.printf("\t  Vencedor\t");
        			System.out.println("+ $%f"  );
        		}
        	}
        	else
        	{
        		for (int j = 0; j < biggestScore.size(); j++) {
        			if (players[i].equals(biggestScore.get(j))) {
        				System.out.printf("\t  Vencedor\t");
            			System.out.println("+ $%f"  );
            			break;

        			}
        		}
        	}	
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
