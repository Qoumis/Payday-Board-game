package model.tile;

import model.Player.player;

public class PayDayTile extends tile{

	/**Constructor
	 * Crates an instance of PayDayTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of PayDayTile is created
	 * @param image The path for the image of the tile
	 */
	public PayDayTile(String image) {
		this.setName("PayDayTile");
		this.setDay(31);      //Standard position
		this.setImage(image);
	}
	
	/**Transformer
	 * The player performs a series of actions depending 
	 * on his loans and bills and on how many months are left
	 * 
	 * @pre None
	 * @post The player performs some actions
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		p.setMoney(3500);
		p.payBills();
		p.payInterest();
		p.setMonthsLeft(-1);
		
		if(p.getMonthsLeft() > 0)
			p.setStartPoint();
		else
			p.setFinished();
		
	}

}
