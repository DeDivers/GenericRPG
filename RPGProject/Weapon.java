public abstract class Weapon extends Item {
	
	private String name;
	private double attack;
	private int accuracy;
	private double defense;

	public Weapon(double att, int acc, double def, String name) {
		super(name);
		attack = att;
		accuracy = acc;
		defense = def;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefense() {
		return defense;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public String toString() {
		return "Weapon";
	}

}