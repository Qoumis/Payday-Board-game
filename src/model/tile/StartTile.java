package model.tile;

import model.Player.player;

public class StartTile extends tile{
	/**Constructor
	 * Crates an instance of StartTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of StartTile is created
	 * @param image The path for the image of the tile
	 */
	public StartTile(String image) {
		this.setName("StartTile");
		this.setDay(0);       //Standard position
		this.setImage(image);
	}
	
	/*There are no actions to actually perform here*/
	public void performAction(player p) {}
}
