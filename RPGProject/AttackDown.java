public class AttackDown extends StatusDown {
	private int turnCount;

	public AttackDown(double percent) {
		super("Attack Down", "Attack Decreases", percent, Types.ATTACK);
		turnCount = 0;
	}

	public void affect(Character ch) {
		turnCount++;
		if (turnCount > 5) {
			ch.setAttackModifier(1);
			ch.healAilment(this);
		} else {
			ch.setAttackModifier(getPercentage());
		}
	}

	public void affect(Monster mo) {
		System.out.println("");
	}
}