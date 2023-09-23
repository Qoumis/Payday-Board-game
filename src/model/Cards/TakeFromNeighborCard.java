package model.Cards;

import model.Player.player;

public class TakeFromNeighborCard extends MailCard{

	/**Constructor
	 * Creates an instance of TakeFromNeighborCard object
	 * 
	 * @post An instance of a TakeFromNeighborCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to take)
	 */
	public TakeFromNeighborCard(String message, String icon, String choice, int Euro) {
		super("Take money from neighbor", message, icon, choice, Euro);
	}

	/**Transformer
	 * 
	 * @pre none
	 * @post Player p takes "Euro" amount of money from their opponent
	 * @param p The player that drew the card
	 */
	public void performAction(player p) {
		p.setMoney(this.getValue());
		p.getOpponent().pay(this.getValue());
	}
}
