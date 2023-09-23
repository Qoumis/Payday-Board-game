package model.tile;

import javax.swing.JOptionPane;

import model.Player.player;
import model.jackpot.jackpot;

public class FamilyCasinoTile extends tile{
	
	/**Constructor
	 * Crates an instance of FamilyCasinoTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of FamilyCasinoTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public FamilyCasinoTile(int day, String image) {
		this.setName("FamilyCasinoTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * The player gives or takes some money depending on the number they rolled
	 * 
	 * @pre None
	 * @post The player's balance is changed
	 * @param p The player that stepped on the tile
	 * @param j The jackpot of the game
	 */
	public void performAction(player p, jackpot j) {
		if(p.getDice().getRoll() % 2 == 0) {
			p.setMoney(500);
		JOptionPane.showMessageDialog(null, p.getName() + " Rolled an even number and got 500 Euros");
		}else {
			JOptionPane.showMessageDialog(null, p.getName() + " Rolled an odd number and payed 500 Euros to jackpot");
			p.pay(500);
			j.addToJackpot(500);
		}
	}

	@Override
	public void performAction(player p) {
		// TODO Auto-generated method stub
		
	}
}
