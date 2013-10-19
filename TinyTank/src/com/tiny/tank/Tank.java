package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import com.tiny.weapons.Shot;

public class Tank {
    
    
    //stores the data of the player
    private Vector2f pos;
    //Angle of the player
    private float barrelAng;
    //previous angle of the player
    private float prevAng;
    private int direction;
    private int health;
    //index of the player
    private int index;
    private ArrayList<Shot> shots;
    
    /**
     * Our good old players
     * @param playerX X position of top right
     * @param playerY Y position of top right
     * @param barrelAng The angle of the barrel
     * @param health Player health
     * @param shots List of weapons
     * @param index Player number
     */
    public void TankInfo(float playerX, float playerY, float barrelAng, int health, ArrayList<Shot> shots, int index) {
        
        this.pos = new Vector2f(playerX, playerY);
        this.barrelAng = barrelAng;
        this.prevAng = barrelAng;
        this.health = health;
        this.index = index;
        this.shots = shots;
        if(index == 1){
        	direction = 1;
        }else{
        	direction = 2;
        }
 
    }
    

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Shot> getShots() {
		return shots;
	}

	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getPlayerDir() {
        return barrelAng;
    }

    public void setPlayerDir(int playerDir) {
        prevAng=this.barrelAng;
        this.barrelAng = playerDir;

    }

    public float getPrevDir() {
        return prevAng;
    }

    public boolean isAlive() {
        return health > 0 ? true : false;
    }


    
}
