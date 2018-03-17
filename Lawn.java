import java.awt.Component;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.io.File;


public class Lawn extends JPanel implements Runnable, LawnInterface{
	private int[] basicInstructorChecker = new int[LawnInterface.NUMBER_OF_LANES_ROW];
	private int[][] lawnChecker = new int[LawnInterface.NUMBER_OF_LANES_ROW][LawnInterface.NUMBER_OF_LANES_COL];
	private BufferedImage background;
	private static ImageIcon jeep = new ImageIcon(LawnInterface.LAWN_JEEP);
	private static ImageIcon oble = new ImageIcon(LawnInterface.LAWN_OBLE);
	private static ImageIcon maria = new ImageIcon(LawnInterface.LAWN_MARIANG_BANGA);
	private static ImageIcon pegaraw = new ImageIcon(LawnInterface.LAWN_PEGARAW);
	
	private static int coffeeCount = LawnInterface.INITIAL_COFFEE;
	private static boolean gameOver = LawnInterface.GAME_STATE;
	
	private ArrayList<BasicInstructor> basicInstructors = new ArrayList<BasicInstructor>(LawnInterface.MAX_INSTRUCTORS_LEVEL1);
	private ArrayList<StudentArsenal> studentArsenals = new ArrayList<StudentArsenal>(LawnInterface.MAX_ARSENALS);
	private static ArrayList<Integer> rows;
	private static ArrayList<Integer> columns;
	
	JLabel coffeeLabel = new JLabel(""+this.coffeeCount);
	PenShooterIcon pencon = new PenShooterIcon(25, 10, ArsenalInterface.PENSHOOTER_COST, this);
	CoffeeMakerIcon cofcon = new CoffeeMakerIcon(100, 10, ArsenalInterface.COFFEE_MAKER_COST, this);
	BlockpackIcon bloccon = new BlockpackIcon(175, 10, ArsenalInterface.BLOCKPACK_COST, this);
	

