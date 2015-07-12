public abstract class Armor extends Item {
	
	private double attack;
	private double defense;
	private double speed;

	public Armor(String name, double att, double def, double spd) {
		super(name);
		attack = att;
		defense = def;
		speed = spd;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefense() {
		return defense;
	}

	public double getSpeed() {
		return speed;
	}

	public String toString() {
		return "Armor";
	}

	public String getInfo() {
		return ("Attack = " + attack + "/ Defense = " + defense + "/ Speed = " + speed);
	}
}