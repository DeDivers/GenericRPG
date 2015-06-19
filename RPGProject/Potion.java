public abstract class Potion extends Item {
	private String description;
	private String type;

	public Potion(String name, String type, String des) {
		super(name);
		this.type = type;
		description = des;
	}

	public abstract double use();

}