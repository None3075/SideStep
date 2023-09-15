import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import javax.swing.ImageIcon;

public class Superpowers extends Rectangle{

	Random random;
	int id;
	Image SuperpowerTimeSprite = new ImageIcon("Resources/Sprites/superpowertime.png").getImage();
	Image SuperpowerInvincibleSprite = new ImageIcon("Resources/Sprites/superpowerinvincible1.png").getImage();
	Image SuperpowerBombSprite = new ImageIcon("Resources/Sprites/superpowerbomb.png").getImage();
	Image explosion = new ImageIcon("Resources/Sprites/explosion.png").getImage();
	boolean explosioncheck=false;
	int explosionloop=360;

	Superpowers(int x, int y, int width, int height,int id){
		super(x,y,width,height);
		this.id = id;
	}
	
	public void draw(Graphics g) {
		switch (id) {
			case 0: {
				g.drawImage(SuperpowerTimeSprite, x, y, height, width, null);
				break;
			}
			case 1:{
				g.drawImage(SuperpowerBombSprite, x, y, height, width, null);
				break;
			}
			case 2:{
				g.drawImage(SuperpowerInvincibleSprite, x, y, height, width, null);
				break;
			}
		}
		}
	public void power( ArrayList<Ball> balls) {
		switch (id) {
		case 0: {
			System.out.println("tiempo ralentizado");
			GamePanel.slowtime = true;
			GamePanel.timesuperpowerloop=GamePanel.timesuperpowerloop+60;
			break;
		}
		case 1:{
			System.out.println("pelotas eliminadas");
			ArrayList<Ball> toRemoveb = new ArrayList<Ball>();
			for (int i =0; i<balls.size();i++) {
				if(Point2D.distance(balls.get(i).x,balls.get(i).y, x, y)<500){
					toRemoveb.add(balls.get(i));
				}
			}

			balls.removeAll(toRemoveb);
			Animation explosion = new Animation(x, y);
			GamePanel.explosiones.add(explosion);
			break;
		}
		case 2:{
			System.out.println("invencible");
			GamePanel.invincibility=true;
			GamePanel.invincibilitysuperpowerloop=GamePanel.invincibilitysuperpowerloop+360;
			break;
		}
		}
	}
}
