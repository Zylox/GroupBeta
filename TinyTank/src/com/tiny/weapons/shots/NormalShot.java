package com.tiny.weapons.shots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.tank.Main_Gameplay;
import com.tiny.weapons.CircularShot;

public class NormalShot extends CircularShot{

	private final float animationLimit = 5;
	private float animationCounter;
	
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
	
	public void init(){
		super.init(pos);
		animationCounter = 0;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		if(!isShot || !isAlive){
			return true;
		}
		
		if(pointCollision()){
			isAnimating = true;
			areaOfEffect = new Circle(pos.x,pos.y, initialRadius);
		}
		
		if(isAnimating){
			
			if(initialRadius < radiusOfEffect){
				animationCounter+=.5;
				if(animationCounter > animationLimit){
					initialRadius++;
					areaOfEffect = new Circle(pos.x,pos.y, initialRadius);
					animationCounter -= animationLimit;
				}
			}
			else{
				onCollisionEffect();
				isAlive = false;
			}
		}else{
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


	
}
