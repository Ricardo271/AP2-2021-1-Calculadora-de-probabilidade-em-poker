package entities;

import java.util.List;

public class TwoSevenLowBallHand extends FiveCardDrawHand {
	
	//classe inutilizada no momento
	
	public TwoSevenLowBallHand (List<Card> hand) {
		super(hand);
	}
	
	@Override
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
		powerOfHand = 1000000000;
		if (isRoyalFlush()) {
			powerOfHand = 1;
		}
		if (isStraightFlush()) {
			powerOfHand = 10;
		}
		if (isQuads()) {
			powerOfHand = 100;
		}
		if (isFullHouse()) {
			powerOfHand = 1000;
		}
		if (isFlush()) {
			powerOfHand = 10000;
		}
		if (isStraight()) {
			powerOfHand = 100000;
		}
		if (isTriplets()) {
			powerOfHand = 1000000;
		}
		if (isDoublePair()) {
			powerOfHand = 10000000;
		}
		if (isPair()) {
			powerOfHand = 100000000;
		}
	}
	
	@Override
	public Integer tieBreak(Hand h) {
		for (int i = 0; i < 5; i++) {
			if (h.getStrongestHand().get(i).getNumber() < strongestHand.get(i).getNumber()) {
				return 2;
			} else if (h.getStrongestHand().get(i).getNumber() > strongestHand.get(i).getNumber()) {
				return 1;
			}
		}
		return 0;
	}
}
