package entities;

import java.util.List;

public class FiveCardDrawHand extends Hand{
	
	public FiveCardDrawHand(List<Card> hand) {
		super(hand);
		strongestHand = hand;
	}
	
	//Escolha um modo de jogo
	//T - Teaxdhoaiujhfd
	//F = fjoiasdjf
	//L = tasvslk
	
	public boolean swap(List<Card> cardsToRemove, List<Card> cardsToAdd) {
		if (cardsToRemove.size() == cardsToAdd.size()) {
			for (int i = 0; i < cardsToRemove.size(); i++) {
				if (!hand.remove(cardsToRemove.get(i))) {
					System.out.println("ERRO: A carta " + cardsToRemove.get(i) + "não está presente na mão, então não pode ser trocada.");
					cardsToAdd.remove(i);
				}
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
