package com.tiny.tank;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
	
	public static final float SMOOTH_DEFAULT = 1f;
	
	
	private Vector2f pos;
	private float scale;
	private Vector2f screenSize;
	private float mostRecentScaleChange;
	private boolean isSmoothing;
	private Vector2f transitionRemainder;
	private float transitionIncrement;
	
	public Camera(float x, float y, float width, float height, float scale ){
		this(new Vector2f(x,y), new Vector2f(width,height),scale);
	}
	
	public Camera(Vector2f pos, Vector2f screenSize, float scale){
		this.pos = pos;
		this.scale = scale;
		this.screenSize = screenSize;
		mostRecentScaleChange = 0;
		isSmoothing = false;
		
		transitionRemainder = new Vector2f(0,0);
		
	}
	
	
	public void update(){
		if(isSmoothing){
			smoothMove();
		}
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
	
	public boolean adjustPosX(float x){
		pos.x+=x;
		float edge = Main_Gameplay.map.getWidth()-(screenSize.x/scale);
		if(screenSize.x > Main_Gameplay.map.getWidth()*scale){
			pos.x=0;
			adjustScale(-mostRecentScaleChange);
			return false;
		}
		
		if(pos.x < 0){
			pos.x = 0;
			return false;
		}else if(pos.x > edge){
			pos.x = edge;
			return false;
		}
		
		return true;
	}
	
	public void smoothTransition(float x, float y, float transitionIncrement){
		smoothTransition(new Vector2f(x,y), transitionIncrement);
	}
	
	public void smoothTransition(Vector2f transitionDis, float transitionIncrement){
		this.transitionIncrement = transitionIncrement;
		this.transitionRemainder.x = transitionDis.x;
		this.transitionRemainder.y = transitionDis.y;
		isSmoothing = true;
		//smoothMove();
	}
	
	private void smoothMove(){
		
		Vector2f inc = new Vector2f();
		float xDir;
		float yDir;
		if(transitionRemainder.x > 0){
			xDir = 1;
		}else{
			xDir = -1;
		}
		if(transitionRemainder.y > 0){
			yDir = 1;
		}else{
			yDir = -1;
		}
		
		if(Math.abs(transitionRemainder.x) <= transitionIncrement){
			inc.x = transitionRemainder.x;
			//System.out.println("inc.x " + inc.x);
		}else{
			inc.x = transitionIncrement*xDir;
		}
		if(Math.abs(transitionRemainder.y) <= transitionIncrement){
			inc.y = transitionRemainder.y;
		}else{
			inc.y = transitionIncrement*yDir;
		}
		
		transitionRemainder.x-=inc.x;
		transitionRemainder.y-=inc.y;
		
		if(!adjustPosX(inc.x)){
			transitionRemainder = clearTransitionRem(transitionRemainder, 'x');
			//System.out.println("cleared x" + " rem.y: " + transitionRemainder.y + " rem.x " + transitionRemainder.x );
		}
		if(!adjustPosY(inc.y)){
			System.out.println("cleared y");
			transitionRemainder = clearTransitionRem(transitionRemainder, 'y');
		}
		
		
		//System.out.println(transitionRemainder.x);
		System.out.println("inc.x: " + inc.x);
		System.out.println(" rem.y: " + transitionRemainder.y + " rem.x " + transitionRemainder.x );
		if(transitionRemainder.x == 0 && transitionRemainder.y == 0){
			isSmoothing = false;
			System.out.println("cleared both");
			transitionRemainder = clearTransitionRem(transitionRemainder);
			transitionIncrement = 0;
		}
		
	}
	
	private Vector2f clearTransitionRem(Vector2f transitionAm){
		return clearTransitionRem(transitionAm, 'a');
	}
	
	private Vector2f clearTransitionRem(Vector2f transitionAm, char axis){
		if(axis == 'x'){
			transitionAm.x = 0;
			return transitionAm;
		}
		if(axis == 'y'){
			transitionAm.y = 0;
			return transitionAm;
		}
		
		return new Vector2f(0,0);
	}
	
	
	public boolean adjustPosY(float y){
		pos.y+=y;
		return true;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	
}
