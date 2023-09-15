import java.awt.*;
import javax.swing.*;

public class Animation extends Rectangle{

    Image explosion = new ImageIcon("Resources/Sprites/explosion.png").getImage();
	boolean explosioncheck=false;
	int explosionloop=240;
    int incremento=31;

    Animation(int x, int y){
		super(x,y,250,250);
	}

    public void draw(Graphics g){
		if (explosionloop == 0) {
			
		}
		else {
			if (explosionloop%10==0 && explosionloop!=360) {
                incremento= incremento +32;
			}
            g.drawImage(explosion, x-400+13, y-400+13, x+400+13, y+400+13, incremento-31, 0, incremento, 29, null);
			explosionloop--;
		}
    }
}

