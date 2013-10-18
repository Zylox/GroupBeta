package com.tiny.weapons;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public abstract class CircularShot extends Shot{

	public CircularShot(Vector2f pos, int radiusOfEffect, Object graphicalRep) {
		super(pos, new Circle(pos.x, pos.y, radiusOfEffect), graphicalRep);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
