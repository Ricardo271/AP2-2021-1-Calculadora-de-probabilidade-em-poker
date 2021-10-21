package entities;

public class Card implements Comparable<Card> {
	
	private String value;
	private Character suit;
	private Integer number;
	
	public Card(String card) {
		int index;
		switch(card.charAt(0)) {
			case 'T': index = 10; break;
			case 'J': index = 11; break;
			case 'Q': index = 12; break;
			case 'K': index = 13; break;
			case 'A': index = 14; break;
			default: index = card.charAt(0) - '0';
		}
		this.number = index;
		this.suit = Character.toLowerCase(card.charAt(1));
		this.value = card;
	}

	public String getValue() {
		return value;
	}

	public Character getSuit() {
		return suit;
	}

	public Integer getNumber() {
		return number;
	}
	
	public String toString() {
		return this.value;
	}
	
	@Override
	public int compareTo(Card c) {
		if (getNumber() == null || c.getNumber() == null) {
			return 0;
		}
		return getNumber().compareTo(c.getNumber());
	}
}
