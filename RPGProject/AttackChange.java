public class AttackChange extends StatusChange {
	private int turnCount;

	public AttackChange(double percent) { //percent affects the modifier in base (.75 = 75% damage done)
		super("Attack Change", "Attack Change", percent, Types.ATTACK);
		turnCount = 0;
	}

	public void affect(Base ch) {
		turnCount++;
		//System.out.println(turnCount);
		if (turnCount > 5) {//This effect lasts 5 turns
			ch.setAttackModifier(1);//resets the modifier and gets rid of the ailment 
			ch.healAilment(this);
		} else {
			ch.setAttackModifier(getPercentage());
		}
	}

}