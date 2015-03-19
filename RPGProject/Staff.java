public class Staff extends Weapon {
	private double magic;

	public Staff(double att, int acc, double def, String name, double mag) {
		super(att, acc, def, name);
		magic = mag;
	}

	public double getMagic() {
		return magic;
	}

	@Override
	public String toString() {
		return "Staff";
	}
}