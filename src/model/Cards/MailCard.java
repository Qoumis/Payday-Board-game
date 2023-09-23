package model.Cards;

import model.Player.player;

public class MailCard extends card{
	
	private String choice;
	private int Euro;
	
	/**Constructor
	 * Creates a new instance of a MailCard object
	 * 
	 * @pre Icon is a valid path
	 * @post An instance of a mailCard is created
	 * @param Type    The Type of the card
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 * @param Euro    The cost of the card (money to pay or to gain)
	 */
	public MailCard(String Type, String message, String icon, String choice, int Euro) {
		this.SetType(Type);
		this.setMessage(message);
		this.setIcon(icon);
		this.choice = choice;
		this.Euro = Euro;
		
	}
	
	@Override
	public void performAction(player p) {}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The card's choice string is returned
	 * @return The card's choice string
	 */
	public String getChoice() {
		return choice;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The value of Euro variable is returned
	 * @return the card's value
	 */
	public int getValue() {
		return Euro;
	}
}
