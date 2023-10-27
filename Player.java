package SinglePlayer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player {
	
	//public Ships shipsRemaining[];
	private ArrayList<Ships> shipsRemaining;
	private int remaining;
	private int playerID;
	private int shipsToBePlaced;
	private boolean setUp;
	protected boolean moved;
	private boolean playing;
	
	public int gridSize = MainMenu.selectedGridSize;
	
	public int carrierIndex = Ships.CARRIER_ID;
	public int battleshipIndex = Ships.BATTLESHIP_ID;
	public int submarineIndex = Ships.SUBMARINE_ID;
	public int destroyerIndex = Ships.DESTROYER_ID;
	public int cruiserIndex = Ships.CRUISER_ID;
	
	public Ships battleship = new Ships(Ships.BATTLESHIP_SIZE, Ships.BATTLESHIP_ID, false, false, false, 0);
	public Ships submarine = new Ships(Ships.SUBMARINE_SIZE, Ships.SUBMARINE_ID, false, false, false, 0);
	public Ships destroyer = new Ships(Ships.DESTROYER_SIZE, Ships.DESTROYER_ID, false, false, false, 0);
	public Ships carrier = new Ships(Ships.CARRIER_SIZE, Ships.CARRIER_ID, false, false, false, 0);
	public Ships cruiser = new Ships(Ships.CRUISER_SIZE, Ships.CRUISER_ID, false, false, false, 0);
	
	//Player creation
	public Player(int remaining, int playerID) {
		this.remaining = remaining;
		this.playerID = playerID;
	}
	
	//Assigning players ships 
	public Player(int remaining, int playerID, ArrayList<Ships> shipsRemaining, boolean setUp, boolean moved, boolean playing) {
		this.remaining = remaining;
		this.playerID = playerID;
		this.shipsRemaining = shipsRemaining;
		this.setSetUp(setUp);
		setUp = false;
		this.moved = moved;
		this.setPlaying(playing);
	}
	
	public Player(Player p, int remaining, int playerID, int shipsToBePlaced, boolean setUp){
		this(p.remaining, p.playerID, p.shipsRemaining, p.isSetUp(), p.isMoved(), p.isPlaying());
		this.setShipsToBePlaced(shipsToBePlaced);
	}
	
	public ArrayList<Ships> shipSetup() {
		ArrayList<Ships> shipsRemaining = new ArrayList<Ships>();
		//Initialize ship object creation for each player
		Ships carrier = new Ships(Ships.CARRIER_SIZE, Ships.CARRIER_ID, false, false, false, 0);
		Ships battleship = new Ships(Ships.BATTLESHIP_SIZE, Ships.BATTLESHIP_ID, false, false, false, 0);
		Ships submarine = new Ships(Ships.SUBMARINE_SIZE, Ships.SUBMARINE_ID, false, false, false, 0);
		Ships cruiser = new Ships(Ships.CRUISER_SIZE, Ships.CRUISER_ID, false, false, false, 0);
		Ships destroyer = new Ships(Ships.DESTROYER_SIZE, Ships.DESTROYER_ID, false, false, false, 0);
		shipsRemaining.add(carrier);
		shipsRemaining.add(battleship);
		shipsRemaining.add(submarine);
		shipsRemaining.add(cruiser);
		shipsRemaining.add(destroyer);
		return shipsRemaining;
	}
	
	public Grid createBoard(JPanel window, Player player, int startingX, int startingY, int gridSize){
		Coordinate grid[][] = new Coordinate[gridSize][gridSize];
		int rowInit = startingX;
		int columnInit = startingY;
		int rowCounter = 0;
		for(int column = 0; column < gridSize; column++) {
			for(int row = 0; row < gridSize; row++) {
				if(rowCounter < (gridSize*45)) {
					rowCounter+=45;
					rowInit+=45;
				}else {
					rowCounter = 45;
					rowInit = startingX + 45;
					columnInit+=45;
				}
				JLabel displayContainer = new JLabel("");
				displayContainer.setIcon(Images.waterIcon);
				displayContainer.setBounds(rowInit, columnInit, 40, 40);
				window.add(displayContainer);
				Coordinate temp = new Coordinate(Images.waterIcon, displayContainer, false, false, row, column, "none");
				grid[row][column] = temp;
			}
		}
		Grid newGrid = new Grid(window, grid);
		for(Coordinate[] temp: newGrid.getGrid()){
			for(int row = 0; row < temp.length; row++){
				Coordinate tem = temp[row];
				tem.getDisplayContainer().addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(!player.isPlaying()) {
							newGrid.placeShip(player, tem);
							System.out.println("Ship location: " + tem.isHasShip());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
				});
			}
			
		}
		return newGrid;
	}
	
	//Multiplayer create board
	public Grid createBoardMulti(JPanel window, Player player, int startingX, int startingY, int gridSize){
		Coordinate grid[][] = new Coordinate[gridSize][gridSize];
		int rowInit = startingX;
		int columnInit = startingY;
		int rowCounter = 0;
		for(int column = 0; column < gridSize; column++) {
			for(int row = 0; row < gridSize; row++) {
				if(rowCounter < (gridSize*45)) {
					rowCounter+=45;
					rowInit+=45;
				}else {
					rowCounter = 45;
					rowInit = startingX + 45;
					columnInit+=45;
				}
				JLabel displayContainer = new JLabel("");
				displayContainer.setIcon(Images.waterIcon);
				displayContainer.setBounds(rowInit, columnInit, 40, 40);
				window.add(displayContainer);
				Coordinate temp = new Coordinate(Images.waterIcon, displayContainer, false, false, row, column, "none");
				grid[row][column] = temp;
			}
		}
		Grid newGrid = new Grid(window, grid);
		for(Coordinate[] temp: newGrid.getGrid()){
			for(int row = 0; row < temp.length; row++){
				Coordinate tem = temp[row];
				tem.getDisplayContainer().addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(!player.isPlaying()) {
							newGrid.multiPlaceShip(player, tem);
							System.out.println("Ship location: " + tem.isHasShip());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
				});
			}
			
		}
		return newGrid;
	}
	
	public Ships getBattleship() {
		return getShipsRemaining().get(battleshipIndex);
	}
	
	public Ships getCarrier() {
		return getShipsRemaining().get(carrierIndex);
	}
	
	public Ships getSubmarine() {
		return getShipsRemaining().get(submarineIndex);
	}
	
	public Ships getCruiser() {
		return getShipsRemaining().get(cruiserIndex);
	}
	
	public Ships getDestroyer() {
		return getShipsRemaining().get(destroyerIndex);
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getShipsToBePlaced() {
		return shipsToBePlaced;
	}

	public void setShipsToBePlaced(int shipsToBePlaced) {
		this.shipsToBePlaced = shipsToBePlaced;
	}

	public ArrayList<Ships> getShipsRemaining() {
		return shipsRemaining;
	}

	public void setShipsRemaining(ArrayList<Ships> shipsRemaining) {
		this.shipsRemaining = shipsRemaining;
	}
	
	public Ships getShip(int index){
		return shipsRemaining.get(index);
	}

	public boolean isSetUp() {
		return setUp;
	}

	public void setSetUp(boolean setUp) {
		this.setUp = setUp;
	}
	
	public boolean allPlaced() {
		boolean ret = false;
		if(getRemaining() == 9) {
			ret = true;
		}
		return ret;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	public boolean checkWin() {
		if(getBattleship().getSunk() && getSubmarine().getSunk() && getCarrier().getSunk() && getDestroyer().getSunk() && getCruiser().getSunk()) {
			return true;
		}
		return false;
	}
	
	public Coordinate[][] gridMask(Coordinate[][] grid){
		for(int x = 0; x < gridSize; x++) {
			for(int y = 0; y < gridSize; y++) {
				if(grid[x][y].isHasShip()) {
					grid[x][y].getDisplayContainer().setIcon(Images.waterIcon);
				}
			}
		}
		return grid;
	}
	
	public ArrayList<Coordinate> scan(Coordinate[][] tempGrid){
		ArrayList<Coordinate> shipCoords = new ArrayList<Coordinate>();
		for(Coordinate[] row: tempGrid){
			for(int column = 0; column < row.length; column++){
				Coordinate coord = row[column];
				if(coord.isHasShip()){
					shipCoords.add(coord);
				}
			}
		}
		return shipCoords;
	}
	
	public ArrayList<Coordinate> scanBroke(Coordinate[][] tempGrid){
		ArrayList<Coordinate> shipCoords = new ArrayList<Coordinate>();
		for(Coordinate[] row: tempGrid){
			for(int column = 0; column < row.length; column++){
				Coordinate coord = row[column];
				if(coord.isHasShip()){
					shipCoords.add(coord);
				}else if(!coord.isHasShip()) {
					Random rndm = new Random();
					int test = rndm.nextInt(10) + 1;
					if(test < 3) {
						shipCoords.add(coord);
					}
				}
			}
		}
		return shipCoords;
	}
}
