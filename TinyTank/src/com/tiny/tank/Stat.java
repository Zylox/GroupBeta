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
	
	public Stat(int moves,int totalShots) {
		setMovementLimitPerTurn(moves);
		setShots_fired(totalShots);
		setShots_hit(0);
	}
	public String[] titleOfStats() {
		String[] stats={"","Number of hits","Number of moves","Number of Shots","Damage Taken "};
		return stats;
	}
	public ArrayList<String> listOfStats() {
		ArrayList<String> stats=new ArrayList<String>();
		stats.add("");
		stats.add("2");
		stats.add(Integer.toString(getTotalMoves()));
		stats.add(Integer.toString(getShots_fired()));
		stats.add(Integer.toString(getTotalDamage()));
		return stats;
	}
	
	public void addToDamage(int amount) {
		setTotalDamage(getTotalDamage() + amount);
		System.out.println(getTotalDamage());
	}
	public Boolean calculate_place(){
		return true;
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
}
