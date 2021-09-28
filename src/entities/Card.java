package entities;

public class Card {
	

	public final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
	//2,3,4,5,6,7,8,9,10,J,Q,K,A
	
	//Suits
	public final int SPADES = 0x8000;
	public final int HEARTS = 0x4000;
	public final int DIAMONDS = 0x2000;
	public final int CLUBS = 0x1000;
	// |   Spades   |   Hearts   |  Diamonds  |   Clubs   |
	// 23456789TJQKA23456789TJQKA23456789TJQKA23456789TJQKA
	// 0001010010000000100000000000000000010000010000000001
	
	
	
	public final String RANKS = "23456789TJQKA";
	public final String SUITS = "shdc";
	
    private int value;
    
    /*
     	card number + suit (As, , 2h ...)
     */

    public Card(Integer value) {
    	this.value = value;
    }
    
    public Card(int rank, int suit) {
    	//10001000000000010
    	value = (1 << (rank + 16)) | suit | (rank << 8) | PRIMES[rank];
    	//         2^16              2^12       0*2^8        2                      2s
    	//         2^17              2^12       1*2^8        3  					3s
    	//	       2^18              2^12       2*2^8        5                      4s
    	//         2^18              2^13       2*2^8        5                      4h
    	// 010001 << 1  ==  100010
    }
    
    public Card(String card) { 
    	
        /*
              1st letter of String card = number of the card
              2nd letter of String card = suit of the card
              Number of the card and suit of the card will correspond to its respective prime number, and then multiplied together; 
              the final result will be "value"
         */
    	
    	final int rank = RANKS.indexOf(card.charAt(0));
        final int suit = CLUBS << SUITS.indexOf(card.charAt(1));
        System.out.println(suit + "e o" + rank);
        //return Card(rank, suit)
        value = (1 << (rank + 16)) | suit | (rank << 8) | PRIMES[rank];
        System.out.println(value);

    }

    public int getCard() {
        return this.value;
    }

}
