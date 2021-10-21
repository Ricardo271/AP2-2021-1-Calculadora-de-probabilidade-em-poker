package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand  {

	// private Card[] hand = new Card[7];
	private List<Card> hand = new ArrayList<Card>();
	// private Integer powerOfHand;
	// private String highestCardInBestHand;
	
	private boolean pair;
	private boolean doublePair;
	private boolean triplets;
	private boolean straight;
	private boolean flush;
	private boolean fullHouse;
	private boolean quads;
	private boolean straightFlush;
	private boolean royalFlush;

	public Hand(List<Card> hand) {
		this.hand = hand;
		sortHand();
		calculatePower();
	}

	public boolean isPair() {
		return pair;
	}

	public boolean isDoublePair() {
		return doublePair;
	}

	public boolean isTriplets() {
		return triplets;
	}

	public boolean isStraight() {
		return straight;
	}

	public boolean isFlush() {
		return flush;
	}

	public boolean isFullHouse() {
		return fullHouse;
	}
	
	public boolean isQuads() {
		return quads;
	}

	public boolean isStraightFlush() {
		return straightFlush;
	}

	public boolean isRoyalFlush() {
		return royalFlush;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public void setPair(boolean pair) {
		System.out.println("Pair is " + pair);
		this.pair = pair;
	}

	public void setDoublePair(boolean doublePair) {
		this.doublePair = doublePair;
	}

	public void setTriplets(boolean triplets) {
		System.out.println("Triplets is " + triplets);
		this.triplets = triplets;
	}

	public void setStraight(boolean straight) {
		System.out.println("Straight is " + straight);
		this.straight = straight;
	}

	public void setFlush(boolean flush) {
		System.out.println("Flush is " + flush);
		this.flush = flush;
	}

	public void setFullHouse(boolean fullHouse) {
		this.fullHouse = fullHouse;
	}
	
	public void setQuads(boolean quads) {
		System.out.println("Quads is " + quads);
		this.quads = quads;
	}

	public void setStraightFlush(boolean straightFlush) {
		this.straightFlush = straightFlush;
	}

	public void setRoyalFlush(boolean royalFlush) {
		this.royalFlush = royalFlush;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void calculatePower() {
		setFlush(hasFlush());
		setStraight(hasStraight());
		// hasStraightFlush();
		// hasRoyalFlush();
		setQuads(hasQuads());
		// hasFullHouse();
		setTriplets(hasTriplets());
		// hasDoublePair();
		setPair(hasPair());
	}

	public void sortHand() {
		hand.sort(Comparator.comparing(Card::getNumber));
		System.out.println(hand);
	}

	private boolean hasFlush() {
		int flushCounter = 0;
		char suitChosen = '0';
		for (int i = 0; i < 7; i++) {
			if (suitChosen == hand.get(i).getSuit()) {
				continue;
			} else {
				suitChosen = hand.get(i).getSuit();
				flushCounter = 1;
			}
			for (int j = 0; j < 7; j++) {
				if (i == j) {
					continue;
				} else if (hand.get(j).getSuit() == suitChosen) {
					flushCounter++;
				}
			}
			if (flushCounter >= 5) {
				return true;
			}
		}
		return false;
	}

	private boolean hasStraight() {
		int straightCounter = 0;
		int chosenNumber = 0;
		for (int i = 0; i < 7; i += straightCounter) {
			chosenNumber = hand.get(i).getNumber();
			straightCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				} else if (hand.get(j).getNumber() - chosenNumber == 1) {
					straightCounter++;
					chosenNumber = hand.get(j).getNumber();
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

	private boolean hasQuads() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				} else if (chosenNumber == hand.get(j).getNumber()) {
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

	private boolean hasTriplets() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				} else if (chosenNumber == hand.get(j).getNumber()) {
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

	private boolean hasPair() {
		int duplicateCounter = 0;
		int chosenNumber;
		for (int i = 0; i < 7; i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < 7; j++) {
				if (j <= i) {
					continue;
				} else if (chosenNumber == hand.get(j).getNumber()) {
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

	private boolean hasFullHouse() {
		// todo
		return false;
	}

	private boolean hasStraightFlush() {
		// todo
		return false;
	}

	private boolean hasRoyalFlush() {
		// todo
		return false;
	}

	private boolean hasDoublePair() {
		// todo
		return false;
	}

	
}
