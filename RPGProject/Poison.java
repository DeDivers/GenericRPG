public class Poison extends Ailment {
	
	public Poison() {
		super("Poison", "Poison");
	}

	public void affect(Base ch) {
		double hp = ch.getMaxHP();
		hp = hp / 16; //Drops HP by a 16th of the maximum HP.
		ch.setHP(-hp);
		System.out.println("You have taken Poison damage:\t" + ch.getHP());
	}
}