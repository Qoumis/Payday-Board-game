package model.Deck;

import java.util.ArrayList;
import java.util.Collections;

import model.Deck.ReadCards;
import model.Cards.*;

public class MailDeck {

	private ArrayList<MailCard> Deck;
	
	/**Constructor
	 * Creates a new deck, initializes and shuffles all cards's in it.
	 * 
	 * @pre none
	 * @post An instance of MailDeck object is created
	 */
	public MailDeck() {
		Deck = new ArrayList<MailCard>();
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
	 * Adds all the cards in the deck (when the game starts
	 * or if the deck is out of cards at some point in the game)
	 * 
	 * @pre None
	 * @post All the cards are added to the deck
	 */
	public void init_deck() {
		String mailCards[][] = ReadCards.readFile("resources/mailCards_greeklish.csv", "Mail");
		
		for(int i = 0; i < 48; i++) {
			String typeEn = mailCards[i][1];
			
			String message = mailCards[i][2];
			String icon = mailCards[i][5];
			String choice =  mailCards[i][3];
			int Euro = Integer.parseInt(mailCards[i][4]);
			
			if(typeEn.equals("Advertisement"))
				Deck.add(new AdvertisementCard(message, icon, choice, Euro));
			else if(typeEn.equals("Bill"))
				Deck.add(new BillCard(message, icon, choice, Euro));
			else if(typeEn.equals("Charity"))
				Deck.add(new CharityCard(message, icon, choice, Euro));
			else if(typeEn.equals("PayTheNeighbor"))
				Deck.add(new PayNeighborCard(message, icon, choice, Euro));
			else if(typeEn.equals("MadMoney"))
				Deck.add(new TakeFromNeighborCard(message, icon, choice, Euro));
			else
				Deck.add(new MoveCard(message, icon, choice));
		}
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
	public void AddToDeck(MailCard card) {
		Deck.add(card);
	}
	
	/**Transformer
	 * Player draws a card and it gets removed from the deck
	 * 
	 * @pre none
	 * @post A card is removed from the deck
	 * @return The card that was drawn by the player
	 */
	public MailCard DrawCard() {
		return Deck.remove(0);
	}
}
