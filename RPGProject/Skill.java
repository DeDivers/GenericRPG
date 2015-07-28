import java.util.ArrayList;
import java.util.Random;

public abstract class Skill {
	private String name;
	private int level;
	private ArrayList<String> useable;
	private int skillLevel;

	public Skill(String name, int level, ArrayList<String> useable, int skillLevel) {
		this.name = name;
		this.level = level;
		this.useable = useable;
		this.skillLevel = skillLevel;
	}

	public abstract void use(Character ch, Monster mo);

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public ArrayList<String> getUseCharacters() {
		return useable;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel() {
		skillLevel++;
	}

	public int randomNumberGen() { //Allows all subclasses of Monster to have random numbers w/o importing Random
		Random r = new Random();
		return r.nextInt(100);
	}

	public double randomDecimal() {
		Random r = new Random();
		return r.nextDouble();
	}

	public String toString() {
		return name;
	}
}