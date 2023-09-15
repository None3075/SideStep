import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
	
	static final int GAME_WIDTH=1000;
	static final int GAME_HEIGHT=500;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 25;
	static final int PLAYER_WIDTH = 25;
	static final int PLAYER_HEIGHT = 25;
	static int loopball=120;
	static int superpowerloop=360;
	static int timesuperpowerloop=0;
	static int invincibilitysuperpowerloop=0;
	static boolean slowtime = false;
	static boolean slowtimecheck = false;
	static boolean invincibility = false;
	static boolean invincibilitycheck = false;
	static boolean loopboolean=true;
	boolean pause=false;
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Superpowers> superpowers = new ArrayList<Superpowers>();
	ArrayList<Superpowers> toRemoves = new ArrayList<Superpowers>();
	static ArrayList<Animation> explosiones = new ArrayList<Animation>();
	Thread gameThread;
	Image image;
	Image backgroundImage= new ImageIcon("Resources/Sprites/fondo.jpg").getImage();
	Image explosion= new ImageIcon("Resources/Sprites/explosion.png").getImage();
	Graphics graphics;
	Random random;
	Player player;
	Ball ball;
	Score score;
	Superpowers superpower;
	
	GamePanel(){
		newPlayer();
		newSuperpower();
		loopboolean=true;
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new KeyHandler());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		int ballx;
		int bally;
		do {
			ballx = random.nextInt(GAME_WIDTH-BALL_DIAMETER);
			bally = random.nextInt(GAME_HEIGHT-BALL_DIAMETER);
		}while(Point2D.distance(ballx,bally, player.x, player.y)<500);
		ball = new Ball(ballx,bally,BALL_DIAMETER,BALL_DIAMETER);
		balls.add(ball);
	}
	public void newSuperpower() {
		random = new Random();
		int Superpowerx;
		int Superpowery;
		int Superpowerid;
		Superpowerx = random.nextInt(GAME_WIDTH-BALL_DIAMETER);
		Superpowery = random.nextInt(GAME_HEIGHT-BALL_DIAMETER);
		Superpowerid = random.nextInt(3);
		superpower = new Superpowers(Superpowerx,Superpowery,BALL_DIAMETER,BALL_DIAMETER,Superpowerid);
		superpowers.add(superpower);
	}
	
	public void newPlayer() {
		player = new Player((GAME_WIDTH/2)-(PLAYER_WIDTH/2),(GAME_HEIGHT/2)-(PLAYER_HEIGHT/2),PLAYER_WIDTH,PLAYER_HEIGHT);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		g.drawImage(backgroundImage, 0, 0,GAME_WIDTH, GAME_HEIGHT, null);
		player.draw(g);
		for (int i =0; i<balls.size();i++) {
			balls.get(i).draw(g);
		}
		for (int i =0; i<superpowers.size();i++) {
			superpowers.get(i).draw(g);
		}
		for (int i =0; i<explosiones.size();i++) {
			explosiones.get(i).draw(g);
		}
		score.draw(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void move() {
		player.move();
		for (int i =0; i<balls.size();i++) {
			balls.get(i).move();
		}
	}
	
	public void checkCollision() {
		if(player.x <= 0) {
			player.x =0;
		}
		else if (player.x >= GAME_WIDTH-PLAYER_WIDTH) {
			player.x = GAME_WIDTH - PLAYER_WIDTH;
		}
		if (player.y<=0) {
			player.y=0;
		}
		else if (player.y>= GAME_HEIGHT-PLAYER_HEIGHT) {
			player.y=GAME_HEIGHT-PLAYER_HEIGHT;
		}
		for (int i =0; i<balls.size();i++) {
			if(balls.get(i).x <= 0) {
				balls.get(i).setXDirection(-balls.get(i).xVelocity);
			}
			else if (balls.get(i).x >= GAME_WIDTH-BALL_DIAMETER) {
				balls.get(i).setXDirection(-balls.get(i).xVelocity);
			}
			if (balls.get(i).y<=0) {
				balls.get(i).setYDirection(-balls.get(i).yVelocity);
			}
			else if (balls.get(i).y>= GAME_HEIGHT-BALL_DIAMETER) {
				balls.get(i).setYDirection(-balls.get(i).yVelocity);
			}
			if (balls.get(i).intersects(player)) {
				if(invincibilitycheck != true) {
					System.out.println("GAME OVER");
					score.savescore();
					loopboolean=false;
					Usernamewindow.restart();
					
				}
			}
		}
		for (Superpowers superpower: superpowers) {
			if (superpower.intersects(player)) {
				superpower.power(balls);
				toRemoves.add(superpower);
			}
		}
		superpowers.removeAll(toRemoves);
	}
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = TimeSuperpower();
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(loopboolean) {
			amountOfTicks = TimeSuperpower();
			ns = 1000000000 / amountOfTicks;
			long now = System.nanoTime();
			CheckFlags();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				move();
				checkCollision();
				repaint();
				Ballloop();
				Superpowerloop();
				StartTimeTimer();
				StartInvincibilityTimer();
				delta--;
			}
		}
	}
	private void CheckFlags() {
		if (slowtime == true) {
			slowtimecheck=true;
		}
		if (invincibility == true) {
			invincibilitycheck=true;
		}
	}

	public int TimeSuperpower() {
		if (slowtime) {
			return 30;
		}
		else {
			return 120;
		}
	}
	public void StartTimeTimer() {
		if(slowtimecheck) {
			if (timesuperpowerloop == 0) {
				slowtime=false;
				slowtimecheck=false;
			}
			else {
				timesuperpowerloop--;
			}
		}
	}
	public void StartInvincibilityTimer() {
		if(invincibilitycheck) {
			if (invincibilitysuperpowerloop == 0) {
				invincibility=false;
				invincibilitycheck=false;
			}
			else {
				invincibilitysuperpowerloop--;
			}
		}
		
	}
	public void Ballloop() {
		if (loopball == 0) {
			newBall();
			loopball=120;
		}
		else {
			loopball--;
		}
	}
	public void Superpowerloop() {
		if (superpowerloop == 0) {
			newSuperpower();
			superpowerloop=360;
		}
		else {
			superpowerloop--;
		}
	}
	
	public class KeyHandler implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.KeyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.KeyReleased(e);
			
		}
	}
}