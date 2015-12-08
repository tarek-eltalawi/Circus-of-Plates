import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;


@SuppressWarnings("serial")
public class Controller extends Applet implements Runnable, KeyListener, MouseMotionListener{

	Shapes S1,S2,S3,S4;
	int score1, score2;
	Players P1,P2;
	public LinkedList<Shapes> unused;
	public LinkedList<Shapes> used;
	
	View v;
	private Image i;
	private Graphics doubleG;
	private int mousePosition = this.getWidth() * 3/4;
	int player = 0;
	int win1 = 0, win2 = 0;
	int speed;

	@Override
	public void init() {
		speed = 2;
		v = View.getInstance();

		ShapesFactory builder = ShapesFactory.getInstance();
		builder.makeShapes();
		unused = builder.getShapes();
		used = new LinkedList<Shapes>();
		
		PlayersFactory pf = PlayersFactory.getInstance();
		P1 = pf.makePlayer(1, getWidth(), getHeight());
		P2 = pf.makePlayer(2, getWidth(), getHeight());
		Players p = pf.makePlayer(2, getWidth(), getHeight());
		v.paintPlayer(getGraphics(), p);
		setSize(700, 700);
		addKeyListener(this);
		addMouseMotionListener(this);
		score1 = 0;
		score2 = 0;
		
		S1 =  unused.poll(); S1.setX(0); S1.setY(50);  S1.setFallingedge((this.getWidth())- 280); S1.setSpeed(speed); S1.setType(1);
		S2 =  unused.poll(); S2.setX(this.getWidth()); S2.setY(50);  S2.setFallingedge(300); S2.setSpeed(-speed); S2.setType(2);
		S3 =  unused.poll(); S3.setX(0); S3.setY(120); S3.setFallingedge(100); S3.setSpeed(speed);S3.setType(3);
		S4 =  unused.poll(); S4.setX(this.getWidth()); S4.setY(120);  S4.setFallingedge((this.getWidth())- 100); S4.setSpeed(-speed);S4.setType(4);
		used.add(S1);
		used.add(S2);
		used.add(S3);
		used.add(S4);
	}
	
	@Override
	public void start() {
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void beginNewShape(int type){
		// khod shakl gdeed mn el unused w tala3o mn el makan el monaseb 3ala 7asab type value
		Shapes s = unused.poll();
		if(type == 1){
			s.setX(0); s.setY(50);  s.setFallingedge((this.getWidth())- 280); s.setSpeed(speed); s.setType(1);
		}
		else if(type == 2){
			s.setX(this.getWidth()); s.setY(50);  s.setFallingedge(300); s.setSpeed(-speed);s.setType(2);			
		}
		else if (type == 3){
			s.setX(0); s.setY(120); s.setFallingedge(110); s.setSpeed(speed);s.setType(3);
		}
		else if (type == 4){
			s.setX(this.getWidth()); s.setY(120);  s.setFallingedge((this.getWidth())- 110); s.setSpeed(-speed);s.setType(4);
		}
		 used.add(s);
		//s.update(this, main);
	}
	
	@Override
	public void run() {
		// leff 3ala kol el ashkal f used w e3rafli men el models eh el haye7salohom next
		// ex: wasal lel ard, hatala3 shakl gdeed, so on
		// e3rafli 7asal collision m3 players walla la2
		// ersm el snapshot el gdeeda ml view using info el gat ml model
		while(true){
			if( used != null){
				LinkedList<Shapes> temp = new LinkedList<Shapes>();
				while(! used.isEmpty()){
					Shapes s =  used.poll();
					temp.add(s);
					if(s == null){
						System.out.println(P2.rightY);
						break;
					}
					int type = s.update(unused, used, this.getWidth(), this.getHeight());
					if(type != -1){
						// ebda2 shakl gdeen mn el type el esmo begin
						beginNewShape(type);
					}
				}
				while(!temp.isEmpty()){
					Shapes s = temp.pop();
					if(s != null)
						used.add(s);
				}
				
			}
			
			P2.checkForCollision(unused, used);
			P1.checkForCollision(unused, used);
			score1 = P1.score;
			score2 = P2.score;
			if( (P1.getLeftY() < 175) || (P1.getRightY() < 175)){
				player = 1;
				break;
			}
			else if( (P2.getLeftY() < 175) || (P2.getRightY() < 175)){
				player = 2;
				break;
			}
			
			repaint();
			try {
				Thread.sleep(17);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(player == 1){
			win2 ++;
		}
		else if(player == 2){
			win1 ++;
		}
		this.init();
		this.start();
	}

	@Override
	public void stop() {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void update(Graphics g) {
		if(i == null){
			i = createImage(getSize().width, getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, getSize().width, getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		v.paint(g, unused, used, getWidth(), getHeight(), P1, P2, win1, win2, score1, score2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			P1.moveRight();
			P1.updateHands(P1.getX());
			break;
		case KeyEvent.VK_LEFT:
			P1.moveLeft();
			P1.updateHands(P1.getX());
			break;
		case KeyEvent.VK_PLUS:
			P1.dx ++;
			break;
		case KeyEvent.VK_MINUS:
			if(P1.dx - 1 >= 0)
				P1.dx --;
			break;
		}
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePosition = e.getX();
		P2.move(mousePosition);
		P2.updateHands(P2.getX());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	

}
