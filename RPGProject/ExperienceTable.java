public class ExperienceTable {//can currently only level up to level 10
	private int[] table; 
	private double multiplier;

	public ExperienceTable() {
		table = new int[100];
		table[0] = 100;
		multiplier = 1.5;
		expRecalc();
	}

	private void expRecalc() {
		for (int i = 1; i < 100; i++) {
			Double d = new Double(table[i - 1] * multiplier);
			table[i] = d.intValue();
		}
	}

	public void recalculateEXP(char class) {
		if (class == 'm') {
			multiplier = 1.25;
			expRecalc();
		}
	}

	public int getNextLevel(int level) {
		return table[level-1];
	}
}