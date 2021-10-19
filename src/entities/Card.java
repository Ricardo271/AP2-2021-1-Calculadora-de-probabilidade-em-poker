package entities;

public class Card {
	
	private String value;
	private char suit;
	private int number;
	
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

	public char getSuit() {
		return suit;
	}

	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return this.value;
	}
}
