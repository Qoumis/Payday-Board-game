package model.dice;

import java.util.Random;

/**
 * This class represents the dice of the game
 * 
 * @author csd4281
 */
public class dice {
	
	private int number;    //Holds the number of the dice
	private boolean roll; //Logical value to check if dice has been rolled this turn
	private static Random r = new Random();
	
	/**Constructor 
	 * Creates a new dice object with two attributes
	 * 
	 * @pre None
	 * @post A new dice object is created 
	 */
	public dice(){
		number = 0;
		roll = false;
	}
	
	/**Transformer
	 * Rolls the dice 
	 * 
	 * @pre None
	 * @post The number variable gets a random value in range (1-6)
	 */
	public void rollDice() {
		number = r.nextInt(6) + 1;
		roll = true;
	}
	
	/**Accessor
	 * Gets the value of number variable
	 * 
	 * @pre None
	 * @post The value of number is returned
	 * @return The value of number
	 */
	public int getRoll() {
		return number;
	}
	
	/**Accessor
	 * Checks if the dice has been rolled (We want to allow the player to roll only once per turn)
	 * 
	 * @pre None
	 * @post The value of roll is returned
	 * @return The value of roll
	 */
	public boolean isRolled() {
		return roll;
	}
	
	/**Transformer
	 * Sets the value of roll to false at the end of the player's turn 
	 * (or if he need to roll again e.g. for a lottery)
	 * 
	 * @pre The players has finished his turn (or he has to roll again for some action)
	 * @post The value of roll is set to false
	 */
	public void rollNext() {
		roll = false;
	}
	
}
