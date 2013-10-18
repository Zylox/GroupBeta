package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import com.tiny.weapons.Shot;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bot.Main;


public class Tank {
    
    
    //stores the data of the player
    private Vector2f pos;
    //Angle of the player
    private float barrelAng;
    //previous angle of the player
    private float prevAng;
    private int health;
    //index of the player
    private int index;
    private ArrayList<Shot> shots;
    
    public void TankInfo(float playerX, float playerY, float playerAng, int health, ArrayList<Shot> shots, int index) {
        
        this.pos = new Vector2f(playerX, playerY);
        this.barrelAng = playerAng;
        this.prevAng = playerAng;
        this.health = health;
        this.index = index;
        this.shots = shots;
 
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
