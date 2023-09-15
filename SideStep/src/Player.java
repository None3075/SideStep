import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends Entity{
	

	int speed = 3;
	Image PlayerDefaultImage = new ImageIcon("Resources/Sprites/playerimagedef.png").getImage();
	Image PlayerRightImage = new ImageIcon("Resources/Sprites/playerimageright.png").getImage();
	Image PlayerLeftImage = new ImageIcon("Resources/Sprites/playerimageleft.png").getImage();
	Image PlayerUpImage = new ImageIcon("Resources/Sprites/playerimageup.png").getImage();
	Image PlayerDownImage = new ImageIcon("Resources/Sprites/playerimagedown.png").getImage();
	Image PlayerCurrentImage=PlayerDefaultImage;
	Image PlayerInv1 = new ImageIcon("Resources/Sprites/playerinv1.png").getImage();
	Image PlayerInv2 = new ImageIcon("Resources/Sprites/playerinv2.png").getImage();
	Image PlayerInv3 = new ImageIcon("Resources/Sprites/playerinv3.png").getImage();
	Image PlayerInv4 = new ImageIcon("Resources/Sprites/playerinv4.png").getImage();
	Image PlayerInv5 = new ImageIcon("Resources/Sprites/playerinv5.png").getImage();
	Image PlayerInv6 = new ImageIcon("Resources/Sprites/playerinv6.png").getImage();
	Image PlayerInv7 = new ImageIcon("Resources/Sprites/playerinv7.png").getImage();
	
	int SpriteHeight=height+20; // +20 is to make the sprite match the hitbox
	int SpriteWidth=width+20;
	int counter=1;
	int counter2=0;
	
	
	Player(int x, int y, int PLAYER_WIDTH, int PLAYER_HEIGHT){
		super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
	}
	
	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(-speed);
			PlayerCurrentImage = PlayerUpImage;

		}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			setXDirection(-speed);
			PlayerCurrentImage = PlayerLeftImage;

		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(speed);
			PlayerCurrentImage = PlayerDownImage;

		}
		if (e.getKeyCode()==KeyEvent.VK_D) {
			setXDirection(speed);
			PlayerCurrentImage = PlayerRightImage;

		}
		
	}	
	
	public void KeyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(0);
			PlayerCurrentImage=PlayerDefaultImage;
		}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			setXDirection(0);
			PlayerCurrentImage=PlayerDefaultImage;
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(0);
			PlayerCurrentImage=PlayerDefaultImage;
		}
		if (e.getKeyCode()==KeyEvent.VK_D) {
			setXDirection(0);
			PlayerCurrentImage=PlayerDefaultImage;
		}
		
	}
	
	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}
	
	public void setXDirection(int xDirection){
		xVelocity = xDirection;
	}
	
	public void move() {
		y = y + yVelocity;
		x = x + xVelocity;
	}
	
	public void draw(Graphics g) {
		if(GamePanel.invincibilitycheck) {
			switch (counter) { 
		    	case 1:
		    		g.drawImage(PlayerInv1, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 2:
		    		g.drawImage(PlayerInv2, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 3:
		    		g.drawImage(PlayerInv3, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 4:
		    		g.drawImage(PlayerInv4, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 5:
		    		g.drawImage(PlayerInv5, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 6:
		    		g.drawImage(PlayerInv6, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    	case 7:
		    		g.drawImage(PlayerInv7, x-10, y-10, SpriteHeight, SpriteWidth, null);
		    		counter2++;
		    		break;
		    
		  }
			if (counter2==10) {
				if(counter==7) {
					counter=1;
					counter2=0;
				}
				else {
				counter2=0;
				counter++;
				}
			}
		}
		else {
		g.drawImage(PlayerCurrentImage, x-10, y-10, SpriteHeight, SpriteWidth, null);
		}
		}
	}
