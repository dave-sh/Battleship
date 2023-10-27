package SinglePlayer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import battleshipNetwork.MultiplayerPlayScreen;

public class Grid {
	private Coordinate[][] grid;
	//accessing buttons from grid
	private static String switcheroo = ">>>>";
	
	JButton battleshipbtn = MainMenu.btnBattleship;
	JButton destroyerbtn = MainMenu.btnDestroyer;
	JButton subbtn = MainMenu.btnSubmarine;
	JButton cruiserbtn = MainMenu.btnCruiser;
	JButton carrierbtn = MainMenu.btnAircraftCarrier;
	
	private static int shipsTBPlaced = 5;
	private int gridSize = 10;

	public int carrierIndex = Ships.CARRIER_ID;
	public int battleshipIndex = Ships.BATTLESHIP_ID;
	public int submarineIndex = Ships.SUBMARINE_ID;
	public int destroyerIndex = Ships.DESTROYER_ID;
	public int cruiserIndex = Ships.CRUISER_ID;
	
	public Grid(JPanel contentPane, Coordinate[][] grid) {
		this.grid = grid;
	}
	
	public void placeShip(Player player, Coordinate temp){
		System.out.println(MainMenu.selectedShip);
		if(MainMenu.selectedShip.equals("battleship") && !player.getShip(battleshipIndex).isPlaced()){
			placeBattleship(player, temp, getGrid(), false);
		}else if(MainMenu.selectedShip.equals("submarine") && !player.getShip(submarineIndex).isPlaced()){
			placeSubmarine(player, temp, getGrid(), false);	
		}else if(MainMenu.selectedShip.contains("destroyer")&& !player.getShip(destroyerIndex).isPlaced()){
			placeDestroyer(player, temp, getGrid(), false);		
		}else if(MainMenu.selectedShip.contains("carrier") && !player.getShip(carrierIndex).isPlaced()){
			placeCarrier(player, temp, getGrid(), false);
		}else if(MainMenu.selectedShip.contains("cruiser")&& !player.getShip(cruiserIndex).isPlaced()){
			placeCruiser(player, temp, getGrid(), false);
		}else{
			JOptionPane.showMessageDialog(null, "Select a ship to be placed.");
			System.out.println("Select a ship to be placed.");
		}
	}
	
	public void multiPlaceShip(Player player, Coordinate temp) {
		if(MultiplayerPlayScreen.selectedShip.equals("battleship") && !player.getShip(battleshipIndex).isPlaced()){
			placeBattleship(player, temp, getGrid(), true);
		}else if(MultiplayerPlayScreen.selectedShip.equals("submarine") && !player.getShip(submarineIndex).isPlaced()){
			placeSubmarine(player, temp, getGrid(), true);	
		}else if(MultiplayerPlayScreen.selectedShip.contains("destroyer")&& !player.getShip(destroyerIndex).isPlaced()){
			placeDestroyer(player, temp, getGrid(), true);		
		}else if(MultiplayerPlayScreen.selectedShip.contains("carrier") && !player.getShip(carrierIndex).isPlaced()){
			placeCarrier(player, temp, getGrid(), true);
		}else if(MultiplayerPlayScreen.selectedShip.contains("cruiser") && !player.getShip(cruiserIndex).isPlaced()){
			placeCruiser(player, temp, getGrid(), true);
		}else{
			JOptionPane.showMessageDialog(null, "Select a ship to be placed.");
			System.out.println("Select a ship to be placed.");
		}
	}
	
	public void computerPlaceShip(Computer cpu, Coordinate temp) {
		if(!cpu.getBattleship().isPlaced()) {
			placeBattleship(cpu, temp, getGrid(), false);
		}else if(!cpu.getSubmarine().isPlaced()){
			placeSubmarine(cpu, temp, getGrid(), false);
		}else if(!cpu.getDestroyer().isPlaced()) {
			placeDestroyer(cpu, temp, getGrid(), false);
		}else if(!cpu.getCruiser().isPlaced()) {
			placeCruiser(cpu, temp, getGrid(), false);
		}else if(!cpu.getCarrier().isPlaced()) {
			placeCarrier(cpu, temp, getGrid(), false);
		}
	}
	
