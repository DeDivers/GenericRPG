public abstract class HPPot extends Potion {
	private double effect;
	
	public HPPot(String name, Types type, String des,
		double eff) {
		super(name, type, des);
		effect = eff;
	}

	public double getEffect() {
		return effect;
	}

	public double use() {
		return effect;
	}

}