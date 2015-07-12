public class Sword extends Weapon {
	
	public Sword(double att, int acc, double def, String name) {
		super(att, acc, def, name);
	}

	@Override
	public String toString() {
		return (getName() + " - Sword");
	}
}