package com.tiny.tank;

import java.awt.TextField;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import com.tiny.weapons.shots.Shots;



public class Select_Weapons_Menu extends BasicGameState  {
	private int id;
	 Font font;
	  TextField textField;
	 

	private Image title = null;
	private Image p1_title=null;
	private Image p2_title=null;
	private Image button_play= null;
	private Image button_menu= null;
	private Image ammo_title= null;
	public Shots[] shots = Shots.values();
	private int total = shots.length;
	Image background = null;
	
	
	
	
	public Select_Weapons_Menu(int id){
			this.id = id;
		}
		
	
	
		public void init(GameContainer container, StateBasedGame game)
		throws SlickException {
			

			background = new Image("res/bg.jpg");
			title = new Image("res/Weapon_Select_ title.png");
			button_play= new Image("res/play_button.png");
			button_menu= new Image("res/back_button.png");
			p1_title= new Image("res/P1.png");
			p2_title= new Image("res/P2.png");
			ammo_title= new Image("res/ammo.png");
		
		}
			

		public void render(GameContainer container, StateBasedGame game, Graphics g)
		throws SlickException {
			
			background.draw(0,0);
			title.draw(0,0);
			button_play.draw(500,520);
			button_menu.draw(80,520);
			p1_title.draw(30, 150);
			p2_title.draw(540,150);
			ammo_title.draw(235,80);
			
			g.setColor(new Color(176, 176, 176, 0.8f));
			g.fillRect(300, 200, 200, 300);
			
			
			
			FillWeapons(container, game, g);
		
		}

		

		public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
			
			int posX = Mouse.getX();
			int posY = Mouse.getY();
			
			// If play game button is clicked on play game, enter the play game state
			if((posX>500 && posX<720) && (posY>20 && posY<80)){
				if(Mouse.isButtonDown(0)){

					game.enterState(STATES.MAIN_GAMEPLAY.getId());
				}
				
			}
				// If back button is clicked, change state to main menu state		
			if((posX>80 && posX<300) && (posY>20 && posY<80)){
				if(Mouse.isButtonDown(0)){
					game.enterState(STATES.MAIN_GAMEPLAY.getId());
				}
			}
			
			
					
		}

		public void FillWeapons(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
			int x = 320;
			int y = 200;

			g.setColor(Color.black);
			for(int i=0; i<total; i++)
			{
				String weapon = shots[i].toString();
				g.drawString(weapon, x, y);	
				System.out.println(shots[i]);
				y=y+20;// next weapon will be placed below the previous
			}
		}
		public int getID() {
		return id;
		}
		
		

		
		
		
	}
