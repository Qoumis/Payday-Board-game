package model.Cards;

import model.Player.player;

public class BillCard extends MailCard{
	/**Constructor
	 * Creates an instance of BillCard object
	 * 
	 * @post An instance of a BillCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to pay)
	 */
	public BillCard(String message, String icon, String choice, int Euro) {
		super("Pay the bill", message, icon, choice, Euro);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Player's bills are increased by "Euro"
	 * @param p The player that drew the card
	 */
	public void performAction(player p) {
		p.setBill(this.getValue());
	}
}
