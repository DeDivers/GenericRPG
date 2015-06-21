import java.util.Random;
import java.util.HashSet;
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

	public abstract Item drop();

	public abstract int dropEXP();

	public int randomNumberGen() {
		Random r = new Random();
		return r.nextInt(100);
	}
} 