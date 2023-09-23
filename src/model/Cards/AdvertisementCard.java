package model.Cards;

import model.Player.player;

public class AdvertisementCard extends MailCard{
	/**Constructor
	 * Creates an instance of AdvertisementCard object
	 * 
	 * @post An instance of a AdvertisementCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to gain)
	 */
	public AdvertisementCard(String message, String icon, String choice, int Euro) {
		super("Advertisement", message, icon, choice, Euro);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Player's balance is increased by "Euro" amount of money
	 * @param p The player that drew the card
	 */
	public void performAction(player p) {
		p.setMoney(this.getValue());
	}
}
