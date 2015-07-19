import java.util.ArrayList;
public class CrossChop extends Skill{
	private ArrayList<Character> cal = new ArrayList<>();
	
	public CrossChop() {
		cal.add(sampleWarrior());
		super("Cross Chop", 2, cal, 1);
	}

	public void use(Character ch, Monster mo) {
		int x = 0;
		double dam = ch.getAtt();
		dam = dam * .75 * ch.getAttackModifier();
		double damageDone = 0;
		double crit = 1;
		while (x < 2) {
			x++;
			int randomNum = randomNumberGenerator();
			double randomDec = randomDecimal();
			double acc = ch.getAccuracy() * getAccuracyModifier();
			double accPer = 100 - acc;
			if (randomNum > accPer) {
				if (randomDec < ch.getCriticalModifier()) {
					crit = 2.00;
					System.out.println("Critical Hit!");
				}
				double damage = mo.damage(crit * dam);
				damageDone = damageDone + damage;
				System.out.println("Hit " + (x + 1) + " did " + damage + " damage.");
			}
			System.out.println("You did a total of " + damageDone + " damage.");
		}
			 
	}
}