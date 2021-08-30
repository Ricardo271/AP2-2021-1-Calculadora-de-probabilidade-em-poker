package entities;

public class Card {

    private final int value;

    /**
     * @param card number + suit (As, Ks, 2h ...)
     */
    public Card(String card) {
        /**
         * TODO: 1st letter of String card = number of the card
              2nd letter of String card = suit of the card
              Number of the card and suit of the card will correspond to its respective prime number, and then multiplied together; 
              the final result will be "value"
         */
    }

    public int getValue() {
        return this.value;
    }

}
