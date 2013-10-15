package com.tiny.terrain;

import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;

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
	 * Generation algorithm still needed
	 * @param x width
	 * @param y height
	 * @return The generated map
	 */
	private ImageBuffer generate(int x, int y){
		//TODO
		
		ImageBuffer genMap = new ImageBuffer(x,y);
		for(int j = 0; j<height; j++){
			for(int i = 0; i<width;i++){
				if(function(i,j)>1){
					genMap.setRGBA(i,j, 255, 50, 50, 255);
				}
			}
		}
		
		return genMap;
	}
	
	private double function(double x, double y){
		//return ((x-width/2)*(x-width/2))/(height-y);
		return height/2*Math.sin((x*-width/2))-((height-y));
	}
	
	/**
	 * Call when effecting map. Particularly with weapons.
	 * Make sure to grab a new picture.
	 */
	public void update(){
		//TODO
		image = map.getImage();
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
