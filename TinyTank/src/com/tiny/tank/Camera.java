package com.tiny.tank;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	public Vector2f pos;
	public float scale;
	private Vector2f fullScaleRes;
	
	public Camera(float x, float y, float width, float height, float scale ){
		this(new Vector2f(x,y), new Vector2f(width,height),scale);
	}
	
	public Camera(Vector2f pos, Vector2f fullScaleRes, float scale){
		this.pos = pos;
		this.scale = scale;
		this.fullScaleRes = fullScaleRes;
	}
	
	public void adjustScale(float scaleChange){
		scale+=scaleChange;
	}
	
	
}
