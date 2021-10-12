package aplication;

import entities.Hand;
import entities.Player;

public class Program {
    
    public static void main(String[] args) {
    	
    }

    public static void round() {

        int playerNumber = 3;
        Player[] players = new Player[playerNumber];

        for (int i = 0; i < playerNumber; i++) {
            String[] cardsOnHand = {"9s", "Ac"};
            players[i] = new Player(i, new Hand(cardsOnHand));
        }

    }

}
