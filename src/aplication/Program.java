package aplication;

import entities.Card;

public class Program {
    
    public static void main(String[] args) {
    	
    	String card = "9h";
        Card testing = new Card(card);
        System.out.println(testing.getCard());
        
    }

}