	public void placeBattleship(Player player, Coordinate temp, Coordinate[][] grid, boolean multiplayer) throws ArrayIndexOutOfBoundsException{
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		System.out.println(tempX + "," + tempY);
		try {
			if(!(player instanceof Computer) && overlapping(player, temp, Ships.BATTLESHIP_SIZE, multiplayer)) {
				System.out.println("Invalid location.");
				JOptionPane.showMessageDialog(null, "Overlapping ships. Cannot Place here.");
			}else if((player instanceof Computer) && overlapping(player, temp, Ships.BATTLESHIP_SIZE, multiplayer)){
				System.out.println("Overlapping");
			}else {
				if(player instanceof Computer) {
					System.out.println("Computer");
				}else if(multiplayer) {
					switcheroo = MultiplayerPlayScreen.currentDirection;
				}else{
					switcheroo = MainMenu.currentDirection;
					System.out.println(switcheroo);
				}
				switch(switcheroo){
				case ">>>>":{
					for(int start = 0; start < Ships.BATTLESHIP_SIZE; start++){
						//places ship and sets icon
						grid[tempX+start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX+start][tempY].setHasShip(true);
						grid[tempX+start][tempY].setShipType("battleship");
					}
					break;
				}
				case "<<<<":{
					for(int start = 0; start < Ships.BATTLESHIP_SIZE; start++){
						//places ship and sets icon
						grid[tempX-start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX-start][tempY].setHasShip(true);
						grid[tempX-start][tempY].setShipType("battleship");
					}
					break;
				}
				case "^^^^":{
					for(int start = 0; start < Ships.BATTLESHIP_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY-start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY-start].setHasShip(true);
						grid[tempX][tempY-start].setShipType("battleship");
					}
					break;
				}	
				case "vvvv":{
					for(int start = 0; start < Ships.BATTLESHIP_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY+start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY+start].setHasShip(true);
						grid[tempX][tempY+start].setShipType("battleship");
					}
					break;
				}
				default:{
					System.out.println("ugly");
				}
				}
				System.out.println("Ships remaining: " + player.getShipsRemaining().size());
				player.getShip(battleshipIndex).setPlaced(true);
				System.out.println("Battleship placed: " + player.getShip(battleshipIndex).isPlaced() + "for Player " + player.getPlayerID());
				player.getShipsRemaining().add(player.getShip(battleshipIndex));
				player.setShipsToBePlaced(shipsTBPlaced--);
				battleshipbtn.setEnabled(false);
				if(multiplayer) {
					MultiplayerPlayScreen.btnBattleship.setEnabled(false);
				}
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("Invalid ship location");
			JOptionPane.showMessageDialog(null, "Invalid Ship Location.");
		}
	}
	
	public void placeSubmarine(Player player, Coordinate temp, Coordinate[][] grid, boolean multiplayer) throws ArrayIndexOutOfBoundsException{
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		System.out.println(tempX + "," + tempY);
		try {
			if(!(player instanceof Computer) && overlapping(player, temp, Ships.SUBMARINE_SIZE, multiplayer)) {
				System.out.println("Invalid location.");
				JOptionPane.showMessageDialog(null, "Overlapping ships. Cannot Place here.");
			}else if(overlapping(player, temp, Ships.SUBMARINE_SIZE, multiplayer)){
				System.out.println("Overlapping");
			}else {
				if(player instanceof Computer) {
					System.out.println("Computer");
				}else if(multiplayer) {
					switcheroo = MultiplayerPlayScreen.currentDirection;
				}else{
					switcheroo = MainMenu.currentDirection;
					System.out.println(switcheroo);
				}
				switch(switcheroo){
				case ">>>>":{
					for(int start = 0; start < Ships.SUBMARINE_SIZE; start++){
						//places ship and sets icon
						grid[tempX+start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX+start][tempY].setHasShip(true);
						grid[tempX+start][tempY].setShipType("submarine");
					}
					break;
				}
				case "<<<<":{
					for(int start = 0; start < Ships.SUBMARINE_SIZE; start++){
						//places ship and sets icon
						grid[tempX-start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX-start][tempY].setHasShip(true);
						grid[tempX-start][tempY].setShipType("submarine");
					}
					break;
				}
				case "^^^^":{
					for(int start = 0; start < Ships.SUBMARINE_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY-start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY-start].setHasShip(true);
						grid[tempX][tempY-start].setShipType("submarine");
					}
					break;
				}
				case "vvvv":{
					for(int start = 0; start < Ships.SUBMARINE_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY+start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY+start].setHasShip(true);
						grid[tempX][tempY+start].setShipType("submarine");
					}
					break;
				}
				default:{
					
				}
				}
				System.out.println("Ships remaining: " + player.getShipsRemaining().size());
				player.getShip(submarineIndex).setPlaced(true);
				System.out.println("submarine placed: " + player.getShip(submarineIndex).isPlaced() + "for Player " + player.getPlayerID());
				player.getShipsRemaining().add(player.getShip(submarineIndex));
				player.setShipsToBePlaced(shipsTBPlaced--);
				subbtn.setEnabled(false);
				if(multiplayer) {
					MultiplayerPlayScreen.btnSubmarine.setEnabled(false);
				}
				if(multiplayer) {
					MultiplayerPlayScreen.btnSubmarine.setEnabled(false);
				}
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			if(!(player instanceof Computer)) {
			System.out.println("Invalid ship location");
			JOptionPane.showMessageDialog(null, "Invalid Ship Location.");
			}
		}
	}
	
	public void placeDestroyer(Player player, Coordinate temp, Coordinate[][] grid, boolean multiplayer) throws ArrayIndexOutOfBoundsException{
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		System.out.println(tempX + "," + tempY);
		try {
			if(!(player instanceof Computer) && overlapping(player, temp, Ships.DESTROYER_SIZE, multiplayer)) {
				System.out.println("Invalid location.");
				JOptionPane.showMessageDialog(null, "Overlapping ships. Cannot Place here.");
			}else if((player instanceof Computer) && overlapping(player, temp, Ships.DESTROYER_SIZE, multiplayer)){
				System.out.println("Overlapping");
			}else {
				if(player instanceof Computer) {
					System.out.println("Computer");
				}else if(multiplayer) {
					switcheroo = MultiplayerPlayScreen.currentDirection;
				}else{
					switcheroo = MainMenu.currentDirection;
					System.out.println(switcheroo);
				}
				switch(switcheroo){
				case ">>>>":{
					for(int start = 0; start < Ships.DESTROYER_SIZE; start++){
						//places ship and sets icon
						grid[tempX+start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX+start][tempY].setHasShip(true);
						grid[tempX+start][tempY].setShipType("destroyer");
					}
					break;
				}
				case "<<<<":{
					for(int start = 0; start < Ships.DESTROYER_SIZE; start++){
						//places ship and sets icon
						grid[tempX-start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX-start][tempY].setHasShip(true);
						grid[tempX-start][tempY].setShipType("destroyer");
					}
					break;
				}
				case "^^^^":{
					for(int start = 0; start < Ships.DESTROYER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY-start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY-start].setHasShip(true);
						grid[tempX][tempY-start].setShipType("destroyer");
					}
					break;
				}
				case "vvvv":{
					for(int start = 0; start < Ships.DESTROYER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY+start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY+start].setHasShip(true);
						grid[tempX][tempY+start].setShipType("destroyer");
					}
					break;
				}
				default:{
				
				}
				}	
				System.out.println("Ships remaining: " + player.getShipsRemaining().size());
				player.getShip(destroyerIndex).setPlaced(true);
				System.out.println("destroyer placed: " + player.getShip(destroyerIndex).isPlaced() + "for Player " + player.getPlayerID());
				player.getShipsRemaining().add(player.getShip(destroyerIndex));
				player.setShipsToBePlaced(shipsTBPlaced--);
				destroyerbtn.setEnabled(false);
				if(multiplayer) {
					MultiplayerPlayScreen.btnDestroyer.setEnabled(false);
				}
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			//debug a.printStackTrace();
			if(!(player instanceof Computer)) {
			System.out.println("Invalid ship location");
			JOptionPane.showMessageDialog(null, "Invalid Ship Location.");
			}
		}
	}
	
	public void placeCarrier(Player player, Coordinate temp, Coordinate[][] grid, boolean multiplayer) throws ArrayIndexOutOfBoundsException{
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		try {
			if(!(player instanceof Computer) && overlapping(player, temp, Ships.CARRIER_SIZE, multiplayer)) {
				System.out.println("Invalid location.");
				JOptionPane.showMessageDialog(null, "Overlapping ships. Cannot Place here.");	
			}else if((player instanceof Computer) && overlapping(player, temp, Ships.CARRIER_SIZE, multiplayer)){
				System.out.println("Overlapping");
			}else {
				if(player instanceof Computer) {
					System.out.println("Computer");
				}else if(multiplayer) {
					switcheroo = MultiplayerPlayScreen.currentDirection;
				}else{
					switcheroo = MainMenu.currentDirection;
					System.out.println(switcheroo);
				}
				switch(switcheroo){
				case ">>>>":
					for(int start = 0; start < Ships.CARRIER_SIZE; start++){
						//places ship and sets icon
						grid[tempX+start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX+start][tempY].setHasShip(true);
						grid[tempX+start][tempY].setShipType("carrier");
					}
					break;
				case "<<<<":
					for(int start = 0; start < Ships.CARRIER_SIZE; start++){
						//places ship and sets icon
						grid[tempX-start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX-start][tempY].setHasShip(true);
						grid[tempX-start][tempY].setShipType("carrier");
					}
					break;
				case "^^^^":
					for(int start = 0; start < Ships.CARRIER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY-start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY-start].setHasShip(true);
						grid[tempX][tempY-start].setShipType("carrier");
					}
					break;
				case "vvvv":{
					for(int start = 0; start < Ships.CARRIER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY+start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY+start].setHasShip(true);
						grid[tempX][tempY+start].setShipType("carrier");
					}
					break;
				}
				default:{
					
				}
				}		
				System.out.println("Ships remaining: " + player.getShipsRemaining().size());
				player.getShip(carrierIndex).setPlaced(true);
				System.out.println("carrier placed: " + player.getShip(carrierIndex).isPlaced());
				player.setShipsToBePlaced(shipsTBPlaced--);
				player.getShipsRemaining().add(player.getShip(carrierIndex));
				System.out.println("Carrier placed for Player " + player.getPlayerID());
				player.setShipsToBePlaced(shipsTBPlaced--);
				carrierbtn.setEnabled(false);
				if(multiplayer) {
					MultiplayerPlayScreen.btnAircraftCarrier.setEnabled(false);
				}
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			if(!(player instanceof Computer)) {
				System.out.println("Invalid ship location");
				JOptionPane.showMessageDialog(null, "Invalid Ship Location.");
			}
		}
	}
	
	public void placeCruiser(Player player, Coordinate temp, Coordinate[][] grid, boolean multiplayer) throws ArrayIndexOutOfBoundsException{
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		try {
			if(!(player instanceof Computer) && overlapping(player, temp, Ships.CRUISER_SIZE, multiplayer)) {
				System.out.println("Invalid location.");
				JOptionPane.showMessageDialog(null, "Overlapping ships. Cannot Place here.");
			}else if((player instanceof Computer) && overlapping(player, temp, Ships.CRUISER_SIZE, multiplayer)){
				
			}else {
				if(player instanceof Computer) {
					System.out.println("Computer");
				}else if(multiplayer) {
					switcheroo = MultiplayerPlayScreen.currentDirection;
				}else{
					switcheroo = MainMenu.currentDirection;
					System.out.println(switcheroo);
				}
				switch(switcheroo){
				case ">>>>":
					for(int start = 0; start < Ships.CRUISER_SIZE; start++){
						//places ship and sets icon
						grid[tempX+start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX+start][tempY].setHasShip(true);
						grid[tempX+start][tempY].setShipType("cruiser");
					}
					break;
				case "<<<<":
					for(int start = 0; start < Ships.CRUISER_SIZE; start++){
						//places ship and sets icon
						grid[tempX-start][tempY].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX-start][tempY].setHasShip(true);
						grid[tempX-start][tempY].setShipType("cruiser");
					}
					break;
				case "^^^^":
					for(int start = 0; start < Ships.CRUISER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY-start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY-start].setHasShip(true);
						grid[tempX][tempY-start].setShipType("cruiser");
					}
					break;
				case "vvvv":{
					for(int start = 0; start < Ships.CRUISER_SIZE; start++){
						//places ship and sets icon
						grid[tempX][tempY+start].getDisplayContainer().setIcon(Images.shipIcon);
						grid[tempX][tempY+start].setHasShip(true);
						grid[tempX][tempY+start].setShipType("cruiser");
					}
					break;
				}
				default:{
			
				}
				}	
				System.out.println("Ships remaining: " + player.getShipsRemaining().size());
				player.getShip(cruiserIndex).setPlaced(true);
				System.out.println("Cruiser placed: " + player.getShip(carrierIndex).isPlaced() + "for Player " + player.getPlayerID());
				player.setShipsToBePlaced(shipsTBPlaced--);
				player.getShipsRemaining().add(player.getShip(carrierIndex));
				cruiserbtn.setEnabled(false);
				if(multiplayer) {
					MultiplayerPlayScreen.btnCruiser.setEnabled(false);
				}
				//
			}
		}catch(ArrayIndexOutOfBoundsException a) {
			
		}
	}
	
	public boolean overlapping(Player player, Coordinate temp, int shipSize, boolean multiplayer){
		boolean overlap = false;
		int tempX = temp.getXCoordinate();
		int tempY = temp.getYCoordinate();
		if(player instanceof Computer) {
			System.out.println("Computer");
		}else if(multiplayer) {
			switcheroo = MultiplayerPlayScreen.currentDirection;
		}else{
			switcheroo = MainMenu.currentDirection;
			System.out.println(switcheroo);
		}
		switch(switcheroo){
		case ">>>>":{
			if(tempX+shipSize > gridSize) {
				overlap = true;
				break;
			}else {
				for(int start = 0; start < shipSize; start++){
					if(grid[tempX+start][tempY].isHasShip()) {
						overlap = true;
						break;
					}else {
						overlap = false;
					}
				}
			}
			break;
		}
		case "<<<<":{
			if(tempX-shipSize < -1) {
				overlap = true;
				break;
			}else {
				for(int start = 0; start < shipSize; start++){
					if(grid[tempX-start][tempY].isHasShip()) {
						overlap = true;
						break;
					}else {
						overlap = false;
					}
				}
			}
			break;
		}
		case "^^^^":{
			if(tempY-shipSize < -1) {
				overlap = true;
				break;
			}else {
				for(int start = 0; start < shipSize; start++){
					if(grid[tempX][tempY-start].isHasShip()) {
						overlap = true;
						break;
					}else {
						overlap = false;
					}
				}
			}
			break;
		}
		case "vvvv":{
			if(tempY+shipSize > gridSize) {
				System.out.println(tempY + shipSize);
				overlap = true;
				break;
			}else {
				for(int start = 0; start < shipSize; start++){
					if(grid[tempX][tempY+start].isHasShip()) {
						overlap = true;
						break;
					}else {
						overlap = false;
					}
				}
			}
			break;
		}
		}
		
		return overlap;
	}
	
	public Coordinate[][] getGrid() {
		return grid;
	}

	public void setGrid(Coordinate[][] grid) {
		this.grid = grid;
	}
	
}
