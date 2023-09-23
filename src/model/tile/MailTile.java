package model.tile;

import model.Player.player;

public class MailTile extends tile{

	private final int DrawNumber;
	
	/**Constructor
	 * Creates a new instance of a MailTile object
	 * 
	 * @pre Image should be a valid path, Draw should be either 1 or 2
	 * @post An instance of MailTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 * @param DrawNumber  The number of cards to be drawn
	 */
	public MailTile(int day, String image, int DrawNumber) {
		this.setName("MailTile");
		this.setDay(day);
		this.setImage(image);
		this.DrawNumber = DrawNumber;
	}
	
	/**Accessor
	 * 
	 * @pre None
	 * @post The number of cards to be drawn is returned
	 * @return The value of Draw variable
	 */
	public int getDrawNumber() {
		return DrawNumber;
	}
	
	/**Transformer
	 * The player draws DrawNumber cards
	 * 
	 * @pre None
	 * @post The player draws some cards and then (maybe) performs 
	 * some actions indicated by the cards
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		//Den uparxei kati na kanw edw kathos oti xriazetai ginetai stin klasi cardListener
		
	}
}
