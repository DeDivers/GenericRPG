public class GameMap {
	private Maze m;
	private int level;
	private GameMap north;
	private GameMap east;
	private GameMap south;
	private GameMap west;

	public GameMap() {
		m = new Maze(50, 50);
		m.generate(25,25);
		level = 1;
	}

	public GameMap(int x, int y, int lvl) {
		m = new Maze(50, 50);
		m.generate(x, y);
		level = lvl;
	}

	public GameMap getNorth() {
		return north;
	}

	public void setNorth(GameMap g) {
		north = g;
	}

	public GameMap getEast() {
		return east;
	}

	public void setEast(GameMap g) {
		east = g;
	}

	public GameMap getSouth() {
		return south;
	}

	public void setSouth(GameMap g) {
		south = g;
	}

	public GameMap getWest() {
		return west;
	}

	public void setWest(GameMap g) {
		west = g;
	}

	public int getLevel() {
		return level;
	}

	public String toString() {
		return m.toString();
	}
	// public static void main(String[] args) {
	// 	Maze m = new Maze(50, 50);
	// 	m.generate(10, 24);
		
	// }
}