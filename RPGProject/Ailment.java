public abstract class Ailment {
	private String name;
	private String effect;
	
	public Ailment(String name, String effect) {
		this.name = name;
		this.effect = effect;
	}

	public abstract void affect(Base ch);

	//public abstract void affect(Monster mo);

	public String toString() {
		return name;
	}
}