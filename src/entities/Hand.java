package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand  {


	private List<Card> hand = new ArrayList<Card>();
	private Integer powerOfHand;
	
	private List<Card> strongestHand = new ArrayList<Card>();
	
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
		sortHand(this.hand);
		calculatePower();
	}
	
	public Hand() {
		
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
	
	public void addCard(Card card) {
		this.hand.add(card);
		sortHand(this.hand);
		if (hand.size() >= 5 ) {
			calculatePower();
		}
	}
	
	public void removeCard(Card card) {
		this.hand.remove(card);
		if (hand.size() >= 5 ) {
			calculatePower();
		}
	}
	
	public void resetHand() {
		this.hand.clear();
		this.strongestHand.clear();
		this.pair = false;
		this.doublePair = false;
		this.triplets = false;
		this.straight = false;
		this.flush = false;
		this.fullHouse = false;
		this.quads = false;
		this.straightFlush = false;
		this.royalFlush = false;
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

	public List<Card> getStrongestHand() {
		return strongestHand;
	}

	public void calculatePower() {
		setPair(hasPair());
		setDoublePair(hasDoublePair());
		setTriplets(hasTriplets());
		setStraight(hasStraight());
		setFlush(hasFlush());
		setFullHouse(hasFullHouse());
		setQuads(hasQuads());
		setStraightFlush(hasStraightFlush());
		setRoyalFlush(hasRoyalFlush());
		
		if (isPair()) {
			powerOfHand = 10;
		}
		if (isDoublePair()) {
			powerOfHand = 100;
		}
		if (isTriplets()) {
			powerOfHand = 1000;
		}
		if (isStraight()) {
			powerOfHand = 10000;
		}
		if (isFlush()) {
			powerOfHand = 100000;
		}
		if (isFullHouse()) {
			powerOfHand = 1000000;
		}
		if (isQuads()) {
			powerOfHand = 10000000;
		}
		if (isStraightFlush()) {
			powerOfHand = 100000000;
		}
		if (isRoyalFlush()) {
			powerOfHand = 1000000000;
		}
	}
	
	public Integer tieBreak(Hand h) {
		for (int i = 0; i < 5; i++) {
			if (h.getStrongestHand().get(i).getNumber() > strongestHand.get(i).getNumber()) {
				return 2;
			} else if (h.getStrongestHand().get(i).getNumber() < strongestHand.get(i).getNumber()) {
				return 1;
			}
		}
		return 0;
	}

	public void sortHand(List<Card> hand) {
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
				strongestHand.clear();
				for (int j = 0; j < hand.size(); j++) {
					if (hand.get(j).getSuit() == suitChosen) {
						strongestHand.add(hand.get(j));
					}
				}
				sortHand(strongestHand);
				while (strongestHand.size() > 5){
					strongestHand.remove(0);
				} 
				return true;
			}
		}
		return false;
	}

	private boolean hasStraight() {
		int straightCounter = 0;
		int chosenNumber = 0;
		int lowerNumber;
		for (int i = 0; i < hand.size(); i += straightCounter) {
			chosenNumber = hand.get(i).getNumber();
			lowerNumber = chosenNumber;
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
				strongestHand.clear();
				int counter = 0;
				for (int j = 0; j < hand.size(); j++) {
					if(hand.get(j).getNumber() == lowerNumber + counter) {
						strongestHand.add(hand.get(j));
						counter++;
					}
				}
				sortHand(strongestHand);
				while (strongestHand.size() > 5) {
					strongestHand.remove(0);
				} 
				
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
				strongestHand.clear();
				for (int n = 0; n < hand.size(); n++) {
					if (hand.get(n).getNumber() == chosenNumber) {
						strongestHand.add(hand.get(n));
					}
				}

				for (int m = 1; m < hand.size(); m++) {
					if (hand.get(hand.size()-m).getNumber() == chosenNumber) {
						m++;
					} else {
						strongestHand.add(hand.get(hand.size()-m));
						break;
					}
				}
				sortHand(strongestHand);
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
				strongestHand.clear();
				for (int n = 0; n < hand.size(); n++) {
					if (hand.get(n).getNumber() == chosenNumber) {
						strongestHand.add(hand.get(n));
					}
				}

				for (int m = 1; m < hand.size(); m++) {
					if (hand.get(hand.size()-m).getNumber() == chosenNumber) {
						continue;
					} else {
						strongestHand.add(hand.get(hand.size()-m));
						if (strongestHand.size() != 5) {
							continue;
						} else {
							break;
						}
					}
				}
				sortHand(strongestHand);

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
				strongestHand.clear();
				for (int n = 0; n < hand.size(); n++) {
					if (hand.get(n).getNumber() == chosenNumber) {
						strongestHand.add(hand.get(n));
					}
				}

				for (int m = 1; m < hand.size(); m++) {
					if (hand.get(hand.size()-m).getNumber() == chosenNumber) {
						continue;
					} else {
						strongestHand.add(hand.get(hand.size()-m));
						if (strongestHand.size() != 5) {
							continue;
						} else {
							break;
						}
					}
				}
				sortHand(strongestHand);
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
			strongestHand.clear();
			int tripletCounter=0, doubleCounter=0;
			for (int j = 0; j < hand.size(); j++) {
				if (hand.get(j).getNumber() == tripletNumber && tripletCounter != 3) {
					strongestHand.add(hand.get(j));
					tripletCounter++;
				}
				else if (hand.get(j).getNumber() == pairNumber && doubleCounter != 2) {
					strongestHand.add(hand.get(j));
					doubleCounter++;
				}
			}
			sortHand(strongestHand);
			
			return true;
		}
		
		return false;
	}

	private boolean hasStraightFlush() {
		if(!isFlush() && !isStraight()) {
			return false;
		}
		int straightFlushCounter = 0;
		int chosenNumber=0, lowerNumber=0;
		int chosenSuit = 0;
		for (int i = 0; i < hand.size(); i += straightFlushCounter) {
			chosenNumber = hand.get(i).getNumber();
			lowerNumber = chosenNumber;
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
				strongestHand.clear();
				int counter = 0;
				for (int j = 0; j < hand.size(); j++) {
					if(hand.get(j).getNumber() == lowerNumber + counter &&
							hand.get(j).getSuit() == chosenSuit) {
						strongestHand.add(hand.get(j));
						counter++;
					}
				}
				sortHand(strongestHand);
				while (strongestHand.size() > 5) {
					strongestHand.remove(0);
				} 
				
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
				strongestHand.clear();
				for (int j = 0; j < hand.size(); j++) {
					if (hand.get(j).getSuit() == chosenSuit) {
						strongestHand.add(hand.get(j));
					}
				}
				sortHand(strongestHand);
				while (strongestHand.size() > 5){
					strongestHand.remove(0);
				} 
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
					
					strongestHand.clear();
					
					int firstPairCounter=0, secondPairCounter=0;
					
					for (int m = 0; m < hand.size(); m++) {
						if (hand.get(m).getNumber() == firstPairDetected && firstPairCounter != 2) {
							firstPairCounter++;
							strongestHand.add(hand.get(m));
						}
						else if (hand.get(m).getNumber() == secondPairDetected && secondPairCounter != 2) {
							secondPairCounter++;
							strongestHand.add(hand.get(m));
						}
					}
					
					for (int n = 1; n < hand.size(); n++) {
						if (hand.get(hand.size() - n).getNumber() != secondPairDetected
							&& hand.get(hand.size() - n).getNumber() != firstPairDetected) {
							strongestHand.add(hand.get(hand.size() - n));
							break;
						}
					}
					sortHand(strongestHand);
					return true;
				}
			}
		}
		return false;
		
	}

	public String toString() {
		return Arrays.toString(hand.toArray());
	}
	
}
