package entities;

import java.util.List;

public class FiveCardDrawHand extends Hand{
	
	public FiveCardDrawHand(List<Card> hand) {
		super(hand);
	}
	
	//metodo para trocar as cartas, usando uma lista com cartas para remove-las e adiciona-las
	public boolean swap(List<Card> cardsToRemove, List<Card> cardsToAdd) {
		if (cardsToRemove.size() == cardsToAdd.size()) {
			for (int i = 0; i < cardsToRemove.size(); i++) {
				hand.remove(cardsToRemove.get(i));
			}
			for (int j = 0; j < cardsToAdd.size(); j++) {
				hand.add(cardsToAdd.get(j));
			}
			sortHand(hand);
			calculatePower();
			return true;
		} else {
			System.out.println("ERRO: A quantidade de cartas adicionadas é diferente da quantidade de cartas a serem removidas.");
			return false;
		}
	}
	
}
