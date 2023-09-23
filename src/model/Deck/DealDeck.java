package model.Deck;

import java.util.ArrayList;
import java.util.Collections;

import model.Cards.DealCard;

public class DealDeck {

	private ArrayList<DealCard> Deck;
	private ArrayList<DealCard> RejectedStack;
	
	/**Constructor
	 * Creates a new deck, initializes and shuffles all cards's in it.
	 * 
	 * @pre none
	 * @post An instance of DealDeck object is created
	 */
	public DealDeck() {
		Deck = new ArrayList<DealCard>();
		RejectedStack = new ArrayList<DealCard>();
		
		init_deck();
		shuffle();
	}
	
	/**Observer
	 * 
	 * @pre none
	 * @post returns if the deck is empty or not
	 * @return True if the deck is empty, false otherwise
	 */
	public  boolean isEmpty() {
		if(Deck.isEmpty())
			return true;
		return false;
	}
	
	/**Transformer
	 * Adds all the cards in the deck (when the game starts)
	 * 
	 * @pre None
	 * @post All the cards are added to the deck
	 */
	public void init_deck() {
		String DealCards[][] = ReadCards.readFile("resources/dealCards_greeklish.csv", "Deal");
		
		for(int i = 0; i < 20; i++) {
			String message = DealCards[i][2];
			String icon = DealCards[i][5];
			int cost = Integer.parseInt(DealCards[i][3]);
			int value = Integer.parseInt(DealCards[i][4]);
			
			Deck.add(new DealCard(message, icon, cost, value));
		}
	}
	
	/**Transformer
	 * When the deck gets empty, adds all the rejected cards back to the deck
	 * 
	 * @pre The deck is empty
	 * @post All the rejected cards are added back to the deck
	 */
	public void reconstruct() {
		while(!RejectedStack.isEmpty()) 
			Deck.add(RejectedStack.remove(0));
		shuffle();
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The deck is shuffled
	 */
	public void shuffle() {
		Collections.shuffle(Deck);
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post A card is added to the deck
	 * @param card The card that will be added
	 */
	public void AddToDeck(DealCard card) {
		Deck.add(card);
	}
	
	/**Transformer
	 * Player draws a card and it gets removed from the deck
	 * If the player decides to keep the card we put it in the DealCards stack,
	 * if the reject it we put it in the RejectedStack
	 * 
	 * @pre none
	 * @post A card is removed from the deck
	 * @return The card that was drawn by the player
	 */
	public DealCard DrawCard() {
		return Deck.remove(0);
	}
	
	/**Transformer
	 * When the player rejects a card we add it to the rejected stack
	 * 
	 * @pre none
	 * @post A card is added to the rejected deck
	 * @param card the card that was rejected
	 */
	public void RejectCard(DealCard card) {
		RejectedStack.add(card);
	}
}
