import java.util.Random;
public class KingSlime extends Boss {
	
	public KingSlime() {
		super("King Slime", 200, 12, 6, 10, 95);
	}

	public Item drop() {
		Random r = new Random();
		int i = r.nextInt(100);
		if (i == 99) {
			return new Sword(15, 95, 7, "King Slayer");
		} else if (i > 10 && i < 26) {
			return new StrPot("Power of a King", Types.ATTACK, "Gain the power of a King with this!",
				8);
		} else {
			return null;
		}
	}

	public int dropEXP() {
		return 1000;
	}

	public double damage(double att) {
		Random ran = new Random();
		double damPer = (100 - (getDef() / att))/ 100;
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
		setHP(- (atk + atak));
		return (atk + atak);
	}

	public void attack(Character target) {
		Random ran = new Random();
		int accPer = 100 - getAccuracy();
		int random = ran.nextInt(100);
		if (random >= accPer) {
			double k = target.damage(getAtt());
			System.out.println(getName() + k + " damage done.");
		} else {
			System.out.println("damage is 0");;
		}
	}
}