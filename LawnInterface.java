public interface LawnInterface{
	public static final String PCLAB = "skins/PCLab.png";
	public static final int NUMBER_OF_LANES_ROW = 5;
	public static final int NUMBER_OF_LANES_COL = 9;
	public static final int SPAWN_SLEEP_MS1 = 14000;
	public static final int SPAWN_SLEEP_MS2 = 7000;
	public static final int SPAWN_SLEEP_MS3 = 3500;
	public static final int SPAWN_SLEEP_HUGE_WAVE = 1000;
	public static final boolean GAME_STATE = false;

	// image files
	public static final String LAWN_JEEP = "movers/JeepMower.png";
	public static final String LAWN_OBLE = "movers/ObleMower.png";
	public static final String LAWN_MARIANG_BANGA = "movers/MariangBangaMower.png";
	public static final String LAWN_PEGARAW = "movers/PegarawMower.png";
	public static final String LAWN_ARSENAL_PANEL = "skins/panel.png";

	// population limit on the lawn
	public static final int MAX_INSTRUCTORS_LEVEL1 = 50;
	public static final int MAX_ARSENALS = 45;

	public static final int INITIAL_COFFEE = 50;
	
	// coordinate ranges - para kahit saan mo idrop yung plants within that range,
	// magla-lock siya dun sa ni-set na corodinates ng isang grid sa lawn
	public static final int[] ROW_MARKER = {
		100,
		190,
		310,
		430,
		550,
		670
	};

	public static final int[] COLUMN_MARKER = {
		90,
		179,
		300,
		420,
		542,
		663,
		783,
		902,
		1024,
		1120
	};

	// coordinates of the lanes
	// x coordinates
	public static final int[] ROW_LANE = {
		130,
		250,
		370,
		490,
		610
	};

	// y coordinates
	public static final int[] COLUMN_LANE = {
		117,
		240,
		360,
		480,
		603,
		723,
		842,
		962,
		1086
	};

	// methods
	public void setLanes();
	public void setRows();
	public void setColumns();
	public BasicInstructor spawnBasicInstructor();
}
