package entities;

import entities.enums.TableStatus;

public class Table {

    private Card[] cardsOnTable = new Card[5];

    private TableStatus status;

    public Table(Card cardsOnTable[], String tableStatus) {
        this.status = TableStatus.valueOf(tableStatus);
        this.cardsOnTable = cardsOnTable;
    }

    // TODO getters and setters
}
