public class StrPot extends Potion {
	private double effect;
	
	public StrPot(String name, String type, String des,
		double eff) {
		super(name, type, des);
		effect = eff;
	}

	public double use() {
		System.out.println();
		return 0;
	}

}