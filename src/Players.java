import java.awt.Color;
import java.util.LinkedList;
import java.util.Stack;


public abstract class Players {

	protected int x ;
	protected int y ;
	protected int leftY ;
	protected int rightY ;
	
	protected int dx ;
	protected int radius ;
	protected int width ;
	protected int height ;
	protected int score ;
	protected Stack<Shapes> leftArm = new Stack<Shapes>();
	protected Stack<Shapes> rightArm = new Stack<Shapes>();
	private Color color;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public abstract void moveRight();
	public abstract void moveLeft();
	public abstract void move(int d);
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
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
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
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
	
	public int getLeftY() {
		return leftY;
	}
	public void setLeftY(int leftY) {
		this.leftY = leftY;
	}
	public int getRightY() {
		return rightY;
	}
	public void setRightY(int rightY) {
		this.rightY = rightY;
	}
	
	public void updateHands(int d) {
		// TODO Auto-generated method stub
		try {
			Stack<Shapes> temp = new Stack<Shapes>();
			while(!leftArm.isEmpty()){
				Shapes s = leftArm.pop();
				temp.push(s);
				s.setX(d + (radius/2) - 40);
			}
			while(!temp.isEmpty()){
				leftArm.push(temp.pop());
			}
			while(!rightArm.isEmpty()){
				Shapes s = rightArm.pop();
				temp.push(s);
				s.setX(d + (radius/2) + 40);
			}
			while(!temp.isEmpty()){
				rightArm.push(temp.pop());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
	}
	
	public void checkForCollision(LinkedList<Shapes> unused, LinkedList<Shapes> used) {
		// TODO Auto-generated method stub
		int range1 = x+(radius/2) - 15 - 50; // left stack begin 
		int range2 = x+(radius/2) + 15 - 50; // left stack end
		
		int range3 = x+(radius/2) - 15 + 30; // right stack begin
		int range4 = x+(radius/2) + 15 + 30; // right stack end
		LinkedList<Shapes> temp = new LinkedList<Shapes>();
		while(!used.isEmpty()){
			Shapes s = used.poll();
			if(s == null){
				System.out.println(rightY);
				break;
			}
			
			int height = s.getHeight() + s.getY();
			int point1 = s.getX();
			int point2 = s.getX() + s.getWidth();
			
			temp.add(s);
			if( (point1 >= range1 && point1 <= range2) || (point2 >= range1 && point2 <= range2)){
				if(height > leftY && height < leftY + 15){
					// left collision
					s.setSpeed(0);
					leftArm = removeShapes(leftArm, s, "left");
					updateHands(x);
				}
			}
			else if ((point1 >= range3 && point1 <= range4) || (point2 >= range3 && point2 <= range4)){
				if(height > rightY && height < rightY + 15){
					// right collision
					s.setSpeed(0);
					rightArm = removeShapes(rightArm, s, "right");
					updateHands(x);
				}
			}
		}
		while(!temp.isEmpty()){
			Shapes s = temp.pop();
			if(s != null)
				used.add(s);
		}
	}
	
	public Stack<Shapes> removeShapes(Stack<Shapes> s, Shapes sh , String type) {
		
		if(s.size() > 1){	
			Shapes s1 = s.pop();
			Shapes s2 = s.pop();
			if(sh.getColor() == s2.getColor() && sh.getColor() == s1.getColor()){
				if(type.equals("left"))
					leftY += s1.getHeight() + s2.getHeight() + 10;
				else if(type.equals("right")){
					rightY += s1.getHeight() + s2.getHeight() + 10;
				}
				
				score++;
				
				return s;
			}
			s.add(s2);
			s.add(s1);
		}
		s.add(sh);
		if(type.equals("left"))
			leftY -= sh.getHeight()+5;
		else if(type.equals("right")){
			rightY -= sh.getHeight()+5;
		}
		
		return s;
		
	}
}
