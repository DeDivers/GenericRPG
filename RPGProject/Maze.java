import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class Maze {
	private char[][] maze;
	private ArrayList<Coordiate> notvisited;
	private int x;
	private int y;
	private int prob = 100;
	private Random r = new Random();

	public Maze(int x, int y) {
		this.x = x;
		this.y = y;
		notvisited = new ArrayList<>();
		maze = new char[x][y]; 
	}

	public Maze(int xx, int yy, int prob) {
		this(xx, yy);
		this.prob = prob;
	}

	public void generate() {
		generate(r.nextInt(x), r.nextInt(y));
	}

	public void generate(int xStart, int yStart) {
		makePath(xStart, yStart);
		Coordiate c = null;
		while(!notvisited.isEmpty()) {
			c = notvisited.get(0);
			notvisited.remove(c);
			if (check(c.getX(), c.getY())) {
				makePath(c.getX(), c.getY());
			} else {
				makeWall(c.getX(), c.getY());
			}
		}
	}

	private void makePath(int dx, int dy) {
		maze[dx][dy] = 'P';
		if (dx > 0) {
			if (maze[dx - 1][dy] == '\0') {
				makeToDetermine(dx - 1, dy);
				notvisited.add(new Coordiate(dx - 1, dy));
			}
		}
		if (dx < (x - 1)) {
			if (maze[dx + 1][dy] == '\0') {
				makeToDetermine(dx + 1, dy);
				notvisited.add(new Coordiate(dx + 1, dy));
			}
		}
		if (dy > 0) {
			if (maze[dx][dy - 1] == '\0') {
				makeToDetermine(dx, dy - 1);
				notvisited.add(new Coordiate(dx, dy - 1));
			}
		}
		if (dy < (y - 1)) {
			if (maze[dx][dy + 1] == '\0') {
				makeToDetermine(dx, dy + 1);
				notvisited.add(new Coordiate(dx, dy + 1));
			}
		}
		Collections.shuffle(notvisited);
	}

	private void makeWall(int dx, int dy) {
		maze[dx][dy] = 'W';
	}
	//Intermediate tile between Nothing and wall/path
	private void makeToDetermine(int dx, int dy) {
		maze[dx][dy] = '?';
	}
	public void setPosition(int dx, int dy) {
		maze[dx][dy] = 'X';
	}

	//Checks if a particular tile should be a wall or a path
	private boolean check(int dx, int dy) {
		ArrayList<Integer> check = new ArrayList<>();
		int state = 0;
		if (dx > 0) {
			if (maze[dx - 1][dy] == 'P') {
				state = state + 1;
				
			}
		}
		if (dx < (x - 1)) {
			if (maze[dx + 1][dy] == 'P') {
				state = state + 2;
				
			}
		}
		if (dy > 0) {
			if (maze[dx][dy - 1] == 'P') {
				state = state + 4;
				
			}
		}
		if (dy < (y - 1)) {
			if (maze[dx][dy + 1] == 'P') {
				state = state + 8;

			}
		}
		check.add(1);
		check.add(2);
		check.add(4);
		check.add(8);
		if (check.contains(state)) {
			return true;
		}
		//Randomly makes loops and extra corridors; Highest is 50% chance.
		if (r.nextInt(prob) == 1){
			return true;
		}
		return false;
	}

	public String toString() {
		String map = "";
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				map = map + maze[j][i];
			}
			map = map + "\n";
		}
		return map;
	}

//Deals with the xy coordinate plane within an ArrayList
	private class Coordiate {
		private int cx;
		private int cy;
		private Coordiate(int x, int y) {
			cx = x;
			cy = y;
		}
		private int getX() {
			return cx;
		}

		private int getY() {
			return cy;
		}
	}
}