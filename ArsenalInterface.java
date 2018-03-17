public interface ArsenalInterface{
	public static final int PUNCH_ARSENAL_SLEEP_MS = 1000;	

	// names of student's arsenal
	public static final String PENSHOOTER_NAME = "Penshooter";
	public static final String COFFEE_MAKER_NAME = "Coffee Maker";
	public static final String BLOCKPACK_NAME = "Blockpack";

	// coffee cost of student's arsenal
	public static final int PENSHOOTER_COST = 100;
	public static final int COFFEE_MAKER_COST = 50;
	public static final int BLOCKPACK_COST = 50;	
	
	// hp of an arsenal
	public static final int LIFE = 6;
	public static final int FORTIFIED_LIFE = 72; 				// life to ng BLOCKPACK 

	public static final int LANE_CONSTANT = -1;									// bakit siya ganito

	// image files
	public static final String PENSHOOTER_IMAGE = "arsenals/penshooter.png";
	public static final String COFFEE_MAKER_IMAGE = "arsenals/coffeemaker.png"; 						// ask them :(
	public static final String BLOCKPACK_IMAGE1 = "arsenals/blockpack.png";							// ask them :(
	public static final String BLOCKPACK_IMAGE2 = "arsenals/blockpack_damage1.png";
	public static final String BLOCKPACK_IMAGE3 = "arsenals/blockpack_damage2.png";

	// recharge time
	public static final int PENSHOOTER_RECHARGE_TIME = 6;
	public static final int COFFEE_MAKER_RECHARGE_TIME = 6;
	public static final int BLOCKPACK_RECHARGE_TIME = 20; 		// ask them :(

	public static final int PEN_DAMAGE = 1;
	public static final int COFEE_MAKING_TIME = 24;

	// methods
	public void activate(); 
}
