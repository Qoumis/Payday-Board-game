package model.tile;

import model.Player.player;

public class YardSaleTile extends tile{
	
	/**Constructor
	 * Crates an instance of YardSaleTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of YardSaleTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public YardSaleTile(int day, String image) {
		this.setName("YardSaleTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * The player is able to draw a DealCard for some fee 
	 * 
	 * @pre None
	 * @post The player pays some money and draws a DealCard
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		p.pay(p.getDice().getRoll() *100);

	}

}
