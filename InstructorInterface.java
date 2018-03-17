public interface InstructorInterface{
	public static int PUNCH_POINTS = 1;
	public static final int INSTRUCTOR_SLEEP_MS = 50;	

	// name of instructors
	public static final String BASIC_INSTRUCTOR_NAME = "Basic Instructor";	
	public static final String BEAUTY_QUEEN_INSTRUCTOR_NAME = "Beauty Queen Instructor";

	// hp of instructors
	public static final int BASIC_INSTRUCTOR_HP = 11;	
	public static final int BEAUTY_QUEEN_INSTRUCTOR_HP = 12;

	// speed of instructors
	public static final int NORMAL_SPEED = 1;	

	// state of instructors
	public static final boolean ALIVE = true;	
	public static final boolean DEAD = false;

	// state of instructors
	public static final boolean BLOCKED = true;	
	public static final boolean UNBLOCKED = false;

	// image files
	public static final String BASIC_INSTRUCTOR_IMAGE = "InstructorsGIF/bi.gif";	
	public static final String BASIC_INSTRUCTOR_ATTACK_IMAGE = "InstructorsGIF/bia.gif";
	public static final String BEAUTY_QUEEN_INSTRUCTOR_DAMAGED_IMAGE = "InstructorsGIF/bqid.gif";	
	public static final String BEAUTY_QUEEN_INSTRUCTOR_DAMAGED_ATTACK_IMAGE = "InstructorsGIF/bqida.gif";
	public static final String BEAUTY_QUEEN_INSTRUCTOR_IMAGE = "InstructorsGIF/bqi.gif";	
	public static final String BEAUTY_QUEEN_INSTRUCTOR_ATTACK_IMAGE = "InstructorsGIF/bqia.gif";	

	// methods
	public void walk();
}
