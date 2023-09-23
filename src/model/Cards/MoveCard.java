package model.Cards;

import model.Player.player;
import model.tile.tile;

public class MoveCard extends MailCard{
	/**Constructor
	 * Creates an instance of MoveCard object
	 * 
	 * @post An instance of a MoveCard is created
	 * @param message The message that the card will show
	 * @param icon    The path for the card's icon
	 * @param choice  What can be done with this card
	 */
	public MoveCard(String message, String icon, String choice) {
		super("MoveToDeal/Buyer", message, icon, choice, 0);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Player p moves to the nearest DealTile
	 * @param p The player that drew the card
	 * @param tiles the tiles of the board
	 */
	public void performAction(player p, tile[] tiles) {
		
		int steps = 0;
		for(int i = p.getPosition(); i < 31; i++) {
			if(tiles[i].getName().equals("BuyerTile") || tiles[i].getName().equals("DealTile")){
				p.movetoDeal(steps);
				return;
			}
			steps++;
		}
	}
}
