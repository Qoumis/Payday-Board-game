package model.Cards;

import model.Player.player;

public class PayNeighborCard extends MailCard{

	/**Constructor
	 * Creates an instance of PayNeighborCard object
	 * 
	 * @post An instance of a PayNeighborCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to pay)
	 */
	public PayNeighborCard(String message, String icon, String choice, int Euro) {
		super("Pay The Neighbor", message, icon, choice, Euro);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Player p pays their opponent "Euro" amount of money
	 * @param p The player that drew the card
	 */
	public void performAction(player p) {
		p.pay(this.getValue());
		p.getOpponent().setMoney(this.getValue());
	}
}
