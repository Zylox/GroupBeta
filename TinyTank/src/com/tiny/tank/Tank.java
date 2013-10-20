package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

/*
 * Player class. Tanks 
 */
public class Tank {

	private final int tankWidth = 20;
	private final int tankHeight = 10;
	private final float animationLimit = 1;
	private float animationCounter;

	// stores the data of the player
	private Vector2f pos;
	// Angle of the player
	private float barrelAng;
	// previous angle of the player
	private float prevAng;

	private int direction;
	private int health;
	// index of the player
	private int index;
	private ArrayList<Shot> shots;
	private Rectangle hitbox;
	//x and y ranges of numbers. Will be usefull when we get rotations
	private float[] xRange;
	private float[] yRange;
	private boolean isMoving;
	private boolean isFalling;
	private boolean isShooting;
	//Needs to change when moved or falling
	private boolean hasRangeChanged;

	/**
	 * Our good old players
	 * 
	 * @param playerX
	 *            X position of top right
	 * @param playerY
	 *            Y position of top right
	 * @param barrelAng
	 *            The angle of the barrel
	 * @param health
	 *            Player health
	 * @param shots
	 *            List of weapons
	 * @param index
	 *            Player number
	 */
	public void TankInfo(float playerX, float playerY, float barrelAng,
			int health, ArrayList<Shot> shots, int index) {

		this.pos = new Vector2f(playerX, playerY);
		this.barrelAng = barrelAng;
		this.prevAng = barrelAng;
		this.health = health;
		this.index = index;
		this.shots = shots;
		//player1 looks right, player 2 looks left
		if (index == 1) {
			direction = 1;
		} else {
			direction = 2;
		}

		isMoving = false;
		isFalling = false;
		isShooting = false;
		
		hitbox = new Rectangle(playerX, playerY, tankWidth, tankHeight);
		xRange = new float[2];
		yRange = new float[2];
		calculateXRange();
		calculateYRange();
		hasRangeChanged = false;
		
		animationCounter = 0;
	}

	/*
	 * Will set the position upon map creation. Puts it right on top of map
	 */
	public void setFirstPos() {
		pos.y = Main_Gameplay.map.getMaxInRange(xRange[0], xRange[1])
				- tankHeight + 1;
		hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
	}

	/*
	 * Will update the events relating to tanks and shots
	 */
	public void update() {

		//state handeling if falling
		if (isFalling) {
			animationCounter += 1;
			if (animationCounter > animationLimit) {

				pos.y += 1;
				hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
				hasRangeChanged = true;
				if (checkCollision()) {
					pos.y -= 1;
					hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
					isFalling = false;
					animationCounter = 0;
				}
				animationCounter -= animationLimit;
			}
		}
		//state handeling if shooting
		if (isShooting) {
			for (int i = 0; i < shots.size(); i++) {
				if (shots.get(i).isShot()) {
					shots.get(i).update();
				}
			}
		}
		
		//states to be added: changing barrel angles, moving

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		//current graphical representation
		g.fill(hitbox);
	}

	public boolean checkCollision() {

		//adjusts range if needbe
		checkRange();

		//checks if outside the map
		if (hitbox.getMaxX() > Main_Gameplay.map.getWidth() - 1
				|| hitbox.getMinX() < 0
				|| hitbox.getMaxY() > Main_Gameplay.map.getHeight() - 1
				|| hitbox.getMinY() < 0) {
			return true;
		}

		//gets teh highest point under the tank
		int mapMaxInRange = Main_Gameplay.map.getMaxInRange(xRange[0],
				xRange[1]);
		//if lowest corner is below mapmax does collision check
		if (yRange[1] > mapMaxInRange) {
			for (int i = (int) xRange[0]; i < xRange[1]; i++) {
				for (int j = (int) yRange[0]; j < yRange[1]; j++) {
					if (Main_Gameplay.map.collision(new Vector2f(i, j))) {
						if (hitbox.contains(i, j)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * recalculates range if needs to
	 */
	private void checkRange() {
		if (!hasRangeChanged) {
			return;
		}

		calculateXRange();
		calculateYRange();
	}

	private void calculateXRange() {

		float left = hitbox.getMinX();
		float right = hitbox.getMaxX();

		xRange[0] = left;
		xRange[1] = right;
	}

	private void calculateYRange() {

		float top = hitbox.getMinY();
		float bottom = hitbox.getMaxY();
		yRange[0] = top;
		yRange[1] = bottom;
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
		prevAng = this.barrelAng;
		this.barrelAng = playerDir;

	}

	public float getPrevDir() {
		return prevAng;
	}

	public boolean isAlive() {
		return health > 0 ? true : false;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public float getBarrelAng() {
		return barrelAng;
	}

	public void setBarrelAng(float barrelAng) {
		this.barrelAng = barrelAng;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

}
