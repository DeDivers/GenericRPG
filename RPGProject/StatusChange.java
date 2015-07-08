public abstract class StatusChange extends Ailment {
	private Types ty;
	private double percent;
	
	public StatusChange(String name, String effect, double percent, Types t) {
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