package entities;

public class Table {

	private Card[] cardsOnTable = new Card[5];

	public Table() {

	}

	public Card[] getCardsOnTable() {
		return this.cardsOnTable;
	}

	public void setCardsOnTable(Card[] cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}

	public void setRandomTable() {

		// TODO

	}

	public void revealCard(Card card) {

		for (int i = 0; i < cardsOnTable.length; i++) {
			if (cardsOnTable[i] == null) {
				cardsOnTable[i] = card;
				return;
			}
		}
		// TODO excecao para quando todas as cartas já tiverem sido reveladas

	}

	public String toString() {
		String output = "";
		for (Card card : cardsOnTable) {
			if (card != null) {
				output += card.toString() + " ";
			}
		}
		return output;
	}
}
