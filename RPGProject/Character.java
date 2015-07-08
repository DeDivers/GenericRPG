import java.util.Scanner;
public abstract class Character extends Base implements Levelable{
	private String gender;
	private int level;
	private int exp;
	private Inventory inventory;
	private ExperienceTable table;

	
	public Character(String name, double hp, double att, double def, double spd, String gender) {
		super(name, hp, att, def, spd);
		this.gender = gender;
		level = 1;
		exp = 0;
		inventory = new Inventory();
		table = new ExperienceTable();
	}

	public String getGender() {
		return gender;
	}

	public int getLevel() {
		return level;
	}

	public void levelUp() {
		if (exp >= table.getNextLevel(level)) {
			Scanner s = new Scanner(System.in);
			level += 1;
			System.out.println("Level up!");
			for (int i = 5; i > 0; i--) {
				String ans = s.next();
				if (ans.equals("a")) {
					setAtt(1);
				} else if (ans.equals("d")) {
					setDef(1);
				} else if (ans.equals("s")) {
					setSpd(1);
				} else if (ans.equals("h")) {
					setMaxHP(7);
				} else {
					System.out.println("Not a valid input.");
					i++;
				}
			}
		} else {
			System.out.print("");
		}
	}

	public int getEXP() {
		return exp;
	}

	public void gainEXP(int e) {
		exp += Math.abs(e);
	}

	public int getRemainingEXP() {
		return table.getNextLevel(level) - exp;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public abstract void equip(Weapon obj);

	public abstract void attack(Monster target, String action);
	
}