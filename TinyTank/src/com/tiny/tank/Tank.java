package com.tiny.tank;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bot.Main;


public class Tank {
    
    
    //stores the data of the player
    private float botX;
    private float botY;
    //Angle of the player
    private float tankAng;
    //previous angle of the player
    private float prevAng;
    private int health;
    private boolean shot;
    //private int money;
    private int points;
    //index of the player
    private int index;

    public void TankInfo(float playerX, float playerY, float playerAng, int health, int index) {
        
        this.botX = playerX;
        this.botY = playerY;
        this.tankAng = playerAng;
        this.prevAng = playerAng;
        this.health = health;
        this.index = index;

 
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /*
    public int getMoney() {
        return coins;
    }

    public void setCoins(int money) {
        this.coins = coins;
    }
	*/
    public boolean hasShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getPlayerDir() {
        return tankAng;
    }

    public void setPlayerDir(int playerDir) {
        prevAng=this.tankAng;
        this.tankAng = playerDir;

    }

    public float getPrevDir() {
        return prevAng;
    }

    

    public float getPlayerX() {
        return botX;
    }

    public void setPlayerX(int playerX) {
        this.botX = playerX;
    }

    public float getPlayerY() {
        return botY;
    }

    public void setPlayerY(int playerY) {
        this.botY = playerY;
    }

    public boolean isAlive() {
        return health > 0 ? true : false;
    }


    
}
