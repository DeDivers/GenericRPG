public abstract class Potion extends Item {
	private String description;
	private Types type;

	public Potion(String name, Types type, String des) {
		super(name);
		this.type = type;
		description = des;
	}

	public abstract double use();

	public Types getType() {
		return type;
	}

}