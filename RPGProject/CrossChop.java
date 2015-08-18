import java.util.ArrayList;
public class CrossChop extends Skill{
	private static ArrayList<String> cal = new ArrayList<>();
	
	public CrossChop() {
		super("Cross Chop", 1, cal, 1, 5);
		//cal = new ArrayList<>();
		if (cal.isEmpty()) {//Since cal is static this must be done to prevent duplicate Usable characters.
			cal.add("W");
		}
		//cal.add("M");
	}

	public void use(Character ch, Monster mo) {
		int x = 0;
		double dam = ch.getAtt();
		dam = dam * .75 * ch.getAttackModifier();
		double damageDone = 0;
		double crit = 1;
		while (x < 2) {
			x++;
			int randomNum = randomNumberGen();
			double randomDec = randomDecimal();
			double acc = ch.getAccuracy() * ch.getAccuracyModifier();
			double accPer = 100 - acc;
			if (randomNum >= accPer) {
				if (randomDec < ch.getCriticalModifier()) {
					crit = 2.00;//Only doubles for the sake of balance
					System.out.println("Critical Hit!");
				}
				double damage = mo.damage(crit * dam);
				damageDone = damageDone + damage;
				System.out.println("Hit " + (x) + " did " + damage + " damage.");
			}
		}
		System.out.println("You did a total of " + damageDone + " damage.");
			 
	}
}