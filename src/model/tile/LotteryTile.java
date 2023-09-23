package model.tile;

import java.util.Random;

import javax.swing.JOptionPane;

import model.Player.player;

public class LotteryTile extends tile{

	/**Constructor
	 * Crates an instance of LotteryTile object
	 * 
	 * @pre image should be a valid path
	 * @post An instance of LotteryTile is created
	 * @param day   The number (position) of the tile
	 * @param image The path for the image of the tile
	 */
	public LotteryTile(int day, String image) {
		this.setName("LotteryTile");
		this.setDay(day);
		this.setImage(image);
	}
	
	/**Transformer
	 * Both players participate on a challenge and the winner takes some money
	 * 
	 * @pre None
	 * @post The winner takes some money
	 * @param p The player that stepped on the tile
	 */
	public void performAction(player p) {
		Integer options[] = {1,2,3,4,5,6};
		int choice[] = new int[2];
		
		choice[0] = (int) JOptionPane.showInputDialog(null, p.getName() + " Chose a number ","Lottery", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		Integer options1[] = new Integer[5];
		int j = 0;
		for(int i = 1; i <= 6; i++) {
			if(i != choice[0]) {
				options1[j] = i;
				j++;
			}
		}
		choice[1] = (int) JOptionPane.showInputDialog(null, p.getOpponent().getName() + " Chose a number ","Lottery", JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
		
		Random r = new Random();
		int x = 0;
		while(x != choice[0] && x != choice[1]) {
			x = r.nextInt(6) +1;
			if(x == choice[0]) {
				JOptionPane.showMessageDialog(null, p.getName() + " won the lottery and got 1000 Euros");
				p.setMoney(1000);
			}else if(x == choice[1]) {
				JOptionPane.showMessageDialog(null, p.getOpponent().getName() + " won the lottery and got 1000 Euros");
				p.getOpponent().setMoney(1000);
			}
		}
	}
	
}
