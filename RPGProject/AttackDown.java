public class AttackDown extends StatusChange {
	private int turnCount;

	public AttackDown(double percent) {
		super("Attack Down", "Attack Decreases", percent, Types.ATTACK);
		turnCount = 0;
	}

	public void affect(Base ch) {
		turnCount++;
		//System.out.println(turnCount);
		if (turnCount > 5) {
			ch.setAttackModifier(1);
			ch.healAilment(this);
		} else {
			ch.setAttackModifier(getPercentage());
		}
	}

}