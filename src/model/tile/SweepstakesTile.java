package model.tile;

import javax.swing.JOptionPane;

import model.Player.player;

public class SweepstakesTile extends tile{
	
	/**Constructor
	 * Crates an instance of SweepstakesTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of SweepstakesTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public SweepstakesTile(int day, String image) {
		this.setName("SweepstakesTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * 
	 * @pre None
	 * @post The player rolls the dice and gets some money
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		p.setMoney(p.getDice().getRoll() * 1000);
		JOptionPane.showMessageDialog(null, p.getName() + " got " + p.getDice().getRoll() * 1000 + "Euros from Sweepstakes");
	}

}
