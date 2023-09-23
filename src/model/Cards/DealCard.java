package model.Cards;

import model.Player.player;

public class DealCard extends card{
	
	private int value, cost;
	private String choice1, choice2; 
	
	/**Constructor 
	 * Crates a new instance of DealCard object
	 * 
	 * @pre icon is a valid path and cost > 0 , value > 0
	 * @param message The message that the card will show
	 * @param icon    The card's icon path
	 * @param cost    The card's buy value
	 * @param value   The card's sell value
	 */
	public DealCard(String message, String icon, int cost, int value) {
		this.SetType("Deal");
		this.setMessage(message);
		this.setIcon(icon);
		this.cost = cost;
		this.value = value;
		this.choice1 = "Buy";     //Standard
		this.choice2 = "Reject"; //Standard
	}
	
	/**
	 * @pre The player has chosen to keep the card
	 * @post The player pays for the card and it gets added to their myDealCards stack
	 * @param p the player that drew the card
	 */
	public void performAction(player p) {
		p.pay(this.getCost());
		p.TakeDealCard(this);
	}

	/**Accessor
	 * 
	 * @return The card's buy value
	 */
	public int getCost() {
		return cost;
	}
	
	/**Accessor
	 * 
	 * @return The card's sell value
	 */
	public int getValue() {
		return value;
	}
	
	/**Accessor
	 * 
	 * @return The String message of choice1
	 */
	public String getChoice1() {
		return choice1;
	}
	
	/**Accessor
	 * 
	 * @return The String message of choice2
	 */
	public String getChoice2() {
		return choice2;
	}
}
