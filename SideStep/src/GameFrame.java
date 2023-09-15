import java.awt.*;
import javax.swing.*;
public class GameFrame extends JFrame{
	
	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		ImageIcon image = new ImageIcon("Resources/Sprites/logo.png");
		this.setTitle("SideStepGame");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(image.getImage());
	}
}
