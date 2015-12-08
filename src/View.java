import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class View {
	
	
	private View() {
		// TODO Auto-generated constructor stub
	}
	
	static View v = new View();
	public static View getInstance(){
		return v;
	}
	
	public void paint(Graphics g, LinkedList<Shapes> unused, LinkedList<Shapes> used, int width, int height,
			Players P1, Players P2, int win1, int win2, int score1, int score2) {
		if(used != null){
			LinkedList<Shapes> temp = new LinkedList<Shapes>();
			while(!used.isEmpty()){
				Shapes s = used.pop();
				if(s == null)
					break;
				temp.add(s);
				paintShape(s, g);
			}
			while(!temp.isEmpty()){
				Shapes s = temp.pop();
				if(s != null)
					used.add(s);
			}
			
		}
		
		paintPlayer(g, P1);
		paintPlayer(g, P2);
		
		g.setColor(Color.BLACK);
		g.drawLine(width/2, 0, width/2, height);
		g.drawLine(0, 25, width, 25);
		g.drawLine(0, 150, width, 150);
		g.drawString("Player One", width/4, 20);
		g.drawString("Player two", width*3/4, 20);
		g.drawString("wins : " + win1, width/2 - 50, 45);
		g.drawString("wins : " + win2, width-340, 45);
		
		g.drawString("Score : " + score1, 5, 45);
		g.drawString("Score : " + score2, width-80, 45);
		// draw every shape in the left stack
				
		
		
		
	}
	
	private void paintShape(Shapes s, Graphics g) {
		// TODO Auto-generated method stub
			g.setColor(s.getColor());
			g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
	}

	public void paintPlayer(Graphics g, Players P){
		int x = P.getX(); int y = P.getY(); int radius = P.getRadius();
		int height = P.getHeight();
		g.setColor(P.getColor());
		g.fillOval(x, y, radius, radius);
		
		g.drawLine(x + (radius/2), y + radius, x+(radius/2), height - 25); 	// body line
		g.drawLine(x+(radius/2), height-25, x+(radius/2)-20, height); 		// left leg
		g.drawLine(x+(radius/2), height-25, x+(radius/2)+20, height);		// right leg
		g.drawLine(x+(radius/2), y+radius+10, x+(radius/2)-40, y+5);		// left arm
		g.drawLine(x+(radius/2), y+radius+10, x+(radius/2)+40, y+5);		// right arm
		g.fillOval(x+(radius/2)+30, y+5, 35, 4);							// right arm stack
		g.fillOval(x+(radius/2)-50, y+5, 35, 4);							// left arm stack
	}
}
