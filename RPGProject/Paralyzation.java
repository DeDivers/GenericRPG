import java.util.Random;
public class Paralyzation extends Ailment {
	Random r = new Random();
	
	public Paralyzation() {
		super("Paralyze", "Paralyze");
	}

	public void affect(Character ch) {
		int chance = r.nextInt(2);
		if (chance == 1) {
			ch.setCanMove(false);
		}
	}

	public void affect(Monster mo) {
		int chance = r.nextInt(2);
		if (chance == 0) {
			mo.setCanMove(false);
		}
	}
}