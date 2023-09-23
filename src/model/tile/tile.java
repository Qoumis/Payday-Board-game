package model.tile;

import model.Player.player;

/**
 * This class represents a tile (of any type) of the board
 * @author csd4281
 *
 */
public abstract class tile {
	
	private String name;
	private int day; //This stands for the position on the board 
	private String image;
		
	/**This is a method that all tiles should implement
	 * and perform a certain action that is indicated
	 * 
	 * @post The player performs an action
	 * @param p The player that stepped on the tile 
	 */
	public abstract void performAction(player p);
	
	/**Transformer 
	 * 
	 * @pre none
	 * @Post The name of the tile is set
	 * @param name The name of the tile
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**Accessor
	 * Returns the name of the tile
	 * 
	 * pre none
	 * @post The name of the tile is returned
	 * @return The name of the tile
	 */
	public String getName() {
		return name;
	}
	
	/**Transformer
	 * 
	 * @pre day is in range 0 to 31
	 * @post The number (position) of the tile is set
	 * @param day The number of the tile
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**Accessor
	 * Returns the number (position) of the tile
	 * 
	 * @pre None
	 * @post The value of day variable is returned
	 * @return the value of day
	 */
	public int getDay() {
		return day;
	}
	
	/**Transformer
	 * 
	 * @pre The image path exists
	 * @post The tile's image URL is set
	 * @param image The path for tile's image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The tile's image path is returned
	 * @return The path of tile's image
	 */
	public String getImage() {
		return image;
	}

}
