package entities;

import entities.enums.TableStatus;

public class Table {

	private String[] cardsOnTable = new String[2];

	public String[] getCardsOnTable() {
		return this.cardsOnTable;
	}

	public void setCardsOnTable(String[] cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}

}
