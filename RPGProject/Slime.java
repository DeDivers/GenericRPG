import java.util.Random;
public class Slime extends Monster {
	private Sword hellRaiser = new Sword(6, 75, 2, "Hell Raiser");

	public Slime(double hp, double att, double def, double spd, int acc) {
		super("Slime", hp, att, def, spd, acc);
	}

	public void attack(Character target) {
		Random ran = new Random();
		int accPer = 100 - getAccuracy();
		int random = ran.nextInt(100);
		if (random >= accPer) {
			double k = target.damage(getAtt());
			System.out.println(k + " damage done.");
		} else {
			System.out.println("damage is 0");;
		}
	}

	public Item drop() {
		return hellRaiser;
	}

	public int dropEXP() {
		return 25;
	}
}