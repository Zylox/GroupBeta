package com.tiny.tank;

import java.util.ArrayList;

public class Stat {
	
	private int totalMoves;
	private int shots_hit;
	private int shots_fired;
	private int place;
	private int total_hp;
	private int hp_left;
	private int movementLimitPerTurn;
	
	public Stat(int moves,int totalShots) {
		setMovementLimitPerTurn(moves);
		setShots_fired(totalShots);
	}
	public ArrayList<String> listOfStats() {
		ArrayList<String> stats=new ArrayList<String>();
		stats.add("");
		stats.add("2");
		stats.add(Integer.toString(getTotalMoves()));
		stats.add(Integer.toString(getShots_fired()));
		stats.add(Integer.toString(getHp_left()));
		return stats;
	}
	public String[] titleOfStats() {
		String[] stats={"","Number of hits","Number of moves","Number of Shots","Hit percentage"};
		return stats;
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

	public int getHp_left() {
		return hp_left;
	}

	public void setHp_left(int hp_left) {
		this.hp_left = hp_left;
	}


	public int getMovementLimitPerTurn() {
		return movementLimitPerTurn;
	}


	public void setMovementLimitPerTurn(int movementLimitPerTurn) {
		this.movementLimitPerTurn = movementLimitPerTurn;
	}
}
