package com.tiny.weapons.shots;

import org.newdawn.slick.geom.Vector2f;

import com.tiny.tank.Main_Gameplay;

public class FillShot extends NormalShot{

	public FillShot(Vector2f pos, int radiusOfEffect, int initialRadius,
			Object graphicalRep, String shotName) {
		super(pos, radiusOfEffect, initialRadius, graphicalRep, shotName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCollisionEffect() {
		// TODO Auto-generated method stub
		circleFill((int)pos.x, (int)pos.y, radiusOfEffect);
		Main_Gameplay.map.update();
	}

}
