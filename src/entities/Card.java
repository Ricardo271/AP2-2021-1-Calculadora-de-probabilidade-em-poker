package entities;

public class Card {
	

	public final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
	//2,3,4,5,6,7,8,9,10,J,Q,K,A
	public final int[] PRIMES_SUITS = {43, 47, 53, 59};
	//spades, hearts, diamonds and clubs
	
    private int value;
    
    /*
     	card number + suit (As, , 2h ...)
     */

    public Card(Integer value) {
    	this.value = value;
    }
    
    public Card(String card) { 
    	
        /*
              1st letter of String card = number of the card
              2nd letter of String card = suit of the card
              Number of the card and suit of the card will correspond to its respective prime number, and then multiplied together; 
              the final result will be "value"
         */
    	
    	char number = card.charAt(0);
    	char suit = card.charAt(1);
    	int index;
    	
    	switch(number)
    	{
    	case 'T': index = 10; break;
    	case 'J': index = 11; break;
       	case 'Q': index = 12; break;
    	case 'K': index = 13; break;
    	case 'A': index = 14; break;
    	default: index = number - '0';
    	break;
    	}
    	value = PRIMES[index - 2];
    	
    	switch(suit)
    	{
    	case 's': index = 0; break;
    	case 'h': index = 1; break;
    	case 'd': index = 2; break;
    	case 'c': index = 3; break;
    	default: break;
    	}
    	value *= PRIMES_SUITS[index];

    }

    public int getCard() {
        return this.value;
    }

}
