package com.tiny.terrain;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.tank.Camera;

/**
 * The land for our game. Uses a image buffer for pixel operations and an image for rendering.
 * Collision detection to be determined
 * @author pzero
 *
 */
public class TerrainMap {

	private ImageBuffer map;
	private Image image;
	private int height;
	private int width;
	private int scaledWidth;
	private int scaledHeight;
	private int[] linearHeightmap;
	
	
	/**
	 * Generates a bytemap and an image representing it.
	 * @param x width
	 * @param y height
	 */
	public TerrainMap(int x, int y){
		init(x,y);
	}
	
	public void reinit(int x, int y){
		init(x,y);
	}
	
	/**
	 * This is abstracted away incase things get more complicated in the future
	 * @param x
	 * @param y
	 */
	private void init(int x, int y){
		width = x;
		height = y;
		map = generate(x,y);
		image= map.getImage();		
	}	
		
	
	/**
	 * Placeholder generation algorithm. Creates a heightmap then fills map.
	 * @param x width
	 * @param y height
	 * @return The generated map
	 */
	private ImageBuffer generate(int x, int y){
		linearHeightmap = new int[x];
		Random ran = new Random();
		
		
		float tolRange=50;
		float randRange=50;
		float tolmax = 100;
		
		int k = 2*y/3;
		float tolerance = 0;
		int change = 3;
		for(int i = 0; i<x; i++){
			tolerance += ran.nextFloat()*randRange-randRange/2;		
			/////
			if(tolerance < -tolmax){
				tolerance +=tolmax;
			}else if (tolerance > tolmax){
				tolerance -= tolmax;
			}
			/////
			if(tolerance<-tolRange){
				k-=1;
				if (k < 0){k=0;}
			}else if(tolerance>tolRange){
				k+=1;
				if(k >= height){k=height-1;}
			}
		
			linearHeightmap[i] = k;
			
		}
		
		//for(int i =0;i<x/2;i++){
			//linearHeightmap[x/4+i] +=i;
			//linearHeightmap[3*x/4-i] +=i;
		//}
		/*
		for(int p : linearHeightmap){
			System.out.println(p);
		}
		*/
		
		ImageBuffer genMap = new ImageBuffer(x,y);
		
		for(int i = 0; i<x; i++){
			k = linearHeightmap[i];
			while(k<y){

				genMap.setRGBA(i, k, 50, 114, 40, 255);
				k++;
			}
		}
		
		
		return genMap;
	}
	
	public void setFilled(int i, int j){
		map.setRGBA(i, j, 50, 114, 40, 255);
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g, Camera cam){
		image.draw((0-cam.pos.x)*cam.scale, (0-cam.pos.y)*cam.scale, cam.scale);
	}

	/**
	 * Call when effecting map. Particularly with weapons.
	 * Make sure to grab a new picture.
	 */
	public void update(){
		//TODO
		image = map.getImage();
	}
	
	//gets if there is a collision of a point on the map
	public boolean collision(Vector2f point){
		
		int x = (int)point.x;
		int y = (int)point.y;
		
		if(map.getRGBA()[((x + (y * map.getTexWidth())) * 4)] == (byte)50){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the max value of the map in range given
	 * @param start Start of range
	 * @param finish End of range
	 * @return max value
	 */
	public int getMaxInRange(float start, float finish){
		int max = linearHeightmap[(int) start];
		for(int i = (int) (start+1); i<finish;i++){
			//seems like a wierd way to find max but remember y is inverted;
			if( max >linearHeightmap[i]){
				max = linearHeightmap[i];
			}
		}
		
		return max;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int[] getLinearHeightmap() {
		return linearHeightmap;
	}
	
	public int getLinearHeightMapPoint(int i){
		return linearHeightmap[i];
	}
	
	public void setLinearHeightmapPoint(int i, int k) {
		linearHeightmap[i] = k;
	}
	

	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public ImageBuffer getMap() {
		return map;
	}
	
	public void setMap(ImageBuffer map) {
		this.map = map;
	}
	
}
