package SinglePlayer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Coordinate {
	
	public static int carrierIndex = Ships.CARRIER_ID;
	public static int battleshipIndex = Ships.BATTLESHIP_ID;
	public static int submarineIndex = Ships.SUBMARINE_ID;
	public static int destroyerIndex = Ships.DESTROYER_ID;
	public static int cruiserIndex = Ships.CRUISER_ID;
	
	public static int carrierSize = Ships.CARRIER_SIZE;
	public static int battleshipSize = Ships.BATTLESHIP_SIZE;
	public static int submarineSize = Ships.SUBMARINE_SIZE;
	public static int cruiserSize = Ships.CRUISER_SIZE;
	public static int destroyerSize = Ships.DESTROYER_SIZE;
	
	//Instance variables
	private int xCoord;
	private int yCoord;
	private ImageIcon display;
	private JLabel displayContainer;
	private boolean hasShip = false;
	private boolean hit = false;
	private String shiptype;
	
	//Constructor to use for playing game
	public Coordinate(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}
	
	//Constructor containing all the information for a Coordinate object
	public Coordinate(ImageIcon waterIcon, JLabel displayContainer, boolean hasShip, boolean hit, int x, int y, String shiptype) {
		this.display = waterIcon;
		this.setDisplayContainer(displayContainer);
		this.setHasShip(hasShip);
		this.setHit(hit);
		this.xCoord = x;
		this.yCoord = y;
	}
	
	
	public ImageIcon getDisplay(){
		return display;
	}
	
	public static void setHitmarkers(Coordinate temp, Player c, MainMenu mm) {
		if(temp.getShipType().equals("battleship")) {
			c.getShipsRemaining().get(battleshipIndex).setHitmarker(c.getShipsRemaining().get(battleshipIndex).getHitmarker() + 1);
			if(c.getShipsRemaining().get(battleshipIndex).getHitmarker() == battleshipSize) {
				c.getBattleship().setSunk();
				mm.contentPane2.textArea.append("Battleship sunk for " + c.getPlayerID() + "\n");
				System.out.println("Battleship sunk");
			}
		}else if(temp.getShipType().equals("cruiser")) {
			c.getShipsRemaining().get(cruiserIndex).setHitmarker(c.getShipsRemaining().get(cruiserIndex).getHitmarker() + 1);
			if(c.getShipsRemaining().get(cruiserIndex).getHitmarker() == cruiserSize) {
				c.getCruiser().setSunk();
				mm.contentPane2.textArea.append("Cruiser sunk for " + c.getPlayerID() + "\n");
				System.out.println("cruiser sunk");
			}
		}else if(temp.getShipType().equals("destroyer")) {
			c.getShipsRemaining().get(destroyerIndex).setHitmarker(c.getShipsRemaining().get(destroyerIndex).getHitmarker() + 1);
			if(c.getShipsRemaining().get(destroyerIndex).getHitmarker() == destroyerSize) {
				c.getDestroyer().setSunk();
				mm.contentPane2.textArea.append("Destroyer sunk for " + c.getPlayerID() + "\n");
				System.out.println("destroyer sunk");
			}
		}else if(temp.getShipType().equals("carrier")) {
			c.getShipsRemaining().get(carrierIndex).setHitmarker(c.getShipsRemaining().get(carrierIndex).getHitmarker() + 1);
			if(c.getShipsRemaining().get(carrierIndex).getHitmarker() == carrierSize) {
				c.getCarrier().setSunk();
				mm.contentPane2.textArea.append("Carrier sunk for " + c.getPlayerID() + "\n");
				System.out.println("carrier sunk");
			}
		}else if(temp.getShipType().equals("submarine")) {
			c.getShipsRemaining().get(submarineIndex).setHitmarker(c.getShipsRemaining().get(submarineIndex).getHitmarker() + 1);
			if(c.getShipsRemaining().get(submarineIndex).getHitmarker() == submarineSize) {
				c.getSubmarine().setSunk();
				mm.contentPane2.textArea.append("Submarine sunk for " + c.getPlayerID() + "\n");
				System.out.println("Submarine sunk");
			}
		}
	}
	
	//Set hit marker on ship
	public void setShipHit(Coordinate location) {
		location.setHit(true);
		//location.setDisplay(Images.shipHit);
	}
	
	public void setShipMiss(Coordinate location) {
		location.setHit(true);
		//location.setDisplay(Images.missed);
	}
	
	//Checks if location is a Ship Location
	public boolean isShipLocation(Coordinate location) {
		boolean ship;
		if(location.isHasShip()) {
			ship = true;
		}else {
			ship = false;
		}
		return ship;
	}
	
	public JLabel getDisplayContainer() {
		return displayContainer;
	}

	public void setDisplayContainer(JLabel displayContainer) {
		this.displayContainer = displayContainer;
	}
	
	public int getXCoordinate(){
		return xCoord;
	}
	
	public int getYCoordinate(){
		return yCoord;
	}

	public boolean isHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

	public String getShipType() {
		return shiptype;
	}

	public void setShipType(String shiptype) {
		this.shiptype = shiptype;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	//Checks if the location has already been fired on
		public boolean isHit() {
			return hit;
		}
}
