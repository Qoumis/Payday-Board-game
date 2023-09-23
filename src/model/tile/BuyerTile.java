package model.tile;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Cards.DealCard;
import model.Player.player;

public class BuyerTile extends tile{
	
	/**Constructor
	 * Crates an instance of BuyerTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of BuyerTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public BuyerTile(int day, String image) {
		this.setName("BuyerTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * The player is able to choose a dealCard to sell
	 * 
	 * @pre None
	 * @post The player sells one DealCard
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		ArrayList<DealCard> myDealCards = p.viewMyDealCards();
		Image image; 
		
		String[] options = {"NEXT", "SELL"};
		DealCard card;
		int i = 0;
		while(i < myDealCards.size() ) {
			card = myDealCards.get(i);
			image = new ImageIcon(this.getClass().getClassLoader().getResource(card.getIcon())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			
			int choice = JOptionPane.showOptionDialog(null, card.getMessage() + "\n Buy value: " + card.getCost() + " Sell value: " + card.getValue()
			, "Deal card of : "+ p.getName(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
			
			if(choice == 1) {
				p.SellDealCard(card);
				return;
			}
			i++;
			if(i == myDealCards.size())
				i = 0;
		}
		
	}

}
