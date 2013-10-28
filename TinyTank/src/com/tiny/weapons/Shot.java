package com.tiny.weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.tank.Camera;
import com.tiny.tank.Main_Gameplay;

public abstract class Shot {
	
	protected Vector2f pos;
	protected Vector2f impulse;
	protected Shape areaOfEffect;
	protected Object graphicalRep;
	protected boolean isAlive;
	protected boolean isAnimating;
	protected boolean isShot;
	protected float animationLimit;
	protected float animationStep;
	protected float animationCounter;
	protected String shotName;


	/**
	 * Sets basic values for the weapons.
	 * The graphical representation object is just a placeholder for now.
	 * @param pos Position of center
	 * @param areaOfEffect Radius that the explosion will effect.
	 * @param graphicalRep Graphical representation
	 */
	public Shot(Vector2f pos, Shape areaOfEffect, Object graphicalRep, float animationLimit, float animationStep, String shotName){
		this.pos = pos;
		this.areaOfEffect = areaOfEffect;
		this.graphicalRep = graphicalRep;
		this.shotName = shotName;
		this.isAlive = true;
		this.isAnimating = false;
		this.isShot = false;
		this.animationLimit = animationLimit;
		this.animationStep = animationStep;
		this.animationCounter = 0;
		this.impulse = new Vector2f(0,0);
	}
	 
	/**
	 * Checks if the positions of the center intersect the terrain. Uses point-point collision.
	 * @return true if colliding
	 */
	public final boolean pointCollision(){
		return Main_Gameplay.map.collision(pos);
	}
	
	
	public String getShotName() {
		return shotName;
	}
	
	
	public void setShotName(String shotName) {
		this.shotName = shotName;
	}
	
	
	public boolean isAnimating() {
		return isAnimating;
	}
	
	
	public void setAnimating(boolean isAnimating) {
		this.isAnimating = isAnimating;
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	
	public Shape getAreaOfEffect() {
		return areaOfEffect;
	}
	
	public void setAreaOfEffect(Shape areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isShot() {
		return isShot;
	}
	
	
	public void setShot(boolean isShot) {
		this.isShot = isShot;
	}
	
	/**
	 * Initializes the position upon firing.
	 * Override and call super if more needs to be done
	 * @param pos
	 */
	public void init(Vector2f pos, Vector2f impulse){
		this.pos = pos;
		this.impulse = impulse;
		this.isAlive = true;
		this.isAnimating = false;
		this.isShot = true;
		this.animationCounter = 0;
	}

	public abstract Shot copy();
	
	/**
	 * Every shot needs an onCollisionEffect
	 */
	public abstract void onCollisionEffect();

	
	/**
	 * Flight behavior should be included in here
	 * @returns isAlive
	 */
	public abstract boolean update();
	
	/**
	 * All parameters passed are directly from render method in Main_Gameplay state.
	 * Draw whatever the graphical representation of the weapon shot is here
	 * @param container
	 * @param game
	 * @param g
	 */
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g, Camera cam);
	
	/**
	 * Cleanup and finishing shots.
	 */
	public abstract void finished();	
	
}
