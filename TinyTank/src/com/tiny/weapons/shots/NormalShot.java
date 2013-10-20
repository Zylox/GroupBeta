package com.tiny.weapons.shots;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.tank.Main_Gameplay;
import com.tiny.tank.Tank;
import com.tiny.weapons.CircularShot;

public class NormalShot extends CircularShot{

	/**
	 * Animation counter limit
	 */
	private final float animationLimit = 1;
	private float animationCounter;
	/**
	 * Creates a standard circle shot with exsplosion of given radius
	 * @param pos Position (duh)
	 * @param radiusOfEffect Radius of final exsplosion
	 * @param initialRadius Radius that exsplosion starts at
	 * @param graphicalRep Unused currently
	 * @param shotName String identifying name of shot
	 */
	public NormalShot(Vector2f pos, int radiusOfEffect, int initialRadius, Object graphicalRep, String shotName) {
		super(pos, radiusOfEffect, initialRadius, graphicalRep, shotName);
		// TODO Auto-generated constructor stub
		animationCounter = 0;
	}

	
	@Override
	public void onCollisionEffect() {
		// TODO Auto-generated method stub
		circleExplosion((int)pos.x, (int)pos.y, radiusOfEffect);
		Main_Gameplay.map.update();
	}
	
	public void init(Vector2f pos){
		super.init(pos);
		animationCounter = 0;
	}
	
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		
		//Returns true if dead and not shot. Allows for removal
		if(!isShot || !isAlive){
			return true;
		}
		
		//if collides with terrain
		if(pointCollision()){
			isAnimating = true;
			areaOfEffect = new Circle(pos.x,pos.y, initialRadius);
		}
		
		//when animating
		if(isAnimating){
			//as long as radius is still growing
			if(initialRadius < radiusOfEffect){
				
				animationCounter+=.1; //increment counter
				if(animationCounter > animationLimit){ //if limit time has been passed
					initialRadius++; //increase radius
					areaOfEffect = new Circle(pos.x,pos.y, initialRadius); //and graphical rep of it
					animationCounter -= animationLimit; //decrement counter
				}
			}
			else{ //its dead jim
				onCollisionEffect();
				isAlive = false;
				finished();
			}
		}else{ //if not otehr states, has to be falling so update
			//temporary
			pos.x = pos.x+0;
			pos.y = pos.y+1;
			if(pos.y > Main_Gameplay.map.getMap().getHeight()){
				isAnimating = true;
				areaOfEffect = new Circle(pos.x,Main_Gameplay.map.getMap().getHeight()-1, initialRadius);
			}
		}
		
		return isAlive;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// TODO Auto-generated method stub
	
		if(!isAlive || !isShot){
			return;
		}
		
		if(isAnimating){
			g.fill(areaOfEffect);
			return;
		}
		
		g.fill(new Circle(pos.x, pos.y, 1));
		
	}

	@Override
	public void finished(){
		ArrayList<Tank> players = Main_Gameplay.players;
		
		for(Tank t : players){
			t.setFalling(true);
			t.setShooting(false);
		}
		
		
	}
	
}
