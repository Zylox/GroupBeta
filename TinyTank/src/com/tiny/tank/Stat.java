package com.tiny.tank;

import java.util.ArrayList;

public class Stat {
	
	private int totalMoves;
	private int shots_hit;
	private int shots_fired;
	private int place;
	private int total_hp;
	private int totalDamage;
	private int movementLimitPerTurn;
	private int maxHit;
	private int shotsLeft;
	private int totalShots;
	public Stat(int moves,int totalShots) {
		setMovementLimitPerTurn(moves);
		setTotalShots(totalShots);
		setShotsLeft(totalShots);
		setShots_hit(0);
		setMaxHit(0);
	}
	//Holds title of stats. to add a new stat, just list it here and add it to the list 
	public String[] titleOfStats() {
		String[] stats={"", " Number of hits  ",
							" Number of moves ",
							" Number of Shots ",
						   "Number of Shots Left",
							"  Damage Taken   ", 
							"     Max Hit     "};
		return stats;
	}
	public ArrayList<String> listOfStats() {
		ArrayList<String> stats=new ArrayList<String>();
		stats.add("");
		stats.add(Integer.toString(getShots_hit()));
		stats.add(Integer.toString(getTotalMoves()));
		stats.add(Integer.toString(getShots_fired()));
		stats.add(Integer.toString(getShotsLeft()));
		stats.add(Integer.toString(getTotalDamage()));
		stats.add(Integer.toString(getMaxHit()));
		return stats;
	}
	
	public void addToDamage(int amount) {
		if(amount > getMaxHit()) {
			setMaxHit(amount);
		}
		setTotalDamage(getTotalDamage() + amount);
		System.out.println(getTotalDamage());
	}
	public void updateShots(int shotsLeft) {
		setShots_fired(getTotalShots()-shotsLeft);
		setShotsLeft(shotsLeft);
	}
	public void addToMovement(int turnMoves){
		setTotalMoves(turnMoves + getTotalMoves());
		System.out.print( " total Moves: " + getTotalMoves() + "\n");
	}

	public int getTotalMoves() {
		return totalMoves;
	}

	public void setTotalMoves(int moves) {
		this.totalMoves = moves;
	}

	public int getShots_hit() {
		return shots_hit;
	}

	public void setShots_hit(int shots_hit) {
		this.shots_hit = shots_hit;
	}

	public int getShots_fired() {
		return shots_fired;
	}

	public void setShots_fired(int shots_fired) {
		this.shots_fired = shots_fired;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getTotal_hp() {
		return total_hp;
	}

	public void setTotal_hp(int total_hp) {
		this.total_hp = total_hp;
	}

	public int getTotalDamage() {
		return totalDamage;
	}

	public void setTotalDamage(int totalDamage) {
		this.totalDamage = totalDamage;
	}


	public int getMovementLimitPerTurn() {
		return movementLimitPerTurn;
	}


	public void setMovementLimitPerTurn(int movementLimitPerTurn) {
		this.movementLimitPerTurn = movementLimitPerTurn;
	}
	public int getMaxHit() {
		return maxHit;
	}
	public void setMaxHit(int maxHit) {
		this.maxHit = maxHit;
	}
	public int getShotsLeft() {
		return shotsLeft;
	}
	public void setShotsLeft(int shotsLeft) {
		this.shotsLeft = shotsLeft;
	}
	public int getTotalShots() {
		return totalShots;
	}
	public void setTotalShots(int totalShots) {
		this.totalShots = totalShots;
	}
	
}
