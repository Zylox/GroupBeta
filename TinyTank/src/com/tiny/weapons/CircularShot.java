package com.tiny.weapons;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.tiny.tank.Main_Gameplay;

public abstract class CircularShot extends Shot{

	protected int radiusOfEffect;
	protected int initialRadius;
	private int storedRadius;
	
	/**
	 * Converts circular representations into general
	 * @param pos
	 * @param radiusOfEffect
	 * @param intialRadius
	 * @param graphicalRep
	 * @param shotName
	 */
	public CircularShot(Vector2f pos, int radiusOfEffect, int intialRadius, Object graphicalRep, float animationLimit, float animationStep, String shotName) {
		super(pos, new Circle(pos.x, pos.y, radiusOfEffect), graphicalRep, animationLimit, animationStep, shotName);
		// TODO Auto-generated constructor stub
		this.radiusOfEffect = radiusOfEffect;
		this.storedRadius = initialRadius;
		this.initialRadius = storedRadius;
	}
	
	/**
	 * Does position and radius storing
	 */
	@Override
	public void init(Vector2f pos, Vector2f impulse){
		super.init(pos,impulse);
		initialRadius = storedRadius;
		
	}
	
	/**
	 * Creates a circle exsplosion on the map
	 * @param x Center of circle coord x
	 * @param y Center of circle coord y
	 * @param radius Radius of circle
	 */
	public void circleExplosion(int x, int y, int radius){
		
		for(int i =x-radius;i<x+radius;i++){
			for(int j = y-radius;j<y+radius;j++){
				
				if((x-i)*(x-i)  + (y-j)*(y-j)< radius*radius){
					if(i < Main_Gameplay.map.getMap().getWidth() && i >= 0 && j < Main_Gameplay.map.getMap().getHeight() && j >=0){
						Main_Gameplay.map.getMap().setRGBA(i, j, 0, 0, 0, 0);
						if(Main_Gameplay.map.getLinearHeightmap()[i] == j ){
							Main_Gameplay.map.setLinearHeightmapPoint(i,j+1);
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Fills a Cirlular area
	 * @param x Center x
	 * @param y Center y
	 * @param radius 
	 */
	public void circleFill(int x, int y, int radius){
		for(int i =x-radius;i<x+radius;i++){
			for(int j = y-radius;j<y+radius;j++){
				
				if((x-i)*(x-i)  + (y-j)*(y-j)< radius*radius){
					if(i < Main_Gameplay.map.getMap().getWidth() && i >= 0 && j < Main_Gameplay.map.getMap().getHeight() && j >=0){
						Main_Gameplay.map.setFilled(i, j);
						if(Main_Gameplay.map.getLinearHeightmap()[i] > j ){
							Main_Gameplay.map.setLinearHeightmapPoint(i,j-1);
						}
					}
				}
			}
		}		
	}
	
}
