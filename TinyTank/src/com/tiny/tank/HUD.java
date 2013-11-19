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
	
	private Image hud_background;
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
	private Button lmove;
	private Button rmove;
	private Button langle;
	private Button rangle;
	private Button lpower;
	private Button rpower;
	private Vector2f hudPos;
	private int shotIndex;
	private Tank tank;
	
	private int gasLength;
	
	public void loadImages() throws SlickException{
		//hud_background = new Image("res/hud_background.png");
		weapons_head = new Image("res/weapons.png");
		move_head = new Image("res/Move.png");
		angle_head = new Image("res/Angle.png");
		power_head = new Image("res/Power.png");
		lweapons = new Button("res/LeftArrow.png", (int) (hudPos.x+435), (int) (hudPos.y+50));
		rweapons = new Button("res/RightArrow.png", (int) (hudPos.x+555), (int) (hudPos.y+50));
		//lmove = new Button("res/Left Arrow.png", 300, 500);
		//rmove = new Button("res/Right Arrow.png", 500, 500);
		langle = new Button("res/LeftArrow.png", (int) (hudPos.x+215), (int) (hudPos.y+50));
		rangle = new Button("res/RightArrow.png", (int) (hudPos.x+335), (int) (hudPos.y+50));
		lpower = new Button("res/LeftArrow.png", (int) (hudPos.x+640), (int) (hudPos.y+50));
		rpower = new Button("res/RightArrow.png", (int) (hudPos.x+760), (int) (hudPos.y+50));
			
	}
	
	
	//public HUD(float barrelAng, int power, int gas, 
	//		int health, ArrayList<Shot> shots, int index, Vector2f hudPos, Tank tank) throws SlickException {
	
	public HUD(Tank tank) throws SlickException {
		
		this.tank = tank;
		this.barrelAng = tank.getBarrelAng();
		this.power = tank.getPower();
		this.gas = tank.getGas();
		this.health = tank.getHealth();
		this.shots = tank.getShots();
		this.index = tank.getIndex();
		//this.hudPos = hudPos;
		hudPos = new Vector2f(0,510);
		loadImages();
		shotIndex = 0;
		gasLength = tank.getGas();
		
	}
	
	
	/*
	public HUD(float barrelAng, int power, int gas, int health,
			ArrayList<Shot> shots, int index, Vector2f vector2f) throws SlickException {
		// TODO Auto-generated constructor stub
		//this.tank = tank;
		this.barrelAng = tank.getBarrelAng();
		this.power = tank.getPower();
		this.gas = tank.getGas();
		this.health = tank.getHealth();
		this.shots = tank.getShots();
		this.index = tank.getIndex();
		//this.hudPos = hudPos;
		hudPos = new Vector2f(0,510);
		loadImages();
		shotIndex = 0;
	}
	*/

	public void enter(GameContainer container, StateBasedGame game){
		
		try {
			hud_background = new Image(container.getWidth(), 200);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// current graphical representation
		//hud_background.draw();
		Color prevCol = g.getColor();
		g.setColor(new Color(0, 0,0, 300));
		g.fillRect(hudPos.x, hudPos.y, container.getWidth(), 90);
		//g.draw(new Rectangle(0,hudY,container.getWidth(),90));
		weapons_head.draw((int) (hudPos.x+435),(int)(hudPos.y+10));
		angle_head.draw((int)(hudPos.x+215),(int)(hudPos.y+10));
		power_head.draw((int)(hudPos.x+640),(int)(hudPos.y+10));
		move_head.draw((int)(hudPos.x+10),(int)(hudPos.y+10));
		rweapons.drawButton(g);
		lweapons.drawButton(g);
		//rmove.drawButton(g);
		//lmove.drawButton(g);
		rangle.drawButton(g);
		langle.drawButton(g);
		rpower.drawButton(g);
		lpower.drawButton(g);
		//Gas rectangle
		g.setColor(Color.red);
		g.fillRect(hudPos.x+10, hudPos.y+57 , gasLength, 10);
		g.setColor(Color.white);
		//Angle
		g.drawString(Float.toString(barrelAng), (int)(hudPos.x+260), (int)(hudPos.y+57));
		g.drawString(Integer.toString(power), (int)(hudPos.x+705), (int)(hudPos.y+57));
	
		if(shots.size() > 0){
			g.drawString(shots.get(shotIndex).getShotName(), (int)(hudPos.x+465), (int)(hudPos.y+50));
		}
		//g.drawString(shots.get(shotIndex).getShotName(), (int)(hudPos.x+465), (int)(hudPos.y+57));
		
		g.drawString(Integer.toString(gas),0,0);
		
		g.setColor(prevCol);

	}
	
	public void update(GameContainer container, StateBasedGame game){
		
		//barrelAng.getBarrelAng();
		
		
		input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			//input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) {
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
			/*
			else if (rmove.isMouseOverButton(posX, posY)) {
				
			}
			else if (lmove.isMouseOverButton(posX, posY)) {
				
			}
			*/
		input = container.getInput();
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				//input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) {
				int pos_X = input.getMouseX();
				int pos_Y = input.getMouseY();
			
			if (rangle.isMouseOverButton(pos_X, pos_Y)) {
				System.out.println("rangle!");
				if(barrelAng < 180){
					barrelAng++;
				}
			}
			else if (langle.isMouseOverButton(pos_X, pos_Y)) {
				System.out.println("langle!");

				if(barrelAng > 0){
					barrelAng--;
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
