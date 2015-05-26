public class ExperienceTable {
	private int[] table;

	public ExperienceTable() {
		table = {100, 250, 500, 1000, 2500, 4100, 6200, 8750, 12000};
	}

	public int getNextLevel(int level) {
		return table[level-1];
	}
}