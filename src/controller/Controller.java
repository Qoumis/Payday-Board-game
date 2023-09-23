package controller;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Board.Board;
import model.Deck.DealDeck;
import model.Deck.MailDeck;
import model.Player.player;
import model.jackpot.jackpot;

/**
 * 
 * @author csd4281
 *
 */
public class Controller {
	
	public player P1, P2;
	public Board board;
	public jackpot Jackpot;
	public DealDeck DealStack;
	public MailDeck MailStack;
	private int months;
	private Image image;
	
	
	/**Constructor
	 * Creates a new controller so that the game
	 * is eligible to start
	 * 
	 * @pre None
	 * @post Constructs a new controller ,with 2 player, with a new board, with a new game window,
	 * 		 with a new jackpot, initializes all the cards in the decks and initializes some int 
	 * 		 and boolean variables. In other words it crates and initializes a new game
	 */
	public Controller() {
		SetMonths();
		initPlayers();
		
		board = new Board();
		P1.setMoney(3500);
		P2.setMoney(3500);
		P1.setOpponent(P2);
		P2.setOpponent(P1);
		P1.setMonthsLeft(months);
		P2.setMonthsLeft(months);
		Jackpot = new jackpot();
		MailStack = new MailDeck();
		DealStack = new DealDeck();
		SetStartingPlayer();
		
	}
	
	/**Transformer
	 * Sets how many months the game will last
	 * 
	 * @pre The game just started
	 * @post The game's months are set
	 */
	public void SetMonths() {
		Integer [] options = {1,2,3};
		image = new ImageIcon(this.getClass().getClassLoader().getResource("images/logo.png")).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		
		months = (Integer) JOptionPane.showInputDialog(null, "Choose how many months you'd like to play:", "Select How long the game will last", JOptionPane.QUESTION_MESSAGE, new ImageIcon(image), options, "");
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post the value of months variable is updated
	 */
	public void updateMonths(int months) {
		this.months = months;
	}
	
	/**Transformer
	 * 
	 * @pre the game just started 
	 * @post The names of players are set
	 */
	public void initPlayers() {	
		image = new ImageIcon(this.getClass().getClassLoader().getResource("images/logo.png")).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		String name = (String) JOptionPane.showInputDialog(null, "Enter first player's name", "Enter a name", JOptionPane.QUESTION_MESSAGE, new ImageIcon(image), null, "");
		P1 = new player(name);
		name = (String) JOptionPane.showInputDialog(null, "Enter second player's name", "Enter a name", JOptionPane.QUESTION_MESSAGE, new ImageIcon(image), null, "");
		P2 = new player(name);
	}
	
	/**Observer
	 * Returns how many months are left
	 * 
	 * @pre none
	 * @post The value of months variable is returned
	 * @return how many months are left
	 */
	public int getMonths() {
		return months;
	}
	
	/**Transformer
	 * 
	 * @pre The game just started
	 * @post Randomly picks a player to play first
	 */
	public void SetStartingPlayer() {
		Random r = new Random();
		if((r.nextInt(100) + 1) <= 50) {
			P1.setTurn(true);
			P2.setTurn(false);
		}
		else {
			P1.setTurn(false);
			P2.setTurn(true);
		}		
	}
	
	/**Accessor
	 * We need that function to updade the infoBox
	 * 
	 * @pre none
	 * @post The player that plays now is returned
	 * @return The player that plays right now
	 */
	public player getPlayerTurn() {
		if(P1.getTurn())
			return P1;
		return P2;
	}
		
	/**Observer
	 * 
	 * @pre none
	 * @post Returns if the game is finished or not
	 * @return True if the game is finished, false otherwise
	 */
	public boolean gameFinished() {
		return P1.hasFinished() && P2.hasFinished();
	}
	
	/**Accessor
	 * 
	 * @pre The game has finished
	 * @post The game's winner is returned
	 */
	public void gameWinner() {
		
		if((P1.getMoney() - P1.getLoan()) > (P2.getMoney() - P2.getLoan())) 
			JOptionPane.showMessageDialog(null, P1.getName() + " is the winner of the game! Congratulations!!!");
		else if((P1.getMoney() - P1.getLoan()) < (P2.getMoney() - P2.getLoan()))
			JOptionPane.showMessageDialog(null, P2.getName() + " is the winner of the game! Congratulations!!!");
		else
			JOptionPane.showMessageDialog(null,"Game ended on a draw! What are the odds?!?");
	}
	
	/**
	 * Saves the current game's data in a file
	 *
	 *@pre The game is running
	 *@post The game's data is saved 
	 */
	public void SaveGame() {
		//Den tha kanw to bonus telika
	}
}
