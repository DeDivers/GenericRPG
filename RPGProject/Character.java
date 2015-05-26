import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;
public abstract class Character implements Inflictable, Levelable{
	private String name;
	private String gender;
	private double healthPoints;
	private double maxHP;
	private double attack;
	private double defense;
	private double speed;
	private int level;
	private int exp;
	private Inventory inventory;
	private ExperienceTable table;
	private HashSet<Ailment> statusEff = new HashSet<>();
	
	public Character(String name, String gender, double hp, double att, double def, double spd) {
		this.name = name;
		this.gender = gender;
		healthPoints = hp;
		maxHP = hp;
		attack = att;
		defense = def;
		speed = spd;
		level = 1;
		exp = 0;
		inventory = new Inventory();
		table = new ExperienceTable();
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public void setName(String n) {
		name = n;
	}

	public double getHP() {
		return healthPoints;
	}

	public void setHP(double value) {
		healthPoints = healthPoints + value;
	}

	public double getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(double ks) {
		maxHP = maxHP + ks;
	}

	public double getAtt() {
		return attack;
	}

	public void setAtt(double value) {
		attack += value;
	}

	public double getDef() {
		return defense;
	}

	public void setDef(double value) {
		defense += value;
	}

	public double getSpd() {
		return speed;
	}

	public void setSpd(double value) {
		speed += value;
	}

	public int getLevel() {
		return level;
	}

	public void levelUp() {
		if (exp >= table.getRemainingEXP(level)) {
			Scanner s = new Scanner(System.in);
			level += 1;
			System.out.println("Level up!");
			for (int i = 5; i < 1; i--) {
				String ans = s.next();
				if (ans.equals("a")) {
					setAtt(1);
				} else if (ans.equals("d")) {
					setDef(1);
				} else if (ans.equals("s")) {
					setSpd(1);
				} else if (ans.equals("h")) {
					setMaxHP(7);
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
	
	public double damage(double att) {
		Random ran = new Random();
		double damPer = (100 - (defense / att))/ 100;
		if (damPer < 0) {
			return 0.0;
		}
		double atk = att * damPer;
		double flux = atk * .5;
		double atak = flux * ran.nextDouble();
		int dou = ran.nextInt(2);
		if (dou == 0) {
			atak = atak * -1;
		}
		healthPoints = healthPoints - (atk + atak);
		return (atk + atak);
	}
	public boolean isDead() {
		if (healthPoints <=0.0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInflicted() {
		if (statusEff.isEmpty()) {
			return false;
		}
		return true;
	}

	public HashSet<Ailment> ailments() {
		return statusEff;
	}

	public void addAilment(Ailment ai) {
		statusEff.add(ai);
	}

	public void healAilment(Ailment ai) {
		statusEff.remove(ai);
	}

	public void healAll() {
		statusEff.clear();
	}
}