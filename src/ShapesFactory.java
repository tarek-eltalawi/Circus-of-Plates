import java.awt.Color;
import java.util.LinkedList;


public class ShapesFactory {

	private LinkedList<Shapes> shapes ;

	
	private ShapesFactory() {}
	
	public void makeShapes() {
		// TODO Auto-generated method stub
		shapes = new LinkedList<Shapes>();
		Color[] array = new Color[10];
		array[0] = Color.BLACK;
		array[1] = Color.BLUE;
		array[2] = Color.YELLOW;
		array[3] = Color.GREEN;
		array[4] = Color.RED;
		array[5] = Color.CYAN;
		array[6] = Color.magenta;
		array[7] = Color.ORANGE;
		array[8] = Color.PINK;
		
		int j = 0;
		for (int i = 0; i < 300; i++) {
			if(i%4 == 0 || i == 0){
				shapes.add(new FirstShape(array[j]));
			}
			else if(i%4 == 1 || i==1){
				shapes.add(new SecondShape(array[j]));
			}
			else if(i%4 == 2 || i == 2){
				shapes.add(new ThirdShape(array[j]));
			}
			else {
				shapes.add(new FourthShape(array[j]));
			}
			if(j < 9)
				j++;
			else
				j = 0;
		}
	}
	
	public LinkedList<Shapes> getShapes() {
		return shapes;
	}

	static ShapesFactory build = new ShapesFactory();
	public static ShapesFactory getInstance(){
		return build;
	}
}
