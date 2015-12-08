import java.awt.Color;


public class PlayerTwo extends Players{

	
	public PlayerTwo(int width, int height) {
		// TODO Auto-generated constructor stub
		setWidth(width);
		setHeight(height);
		setColor(Color.DARK_GRAY);
		setX( (width/4) * 3);
		setY(height - 100);
		setLeftY(height - 100);
		setRightY(height - 100);
		setRadius(30);
		setDx(0);
		setScore(0);
	}
	
	@Override
	public void move(int d) {
		// TODO Auto-generated method stub
		if(d >= (width/2)+radius+4 && d < width)
			x = d;
	}
	
	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
	}
	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
	}


	


	


}
