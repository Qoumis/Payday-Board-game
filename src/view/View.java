package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
import model.Cards.CharityCard;
import model.Cards.DealCard;
import model.Cards.MailCard;
import model.Cards.MoveCard;
import model.Player.player;
import model.tile.*;

public class View extends JFrame{

	static Controller Controller;   
	private ClassLoader cldr; 
	private URL imageURL;    
	private Image image;
	
	
	private JLayeredPane Background;
	private JTextArea InfoBox;
	private static JTextField monthsLeft;
	private static JTextField playerTurn;
	private static JTextField currAction;
	private JButton MailCardStack , DealCardStack;
	private PlayerField Player1, Player2;
	private JLabel[] tiles;
	private static JLabel PlayerPawn1, PlayerPawn2;
	private static JPanel[] pawnPositions;
	private JLabel Jackpot, PayDayLogo;
	private static JTextArea jackpotText;
	
	/**Constructor 
	 * 
	 * @pre none
	 * @post Creates a new Window and initializes some buttons and panels
	 * starting a new game.
	 */
	public View() {
		Controller = new Controller();
		cldr = this.getClass().getClassLoader();
		
		Background = new JLayeredPane();
		InfoBox = new JTextArea();
		monthsLeft = new JTextField();
		playerTurn = new JTextField();
		currAction = new JTextField();
		MailCardStack = new JButton();
		DealCardStack = new JButton();
		Player1 = new PlayerField(Controller.P1);
		Player2 = new PlayerField(Controller.P2);
		tiles = new JLabel[32];
		pawnPositions = new JPanel[32];
		PlayerPawn1 = new JLabel();
		PlayerPawn2 = new JLabel();
		Jackpot = new JLabel();
		PayDayLogo = new JLabel();
		jackpotText = new JTextArea();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("PayDay BoardGame");
        this.setBounds(400, 100, 1440,900);
		imageURL = cldr.getResource("images/logo.png");
		this.setIconImage(new ImageIcon(imageURL).getImage());
		 
		initComponents();
		this.setVisible(true);
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post initializes some buttons and components
	 */
	public void initComponents() {

/*---------------------Background and Logo---------------------------------*/
		
		JLabel img = new JLabel();
		img.setBounds(0, 0, 1440, 900);
		PayDayLogo.setBounds(0, 0, 1058, 133);
		try {
			imageURL = cldr.getResource("images/bg_green.png");
			BufferedImage im =  ImageIO.read(imageURL);
			ImageIcon imgIcon = new ImageIcon(im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH));
			img.setIcon(imgIcon);
			
			imageURL = cldr.getResource("images/logo.png");
			im = ImageIO.read(imageURL);
			imgIcon = new ImageIcon(im.getScaledInstance(PayDayLogo.getWidth(), PayDayLogo.getHeight(), Image.SCALE_SMOOTH));
			PayDayLogo.setIcon(imgIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Background.add(img);		
		Background.setLayer(PayDayLogo, 1);
		Background.add(PayDayLogo);			

/*----------------------Player fields---------------------------*/
		
		Player1.setBounds(1100, 3, 300, 280);
		Player1.setBorder(BorderFactory.createBevelBorder(NORMAL, Color.BLUE, Color.BLUE));
		Background.setLayer(Player1,1);
		Background.add(Player1);
		
		Player2.setBounds(1100, 583, 300, 280);
		Player2.setBorder(BorderFactory.createBevelBorder(NORMAL, Color.YELLOW, Color.YELLOW));
		Background.setLayer(Player2,1);
		Background.add(Player2);
		
/*----------------------Tiles & Pawns----------------------------------*/
		JPanel brd = new JPanel();
		Background.setLayer(brd, 1);
		brd.setLayout(new GridLayout(5,7));
		brd.setBounds(0,150, 1060, 745);
		brd.setOpaque(false);
		
		JPanel brdPawn = new JPanel();
		Background.setLayer(brdPawn, 2);
		brdPawn.setLayout(new GridLayout(5,7));
		brdPawn.setBounds(0, 150, 1060, 745);
		brdPawn.setOpaque(false);
		
		for(int i = 0; i < 32; i++) {
			tiles[i] = new JLabel();
			pawnPositions[i] = new JPanel();
			
			imageURL = cldr.getResource(Controller.board.tiles[i].getImage());
			image =  new ImageIcon(imageURL).getImage().getScaledInstance(152, 100, Image.SCALE_SMOOTH);
			tiles[i].setIcon(new ImageIcon(image));
			pawnPositions[i].setOpaque(false);
			pawnPositions[i].setLayout(new GridLayout(1,2));
			
			brd.add(tiles[i]);
			brdPawn.add(pawnPositions[i]);
		}
		
		imageURL = cldr.getResource("images/pawn_blue.png");
		image =  new ImageIcon(imageURL).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		PlayerPawn1.setIcon(new ImageIcon(image));
		PlayerPawn1.setName(Controller.P1.getName());
		
		imageURL = cldr.getResource("images/pawn_yellow.png");
		image =  new ImageIcon(imageURL).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		PlayerPawn2.setIcon(new ImageIcon(image));
		PlayerPawn2.setName(Controller.P2.getName());
		
		pawnPositions[0].add(PlayerPawn1);
		pawnPositions[0].add(PlayerPawn2);
		
		Background.add(brd);
		Background.add(brdPawn);
		
/*------------------------Days on board -------------------------------*/
		
		JTextField week1 = new JTextField("Start \t \t Monday 1\t         "
				+ " Tuesday 2 \t Wednesday 3              "
				+ "Thursday 4 \t   Friday 5 \t             Saturday 6");
		week1.setFont(new Font(null, Font.BOLD, 12));
		week1.setBounds(0, 134, 1058, 39);
		week1.setBackground(Color.YELLOW);
		week1.setBorder(null);
		week1.setEditable(false);
		Background.setLayer(week1, 1);
		Background.add(week1);
		
		JTextField week2 = new JTextField("Sunday 7 \t \t Monday 8\t         "
				+ " Tuesday 9 \t Wednesday 10              "
				+ "Thursday 11 \t   Friday 12 \t             Saturday 13");
		week2.setFont(new Font(null, Font.BOLD, 12));
		week2.setBounds(0, 275, 1058, 47);
		week2.setBackground(Color.YELLOW);
		week2.setBorder(null);
		week2.setEditable(false);
		Background.setLayer(week2, 1);
		Background.add(week2);
		
		JTextField week3 = new JTextField("Sunday 14 \t \t Monday 15\t         "
				+ " Tuesday 16 \t Wednesday 17              "
				+ "Thursday 18 \t   Friday 19 \t             Saturday 20");
		week3.setFont(new Font(null, Font.BOLD, 12));
		week3.setBounds(0, 424, 1058, 47);
		week3.setBackground(Color.YELLOW);
		week3.setBorder(null);
		week3.setEditable(false);
		Background.setLayer(week3, 1);
		Background.add(week3);
		
		JTextField week4 = new JTextField("Sunday 21 \t \t Monday 22\t         "
				+ " Tuesday 23 \t Wednesday 24              "
				+ "Thursday 25 \t   Friday 26 \t             Saturday 27");
		week4.setFont(new Font(null, Font.BOLD, 12));
		week4.setBounds(0, 573, 1058, 47);
		week4.setBackground(Color.YELLOW);
		week4.setBorder(null);
		week4.setEditable(false);
		Background.setLayer(week4, 1);
		Background.add(week4);
		
		JTextField week5 = new JTextField("Sunday 28 \t \t Monday 29\t         "
				+ " Tuesday 30 \t Wednesday 31");
		week5.setFont(new Font(null, Font.BOLD, 12));
		week5.setBounds(0, 722, 605, 47);
		week5.setBackground(Color.YELLOW);
		week5.setBorder(null);
		week5.setEditable(false);
		Background.setLayer(week5, 1);
		Background.add(week5);
		
/*--------------------------------Card Stacks--------------------------------*/
		
		imageURL = cldr.getResource("images/mailCard.png");
		image =  new ImageIcon(imageURL).getImage().getScaledInstance(140, 70, Image.SCALE_SMOOTH);
		MailCardStack.setIcon(new ImageIcon(image));
		MailCardStack.setBounds(1100, 490, 140, 70);
		MailCardStack.addActionListener(new MailCardsListener());
		Background.setLayer(MailCardStack, 1);
		Background.add(MailCardStack);
		
		imageURL = cldr.getResource("images/dealCard.png");
		image =  new ImageIcon(imageURL).getImage().getScaledInstance(140, 70, Image.SCALE_SMOOTH);
		DealCardStack.setIcon(new ImageIcon(image));
		DealCardStack.setBounds(1260, 490, 140, 70);
		DealCardStack.addActionListener(new DealCardsListener());
		Background.setLayer(DealCardStack, 1);
		Background.add(DealCardStack);
		
/*-----------------------------InfoBox---------------------------------------*/
		InfoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		InfoBox.setBounds(1100, 300, 300, 170);
		InfoBox.setEditable(false);
		InfoBox.setLayout(new BoxLayout(InfoBox, BoxLayout.Y_AXIS));
		Background.setLayer(InfoBox, 1);
		
		JTextField tmp = new JTextField("\tInfo Box");
		tmp.setBorder(null);
		tmp.setFont (new Font(null, Font.BOLD, 12));
		InfoBox.add(tmp);
		
		monthsLeft.setText(Controller.getMonths() + " Months left");
		monthsLeft.setBorder(null);
		InfoBox.add(monthsLeft);
		
		playerTurn.setText("Turn: " + Controller.getPlayerTurn().getName());
		playerTurn.setBorder(null);
		InfoBox.add(playerTurn);
		
		currAction.setBorder(null);
		currAction.setText("-> Roll the dice");
		InfoBox.add(currAction);
		Background.add(InfoBox);
		
/*-------------------------------Jackpot----------------------------------------*/			
		imageURL = cldr.getResource("images/jackpot.png");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
		Jackpot.setIcon(new ImageIcon(image));
		Jackpot.setBounds(700,770, 250 ,100);
		Background.setLayer(Jackpot, 1);
		Background.add(Jackpot);
		
		jackpotText.setText("Jackpot: " + Controller.Jackpot.getBalance() + " Euros");
		jackpotText.setEditable(false);
		jackpotText.setBorder(null);
		jackpotText.setOpaque(false);
		jackpotText.setFont(new Font("TimesRoman BOLD", Font.BOLD, 20));
		jackpotText.setForeground(Color.WHITE);
		jackpotText.setBounds(700, 740, 230, 40);
		Background.setLayer(jackpotText, 1);
		Background.add(jackpotText);
		
		this.add(Background);
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Updates the info in the infoBox text field
	 */
	public static void UpdateInfoBox(String action) {
		currAction.setText("-->" + action);
		monthsLeft.setText(Controller.getMonths() + " Months left");
		playerTurn.setText("Turn: " + Controller.getPlayerTurn().getName());
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post Updates the jackpot's text field area
	 */
	public static void UpdateJackpot() {
		jackpotText.setText("Jackpot: " + Controller.Jackpot.getBalance() + " Euros");
	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post The Pawn's position on the board is updated
	 * @param player The player that has to move the pawn
	 * @param oldPosition the old position of the pawn on the board
	 */
	public static void movePawn(player Player ,int oldPosition) {
		if(Player.getName() == PlayerPawn1.getName()){
			pawnPositions[oldPosition].remove(PlayerPawn1);
			pawnPositions[oldPosition].repaint();
			
			pawnPositions[Controller.P1.getPosition()].add(PlayerPawn1);
			pawnPositions[Controller.P1.getPosition()].repaint();
		}
		else {
			pawnPositions[oldPosition].remove(PlayerPawn2);
			pawnPositions[oldPosition].repaint();
			
			pawnPositions[Controller.P2.getPosition()].add(PlayerPawn2);
			pawnPositions[Controller.P2.getPosition()].repaint();
		}
			TileAction(Player);
	}
	
	
	/**Transformer
	 * When the player steps on a tile some actions must happen
	 * 
	 * @pre none
	 * @post Some actions are performed and displayed
	 * @param p the player that stepped on the tile
	 */
	public static void TileAction(player p) {
		tile Tile = Controller.board.tiles[p.getPosition()];
		
		if(Tile.getName() == "SweepstakesTile" || Tile.getName() == "YardSaleTile") {
			UpdateInfoBox("Roll the dice again");
			p.setActionLeft(true);
			
		}else if(Tile.getName() == "FamilyCasinoTile") {
			FamilyCasinoTile t = (FamilyCasinoTile) Controller.board.tiles[p.getPosition()];
			t.performAction(p, Controller.Jackpot);
			UpdateJackpot();
		
		}else if(Tile.getName() == "LotteryTile") {
			Tile.performAction(p);
			
		}else if(Tile.getName() == "RadioTile") {
			UpdateInfoBox("Both Players roll the dice");
			p.setActionLeft(true);
		
		}else if(Tile.getName() == "BuyerTile"){
			UpdateInfoBox("Sell one of your cards (If you own any)");
			Tile.performAction(p);
		
		}else if(Tile.getName() == "MailTile" || Tile.getName() == "DealTile") {
			UpdateInfoBox("Draw your card");
			p.setActionLeft(true);
			
		}else if(Tile.getName() == "PayDayTile"){
			Tile.performAction(p);
			Controller.updateMonths(p.getMonthsLeft());
			
			if(Controller.gameFinished()) {
				Controller.gameWinner();
				System.exit(0);
			}else if(p.getMonthsLeft() != 0)
				resetPawn(p);
		}
	}
	/**Transformer
	 * 
	 * @pre none
	 * @post The pawn of the player goes to the starting tile
	 * @param Player the player that stepped on the paydayTile
	 */
	public static void resetPawn(player Player) {
		if(Player.getName() == PlayerPawn1.getName()){
			pawnPositions[31].remove(PlayerPawn1);
			pawnPositions[31].repaint();
			
			pawnPositions[0].add(PlayerPawn1);
			pawnPositions[0].repaint();
		}
		else {
			pawnPositions[31].remove(PlayerPawn2);
			pawnPositions[31].repaint();
			
			pawnPositions[0].add(PlayerPawn2);
			pawnPositions[0].repaint();
		}
	}
	
	/* A class used to do some action after DealCardStack button has been pressed*/
	private class DealCardsListener implements ActionListener{

		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after DealCardStack button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			player p;
			if(Controller.P1.getTurn()) 
				p = Controller.P1;
			else  
				p = Controller.P2;
			tile Tile = Controller.board.tiles[p.getPosition()];
			
			if(Tile.getName() != "DealTile") {
				UpdateInfoBox("You cant draw a DealCard right now!");
				return;
			}else if(!p.getActionLeft()) {
				UpdateInfoBox("Hey you already drew your card!");
				return;
			}
			
			if(Controller.DealStack.isEmpty())
				Controller.DealStack.reconstruct();
			
			
			DealCard card = Controller.DealStack.DrawCard();
			int choice = showDealCard(card);
			
			if(choice == 1)
				Controller.DealStack.RejectCard(card);
			else {
				card.performAction(p);
				Player1.updateTextInfo();
				Player2.updateTextInfo();
			}
			p.setActionLeft(false);
		}
	}
	
	/* A class used to do some action after MailCardStack button has been pressed*/
	private class MailCardsListener implements ActionListener{
		
		int cnt = 0;
		
		/**Transformer
		 * 
		 * @pre none
		 * @post doing some action after MailCardStack button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			player p;
			if(Controller.P1.getTurn()) 
				p = Controller.P1;
			else  
				p = Controller.P2;
			
			if(Controller.board.tiles[p.getPosition()].getName() != "MailTile") {
				UpdateInfoBox("You cant draw a MailCard right now!");
				return;
			}else if(!p.getActionLeft()) {
				UpdateInfoBox("Hey you already drew your cards!");
				return;
			}
			
			if(Controller.MailStack.isEmpty()) {
				Controller.MailStack.init_deck();
				Controller.MailStack.shuffle();
			}
			
			MailCard card = Controller.MailStack.DrawCard();
			showMailCard(card);
			MailTile t = (MailTile) Controller.board.tiles[p.getPosition()];
			
			if(card.getType().equals("Charity")) {     					 //Charity card scenario
				CharityCard C = (CharityCard) card;
				C.performAction(p, Controller.Jackpot);
				UpdateJackpot();
			}
			else if(card.getType().contentEquals("MoveToDeal/Buyer")) { //Move card scenario
				int oldpos = p.getPosition();
				MoveCard C = (MoveCard) card;
				C.performAction(p, Controller.board.tiles);
				p.setActionLeft(true);
				
				movePawn(p,oldpos);
			}
			else													   //all other cards
				card.performAction(p);
			
			cnt++;
			if(t.getDrawNumber() == cnt) {
				p.setActionLeft(false);
				cnt = 0;
			}
			
			Player1.updateTextInfo();
			Player2.updateTextInfo();
		}
	}
	
	
	/**Transformer
	 * 
	 * @pre none
	 * @post A mail card is shown on the screen
	 * @param card the card that will be displayed
	 */
	public void showMailCard(MailCard card) {
		String[] options = {card.getChoice()};
		
		image = new ImageIcon(cldr.getResource(card.getIcon())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		
		JOptionPane.showOptionDialog(this, card.getMessage(), card.getType(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);

	}
	
	/**Transformer
	 * 
	 * @pre none
	 * @post A deal card is shown on the screen
	 * @param card the card that will be displayed
	 */
	public int showDealCard(DealCard card) {
		String[] options = {card.getChoice1(), card.getChoice2()};
		
		image = new ImageIcon(cldr.getResource(card.getIcon())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		
		return JOptionPane.showOptionDialog(this, card.getMessage() + "\n Buy value: " + card.getCost() + " Sell value: " + card.getValue()
				, card.getType(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
	}
	
	public static void main(String[] args) {
		View view = new View();
	}
}
