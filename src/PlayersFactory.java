
public class PlayersFactory {

	private PlayersFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Players makePlayer(int type, int width, int height) {
		// TODO Auto-generated method stub
		if(type == 1){
			return new PlayerOne(width, height);
		}
		else if (type == 2){
			return new PlayerTwo(width, height);
		}
		
		return null;
	}
	
	static PlayersFactory pf = new PlayersFactory();
	public static PlayersFactory getInstance() {
		// TODO Auto-generated method stub
		return pf;
	}
}
