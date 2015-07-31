import java.util.Scanner;
public abstract class Character extends Base implements Levelable{
	private String gender;
	private int level;
	private int exp;
	private Inventory inventory;
	private ExperienceTable table;
	private boolean canRun;
	private int mana;
	private int maxMana;

	
	public Character(String name, double hp, double att, double def, double spd, String gender, int mana) {
		super(name, hp, att, def, spd);
		this.gender = gender;
		maxMana = mana;
		this.mana = mana;
		level = 1;
		exp = 0;
		inventory = new Inventory();
		table = new ExperienceTable();
		canRun = false;
	}

	public String getGender() {//Currently useless, may be functional later on
		return gender;
	}

	public int getLevel() {
		return level;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int i) {
		mana = mana + i;
		if (mana > maxMana) {
			mana = maxMana;
		}
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int plus) {
		maxMana = maxMana + plus;
	}

	public void levelUp() {
		if (exp >= table.getNextLevel(level)) { //Checks the ExperienceTable to see if the experience is higher than the next EXP level
			Scanner s = new Scanner(System.in);
			level += 1;
			System.out.println("Level up!");
			for (int i = 5; i > 0; i--) {
				String ans = s.next();
				System.out.println("Points remaining: " + i);
				if (ans.equals("a") || ans.equals("A")) { //Increases attack stat by 1
					setAtt(1);
				} else if (ans.equals("d") || ans.equals("D")) { //Increases defense stat by 1
					setDef(1);
				} else if (ans.equals("s") || ans.equals("S")) { //Increases Speed stat by 1
					setSpd(1);
				} else if (ans.equals("h") || ans.equals("H")) { // Increases Maximum HP by 7
					setMaxHP(7);
				} else if (ans.equals("m") || ans.equals("M")) { //Increases Maximum mana by 5
					setMaxMana(5);
				} else {
					System.out.println("Not a valid input.");
					i++;
				}
			}
		} else {
			System.out.print(""); // Does nothing if not enough Experience
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

	public boolean canRunAway() {
		return canRun;
	}

	public void setRunAway(boolean b) {
		canRun = b;
	}

	/*public boolean equals(Object other) {
		if (other.getType().equals(this.getType())) {
			return true;
		}
		return false;
	}*/

	public abstract void equip(Weapon obj); //Abstract to allow for more modularity for different classes.

	public abstract void equipA(Armor obj);

	public abstract void attack(Monster target, String action); //Abstract to allow more modularity for any extra classes.

	public abstract String getType();

	public abstract double getAccuracy();
	
}