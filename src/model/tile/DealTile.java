package model.tile;

import model.Player.player;

public class DealTile extends tile{
	
	/**Constructor
	 * Crates an instance of DealTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of DealTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public DealTile(int day, String image) {
		this.setName("DealTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The player draws a deal card
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		//Den uparxei kati na kanw edw kathos oti xriazetai ginetai stin klasi cardListener
	}
	
	
}
