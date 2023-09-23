package model.Player;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Cards.DealCard;
import model.dice.dice;

/**
 * This class represents a Player
 * @author csd4281
 *
 */
public class player {
	
	private String name;
	private int money, loan, bill;
	private dice Dice;
	private int day;   //The day variable stands for the position of the player
	private ArrayList<DealCard> MyDealCards;
	private boolean turn, finished;
	private player opponent; //This attribute will make some transactions between the two players easier
	private int monthsLeft;
	private boolean actionLeft;
	
	/**Constructor
	 * Creates a new instance of player object and initializes all its fields
	 * 
	 * @pre None
	 * @post A new player is created
	 * @param name The name of the player
	 */
	public player(String name){
		
		this.name = name;
		setLoan(0);
		setBill(0);
		Dice = new dice();
		setStartPoint();
		finished = false;
		monthsLeft = 0;
		MyDealCards = new ArrayList<DealCard>();
		setActionLeft(false);
	}
	
	/**Accessor
	 * 
	 * @pre None
	 * @post The name of the player is returned
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**Transformer
	 * Adds money to the player
	 * 
	 * @pre money > 0
	 * @post The money field is updated
	 * @param money The amount that will be added 
	 */
	public void setMoney(int money) {
		this.money += money;
	}
	
	/**Accessor
	 * Gets the value of money variable
	 * 
	 * @pre None
	 * @post The value of money variable is returned
	 * @return the value of money 
	 */
	public int getMoney() {
		return money;
	}
	
	/**Transformer
	 * 
	 * @pre money > 0
	 * @Post The player pays the money
	 * @param money The amount that will be paid
	 */
	public void pay(int money) {
		/*If player doesn't have enough money to pay, they automatically get a loan*/
		if(money > this.money) {
			int loan = ((money - this.money )/1000 +1) *1000;
			JOptionPane.showMessageDialog(null, this.getName() + " Automatically got a loan of : " + loan + " Euros");
			setMoney(loan);
			setLoan(loan);
		}
		this.money -= money;
	}

	/**Transformer
	 * Increases the loan of a player
	 * 
	 * @pre None
	 * @post The loan field is updated
	 * @param loan The amount that will be added
	 */
	
	public void setLoan(int loan) {
		this.loan += loan;
	}
	
	/**Transformer
	 * The player pays some loan
	 * 
	 * @pre amount is a multiple of 1000
	 * @post An amount of the player's loan is payed (or all of it)
	 * @param amount The amount of loan to be payed
	 */
	public void payLoan(int amount) {
		pay(amount);
		loan -= amount;
	}
	
	/**Transformer
	 * The player pays 10% interest of his loan
	 * 
	 * @pre none
	 * @post Player pays interest and gets asked if they want to pay their loan (or some of it) right now
	 */
	public void payInterest() {
		if(loan == 0)
			return;
		pay((loan *10) /100);
		
		int answer = (int) JOptionPane.showConfirmDialog(null,getName() + " Do you want to pay some of your remaining loan ?", "Pay loan", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			
			Integer[] options = new Integer[getLoan()/1000];
			int j = 0;
			for(int i = getLoan(); i > 0; i-=1000) {
				options[j] = i;
				j++;
			}
			int amount = (int) JOptionPane.showInputDialog(null, "Select how much money you'd like to pay", "Pay loan", JOptionPane.QUESTION_MESSAGE, null, options, "");
			payLoan(amount);
		}
	}
	
	/**Accessor
	 * Gets the value of loan variable
	 * 
	 * @post The value of loan variable is returned
	 * @return The value of loan 
	 */
	public int getLoan() {
		return loan;
	}
	
	/**Transformer
	 * Increases the bills of a player
	 * 
	 * @pre bill > 0
	 * @post The bill field is updated
	 * @param bill The amount that will be added
	 */
	public void setBill(int bill) {
		this.bill += bill;
	}
	
	/**Transfoermer
	 * Pays the bills of the player
	 * 
	 * @pre None
	 * @post The player's bills are payed
	 */
	public void payBills() {
		pay(bill);
		bill = 0;
	}
	
	/**Accessor
	 * Gets the value of bill variable
	 * 
	 * @post The value of bill variable is returned
	 * @return The value of bill
	 */
	public int getBill() {
		return bill;
	}
	
	/**Transformer
	 * 
	 * @pre Either the game just started or both players finished and we move to the next month
	 * @post The player's position is set to START
	 */
	public void setStartPoint() {
		day = 0;
	}
	
