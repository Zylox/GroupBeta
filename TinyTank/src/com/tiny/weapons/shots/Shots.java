package com.tiny.weapons.shots;

import org.newdawn.slick.geom.Vector2f;

import com.tiny.weapons.Shot;

public enum Shots {

	NORMAL_SHOT(new NormalShot(new Vector2f(0,0), 10, 1, null)),
	BIG_SHOT(new NormalShot(new Vector2f(0,0), 30, 1, null));
	
	Shots(Shot shot){
		
	}
	
}
