import java.awt.Rectangle;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BasicInstructor extends JLabel implements Runnable, InstructorInterface{
	protected String name;	
	protected int speed;
	protected int hp;

	protected int xPos;
	protected int yPos;
	protected int height;
	protected int width;

	protected int lane;
	protected boolean vacancy;
	protected boolean state;
	protected Lawn lawn;
	protected Rectangle rectangle;
	
	protected static ImageIcon BI = new ImageIcon(InstructorInterface.BASIC_INSTRUCTOR_IMAGE);
	protected static ImageIcon BIA = new ImageIcon(InstructorInterface.BASIC_INSTRUCTOR_ATTACK_IMAGE);
	protected static ImageIcon BQID = new ImageIcon(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_DAMAGED_IMAGE);
	protected static ImageIcon BQIDA = new ImageIcon(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_DAMAGED_ATTACK_IMAGE);
	protected static ImageIcon BQI = new ImageIcon(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_IMAGE);
	protected static ImageIcon BQIA = new ImageIcon(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_ATTACK_IMAGE);
	
	public BasicInstructor(int xPos, int yPos, Lawn lawn, int lane, int hp, String name){
		this.name = name;
		if(this.name.equals(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_NAME)) {
			this.setIcon(BQI);
			this.height = this.BQI.getIconHeight();
			this.width = this.BQI.getIconWidth();
		}
		else {
			this.setIcon(BI);
			this.height = this.BI.getIconHeight();
			this.width = this.BI.getIconWidth();
		}
		this.speed = InstructorInterface.NORMAL_SPEED;
		this.hp = hp;
		this.xPos = xPos;
		this.yPos = yPos-17;
		

		this.lane = lane;
		this.vacancy = InstructorInterface.UNBLOCKED;
		this.state = InstructorInterface.ALIVE;
		this.lawn = lawn;
		this.rectangle = new Rectangle(xPos, yPos, this.width, this.height);
		
		this.setBounds(xPos, yPos, this.width, this.height);
	}
	
	// GETTERS
	public int getXPos(){
		return this.xPos;
	}
	
	public int getYPos(){
		return this.yPos;
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public int getHp(){
		return this.hp;
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	// ABSTRACT METHOD IMPLEMENTATIONS: InstructorInterface
	public void changeImage() {
		if(this.name.equals(InstructorInterface.BEAUTY_QUEEN_INSTRUCTOR_NAME)) {
			if(this.hp<=27 && this.hp>17 && this.vacancy==InstructorInterface.UNBLOCKED) {
				this.setIcon(BQI);
				this.height = this.BQI.getIconHeight();
				this.width = this.BQI.getIconWidth();
			}
			else if(this.hp<=27 && this.hp>17 && this.vacancy==InstructorInterface.BLOCKED) {
				this.setIcon(BQIA);
				this.height = this.BQIA.getIconHeight();
				this.width = this.BQIA.getIconWidth();
			}
			else if(this.hp<=17 && this.vacancy==InstructorInterface.UNBLOCKED) {
				this.setIcon(BQID);
				this.height = this.BQID.getIconHeight();
				this.width = this.BQID.getIconWidth();
			}
			else if(this.hp<=17 && this.vacancy==InstructorInterface.BLOCKED) {
				this.setIcon(BQIDA);
				this.height = this.BQIDA.getIconHeight();
				this.width = this.BQIDA.getIconWidth();
			}
		}
		else {
			if(this.hp<=17 && this.vacancy==InstructorInterface.UNBLOCKED) {
				this.setIcon(BI);
				this.height = this.BI.getIconHeight();
				this.width = this.BI.getIconWidth();
			}
			else if(this.hp<=17 && this.vacancy==InstructorInterface.BLOCKED) {
				this.setIcon(BIA);
				this.height = this.BIA.getIconHeight();
				this.width = this.BIA.getIconWidth();
			}
		}
		this.setBounds(this.xPos, this.yPos, this.width, this.height);
	}
	
	public void walk(){
		this.changeImage();
		if(this.vacancy == InstructorInterface.UNBLOCKED){
			this.xPos -= this.speed;
			this.setBounds(this.xPos, this.yPos, this.width, this.height);
			if(xPos==0) {
				this.lawn.setGameState(true);
			}
		}
	}

	// THREAD	
	public synchronized void decreaseHP(int damage){
		this.hp -= damage;
	}

	public void punchStudentArsenal(){
		Iterator<StudentArsenal> itr = this.lawn.getStudentArsenal().iterator();
		while(itr.hasNext()){
			StudentArsenal sa = itr.next();
			Rectangle arsenalBound = new Rectangle(sa.getX(), sa.getY(), sa.getWidth(), sa.getHeight()); // nasaan yung mga getters sa Arsenal?
			Rectangle instructorBound = new Rectangle(this.xPos, this.yPos, this.width, this.height);
			if(arsenalBound.intersects(instructorBound)){
				this.vacancy = InstructorInterface.BLOCKED;
				sa.decreaseLife(InstructorInterface.PUNCH_POINTS);
				if(sa.getLife()==0) this.vacancy = InstructorInterface.UNBLOCKED;
				break;
			}
		}
	}

	public void run(){
		while(this.hp > 0){
			try {
				this.walk();
				this.punchStudentArsenal();
				Thread.sleep(InstructorInterface.INSTRUCTOR_SLEEP_MS);
			}
			catch(InterruptedException e){
				System.out.println("Thread has been interrupted.");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		this.setBounds(this.xPos, this.yPos + 3000, this.width, this.height); // para saan yung 3000
		this.lawn.getBasicInstructor().remove(this);
		this.lawn.removeLawnComponent(this);
		this.lawn.getBasicInstructorChecker()[this.lane] -= 1;
	} 
}