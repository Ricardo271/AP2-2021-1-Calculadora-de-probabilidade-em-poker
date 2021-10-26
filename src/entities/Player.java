package entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int number;
    private Hand fullHand; // Includes the player cards and the table cards
    private List<Card> playerHand = new ArrayList<Card>(); // Only the cards in the player hand
    private double cash;
    private double bet;

    public Player(int number, List<Card> playerHand, List<Card> tableCards) {
        this.number = number;
        this.playerHand = playerHand;
        this.fullHand = new Hand(merge(playerHand, tableCards));
        this.cash = 0;
        this.bet = 0;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Hand getFullHand() {
        return this.fullHand;
    }

    public void setFullHand(Hand fullHand) {
        this.fullHand = fullHand;
    }

    public List<Card> getPlayerHand() {
        return this.playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public double getCash() {
        return this.cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBet() {
        return this.bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public void addBet(double bet) {
        this.bet += bet;
    }
    
    public void wonGame(double bets) {
        this.cash += bets;
    }

    public void lostGame() {
        this.cash -= this.bet;
    }

    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();

        list.addAll(list1);
        list.addAll(list2);

        return list;
    }

}
