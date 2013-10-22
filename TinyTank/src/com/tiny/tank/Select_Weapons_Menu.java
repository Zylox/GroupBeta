package com.tiny.tank;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.shots.Shots;


public class Select_Weapons_Menu extends BasicGameState  {
	private int id;
	private Image title = null;
	private Image p1_title=null;
	private Image p2_title=null;
	private Image button_play= null;
	private Image button_menu= null;
	private Image ammo_title= null;
	private boolean playButtonActive = false;

	public Shots[] shots = Shots.values();
	private int ycord = 200;
	private int playercount = 0;
	private int spacing = 20;
	private boolean even = false; 
	private boolean odd = false; 
	private Image background = null;
	private ArrayList<SimpleTempButton> buttons;
	private ArrayList<SimpleTempButton> p1weapons;
	private ArrayList<SimpleTempButton> p2weapons;
	
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
		
		@Override
		public void enter(GameContainer container, StateBasedGame game){
			
			int x = 300;
			int y = 200;
			//int spacing = 20;

			buttons = new ArrayList<SimpleTempButton>();
			p1weapons = new ArrayList<SimpleTempButton>();
			p2weapons = new ArrayList<SimpleTempButton>();
			
			
			
			for(Shots s : shots){
				buttons.add(new SimpleTempButton(new Vector2f(x,y),200,spacing,s.getShot()));
				y+=spacing;
			}
		}
			

		public void render(GameContainer container, StateBasedGame game, Graphics g)
		throws SlickException {
			
			background.draw(0,0);
			title.draw(0,0);
			if (playButtonActive)
			{
				button_play.draw(500,520);
			}

			button_menu.draw(80,520);
			p1_title.draw(30, 150);
			p2_title.draw(540,150);
			ammo_title.draw(235,80);
			

			//Transparent boxes for weapon selection
			g.setColor(new Color(300,300,300, 0.5f));
			g.fillRect(60, 200, 170, 300);
			g.fillRect(560,200, 170, 300);
			
			g.setColor(new Color(176, 176, 176, 0.8f));
			g.fillRect(300, 200, 200, 300);
			
			g.setColor(Color.black);
			for(SimpleTempButton s : buttons){
				s.render(container, game, g);
			}
			for(SimpleTempButton s : p1weapons){
				s.render(container, game, g);
			}
			for(SimpleTempButton s : p2weapons){
				s.render(container, game, g);
			}
			g.setColor(Color.white);
			

			
		
		}

		

		public void update(GameContainer container, StateBasedGame game, int delta)
		throws SlickException {
			
			int posX = Mouse.getX();
			int posY = Mouse.getY();
		
						
			boolean clicked = container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
			for(int s = 0;s<buttons.size();s++){
				if(buttons.get(s).update(container.getInput(), clicked)){
					System.out.println(buttons.get(s).getShot().getShotName());
					ButtonPressed(buttons.get(s), ycord);
					
					
				}
			}
			
			if (playButtonActive)
			{
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
			
				
		}

		private void ButtonPressed(SimpleTempButton s, int y) {
		 
			if(playercount%2 == 0)
			{
				even = true;
				odd = false;
			}
			else
			{
				even = false;
				odd = true;
			}
					
			// even number selected weapons go to Player 1
			if (even)
				{
					int x1 = 60;
					
					p1weapons.add(new SimpleTempButton(new Vector2f(x1,ycord),173,spacing,s.getShot()));
					playercount += 1;
					System.out.println("EVEN!!  selection #:"+ playercount);
				
				}
				// odd number selected weapons go to Player2
				else if (odd)
				{
					int x2 = 560;
					
					p2weapons.add(new SimpleTempButton(new Vector2f(x2,ycord),173,spacing,s.getShot()));
					// increment Y value by 20 to put the next button below the previous
					ycord += spacing;
					playercount += 1;
					System.out.println("ODD!!  selection #:"+ playercount);
				}
			
			// if all weapons are choosen, enable play button
			if(playercount -1 >= buttons.size())
				playButtonActive=true;
							
			buttons.remove(s);
	
		}

		

		public int getID() {
		return id;
		}

		
		
	}
