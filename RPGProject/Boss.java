public abstract class Boss extends Monster {
	
	public Boss(String name, double hp, double att, double def, double spd, int acc) {
		super(name, hp, att, def, spd, acc);
	}

	public abstract void attack(Character c);

	public abstract Item drop();

	public abstract double damage(double att);
}