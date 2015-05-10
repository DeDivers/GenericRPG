public class Poison extends Ailment {
	
	public Poison() {
		super("Poison", "Poison");
	}

	public void affect(Character ch) {
		double hp = ch.getMaxHP();
		hp = hp / 16;
		ch.setHP(-hp);
		System.out.println("You have taken Poison damage:\t" + ch.getHP());
	}

	public void affect(Monster mo) {
		double hp = mo.getMaxHP();
		hp = hp / 32;
		mo.setHP(-hp);
		//System.out.println("You have taken Poison damage:\t" + mo.getHP());
	}
}