package com.tiny.weapons.shots;

import org.newdawn.slick.geom.Vector2f;

import com.tiny.weapons.Shot;

public enum Shots {

	NORMAL_SHOT(new NormalShot(new Vector2f(0,0), 25, 1, null,1,.3f, "reg shot")),
	BIG_SHOT(new NormalShot(new Vector2f(0,0), 50, 1, null,1,.8f, "big shot")),
	//CRATER_SHOT(new CraterShot(new Vector2f(0,0), 30,1,null,"crater shot")), doesnt work yet
	FILL_SHOT(new FillShot(new Vector2f(0,0), 30,1,null,1,.8f,"fill shot"));
	
	Shot shot;
	
	Shots(Shot shot){
		this.shot = shot;
	}

	public Shot getShot() {
		return shot;
	}
	
	public Shot getCopyOfShot(){
		return this.getShot().copy();
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}
	
}
