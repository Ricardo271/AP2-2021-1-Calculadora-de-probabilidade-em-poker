package entities;

public class Hand {

	private Card[] hand = new Card[7];
	//private Integer powerOfHand;
	//private String highestCardInBestHand;

	public Hand(Card[] hand) {
		this.hand = hand;
		sortHand();
		calculatePower();
	}

	public Card[] getHand() {
		return hand;
	}

	public void calculatePower() {
		System.out.println("Flush is " + hasFlush());
		System.out.println("Straight is " + hasStraight());
		// hasStraightFlush();
		// hasRoyalFlush();
		System.out.println("Quads is " + hasQuads());
		// hasFullHouse();
		System.out.println("Triplets is " + hasTriplets());
		// hasDoublePair();
		System.out.println("Pair is " + hasPair()); 
		
		
		//todo: distribuir powerOfHand de acordo com a checagem de booleans acima.
	}

	public void sortHand() {
		Card tempCard;
/** ordenando pelo naipe
*		for (int i = 0; i < 7; i++) {
*			for (int j = 0; j < 7; j++) {
*				if (i <= j) {
*					continue;
*				} else if (hand[i].getSuit() > hand[j].getSuit()) {
*					tempCard = hand[i];
*					hand[i] = hand[j];
*					hand[j] = tempCard;
*				}
*			}
*		}
*/
		// ordenando pelo valor da carta
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (i <= j) {
					continue;
				} else if (hand[i].getNumber() < hand[j].getNumber()) {
					tempCard = hand[i];
					hand[i] = hand[j];
					hand[j] = tempCard;
				}
			}
		}
		System.out.print(hand[0]);
		System.out.print(hand[1]);
		System.out.print(hand[2]);
		System.out.print(hand[3]);
		System.out.print(hand[4]);
		System.out.print(hand[5]);
		System.out.println(hand[6]);
	}

	public boolean hasFlush() {
		int flushCounter = 0;
		char suitChosen = '0';
		for (int i = 0; i < 7; i++) {
			if (suitChosen == hand[i].getSuit()) {
				continue;
			} else {
				suitChosen = hand[i].getSuit();
				flushCounter = 1;
			}
			for (int j = 0; j < 7; j++) {
				if (i == j) {
					continue;
				} else if (hand[j].getSuit() == suitChosen) {
					flushCounter++;
				}
			}
			if (flushCounter >= 5) {
				return true;
			}
		}
		return false;
	}

	public boolean hasStraight() {
		int straightCounter = 0;
		int chosenNumber = 0;
		for (int i = 0; i < 7; i += straightCounter) {
			chosenNumber = hand[i].getNumber();
			straightCounter = 1;
			for (int j = 0; j < 7; j++) {
				if ( j <= i ) {
					continue;
				} else if (hand[j].getNumber() - chosenNumber == 1) {
					straightCounter++;
					chosenNumber = hand[j].getNumber();
				} else {
					break;
				}
			}
			if (straightCounter >= 5) {
				return true;
			}
		}
		return false;
	}
	public boolean hasQuads() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand[i].getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				}
				else if (chosenNumber == hand[j].getNumber()) {
					duplicateCounter++;
				} else {
					break;
				}
			}
			if (duplicateCounter == 4) {
				return true;
			}
		}
		return false;
	}
	public boolean hasTriplets() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand[i].getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				}
				else if (chosenNumber == hand[j].getNumber()) {
					duplicateCounter++;
				} else {
					break;
				}
			}
			if (duplicateCounter >= 3) {
				return true;
			}
		}
		return false;
	}
	public boolean hasPair() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand[i].getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				}
				else if (chosenNumber == hand[j].getNumber()) {
					duplicateCounter++;
				} else {
					break;
				}
			}
			if (duplicateCounter >= 2) {
				return true;
			}
		}
		return false;
	}
	public boolean hasFullHouse() {
		//todo
		return false;
	}
	public boolean hasStraightFlush() {
		//todo
		return false;
	}
	public boolean hasRoyalFlush() {
		//todo
		return false;
	}
	public boolean hasDoublePair() {
		//todo
		return false;
	}
}
