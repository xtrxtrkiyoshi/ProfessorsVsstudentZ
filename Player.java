public class Player{
	private String name;
	private int score;
	
	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

	public void setName(){
		this.name = name;
	}

	public void setScore(){
		this.score = score;
	}

	public String getName(){
		return this.name;
	}

	public int getScore(){
		return this.score;
	}

	public String toString(){
		return 	"Player Name: "	+ this.getName() 	+ "\n" +
				"Score: "		+ this.getScore();
	}
}