import java.awt.Color;


public class PlayerOne extends Players {

	
	public PlayerOne(int width, int height) {
		// TODO Auto-generated constructor stub
		setWidth(width);
		setHeight(height);
		setColor(Color.RED);
		setX(width/4);
		setY(height-100);
		setLeftY(height - 100);
		setRightY(height - 100);
		setRadius(30);
		setDx(14);
		setScore(0);
	}
	
	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		if(x + dx <= (width/2) -radius - 37 + 1 )
			x += dx;
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		if(x - dx >= 0)
			x -= dx;
	}
	

	@Override
	public void move(int d) {
		// TODO Auto-generated method stub
	}

	
	

	


}
