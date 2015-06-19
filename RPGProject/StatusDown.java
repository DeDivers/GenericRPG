public abstract class StatusDown extends Ailment {
	private Types ty;
	private double percent;
	
	public StatusDown(String name, String effect, double percent, Types t) {
		super(name, effect);
		this.percent = percent;
		ty = t;
	}

	public Types getType() {
		return ty;
	}

	public double getPercentage() {
		return percent;
	}
}