import java.util.Random;
public class PoisonSnail extends Monster { //used to test the poison ailment
	private Ailment p = new Poison();
	
	public PoisonSnail() {
		super("Poison Snail", 50, 2, 10, 2, 95);
	}

	public Item drop() {
		return null;
	}

	public int dropEXP() {
		return 200;
	}

	public void attack(Character ch) {
		if (getCanMove()) {
			Random ran = new Random();
			int accPer = 100 - getAccuracy();
			int random = ran.nextInt(100);
			if (random >= accPer) {
				double k = ch.damage(getAtt());
				p.affect(ch);
				System.out.println(k + " damage done.");
			} else {
				System.out.println("Damage is cero");
			}
		} else {
			System.out.println("The enemy can't move!");
		}
	}
}