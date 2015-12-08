import java.awt.Color;
import java.util.LinkedList;



public abstract class Shapes {
	
	int x;
	int y;
	private int type;
	private int speed;
	private int width;
	private int height;
	private int fallingedge;
	Color color;
	
	public int update(LinkedList<Shapes> unused, LinkedList<Shapes> used, int width, int height){
		int begin = -1;
		if( (getSpeed() > 0 && x + getSpeed() < getFallingedge()) || (getSpeed() < 0 && x +getSpeed() > getFallingedge()) ){
			if( x == 200 || x == width-200)
				begin = this.getType();
			x += getSpeed();
		}
		else if( (y + 2*Math.abs(getSpeed()) > height + getHeight()) ){
			used.remove(this);
			unused.add(this);
		}
		else{
			
			if(y == 120 )
				begin = this.getType();
			y += 2*Math.abs(getSpeed());
		}
		return begin;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public int getFallingedge() {
		return fallingedge;
	}
	public void setFallingedge(int fallingedge) {
		this.fallingedge = fallingedge;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
