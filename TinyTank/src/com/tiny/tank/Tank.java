package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

/**
 * Player class. Tanks
 */
public class Tank {

	//Constants of size of tank
	private final int tankWidth = 20;
	private final int tankHeight = 10;

	//Constants of Tank
	private final int movementLimit = 80;
	
	//animation end and the counter for it.
	private final float animationLimit = 1;
	private float animationCounter;

	// stores the data of the player
	private Vector2f pos;
	// Angle of the player
	private float barrelAng;
	// previous angle of the player
	private float prevAng;
	private int direction;
	private int health;
	private int shotIndex;
	private int power;
	private int gas;
	// index of the player
	private int index;
	private ArrayList<Shot> shots;
	private HUD hud;
	private Rectangle hitbox;
	//Keeps an original incase resiszing must happen
	private Image originalImage;
	private Image image;
	private Image originalBarrel;
	private Image BarrelImage;
	private Sound ShotSound = null;
	private Sound tankSound = null;
	private Sound barrelNoise = null;
	
	// x and y ranges of numbers. Will be usefull when we get rotations
	private float[] xRange;
	private float[] yRange;

	//Only so much movement allowed per turn. 
	private int movementCounter;

	//States
	private boolean isMoving;
	private boolean isFalling;
	private boolean isShooting;
	//flag for if turn
	private boolean isTurn;
	//stat objects
	private Stat stat;
	private boolean shotHit;
	private int damageThisTurn;
	
	
	public void TankInfo(float playerX, float playerY, float barrelAng,int health, ArrayList<Shot> shots, int index, HUD hud) throws SlickException {
		TankInfo(playerX,playerY,barrelAng,health,shots,index,hud);
	}

	
	/**
	 * Our good old players
	 * 
	 * @param playerX
	 *            X position of top right
	 * @param playerY
	 *            Y position of top right
	 * @param barrelAng
	 *            The angle of the barrel
	 * @param health
	 *            Player health
	 * @param shots
	 *            List of weapons
	 * @param index
	 *            Player number
	 * @throws SlickException 
	 */
	public void TankInfo(float playerX, float playerY, float barrelAng,int health, ArrayList<Shot> shots, int index, int power, int gas) throws SlickException {
		this.pos = new Vector2f(playerX, playerY);
		this.barrelAng = barrelAng;
		this.prevAng = barrelAng;
		this.health = health;
		this.index = index;
		this.shots = shots;
		this.stat = new Stat(getMovementLimit(),shots.size());
		this.power = power;
		this.gas = gas;
		ShotSound = new Sound("res/SoundFX/Tank_Shooting.ogg");
		tankSound = new Sound("res/SoundFX/tank_tracks.ogg");
		barrelNoise = new Sound("res/SoundFX/highPitched.ogg");
		
		//this.hud = new HUD(barrelAng, power, gas, health, shots, index, new Vector2f(0,510));
		
		// player1 looks right, player 2 looks left
		if (index == 1) {
			direction = 1;
			try {
				
				//tank
				originalImage = new Image("res/BlueTank.png");
				originalImage.setFilter(Image.FILTER_NEAREST);
				image = originalImage.getScaledCopy(tankWidth, tankHeight);
				
				
				//barrel (separate so it can change angle)
				originalBarrel = new Image("res/BlueBarrel.png");
				originalBarrel.setFilter(Image.FILTER_NEAREST);
				BarrelImage = originalBarrel.getScaledCopy(tankWidth,tankHeight);
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			direction = -1;
			try {
				originalImage = new Image("res/RedTank.png");
				originalImage.setFilter(Image.FILTER_NEAREST);
				image = originalImage.getScaledCopy(tankWidth, tankHeight);
				
				//barrel (separate so it can change angle)
				originalBarrel = new Image("res/RedBarrel.png");
				originalBarrel.setFilter(Image.FILTER_NEAREST);
				BarrelImage = originalBarrel.getScaledCopy(tankWidth, tankHeight);

			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//sets states
		isMoving = false;
		isFalling = false;
		isShooting = false;

		//sets range and hitbox
		hitbox = new Rectangle(playerX, playerY, tankWidth, tankHeight);
		xRange = new float[2];
		yRange = new float[2];
		xRange = calculateXRange(hitbox);
		yRange = calculateYRange(hitbox);

		this.hud = new HUD(this);
		//initializes player 1 to go first;
		if(index == 1){
			onTurnSwitch();
			
		}else{
			isTurn = false;
		}
		//start animation over
		resetAnimationCounter();
		animationCounter = 0;
		
		setFirstPos();

	}

	

	/**
	 * Will set the position upon map creation. Puts it right on top of map
	 */
	public void setFirstPos() {
		pos.y = Main_Gameplay.map.getMaxInRange(xRange[0], xRange[1])
				- tankHeight + 0;
		hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
	}

	/**
	 * takes care of things that happen on switch of turns. Also resets movement limit here.
	 * This will allow for speed based attacks later (such as a movement booster).
	 */
	public void onTurnSwitch() {
		setMovementCounter(0);;
		animationCounter = 0;
		
		isTurn = true;
		hud.onTurnSwitch(shots);
	}
	
	/**
	 * What to do when done.
	 * Currently shot calls this when done.
	 */
	public void shotDone(){
		if(isShooting){
			isShooting = false;
			isFalling = true;
			shots.remove(shotIndex);
			turnEnd();
		}else{
			isFalling = true;
		}
		System.out.println("shot length" + shots.size());
		getStat().updateShots(shots.size());
	}
	
	/**
	 * Clean up for when turn is over.
	 */
	public void turnEnd(){
		if(shotHit) {
			getStat().setShots_hit(getStat().getShots_hit() + 1);
		}
		//send turns total damage to Stat and then reset counter for next turn
		getStat().addToDamage(getDamageThisTurn());
		setDamageThisTurn(0);
		isTurn = false;
	}


	/**
	 * Will update the events relating to tanks and shots
	 */
	public void update(GameContainer container,StateBasedGame game, Camera cam) {

		
		Input input = container.getInput();
		// state handeling if falling
		if (isFalling) {
			isMoving = false;
			animationCounter += 1;
			if (animationCounter > animationLimit) {

				pos.y += 1;
				hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
				if (checkCollision(pos)) {
					pos.y -= 1;
					hitbox.setBounds(pos.x, pos.y, tankWidth, tankHeight);
					isFalling = false;
					animationCounter = 0;
				}
				animationCounter -= animationLimit;
			}
		}
		


		// state handeling if shooting
		if (isShooting) {
			for (int i = 0; i < shots.size(); i++) {
				if (shots.get(i).isShot()) {
					shots.get(i).update(container);
				}
			}
		}
		
		//will execute if sitting essentially
		if(!isShooting && !isFalling && !isMoving && isTurn)
		{
			//if the shoot key is pushed, inilize shot and start shooting
			if(input.isKeyPressed(Input.KEY_SPACE)){
				stat.addToMovement(getMovementCounter());
				ShotSound.play(.9f, .8f);
				shotIndex = hud.getShotIndex();
				power = hud.getPower();
				float barrelA = Math.abs(hud.getBarrelAng());
				float calcedPow = ((float)power)/100 * 8;
				float xComp = (float) (calcedPow * Math.cos(Math.toRadians(barrelA)) * direction);
				float yComp = (float) (calcedPow * Math.sin(Math.toRadians(barrelA)));
				getShots().get(shotIndex).init(new Vector2f(pos.x,pos.y),new Vector2f(xComp,yComp));
				setShooting(true);
			
			}

				
			
			
				BarrelImage.setCenterOfRotation(BarrelImage.getWidth()/2,BarrelImage.getHeight());

				
				if(input.isKeyDown(Input.KEY_Q)){
					if(power>0){
						power--;
						hud.setPower(power);
					}
				}

				if(input.isKeyDown(Input.KEY_E)){
					if(power < 100){
						power++;
						hud.setPower(power);
					}
				}
				
				// angle the barrel up	
				if(input.isKeyDown(Input.KEY_W))
				{

					// if its facing left
					if (direction == -1)
						{
							if(BarrelImage.getRotation() < 90){
								BarrelImage.rotate(1);
								barrelAng = BarrelImage.getRotation();
								hud.setBarrelAng(barrelAng);
							}
						}
									
					// facing right
					if (direction == 1)
						{
							if(BarrelImage.getRotation() > -90){
								BarrelImage.rotate(-1);
								barrelAng = BarrelImage.getRotation();
								hud.setBarrelAng(barrelAng);
							}
						}
						
								
				}
			

					// angle the barrel Down
				if(input.isKeyDown(Input.KEY_S))
				{
					
					// facing left
					if (direction == -1)
						{
							if(BarrelImage.getRotation() > -30){ // set lower limit
								BarrelImage.rotate(-1);
								barrelAng = BarrelImage.getRotation();
								hud.setBarrelAng(barrelAng);
							}
						}
					// facing right
					if (direction == 1)
						{

							if(BarrelImage.getRotation() < 30){ // set lower limit
								BarrelImage.rotate(1);
								barrelAng = BarrelImage.getRotation();
								hud.setBarrelAng(barrelAng);
							}
						}
				}
				
				// play barrel moving sound 
				if (input.isKeyDown(Input.KEY_S)||input.isKeyDown(Input.KEY_W)) 
				{ 
					if (!barrelNoise.playing()) 
					{ 
						barrelNoise.loop(.2f, .4f); 
					} 
		
				} else 
					{ 
						if (barrelNoise.playing()) 
						{ 
							barrelNoise.stop(); 
						} 
					} 
			gas = movementLimit-movementCounter;
			hud.setGasLength(gas);
			hud.update(container, game);
			barrelAng = hud.getBarrelAng();
			BarrelImage.setRotation(barrelAng);
		}


		
	}

	/**
	 * Takes care of movement of tank.
	 * @param input
	 */
	public void move(Input input) {
		
		//wont move is shooting or falling
		if (!isShooting && !isFalling) {
			//checks if player has used all movement alloted this turn
			if (getMovementCounter() < getMovementLimit()) {
				float tankMovement = .5f;
				if (input.isKeyDown(Input.KEY_A)) {
					incrementMovementCounter();
					movementCounter += 1;
					isMoving = true;
					pos = movePos(-tankMovement, 0);
					isFalling = true;
					gas = movementLimit-movementCounter;
				} else if (input.isKeyDown(Input.KEY_D)) {
					movementCounter += 1;
					isMoving = true;
					pos = movePos(tankMovement, 0);
					isFalling = true;
					gas = movementLimit-movementCounter;
				} else {

					isMoving = false;
				}
			}else{
				
				isMoving = false;
			}
			
			// play tank moving sound 
			if (isMoving == true) 
			{ 
				if (!tankSound.playing()) 
				{ 
				tankSound.loop(); 
				} 
	
			} else 
				{ 
					if (tankSound.playing()) 
					{ 
						tankSound.stop(); 
					} 
				} 
			

			
			
		}
		//gas=movementLimit-movementCounter;
	}

	

	/**
	 * Does the actual movement and returns new position. 
	 * Moves and then resolves collision.
	 * If height change is too much, will return old pos.
	 * @param x Amount to move in the x direction
	 * @param y Amount to move in the y drection
	 * @return the new position
	 */
	private Vector2f movePos(float x, float y) {
		Vector2f tempPos = pos.copy();
		tempPos.x += x;
		tempPos.y += y;

		int tolerance = 2;
		for (int i = 0; i < tolerance; i++) {
			if (checkCollision(tempPos)) {
				tempPos.y -= 1;
			} else {
				return tempPos;
			}
		}

		return pos;
	}

	/**
	 * Anything needed to render the tanks goes here.
	 * 
	 * @param container
	 * @param game
	 * @param g
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g, Camera cam) {
		// current graphical representation
		BarrelImage.setCenterOfRotation(BarrelImage.getWidth()/2*cam.getScale(),BarrelImage.getHeight()/2*cam.getScale());
		BarrelImage.draw(cam.transformScreenToCamX(pos.x), cam.transformScreenToCamY(pos.y), cam.getScale());
		//tank
		image.draw(cam.transformScreenToCamX(pos.x), cam.transformScreenToCamY(pos.y), cam.getScale());
		//barrel
		//BarrelImage.draw(cam.transformScreenToCamX(pos.x+hitbox.getWidth()/2), cam.transformScreenToCamY(pos.y+hitbox.getHeight()/2), cam.getScale());
		if(isShooting){
			getShots().get(shotIndex).render(container, game, g, cam);
		}
		

		/*
		if(isTurn){
			hud.render(container, game, g);
		}*/
	}

	public void renderHud(GameContainer container, StateBasedGame game, Graphics g, Camera cam){
		hud.render(container, game, g);
	}
	/**
	 * Checks if tanks collide with terrain
	 * 
	 * @return
	 */
	public boolean checkCollision(Vector2f pos) {

		Rectangle hitbox = new Rectangle(pos.x, pos.y, tankWidth, tankHeight);
		float[] xRange = calculateXRange(hitbox);
		float[] yRange = calculateYRange(hitbox);

		// checks if outside the map
		if (hitbox.getMaxX() > Main_Gameplay.map.getWidth() - 1
				|| hitbox.getMinX() < 0
				|| hitbox.getMaxY() > Main_Gameplay.map.getHeight() - 1
				|| hitbox.getMinY() < 0) {
			return true;
		}

		// gets teh highest point under the tank
		int mapMaxInRange = Main_Gameplay.map.getMaxInRange(xRange[0],
				xRange[1]);
		// if lowest corner is below mapmax does collision check
		if (yRange[1] > mapMaxInRange) {
			for (int i = (int) xRange[0]; i < xRange[1]; i++) {
				for (int j = (int) yRange[0]; j < yRange[1]; j++) {
					if (Main_Gameplay.map.collision(new Vector2f(i, j))) {
						if (hitbox.contains(i, j)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * recalculates range if needs to
	 */
	/*
	 * private void checkRange() { if (!hasRangeChanged) { return; }
	 * 
	 * calculateXRange(); calculateYRange(); }
	 */

	private float[] calculateXRange(Rectangle hitbox) {

		float left = hitbox.getMinX();
		float right = hitbox.getMaxX();

		float[] xRange = new float[2];
		xRange[0] = left;
		xRange[1] = right;
		return xRange;
	}
	
	private float[] calculateYRange(Rectangle hitbox) {

		float top = hitbox.getMinY();
		float bottom = hitbox.getMaxY();

		float[] yRange = new float[2];
		yRange[0] = top;
		yRange[1] = bottom;
		return yRange;
	}
	private void resetAnimationCounter() {
		setAnimationCounter(0);
	}
	private void incrementMovementCounter() {
		setMovementCounter(getMovementCounter()+1);
	}
	
	public void addToDamage(int amount) {
		setDamageThisTurn(amount + getDamageThisTurn() );
	}
	
	/**
	 * series of gets and sets to assign the variables of the tank
	 * the given and calculated values
	 */
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
	
	public HUD getHud() {
		return hud;
	}
	
	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public int getHealth() {
		return health;
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

	public void setHealth(int health) {
		this.health = health;
	}

	public float getPlayerDir() {
		return barrelAng;
	}

	public void setPlayerDir(int playerDir) {
		prevAng = this.barrelAng;
		this.barrelAng = playerDir;

	}

	public float getPrevDir() {
		return prevAng;
	}

	public boolean isAlive() {
		return health > 0 ? true : false;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public float getBarrelAng() {
		return barrelAng;
	}

	public void setBarrelAng(float barrelAng) {
		this.barrelAng = barrelAng;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	public boolean isTurn() {
		return isTurn;
	}
	
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public int getMovementCounter() {
		return movementCounter;
	}

	public void setMovementCounter(int movementCounter) {
		this.movementCounter = movementCounter;
	}

	public int getMovementLimit() {
		return movementLimit;
	}

	public float getAnimationCounter() {
		return animationCounter;
	}

	public void setAnimationCounter(float animationCounter) {
		this.animationCounter = animationCounter;
	}



	public Stat getStat() {
		return stat;
	}



	public void setStat(Stat stat) {
		this.stat = stat;
	}


	public boolean isShotHit() {
		return shotHit;
	}


	public int getDamageThisTurn() {
		return damageThisTurn;
	}


	public void setDamageThisTurn(int damageThisTurn) {
		this.damageThisTurn = damageThisTurn;
	}


	public void setShotHit(boolean shotHit) {
		this.shotHit = shotHit;
	}
}
