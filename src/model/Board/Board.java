package model.Board;

import java.util.ArrayList;
import java.util.Random;

import model.tile.BuyerTile;
import model.tile.DealTile;
import model.tile.FamilyCasinoTile;
import model.tile.LotteryTile;
import model.tile.MailTile;
import model.tile.PayDayTile;
import model.tile.RadioTile;
import model.tile.StartTile;
import model.tile.SweepstakesTile;
import model.tile.YardSaleTile;
import model.tile.tile;

/**This class represents the Board of the game
 * 
 * @author csd4281
 *
 */
public class Board {

	public tile[] tiles = new tile[32]; //All the blocks of the board (one for each day + the start tile)
	
	/**Constructor
	 * Crates a new Board object
	 * 
	 * @pre None
	 * @post A new game Board is created
	 */
	public Board() {
		
		initBoard();
	}
	
	/**Transformer
	 * Initializes the game's board
	 * 
	 * @pre none
	 * @post All the tiles of the board are initialized
	 */
	public void initBoard() {
		RandomPosition r = new RandomPosition();
		
		tiles[0]  = new StartTile("images/start.png");
		tiles[31] = new PayDayTile("images/pay.png");
		
		int i = 0 ,j;
		while(i < 8) {
			j = r.getPosition();
			tiles[j] = new MailTile(j, "images/mc1.png", 1);
			j = r.getPosition();
			tiles[j] = new MailTile(j, "images/mc2.png", 2);
			i+= 2;
		}
		while(i < 13) {
			j = r.getPosition();
			tiles[j] = new DealTile(j, "images/deal.png");
			i++;
		}
		while(i < 15) {
			j = r.getPosition();
			tiles[j] = new SweepstakesTile(j, "images/sweep.png");
			i++;
		}
		while(i < 18) {
			j = r.getPosition();
			tiles[j] = new LotteryTile(j, "images/lottery.png");
			i++;
		}
		while(i < 20) {
			j = r.getPosition();
			tiles[j] = new RadioTile(j, "images/radio.png");
			i++;
		}
		while(i < 26) {
			j = r.getPosition();
			tiles[j] = new BuyerTile(j, "images/buyer.png");
			i++;
		}
		while(i < 28) {
			j = r.getPosition();
			tiles[j] = new FamilyCasinoTile(j, "images/casino.png");
			i++;
		}
		while(i < 30) {
			j = r.getPosition();
			tiles[j] = new YardSaleTile(j, "images/yard.png");
			i++;
		}
	}
}


/**This class is used to get a random number in range 1-30
 * 
 * @author csd4281
 *
 */
class RandomPosition{
	
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	/**Constructor
	 * Creates a new RandomPosition object
	 * 
	 * @pre none
	 * @post a new RandomPosition object is created and initialized
	 */
	public RandomPosition() {
		for(int i = 1; i <= 30; i++) {
			list.add(i);
		}
	}
	
	/**Transformer
	 * Returns and removes a random number from the list
	 * 
	 * @return a random number from the list
	 */
	public int getPosition() {
		Random r = new Random();
		return list.remove(r.nextInt(list.size()));
	}
}