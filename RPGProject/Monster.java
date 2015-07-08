import java.util.Random;
public abstract class Monster extends Base implements Inflictable{
	private int accuracy;
	
	public Monster(String name, double hp, double att, double def, double spd, int acc) {
		super(name, hp, att, def, spd);
		accuracy = acc;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int value) {
		accuracy += value;
	}

	public abstract void attack(Character target);

	public abstract Item drop();

	public abstract int dropEXP();

	public int randomNumberGen() {
		Random r = new Random();
		return r.nextInt(100);
	}
} 