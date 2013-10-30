package com.tiny.tank;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	private Vector2f pos;
	private float scale;
	private Vector2f screenSize;
	private float mostRecentScaleChange;
	
	public Camera(float x, float y, float width, float height, float scale ){
		this(new Vector2f(x,y), new Vector2f(width,height),scale);
	}
	
	public Camera(Vector2f pos, Vector2f screenSize, float scale){
		this.pos = pos;
		this.scale = scale;
		this.screenSize = screenSize;
		mostRecentScaleChange = 0;
	}
	
	
	public void adjustScale(float scaleChange){
		scale+=scaleChange;
		mostRecentScaleChange = scaleChange;
		adjustPos(0,0);
	}

	
	public float transformScreenToCamX(float x){
		return (x-pos.x)*scale;
	}
	
	public float transformScreenToCamY(float y){
		return (y-pos.y)*scale;
	}
	
	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public void adjustPos(float x, float y){
		adjustPos(new Vector2f(x,y));
	}
	
	public void adjustPos(Vector2f shift){
		adjustPosX(shift.x);
		adjustPosY(shift.y);
	}
	
	public void adjustPosX(float x){
		pos.x+=x;
		float edge = Main_Gameplay.map.getWidth()-(screenSize.x/scale);
		if(screenSize.x > Main_Gameplay.map.getWidth()*scale){
			pos.x=0;
			adjustScale(-mostRecentScaleChange);
			return;
		}
		
		if(pos.x < 0){
			pos.x = 0;
			return;
		}else if(pos.x > edge){
			pos.x = edge;
			return;
		}
	}
	
	public void adjustPosY(float y){
		pos.y+=y;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	
}
