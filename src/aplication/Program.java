package aplication;

import java.util.Scanner;

import entities.Hand;
import entities.Player;
import entities.Visual;

public class Program {
    
    public static void main(String[] args) {
    	
    Visual.inicialMenu();
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
