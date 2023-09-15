import java.awt.*;
abstract class Entity extends Rectangle{
	int yVelocity;
	int xVelocity;

	public Entity(int x, int y, int ENTITY_WIDTH, int ENTITY_HEIGHT) {
		super(x,y,ENTITY_WIDTH,ENTITY_HEIGHT);
	}
	abstract void draw(Graphics g);	
	abstract void move();
	abstract void setXDirection(int xDirection);
	abstract void setYDirection(int yDirection);

}
