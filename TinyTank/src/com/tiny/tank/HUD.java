package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;
import com.tiny.weapons.Shot;

public class HUD {
	
	private Input input;
	private float barrelAng;
	private int power;
	private int gas;
	private int health;
	private int index;
	private ArrayList<Shot> shots;
	private Image weapons_head;
	private Image move_head;
	private Image angle_head;
	private Image power_head;
	private Button lweapons;
	private Button rweapons;
	private Button langle;
	private Button rangle;
	private Button lpower;
	private Button rpower;
	private Vector2f hudPos;
	private int shotIndex;
	private int gasLength;
	
	/**
	 * This is a function used to load all of the images needed for the HUD
	 * @throws SlickException
	 */
	public void loadImages() throws SlickException{
		weapons_head = new Image("res/weapons.png");
		move_head = new Image("res/Gas.png");
		angle_head = new Image("res/Angle.png");
		power_head = new Image("res/Power.png");
		lweapons = new Button("res/LeftArrow.png", (int) (hudPos.x+420), (int) (hudPos.y+50));
		rweapons = new Button("res/RightArrow.png", (int) (hudPos.x+560), (int) (hudPos.y+50));
		langle = new Button("res/LeftArrow.png", (int) (hudPos.x+215), (int) (hudPos.y+50));
		rangle = new Button("res/RightArrow.png", (int) (hudPos.x+335), (int) (hudPos.y+50));
		lpower = new Button("res/LeftArrow.png", (int) (hudPos.x+640), (int) (hudPos.y+50));
		rpower = new Button("res/RightArrow.png", (int) (hudPos.x+760), (int) (hudPos.y+50));
			
	}
	
	/**
	 * Constructor for HUD
	 * @param tank
	 * @throws SlickException
	 */
	public HUD(Tank tank) throws SlickException {
		
		this.barrelAng = tank.getBarrelAng();
		this.power = tank.getPower();
		this.gas = tank.getGas();
		this.health = tank.getHealth();
		this.shots = tank.getShots();
		this.index = tank.getIndex();
		hudPos = new Vector2f(0,510);
		loadImages();
		shotIndex = 0;
		gasLength = tank.getGas();
		
	}
	
	
	/**
	 * In render, the Variable hudPos is used so we can dynamically move the HUD with all
	 * buttons and headers simultaneously. 
	 * @param container
	 * @param game
	 * @param g
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// current graphical representation
		Color prevCol = g.getColor();
		g.setColor(Color.darkGray);
		g.fillRect(hudPos.x, hudPos.y, container.getWidth(), 90);
		weapons_head.draw((int) (hudPos.x+420),(int)(hudPos.y+10));
		angle_head.draw((int)(hudPos.x+215),(int)(hudPos.y+10));
		power_head.draw((int)(hudPos.x+640),(int)(hudPos.y+10));
		move_head.draw((int)(hudPos.x+10),(int)(hudPos.y+10));
		rweapons.drawButton(g);
		lweapons.drawButton(g);
		rangle.drawButton(g);
		langle.drawButton(g);
		rpower.drawButton(g);
		lpower.drawButton(g);
		//Gas rectangle
		g.setColor(Color.red);
		g.fillRect(hudPos.x+10, hudPos.y+57 , (int)(gasLength*1.87), 10);
		g.setColor(Color.white);
		//Angle
		g.drawString(Float.toString(barrelAng), (int)(hudPos.x+270), (int)(hudPos.y+55));
		g.drawString(Integer.toString(power), (int)(hudPos.x+705), (int)(hudPos.y+55));
	
		if(shots.size() > 0){
			g.drawString(shots.get(shotIndex).getShotName(), (int)(hudPos.x+465), (int)(hudPos.y+55));
		}
		g.setColor(prevCol);

	}
	
	/**
	 * In function update, we update the HUD based upon which interaction is received. 
	 * In regards to changing weapons, a simple left button click is sufficient.
	 * For Power and Angle, it is allowed to use a held left button so changing the values a
	 * significant amount is much quicker and easier.  
	 * @param container
	 * @param game
	 */
	public void update(GameContainer container, StateBasedGame game){
		
		input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int posX = input.getMouseX();
			int posY = input.getMouseY();
			
			if (rweapons.isMouseOverButton(posX, posY)) {
				System.out.println("rweapons!" + " " + index);
				shotIndex++;
				if(shotIndex > shots.size()-1){
					shotIndex=0;
				}
			}
			else if (lweapons.isMouseOverButton(posX, posY)) {
				System.out.println("lweapons!");
				shotIndex--;
				if(shotIndex < 0){
					shotIndex=shots.size()-1;
				}
			}
		}	
			
		input = container.getInput();
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				int pos_X = input.getMouseX();
				int pos_Y = input.getMouseY();
			
			if (rangle.isMouseOverButton(pos_X, pos_Y)) {
				//System.out.println("rangle!");
				if(barrelAng < 30 && index == 1){
					barrelAng++;
				}
				
				if(barrelAng > -30 && index == 2){
					barrelAng--;
				}
			}
			else if (langle.isMouseOverButton(pos_X, pos_Y)) {
				System.out.println("langle!");

				if(barrelAng > -90 && index == 1){
					barrelAng--;
				}
				
				if(barrelAng < 90 && index == 2){
					barrelAng++;
				}
				
			}
			else if (rpower.isMouseOverButton(pos_X, pos_Y)) {
				System.out.println("rpower!");
				if(power < 100){
					power++;
				}
			}
			else if (lpower.isMouseOverButton(pos_X, pos_Y)) {
				System.out.println("lpower!");
				if(power > 0){
					power--;
				}
			}

		}	
		
	}

	
	public void onTurnSwitch(ArrayList<Shot> shots){
		shotIndex = 0;
		this.shots = shots;
	}

	public Vector2f getHudPos() {
		return hudPos;
	}


	public void setHudPos(Vector2f hudPos) {
		this.hudPos = hudPos;
	}


	public float getBarrelAng() {
		return barrelAng;
	}


	public void setBarrelAng(float barrelAng) {
		this.barrelAng = barrelAng;
	}


	public int getPower() {
		return power;
	}


	public void setPower(int power) {
		this.power = power;
	}


	public int getGas() {
		return gas;
	}


	public void setGas(int gas) {
		this.gas = gas;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public ArrayList<Shot> getShots() {
		return shots;
	}


	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}


	public Image getAngle_head() {
		return angle_head;
	}


	public void setAngle_head(Image angle_head) {
		this.angle_head = angle_head;
	}


	public int getShotIndex() {
		return shotIndex;
	}


	public void setShotIndex(int shotIndex) {
		this.shotIndex = shotIndex;
	}


	public int getGasLength() {
		return gasLength;
	}


	public void setGasLength(int gasLength) {
		this.gasLength = gasLength;
	}



}
