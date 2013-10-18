package com.tiny.weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.tank.Main_Gameplay;
import com.tiny.terrain.TerrainMap;

public abstract class Shot {
	
	protected Vector2f pos;
	protected int radiusOfEffect;
	protected Object graphicalRep;
	
	/**
	 * Sets basic values for the weapons.
	 * The graphical representation object is just a placeholder for now.
	 * @param pos Position of center
	 * @param radiusOfEffect Radius that the explosion will effect.
	 * @param graphicalRep 
	 */
	public Shot(Vector2f pos, int radiusOfEffect, Object graphicalRep){
		this.pos = pos;
		this.radiusOfEffect = radiusOfEffect;
		this.graphicalRep = graphicalRep;
	}
	 
	/**
	 * Checks if the positions of the center intersect the terrain. Uses point-point collision.
	 * @return true if colliding
	 */
	public final boolean pointCollision(){
		return Main_Gameplay.map.collision(pos);
	}
	
	
	public Vector2f getPos() {
		return pos;
	}
	
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	
	public int getRadiusOfEffect() {
		return radiusOfEffect;
	}
	
	public void setRadiusOfEffect(int radiusOfEffect) {
		this.radiusOfEffect = radiusOfEffect;
	}
	
	public abstract void onCollisionEffect();

	/**
	 * Flight behavior should be included in here
	 */
	public abstract void update();
	
	/**
	 * All parameters passed are directly from render method in Main_Gameplay state.
	 * Draw whatever the graphical representation of the weapon shot is here
	 * @param container
	 * @param game
	 * @param g
	 */
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g);
	
	
	
}
