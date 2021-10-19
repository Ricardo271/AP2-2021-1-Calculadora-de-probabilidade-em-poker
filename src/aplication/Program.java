package aplication;

import entities.Card;
import entities.Deck;
import entities.Hand;
import entities.Player;
import entities.Table;

public class Program {
    
    public static void main(String[] args) {

        Deck deck = new Deck();
        deck.shuffle();

        Table table = new Table();

        for (Card card : deck.getDeck()) {
            System.out.print(card.toString() + " ");
        }
        System.out.println();

        System.out.println(table.toString());
        table.revealCard(deck.removeCard());

        for (Card card : deck.getDeck()) {
            System.out.print(card.toString() + " ");
        }
        System.out.println();

        System.out.println(table.toString());

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
