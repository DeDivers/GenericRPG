import java.util.HashSet;
import java.util.Random;
public abstract class Base implements Inflictable {
	private String name;
	private double healthPoints;
	private double maxHP;
	private double attack;
	private double defense;
	private double speed;
	private HashSet<Ailment> statusEff = new HashSet<>();
	private boolean canMove;
	private double attackMod;
	private double defenseMod;
	private double speedMod;
	private double accuracyMod;

	public Base(String name, double hp, double att, double def, double spd) {
		this.name = name;
		healthPoints = hp;
		maxHP = hp;
		attack = att;
		defense = def;
		speed = spd;
		canMove = true;
		attackMod = 1;
		defenseMod = 1;
		speedMod = 1;
		accuracyMod = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public double getHP() {
		return healthPoints;
	}

	public void setHP(double value) {
		healthPoints = healthPoints + value;
		if (healthPoints > maxHP) {
			healthPoints = maxHP;
		}
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

	public boolean isDead() {
		if (healthPoints <=0.0) {
			return true;
		}
		return false;

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

	public boolean getCanMove() {
		return canMove;
	}

	public void setCanMove(boolean b) {
		canMove = b;
	}

	public double getAttackModifier() {
		return attackMod;
	}

	public void setAttackModifier(double value) {
		attackMod = value;
	}

	public double getDefenseModifier() {
		return defenseMod;
	}

	public void setDefenseModifier(double value) {
		defenseMod = value;
	}

	public double getSpeedModifier() {
		return speedMod;
	}

	public void setSpeedModifier(double value) {
		speedMod = value;
	}

	public double getAccuracyModifier() {
		return accuracyMod;
	}

	public void setAccuracyModifier(double value) {
		accuracyMod = value;
	}

	public double damage(double att) {
		Random ran = new Random();
		double defence = getDef() * getDefenseModifier();
		double damPer = (100 - (defence / att))/ 100;
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
		setHP(-(atk + atak));
		return (atk + atak);
	}

}