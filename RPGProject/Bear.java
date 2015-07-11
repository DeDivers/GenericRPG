public class Bear extends Monster {
	private Ailment ad = new AttackChange(.75);

	public Bear() {
		super("Forest Bear", 75, 10, 5, 6, 80);
	}

	public Item drop() {
		return new Sword(10, 80, 2, "Bear Claw");
	}

	public int dropEXP() {
		return 50;
	}

	public void attack(Character ch) {
		if (getCanMove()) {
			int accPer = 100 - getAccuracy();
			int rand = randomNumberGen();
			if (accPer > rand) {
				ad.affect(ch);
				System.out.println("Attack was decreased.");
			} else if (rand > 25) {
				ch.damage(getAtt());
			} else {
				System.out.println("The enemy's attack missed!");
			}
		} else {
			System.out.println("The enemy can't move");
		}
	}
}