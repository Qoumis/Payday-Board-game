package model.Cards;

import model.Player.player;
import model.jackpot.jackpot;

public class CharityCard extends MailCard{
	/**Constructor
	 * Creates an instance of CharityCard object
	 * 
	 * @post An instance of a CharityCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to pay)
	 */
	public CharityCard(String message, String icon, String choice, int Euro) {
		super("Charity", message, icon, choice, Euro);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Player pays "Euro" amount of money to the jackpot
	 * @param p The player that drew the card
	 * @param j The jackpot of the game
	 */
	public void performAction(player p, jackpot j) {
		p.pay(this.getValue());
		j.addToJackpot(this.getValue());
	}
}
