package model.Cards;

import model.Player.player;

public abstract class card {

	private String Type, message; 
	private String icon;
	
	/**This is a method that all cards should implement
	 * and perform a certain action that is indicated
	 * 
	 * @post The player performs an action
	 * @param p The player that drew the card
	 */
	public abstract void performAction(player p);
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The card's type is set
	 * @param Type The Type of the card
	 */
	public void SetType(String Type) {
		this.Type = Type;
	}
	
	/**Accessor
	 * 
	 * @pre None
	 * @post The Type of the card is returned
	 * @return The Type of the card
	 */
	public String getType() {
		return Type;
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The card's message is set
	 * @param message The message that the card will show
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**Accessor
	 * 
	 * @pre none
	 * @post The card's message is returned
	 * @return The card's message
	 */
	public String getMessage() {
		return message;
	}
	
	/**Transformer
	 * 
	 * @pre The path is valid
	 * @post The card's icon is set
	 * @param icon The path for card's icon 
	 */
	public void setIcon(String icon) {
		this.icon = "resources/images/" + icon;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The card's icon path is returned
	 * @return The card's icon path
	 */
	public String getIcon() {
		return icon;
	}
}
