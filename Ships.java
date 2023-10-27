package SinglePlayer;

public class Ships {
	public static final int CARRIER_SIZE = 5;
	public static final int BATTLESHIP_SIZE = 4;
	public static final int SUBMARINE_SIZE = 3;
	public static final int CRUISER_SIZE = 3;
	public static final int DESTROYER_SIZE = 2;
	
	public static final int CARRIER_ID = 0;
	public static final int BATTLESHIP_ID = 1;
	public static final int SUBMARINE_ID = 2;
	public static final int CRUISER_ID = 3;
	public static final int DESTROYER_ID = 4;
	
	public int carrierIndex = Ships.CARRIER_ID;
	public int battleshipIndex = Ships.BATTLESHIP_ID;
	public int submarineIndex = Ships.SUBMARINE_ID;
	public int destroyerIndex = Ships.DESTROYER_ID;
	public int cruiserIndex = Ships.CRUISER_ID;
	
	private int shipSize;
	private int shipID;
	private boolean hit;
	private boolean sunk;
	private boolean placed;
	private int hitmarker;
	
	public boolean getHit() {
		return this.hit;
	}

	//Ship object constructor
	public Ships(int shipSize, int shipID, boolean hit, boolean sunk, boolean placed, int hitmarker) {
		this.setShipSize(shipSize);
		this.setShipID(shipID);
		this.hit = hit;
		this.sunk = sunk;
		this.placed = placed;
		this.setHitmarker(hitmarker);
		hitmarker = 0;
	}
	
	public void setHit(){
		this.hit = true;
	}
	
	public void setSunk(){
		this.sunk = true;
	}
	
	public boolean getSunk() {
		return sunk;
	}
	
	public void setPlaced(boolean place){
		this.placed = place;
	}

	public boolean isPlaced() {
		return placed;
	}

	public int getShipID() {
		return shipID;
	}

	public void setShipID(int shipID) {
		this.shipID = shipID;
	}

	public int getShipSize() {
		return shipSize;
	}

	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}

	public int getHitmarker() {
		return hitmarker;
	}

	public void setHitmarker(int hitmarker) {
		this.hitmarker = hitmarker;
	}
}
