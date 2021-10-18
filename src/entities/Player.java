package entities;

public class Player {

    private int number;
    private Hand hand;

    public Player(int number, Hand hand) {
        this.number = number;
        this.hand = hand;
    }

    public int getNumber() {
        return this.number;
    }

    public Hand getHand() {
        return this.hand;
    }

}
