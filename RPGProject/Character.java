import java.util.Random;
public abstract class Character {
	private String name;
	private String gender;
	private double healthPoints;
	private double attack;
	private double defense;
	private double speed;
	
	public Character(String name, String gender, double hp, double att, double def, double spd) {
		this.name = name;
		this.gender = gender;
		healthPoints = hp;
		attack = att;
		defense = def;
		speed = spd;
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

	public abstract void equip(Weapon obj);

	public abstract void attack(Monster target);
	
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
}