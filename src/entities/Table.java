package entities;

import entities.enums.TableStatus;

public class Table {

    private TableStatus status;

    public Table(Card cardsOnTable[], String tableStatus) {
        this.status = TableStatus.valueOf(tableStatus);
        //this.cardsOnTable = cardsOnTable;
    }

    // TODO getters and setters
}
