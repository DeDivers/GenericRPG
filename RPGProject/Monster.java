import java.util.Random;
import java.util.HashSet;
public abstract class Monster implements Inflictable{
	private String name;
	private double healthPoints;
	private double maxHP;
	private double attack;
	private double defense;
	private double speed;
	private int accuracy;
	private HashSet<Ailment> statusEff = new HashSet<>();
	private boolean canMove;
	
	public Monster(String name, double hp, double att, double def, double spd, int acc) {
		this.name = name;
		healthPoints = hp;
		maxHP = hp;
		attack = att;
		defense = def;
		speed = spd;
		accuracy = acc;
		canMove = true;
	}

	public String getName() {
		return name;
	}

	public double getMaxHP() {
		return maxHP;
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

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int value) {
		accuracy += value;
	}

	public abstract void attack(Character target);
	
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

	public abstract Item drop();

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

	public abstract int dropEXP();

	public boolean getCanMove() {
		return canMove;
	}

	public void setCanMove(boolean b) {
		canMove = b;
	}

	public int randomNumberGen() {
		Random r = new Random();
		return r.nextInt(100);
	}
} 