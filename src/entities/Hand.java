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
		this.pair = pair;
	}

	public void setDoublePair(boolean doublePair) {
		this.doublePair = doublePair;
	}

	public void setTriplets(boolean triplets) {
		this.triplets = triplets;
	}

	public void setStraight(boolean straight) {
		this.straight = straight;
	}

	public void setFlush(boolean flush) {
		this.flush = flush;
	}

	public void setFullHouse(boolean fullHouse) {
		this.fullHouse = fullHouse;
	}
	
	public void setQuads(boolean quads) {
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
		setPair(hasPair());
		setDoublePair(hasDoublePair());
		setTriplets(hasTriplets());
		setQuads(hasQuads());
		setStraight(hasStraight());
		setFlush(hasFlush());
		setFullHouse(hasFullHouse());
		setQuads(hasQuads());
		setStraightFlush(hasStraightFlush());
		setRoyalFlush(hasRoyalFlush());
	}

	public void sortHand() {
		hand.sort(Comparator.comparing(Card::getNumber));
	}

	private boolean hasFlush() {
		int flushCounter = 0;
		char suitChosen = '0';
		for (int i = 0; i < hand.size(); i++) {
			if (suitChosen == hand.get(i).getSuit()) {
				continue;
			} else {
				suitChosen = hand.get(i).getSuit();
				flushCounter = 1;
			}
			for (int j = 0; j < hand.size(); j++) {
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
		for (int i = 0; i < hand.size(); i += straightCounter) {
			chosenNumber = hand.get(i).getNumber();
			straightCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
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
		if (!isPair()) {
			return false;
		}
		for (int i = 0; i < hand.size(); i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
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
		if (!isPair()) {
			return false;
		}
		for (int i = 0; i < hand.size(); i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
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
		for (int i = 0; i < hand.size(); i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
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
		if (!isTriplets()) {
			return false;
		} 
		int chosenNumber;
		int duplicateCounter = 0;
		int pairNumber = 0, tripletNumber = 0;
		for(int i = 0; i < hand.size(); i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for(int j = 0; j < hand.size(); j++) {
				if (j <= i) {
					continue;
				} else if (chosenNumber == hand.get(j).getNumber()) {
					duplicateCounter++;
				} else {
					break;
				}
			}
			if (duplicateCounter >= 2) {
				pairNumber = chosenNumber;
			}
			if (duplicateCounter >= 3) {
				if (tripletNumber != 0) {
					if (chosenNumber > tripletNumber) {
						pairNumber = tripletNumber;
						tripletNumber = chosenNumber;
					} else if (chosenNumber < tripletNumber) {
						tripletNumber = chosenNumber;
					}
				} else {
					tripletNumber = chosenNumber;
				}
			}
		}
		if(tripletNumber != 0 && pairNumber != 0 && tripletNumber != pairNumber) {
			return true;
		}
		
		return false;
	}

	private boolean hasStraightFlush() {
		if(!isFlush() && !isStraight()) {
			return false;
		}
		int straightFlushCounter = 0;
		int chosenNumber = 0;
		int chosenSuit = 0;
		for (int i = 0; i < hand.size(); i += straightFlushCounter) {
			chosenNumber = hand.get(i).getNumber();
			chosenSuit = hand.get(i).getSuit();
			straightFlushCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
				if (j <= i) {
					continue;
				} else if (hand.get(j).getNumber() - chosenNumber == 1 && hand.get(j).getSuit() == chosenSuit) {
					straightFlushCounter++;
					chosenNumber = hand.get(j).getNumber();
				} else {
					break;
				}
			}
			if (straightFlushCounter >= 5) {
				return true;
			}
		}
		return false;
	}

	private boolean hasRoyalFlush() {
		if(!isStraightFlush()) {
			return false;
		}
		
		
		int royalFlushCounter = 0;
		int chosenNumber = 0;
		int chosenSuit = 0;
		for (int i = 0; i < hand.size(); i += royalFlushCounter) {
			chosenNumber = hand.get(i).getNumber();
			chosenSuit = hand.get(i).getSuit();
			royalFlushCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
				if (j <= i) {
					continue;
				} else if (hand.get(j).getNumber() - chosenNumber == 1 && hand.get(j).getSuit() == chosenSuit) {
					royalFlushCounter++;
					chosenNumber = hand.get(j).getNumber();
				} else {
					break;
				}
			}
			if (royalFlushCounter >= 5 && chosenNumber == 14) {
				return true;
			}
		}
		return false;
	}

	private boolean hasDoublePair() {
		if(!isPair()) {
			return false;
		}
		
		int duplicateCounter = 0;
		int chosenNumber;
		int firstPairDetected = 0, secondPairDetected = 0;
		for (int i = 0; i < hand.size(); i += duplicateCounter) {
			chosenNumber = hand.get(i).getNumber();
			duplicateCounter = 1;
			for (int j = 0; j < hand.size(); j++) {
				if (j <= i) {
					continue;
				} else if (chosenNumber == hand.get(j).getNumber()) {
					duplicateCounter++;
				} else {
					break;
				}
			}
			if (duplicateCounter >= 2) {
				if (firstPairDetected == 0) {
					firstPairDetected = chosenNumber;
				} else {
					secondPairDetected = chosenNumber;
					return true;
				}
			}
		}
		return false;
		
	}

}
