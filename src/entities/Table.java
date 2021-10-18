package entities;

public class Table {

	private Card[] cardsOnTable = new Card[2];

	public Card[] getCardsOnTable() {
		return this.cardsOnTable;
	}

	public void setCardsOnTable(Card[] cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}

}
