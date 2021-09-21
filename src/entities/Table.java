package entities;

import entities.enums.TableStatus;

public class Table {

	//Pode ser necessario uma list
    private Card[] cardsOnTable = new Card[5];

    private TableStatus status;

    public Table(Card cardsOnTable[], String tableStatus) {
        this.status = TableStatus.valueOf(tableStatus);
        this.cardsOnTable = cardsOnTable;
    }

	public Card[] getCardsOnTable() {
		return cardsOnTable;
	}

	//Pode ser necessario uma list
	public void setCardsOnTable(Card[] cardsOnTable) {
		this.cardsOnTable = cardsOnTable;
	}

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}

    
}
