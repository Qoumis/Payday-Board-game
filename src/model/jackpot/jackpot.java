package model.jackpot;

import model.Player.player;

/**This class represents the jackpot block of the game
 * and provides all the methods required to add and remove money from the jackpot
 * 
 * @author csd4281
 *
 */
public class jackpot {
	private int balance;
	
	/**Constructor 
	 * Creates a new instance of jackpot object
	 * 
	 * @pre None
	 * @post An instance of jackpot is crated
	 */
	public jackpot() {
		balance = 0;
	}
	
	/**Transformer
	 * 
	 * @pre amount > 0
	 * @post The balance of the jackpot is increased
	 * @param amount The amount of money to be added to the jackpot
	 */
	public void addToJackpot(int amount) {
		balance += amount;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The value of balance variable is returned
	 * @return The jackpot's current balance
	 */
	public int getBalance() {
		return balance;
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The player's balance is increased by balance and then balance is set to 0
	 * @param p The player that won the jackpot
	 */
	public void winJackpot(player p) {
		p.setMoney(balance);
		balance = 0;
	}
	
}
