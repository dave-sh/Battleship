package SinglePlayer;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
public class Computer extends Player{
	
	private int difficulty = 1;
	private ArrayList<Coordinate> shipCoords;
	
	public int carrierIndex = Ships.CARRIER_ID;
	public int battleshipIndex = Ships.BATTLESHIP_ID;
	public int submarineIndex = Ships.SUBMARINE_ID;
	public int destroyerIndex = Ships.DESTROYER_ID;
	public int cruiserIndex = Ships.CRUISER_ID;
	public Computer(int remaining, int playerID, ArrayList<Ships> shipsRemaining, boolean setUp, boolean moved, int difficulty, boolean playing) {
		super(remaining, playerID, shipsRemaining, setUp, moved, playing);
		this.difficulty = difficulty;
	}
	
	public Computer(int remaining, int playerID, ArrayList<Ships> shipsRemaining, boolean setUp, boolean moved, int difficulty, boolean playing, ArrayList<Coordinate> shipCoords) {
		super(remaining, playerID, shipsRemaining, setUp, moved, playing);
		this.difficulty = difficulty;
		this.shipCoords = shipCoords;
	}
	
	public Computer(int remaining, int playerID) {
		super(remaining, playerID);
	}
	
	int gridSize = MainMenu.selectedGridSize;
	
	public int shipsRemaining;
	private Random coordGenerator = new Random();
	
	public ArrayList<Ships> getPlayerShipLocations(){
		return null;
	}
	