	/**Transformer
	 * The player rolls the dice and move accordingly
	 * 
	 * @pre None
	 * @post The player's positions is updated
	 */
	public void move() {
		if(Dice.getRoll() + day > 31)
			day = 31;
		else
			day += Dice.getRoll();
		
		if(day % 7 == 0) {
			SundayMatch();
		}
		else if(day ==  4 || day == 11 || day == 18 || day == 25) {
			ThursdayCrypto();
		}
	}
	/**Transformer
	 * This functions is used only when the player draws a MoveCard
	 * and it helps with the implementation of MoveCard action
	 * 
	 * @pre a Deal or Buyer Tile exists ahead
	 * @post The player is moved to that tile
	 * @param steps The number of steps to reach the tile
	 */
	public void movetoDeal(int steps) {
		day += steps;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The player's dice is returned
	 * @return The dice of the player
	 */
	public dice getDice() {
		return Dice;
	}
	
	/**Accessor
	 * Returns the day(position) that the players is located 
	 * 
	 * @pre None
	 * @post The player's current position is returned
	 * @return The player's position
	 */
	public int getPosition() {
		return day;
	}
		
	/**Transformer
	 * Starts or ends the player's turn
	 * 
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	/**Observer
	 * 
	 * @pre none
	 * @post The value of turn variable is returned
	 * @return If its the player's turn or not
	 */
	public boolean getTurn() {
		return turn;
	}
	
	/**Accessor
	 * 
	 * @return If the player has reached PayDay or not
	 */
	public boolean hasFinished() {
		return finished;
	}
	
	/**Transformer
	 * Sets the value of finished variable to true
	 * 
	 * @pre player has reached payday
	 * @post The finished variable is set to true
	 */
	public void setFinished() {
		finished = true;
	}
	
	/**Transformer
	 * Adds a new card to the player's deal cards
	 * 
	 * @post A new deal card is added
	 * @param card The new card to be added
	 */
	public void TakeDealCard(DealCard card) {
		MyDealCards.add(card);
	}
	
	/**Transformer
	 * Sells a deal card from the player's card stack
	 * 
	 * @post A card is removed from the array list
	 * @param card The card that will be sold
	 */
	public void SellDealCard(DealCard card) {
		this.setMoney(card.getValue());
		MyDealCards.remove(card);
	}
	
	/**Accessor
	 * The player can view the dealCards in their possession at any time
	 * 
	 * @pre None
	 * @post The player views their deal cards
	 * @return The Deal cards that the player currently owns
	 */
	public ArrayList<DealCard> viewMyDealCards(){
		return MyDealCards;
	}
	
	/**Transformer
	 * Sets the opponent of the player
	 * 
	 * @pre None
	 * @post The player's opponent is set
	 * @param opponent The opponent of the player
	 */
	public void setOpponent(player opponent) {
		this.opponent = opponent;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The player's opponent is returned
	 * @return The player's opponent
	 */
	public player getOpponent() {
		return opponent;
	}
	
	/**Transformer
	 * The player bets on a match if they want and they can either win or lose some money
	 * 
	 * @pre  None
	 * @post The player's balance is changed 
	 */
	public void SundayMatch() {
		String[] options = {"Barcelona will win", "Draw", "Real will win", "Skip bet"};
		Image image = new ImageIcon(this.getClass().getClassLoader().getResource("images/Barcelona_Real.jpg")).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		int choice = JOptionPane.showOptionDialog(null, "Bet 500 euros on who will win and get double as much!", "Sunday Football Match", 
													JOptionPane.DEFAULT_OPTION, 1, new ImageIcon(image), options, null);
		
		Random r = new Random();
		int result = r.nextInt(2);
		
		if(choice == 3 || choice == -1)
			return;
		if(result == choice) {
			JOptionPane.showOptionDialog(null, "Congratulations you won!!!", "Sunday Football Match", 
					JOptionPane.OK_OPTION, 1, new ImageIcon(image), new String[] {"Won 1000 Euros"}, null);
			setMoney(500);
		}else {
			JOptionPane.showOptionDialog(null, "Oof you lost! Better luck next time!", "Sunday Football Match", 
					JOptionPane.OK_OPTION, 0, new ImageIcon(image), new String[] {"Lost 500 Euros"}, null);
			pay(500);
		}	
	}
	
	/**Transformer
	 * The player bets on a crypto if they want and the can win, take their money back or lose some money
	 * 
	 * @pre None
	 * @post The player's balance is changed 
	 */
	public void ThursdayCrypto() {
		String[] options = {"Bet on crypto", "Skip bet"};
		Image image = new ImageIcon(this.getClass().getClassLoader().getResource("images/crypto.jpeg")).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		int choice = JOptionPane.showOptionDialog(null, "Bet 300 euros on Crypto?", "Crypto Thursday", 
													JOptionPane.DEFAULT_OPTION, 1, new ImageIcon(image), options, null);
		
		Random r = new Random();
		int result = r.nextInt(2);
		
		if(choice == 1 || choice == -1)
			return;
		if(result == 0) {
			JOptionPane.showOptionDialog(null, "Crypto's value unfortunately fell", "Crypto Thursday", 
					JOptionPane.OK_OPTION, 1, new ImageIcon(image), new String[] {"Lost 300 Euros"}, null);
			pay(300);
		}else if(result == 1){
			JOptionPane.showOptionDialog(null, "Crypto's value stayed the same", "Crypto Thursday", 
					JOptionPane.OK_OPTION, 1, new ImageIcon(image), new String[] {"Got your money back"}, null);
		}else {
			JOptionPane.showOptionDialog(null, "Crypto's value stayed the same", "Crypto Thursday", 
					JOptionPane.OK_OPTION, 1, new ImageIcon(image), new String[] {"Won 600 Euros"}, null);
			setMoney(300);
		}
	}
	/**Transformer
	 * 
	 * @pre None
	 * @post the value of monthsLeft variable is updated
	 * @param x 
	 */
	public void setMonthsLeft(int x) {
		monthsLeft += x;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post The value of monthsLeft variable is returned
	 * @return how many months of game are left
	 */
	public int getMonthsLeft() {
		return monthsLeft;
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post The value of actionLeft variable is set
	 * @param b the value that actionLeft variable will take
	 */
	public void setActionLeft(boolean b) {
		actionLeft = b;
	}
	
	/**Accessor
	 * 
	 * @pre none
	 * @post Returns if the player has any actions left to do or not
	 * @return true if the player has any actions left to do or false otherwise
	 */
	public boolean getActionLeft() {
		return actionLeft;
	}
}