	public Lawn(){
		try{
			this.background = ImageIO.read(getClass().getResource(LawnInterface.PCLAB));
		}
		catch(IllegalArgumentException e){
			System.out.println("Input is null.");
		}
		catch(IOException e){
			System.out.println("A cache file is needed but cannot be created.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		this.setLayout(null);
		this.setRows();
		this.setColumns();
		this.setLanes();
		this.addLawnMower();
		this.setArsenalPanel();
		this.initializeLawnChecker();
	}
	
	public void addLawnComponent(Component component){
		this.add(component);
	}
	
	public int getCoffeeCount() {
		return this.coffeeCount;
	}
	
	public void setGameState(boolean state)  {
		this.gameOver = state;
	}
	
	public void addCoffee() {
		this.coffeeCount += 25;
		coffeeLabel.setText(""+this.coffeeCount);
	}
	
	public void deductCoffee(int cost) {
		this.coffeeCount -= cost;
		coffeeLabel.setText(""+this.coffeeCount);
	}
	
	public void setAvailabilityOfPlants() {
		pencon.setAvailability();
		cofcon.setAvailability();
		bloccon.setAvailability();
	}
	
	private void initializeLawnChecker(){
		for(int i=0; i<LawnInterface.NUMBER_OF_LANES_ROW; i+=1){
			for(int j=0; j<LawnInterface.NUMBER_OF_LANES_COL; j+=1){
				lawnChecker[i][j] = 0;
			}
		}
	}
	
	public void setLawnCheckerUnavailable(int y, int x){
		lawnChecker[y][x] = 1;
	}
	
	public void setLawnCheckerAvailable(int y, int x){
		lawnChecker[y][x] = 0;
	}
	
	public int getLawnChecker(int x, int y){
		return lawnChecker[x][y];
	}
	
	public void setArsenalPanel() {
		this.addLawnComponent(pencon);
		this.addLawnComponent(cofcon);
		this.addLawnComponent(bloccon);
		coffeeLabel.setBounds(335, 25, 100, 40);
		coffeeLabel.setFont(new Font("coffeeLabel", Font.BOLD, 26));
		coffeeLabel.setText(""+this.coffeeCount);
		this.addLawnComponent(coffeeLabel);
		ArsenalPanel ap = new ArsenalPanel();
		this.addLawnComponent(ap);
		
	}
	
	public void removeLawnComponent(Component component){
		this.remove(component);
	}

	public static int getRowCoordinate(int y){
		int row;

		for(row = 0; row < LawnInterface.NUMBER_OF_LANES_ROW; row += 1){
			if(y > LawnInterface.ROW_MARKER[row] && y < LawnInterface.ROW_MARKER[row + 1]){
				return rows.get(row);
			}
		}
		return -1;
	}

	public static int getColumnCoordinate(int x){
		int column;

		for(column = 0; column < LawnInterface.NUMBER_OF_LANES_COL; column += 1){
			if(x > LawnInterface.COLUMN_MARKER[column] && x < LawnInterface.COLUMN_MARKER[column + 1]){
				return columns.get(column);
			}
		}
		return -1;
	}

	// GETTERS
	public int[] getBasicInstructorChecker(){
		return this.basicInstructorChecker;
	}

	public ArrayList<Integer> getRows(){
		return this.rows;
	}
	public ArrayList<Integer> getColumns(){
		return this.columns;
	}

	public ArrayList<BasicInstructor> getBasicInstructor(){
		return this.basicInstructors;
	}
	
	public ArrayList<StudentArsenal> getStudentArsenal(){
		return this.studentArsenals;
	}

	// ABSTRACT METHOD IMPLEMENTATIONS: LawnInterface
	public void addLawnMower(){
		LawnMower lm_kaliwa = new LawnMower(-110, LawnInterface.ROW_LANE[4] - 10, jeep, this);
		LawnMower lm_maria = new LawnMower(10, LawnInterface.ROW_LANE[3] - 10, maria, this);
		LawnMower lm_oble = new LawnMower(10, LawnInterface.ROW_LANE[2] - 10, oble, this);
		LawnMower lm_pegaraw = new LawnMower(0, LawnInterface.ROW_LANE[1] - 20, pegaraw, this);
		LawnMower lm_kanan = new LawnMower(-110, LawnInterface.ROW_LANE[0] - 20, jeep, this);
		
		this.addLawnComponent(lm_kaliwa);
		this.addLawnComponent(lm_maria);
		this.addLawnComponent(lm_oble);
		this.addLawnComponent(lm_pegaraw);
		this.addLawnComponent(lm_kanan);

		Thread mower1 = new Thread(lm_kaliwa);
		Thread mower2 = new Thread(lm_maria);
		Thread mower3 = new Thread(lm_oble);
		Thread mower4 = new Thread(lm_pegaraw);
		Thread mower5 = new Thread(lm_kanan);

		mower1.start();
		mower2.start();
		mower3.start();
		mower4.start();
		mower5.start();
	}

	public void setLanes(){
		int i;

		for(i = 0; i < LawnInterface.NUMBER_OF_LANES_ROW; i += 1){
			basicInstructorChecker[i] += 0;
		}
	}
	
	public void setRows(){
		int i;

		rows = new ArrayList<Integer>(LawnInterface.NUMBER_OF_LANES_ROW);
		for(i = 0; i < LawnInterface.NUMBER_OF_LANES_ROW; i += 1){
			rows.add(LawnInterface.ROW_LANE[i]);
		}
	}
	
	public void setColumns(){
		int i;

		columns = new ArrayList<Integer>(LawnInterface.NUMBER_OF_LANES_COL);
		for(i = 0; i < LawnInterface.NUMBER_OF_LANES_COL; i += 1){
			columns.add(LawnInterface.COLUMN_LANE[i]);
		}
	}
	
	public BasicInstructor spawnBasicInstructor(){
		int row, zombiePicker; 

		Random rowRandomizer = new Random();
		row = rowRandomizer.nextInt(LawnInterface.NUMBER_OF_LANES_ROW);

		Random anotherRandomizer = new Random();
		zombiePicker = anotherRandomizer.nextInt(LawnInterface.NUMBER_OF_LANES_ROW);

		if(zombiePicker < 4){
			BasicInstructor basic = new BasicInstructor(this.columns.get(8)+120, this.rows.get(row), this, row, InstructorInterface.BASIC_INSTRUCTOR_HP, InstructorInterface.BASIC_INSTRUCTOR_NAME);
			basicInstructorChecker[row] += 1;
			return basic; // will appear 80% of the time.
		}
		BasicInstructor beautyQ = new BasicInstructor(this.columns.get(8)+120, this.rows.get(row)-5, this, row, InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_HP, InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_NAME);
		basicInstructorChecker[row] += 1;
		return beautyQ; // will appear 20% of the time.
	}
	
	// THREAD
	public void run(){
		int basicInstructorCounter = 0;
		boolean trueForAll = true;
		
		CoffeeFromSky cfs = new CoffeeFromSky(this);
		Thread thr = new Thread(cfs);
		thr.start();
		try {
			Thread.sleep(1000*20);
		} catch(Exception e) {}
		
		try {
			while(basicInstructorCounter < LawnInterface.MAX_INSTRUCTORS_LEVEL1 && this.gameOver==false){
				BasicInstructor z = this.spawnBasicInstructor();
				basicInstructorCounter += 1;
				this.addLawnComponent(z);
				this.basicInstructors.add(z);
				
				Thread th = new Thread(z);
				th.start();
				
				if(basicInstructorCounter >= 0 && basicInstructorCounter < 7){
					Thread.sleep(LawnInterface.SPAWN_SLEEP_MS1);
				}
				else if(basicInstructorCounter >= 7 && basicInstructorCounter < 18){
					Thread.sleep(LawnInterface.SPAWN_SLEEP_MS2);
				}
				else if(basicInstructorCounter >= 18 && basicInstructorCounter < 36){
					Thread.sleep(LawnInterface.SPAWN_SLEEP_MS3);
				}
				else{
					if(basicInstructorCounter == 36){
						for(int i=0; i<LawnInterface.NUMBER_OF_LANES_ROW; i+=1){
							if(basicInstructorChecker[i]!=0) {
								trueForAll = false;
							}
							if(trueForAll==true){
								Thread.sleep(5000);
							}
						}
					}
					else{
						Thread.sleep(LawnInterface.SPAWN_SLEEP_HUGE_WAVE);
					}
				}
			}
		}
		catch(InterruptedException e){
			System.out.println("Thread has been interrupted.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}



		if(basicInstructorCounter <= LawnInterface.MAX_INSTRUCTORS_LEVEL1){
			this.removeAll();
			this.gameOver = LawnInterface.GAME_STATE;
			this.coffeeCount = LawnInterface.INITIAL_COFFEE;

			GameOver gameOverAlready = new GameOver();

			CardLayout cl = new CardLayout();
			this.setLayout(cl);

			this.add(gameOverAlready, "Game Over");
			cl.show(gameOverAlready, "Game Over");
		}
		else{
			// nextLevel
		}
	}
	
	// GUI-RELATED
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D)graphics;
		
		g2d.drawImage(background, 0, 0, this);
	} 
}