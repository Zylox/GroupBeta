package com.tiny.terrain;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Vector2f;

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
	
	
	/**
	 * Generates a bytemap and an image representing it.
	 * @param x width
	 * @param y height
	 */
	public TerrainMap(int x, int y){
		init(x,y);
	}
	
	/**
	 * Reinitializes the map to the given specification
	 * @param x width
	 * @param y height
	 */
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
		//TODO
		int[] heightmap = new int[x];
		Random ran = new Random();
		
		float tolRange=50;
		float randRange=50;
		float tolmax = 100;
		
		int k = y/2;
		float tolerance = 0;
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
		
			heightmap[i] = k;
			
		}
		
		for(int p : heightmap){
			System.out.println(p);
		}
		
		ImageBuffer genMap = new ImageBuffer(x,y);
		
		for(int i = 0; i<x; i++){
			k = heightmap[i];
			while(k<y){
				genMap.setRGBA(i, k, 118, 114, 40, 255);
				k++;
			}
		}
		
		
		return genMap;
	}
	
	
	/**
	 * Call when effecting map. Particularly with weapons.
	 * Make sure to grab a new picture.
	 */
	public void update(){
		//TODO
		image = map.getImage();
	}
	
	public boolean collision(Vector2f point){
		
		int x = (int)point.x;
		int y = (int)point.y;
		
		if(map.getRGBA()[((x + (y * map.getTexWidth())) * 4)] == (byte)118){
			return true;
		}
		
		return false;
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
