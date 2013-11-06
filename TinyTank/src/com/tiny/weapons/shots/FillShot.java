package com.tiny.weapons.shots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import com.tiny.tank.Main_Gameplay;
import com.tiny.weapons.Shot;

public class FillShot extends NormalShot{

	public FillShot(Vector2f pos, int radiusOfEffect, int initialRadius,
			Object graphicalRep,float animationLimit, float animationStep, String shotName) {
		super(pos, radiusOfEffect, initialRadius, graphicalRep, animationLimit, animationStep, shotName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCollisionEffect(GameContainer container) {
		// TODO Auto-generated method stub
		circleFill((int)pos.x, (int)pos.y, radiusOfEffect);
		Main_Gameplay.map.update();
	}
	
	@Override
	public Shot copy(){
		return new FillShot(pos, radiusOfEffect, initialRadius, graphicalRep, animationLimit, animationStep, shotName);
	}

}
