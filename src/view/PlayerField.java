package view;

import  view.View;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cards.DealCard;
import model.Player.player;
import model.tile.*;

public class PlayerField extends JPanel{

	private JTextField PlayerName, Money, Loan, Bills;
	private JLabel Dice;
	private JButton RollDice, myDealCards, GetLoan, EndTurn;
	private ClassLoader cldr;
	private URL imageURL;  
	private Image image; 
	private player Player;
	
	
	/**Constructor
	 * Creates a new PlayerField area
	 * 
	 * @pre none
	 * @post A player area is created and all buttons and text fields are initialized
	 */
	public PlayerField(player p) {
		cldr = this.getClass().getClassLoader();
		Player = p;
		PlayerName = new JTextField(Player.getName());
		Money = new JTextField();
		Loan = new JTextField();
		Bills = new JTextField();
		Dice = new JLabel();
		RollDice = new JButton();
		myDealCards = new JButton();
		GetLoan = new JButton();
		EndTurn = new JButton();
		
		initComponents();
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post initializes some buttons and text fields
	 */
	public void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel components = new JPanel();
		components.setLayout(new BoxLayout(components, BoxLayout.Y_AXIS));
		
		PlayerName.setEditable(false);
		PlayerName.setBorder(null);
		PlayerName.setFont(new Font(null, Font.BOLD, 15));
		components.add(PlayerName);
		
		Money.setEditable(false);
		Money.setBorder(null);
		Money.setText("Money : " + Player.getMoney() +" Euros");
		components.add(Money);
		
		Loan.setEditable(false);
		Loan.setBorder(null);
		Loan.setText("Loan : "+ Player.getLoan() +" Euros");
		components.add(Loan);
		
		Bills.setEditable(false);
		Bills.setBorder(null);
		Bills.setText("Bills : "+ Player.getBill() +" Euros");
		components.add(Bills);
		
		RollDice.setText("Roll Dice");
		RollDice.addActionListener(new DiceListener());
		components.add(RollDice);
		
		myDealCards.setText("My Deal Cards");
		myDealCards.addActionListener(new myDealCardsListener());
		components.add(myDealCards);
		
		GetLoan.setText("Get Loan");
		GetLoan.addActionListener(new GetLoanListener());
		EndTurn.setText("End Turn");
		EndTurn.addActionListener(new EndTurnListener());
		
		JPanel components1 = new JPanel();
		components1.setLayout(new BoxLayout(components1, BoxLayout.X_AXIS));
		components1.add(GetLoan);
		components1.add(EndTurn);
		
		this.add(components, BorderLayout.BEFORE_LINE_BEGINS);
		this.add(Dice, BorderLayout.EAST);
		this.add(components1, BorderLayout.PAGE_END);
		
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Updates all info about a player's money, loan, bills
	 */
	public void updateTextInfo() {
		Money.setText("Money : " + Player.getMoney() +" Euros");
		Loan.setText("Loan : "+ Player.getLoan() +" Euros");
		Bills.setText("Bills : "+ Player.getBill() +" Euros");
	}
	
	/* A class used to do some action after rollDice button has been pressed*/
	private class DiceListener implements ActionListener {
		
		int roll1 = 0;
		
		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after rollDice button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
		
			if(!Player.getTurn()) {
				View.UpdateInfoBox(" Haha not your turn");
				return;
			}
			
			tile Tile = View.Controller.board.tiles[Player.getPosition()];
			
			if(!(Player.getDice().isRolled())) { //Regular roll
				int old = Player.getPosition();
				Player.getDice().rollDice();
				Player.move();
				imageURL = cldr.getResource("images/dice-" + Player.getDice().getRoll() + ".jpg");
				image = new ImageIcon(imageURL).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				Dice.setIcon(new ImageIcon(image));
				View.movePawn(Player, old);
				
				if(Player.getDice().getRoll() == 6) {
					View.UpdateInfoBox(Player.getName() + " has won the jackpot and got " + View.Controller.Jackpot.getBalance() + " Euros!!!");
					View.Controller.Jackpot.winJackpot(Player);
					View.UpdateJackpot();
				}
			
			}else if((Tile.getName() == "SweepstakesTile" || Tile.getName() == "YardSaleTile") && Player.getActionLeft()) { //Roll for sweep or yardsale
				Player.getDice().rollDice();
				imageURL = cldr.getResource("images/dice-" + Player.getDice().getRoll() + ".jpg");
				image = new ImageIcon(imageURL).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				Dice.setIcon(new ImageIcon(image));
				Tile.performAction(Player);
				Player.setActionLeft(false);
				
				if(Tile.getName() == "YardSaleTile") {
					if(View.Controller.DealStack.isEmpty())
						View.Controller.DealStack.reconstruct();
					DealCard card = View.Controller.DealStack.DrawCard();
					
					String[] options = {"AWSOME"};
					image = new ImageIcon(cldr.getResource(card.getIcon())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					JOptionPane.showOptionDialog(null, card.getMessage() + "\n Buy value: " + card.getCost() + " Sell value: " + card.getValue() + "\n And you bought it for just " + Player.getDice().getRoll() *100 + " Euros"
					, card.getType(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
					Player.TakeDealCard(card);
				}
				
			}else if(Tile.getName() == "RadioTile" && Player.getActionLeft()) {										 //Roll for Radio
				Player.getDice().rollDice();
				imageURL = cldr.getResource("images/dice-" + Player.getDice().getRoll() + ".jpg");
				image = new ImageIcon(imageURL).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				Dice.setIcon(new ImageIcon(image));
				
				if(roll1 == 0) 
					roll1 = Player.getDice().getRoll();
				else {
					if(roll1 > Player.getDice().getRoll()) {
						Tile.performAction(Player);
						Player.setActionLeft(false);
					}else if(roll1 < Player.getDice().getRoll()) {
						Tile.performAction(Player.getOpponent());
						Player.setActionLeft(false);
					}
					roll1 = 0;
				}
			}else
				View.UpdateInfoBox(" You already rolled this turn!");
			
			updateTextInfo();
		}
	}
	/* A class used to do some action after myDealCards button has been pressed*/
	private class myDealCardsListener implements ActionListener{

		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after myDealCards button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			ArrayList<DealCard> myDealCards = Player.viewMyDealCards();
			
			if(myDealCards.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You dont own any deal cards " + Player.getName());
				return;
			}
			
			String[] options = {"NEXT"};
			DealCard card;
			for(int i = 0; i < myDealCards.size(); i++) {
				card = myDealCards.get(i);
				image = new ImageIcon(cldr.getResource(card.getIcon())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				JOptionPane.showOptionDialog(null, card.getMessage() + "\n Buy value: " + card.getCost() + " Sell value: " + card.getValue()
				, "Deal card of : "+ Player.getName(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
			}
			
		}
	}
	
	/* A class used to do some action after GetLoan button has been pressed*/
	private class GetLoanListener implements ActionListener{

		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after GetLoan button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			if(!Player.getTurn()) {
				View.UpdateInfoBox(" Haha not your turn");
				return;
			}
			Integer[] options = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
			
			Integer loan = (Integer) JOptionPane.showInputDialog(null, "How much money do you want " + Player.getName() + " ?", "Get Loan", JOptionPane.QUESTION_MESSAGE, null, options, "");
			Player.setLoan(loan);
			Player.setMoney(loan);
			View.UpdateInfoBox(Player.getName() + " got a loan of " + loan + " Euros");
			updateTextInfo();
		}
	}
	
	/* A class used to do some action after EndTurn button has been pressed*/
	private class EndTurnListener implements ActionListener{

		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after EndTurn button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			if(!Player.getTurn()) {
				View.UpdateInfoBox(" Its not even your turn lol");
				return;
			}
			
			if(!Player.getDice().isRolled())
				View.UpdateInfoBox(" Wait you haven't even rolled yet");
			else if(Player.getActionLeft())
				View.UpdateInfoBox(" Wait you have some actions left to do");
			else {
				if(Player.getOpponent().hasFinished()) {
					Player.getDice().rollNext();
					View.UpdateInfoBox(" your turn again: Roll the dice");
				}else {
					Player.getDice().rollNext();
					Player.setTurn(false);
					Player.getOpponent().setTurn(true);
					View.UpdateInfoBox(" Roll the dice");
				}

			}
		}
	}
}
