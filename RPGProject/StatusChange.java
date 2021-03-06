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

	public abstract void normalize(Base bs); //Gets rid of whatever changed and returns it to normal.
}