	public Grid createGrid(JPanel window, Computer c, int startingX, int startingY, Player opponent, Grid opponentGrid, int gridSize) {
		Coordinate grid[][] = new Coordinate[gridSize][gridSize];
		int rowInit = startingX;
		int columnInit = startingY;
		int rowCounter = 0;
		for(int column = 0; column < gridSize; column++) {
			for(int row = 0; row < gridSize; row++) {
				if(rowCounter < 45 * gridSize) {
					rowCounter+=45;
					rowInit+=45;
					System.out.println(rowInit);
				}else {
					rowInit = 45+startingX;
					rowCounter = 45;
					columnInit+=45;
				}
				System.out.println("Row: " + rowInit);
				System.out.println("Column: " + columnInit);
				JLabel displayContainer = new JLabel("");
				displayContainer.setIcon(Images.waterIcon);
				displayContainer.setBounds(rowInit, columnInit, 40, 40);
				window.add(displayContainer);
				Coordinate temp = new Coordinate(Images.waterIcon, displayContainer, false, false, row, column, "none");
				grid[row][column] = temp;
			}
		}
		
		Grid newGrid = new Grid(window, grid);
		Random rndm = new Random();
		
		//Computer place ships, randomize ship placement
		while(!c.getBattleship().isPlaced()||!c.getSubmarine().isPlaced()||!c.getDestroyer().isPlaced()||!c.getCruiser().isPlaced()||!c.getCarrier().isPlaced()) {
			int x = rndm.nextInt(gridSize);
			int y = rndm.nextInt(gridSize);
			newGrid.computerPlaceShip(c, grid[x][y]);
		}
		
		for(Coordinate[] temp: newGrid.getGrid()){
			for(int row = 0; row < temp.length; row++){
				Coordinate tem = temp[row];
				tem.getDisplayContainer().addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(tem.isHit()) {
							JOptionPane.showMessageDialog(null, "Already shot here.", "Cannot hit here", 0);
						}else {
							if(opponent.isPlaying()) {
								System.out.println("Coordinate hit: " + "(" + tem.getXCoordinate() + "," + tem.getYCoordinate() + ")");
								tem.setHit(true);
								if(tem.isHasShip()) {
									tem.getDisplayContainer().setIcon(Images.shipHit);
									tem.setShipHit(tem);
									Coordinate.setHitmarkers(tem, c, MainMenu.main);
									MainMenu.main.contentPane2.textArea.append("Player " + opponent.getPlayerID() + " hit ship at " + "(" + tem.getXCoordinate() + "," + tem.getYCoordinate() + ")" + "\n");
								}else {
									tem.getDisplayContainer().setIcon(Images.missed);
									MainMenu.main.contentPane2.textArea.append("Player " + opponent.getPlayerID() + " shot at " + "(" + tem.getXCoordinate() + "," + tem.getYCoordinate() + ")" + "\n");
								}
							}
							System.out.println(tem.getShipType());
							c.moved = false;
							opponent.moved = true;
							if(MainMenu.selectedQuesDifficulty.equals("Disabled")) {
								
							}else {
								ProblemGenerator w = new ProblemGenerator();
								w.setVisible(true);
								w.setDefaultCloseOperation(0);
							}
							computerHit(opponentGrid.getGrid(), MainMenu.main);
							window.revalidate();
							window.repaint();
							if(opponent.checkWin()){
								JOptionPane.showMessageDialog(null, "You win!");								
								MainMenu.frame.removeAll();
								MainMenu.frame.dispose();
								MainMenu.initialize();
							}else if(c.checkWin()) {
								JOptionPane.showMessageDialog(null, "Win!");								
								MainMenu.frame.removeAll();
								MainMenu.frame.dispose();
								MainMenu.initialize();
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
		}
		return newGrid;
	}
	
	public void computerHit(Coordinate[][] tempGrid, MainMenu mm) {
		System.out.println(difficulty);
		if(difficulty == 0) {
			randomHit(tempGrid, mm);
		}else if(difficulty == 1) {
			mediumAI(tempGrid, mm);
		}else if(difficulty == 2) {
			cheaterAI(mm);
		}
	}
	
	public boolean randomHit(Coordinate[][] tempGrid, MainMenu mm){
		int x = coordGenerator.nextInt(10);
		int y = coordGenerator.nextInt(10);
		if(tempGrid[x][y].isHit()) {
			System.out.println("Duplicate.");
			randomHit(tempGrid, mm);
		}else {
			if(tempGrid[x][y].isHasShip()) {
				tempGrid[x][y].getDisplayContainer().setIcon(Images.shipHit);
				tempGrid[x][y].setHit(true);
				mm.contentPane2.textArea.append("Computer hit" + "(" + x + "," + y + ")\n");
				return true;
			}else {
				tempGrid[x][y].getDisplayContainer().setIcon(Images.missed);
				tempGrid[x][y].setHit(true);
				mm.contentPane2.textArea.append("Computer hit" + "(" + x + "," + y + ")\n");
			}
		}
		return false;
	}
	
	public void cheaterAI(MainMenu mm){
		if(shipCoords.size() >= 1){
			Random rndm = new Random();
			int random = rndm.nextInt(shipCoords.size());
			Coordinate temp = shipCoords.get(random);
			System.out.print(temp.getXCoordinate() + " " +temp.getYCoordinate());
			if(!temp.isHit()){	
				temp.getDisplayContainer().setIcon(Images.shipHit);
				temp.setHit(true);
				shipCoords.remove(random);
				mm.contentPane2.textArea.append("Computer hit" + "(" + temp.getXCoordinate() + "," + temp.getYCoordinate() + ")\n");
			}else{
				cheaterAI(mm);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Computer Wins!");
			MainMenu.frame.dispose();
			MainMenu.frame.repaint();
			MainMenu.initialize();
		}
	}
	
	public void mediumAI(Coordinate tempGrid[][], MainMenu mm) {
		if(!checkWin()){
			Random rndm = new Random();
			int random = rndm.nextInt(shipCoords.size());
			Coordinate temp = shipCoords.get(random);
			System.out.print(temp.getXCoordinate() + " " +temp.getYCoordinate());
			if(!temp.isHit()){
				if(temp.isHasShip()) {
					temp.getDisplayContainer().setIcon(Images.shipHit);
					temp.setHit(true);
				}else {
					temp.getDisplayContainer().setIcon(Images.missed);
					temp.setHit(true);
				}
				shipCoords.remove(random);
				mm.contentPane2.textArea.append("Computer hit" + "(" + temp.getXCoordinate() + "," + temp.getYCoordinate() + ")\n");
			}else{
				mediumAI(tempGrid, mm);
			}
		}else{
			MainMenu.frame.removeAll();
			JOptionPane.showMessageDialog(null, "Computer Wins!");
			MainMenu.frame.dispose();
			MainMenu.initialize();
		}
	}
	
	public static String placeShipDirection() {
		Random rndm = new Random();
		int direction = rndm.nextInt(4);
		switch(direction) {
		case 0:
			return ">>>>";
		case 1:
			return "<<<<";
		case 2:
			return "^^^^";
		case 3: 
			return "vvvv";
		}
		return "";
	}
}
