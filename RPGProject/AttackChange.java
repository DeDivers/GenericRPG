public class AttackChange extends StatusChange {
	private int turnC;

	public AttackChange(double percent) { //percent affects the modifier in base (.75 = 75% damage done)
		super("Attack Change", "Attack Change", percent, Types.ATTACK);
		turnC = turnCount;
	}

	public void affect(Base ch) {
		//turnCount++;
		//System.out.println(turnCount);
		if (turnCount > (turnC + 5)) {//This effect lasts 5 turns
			normalize(ch);
		} else {
			ch.setAttackModifier(getPercentage());
		}
	}

	public void normalize(Base ch) {
		ch.setAttackModifier(1);//resets the modifier and gets rid of the ailment 
		ch.healAilment(this);
	}

}