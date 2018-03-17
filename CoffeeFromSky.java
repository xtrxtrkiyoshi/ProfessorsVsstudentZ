import java.util.Random;

public class CoffeeFromSky implements Runnable {
	
	private Lawn lawn;
	Random r = new Random();
	private int speed;
	
	public CoffeeFromSky(Lawn lawn) {
		this.lawn = lawn;
		this.speed = 2;
	}
	
	public void run() {
		try{
			Thread.sleep(1000*10);
		} catch(InterruptedException e){
			System.out.println("Thread has been interrupted.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		while(true) {
			int xPos = r.nextInt(980)+100;
			Coffee c = new Coffee(xPos, 0, this.lawn, false);
			this.lawn.addLawnComponent(c);
			Thread t = new Thread(c);
			t.start();
			int pause = r.nextInt(15)+10;
			try {
				Thread.sleep(1000*pause);
			} catch(Exception e) {}
		}
	}
	
}