package model.tile;

import javax.swing.JOptionPane;

import model.Player.player;

public class RadioTile extends tile{
	
	/**Constructor
	 * Crates an instance of RadioTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of RadioTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public RadioTile(int day, String image) {
		this.setName("RadioTile");
		this.setDay(day);
		this.setImage(image);

	}
	
	/**Transformer
	 * Both players roll, the player with the higher roll take some money
	 * 
	 * @pre None
	 * @post The winner takes some money
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		p.setMoney(1000);
		JOptionPane.showMessageDialog(null, p.getName() + " won the Radio contest and got 1000 Euros");
	}
}
