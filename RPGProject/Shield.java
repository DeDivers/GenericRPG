public class Shield extends Armor {
	
	public Shield(String name, double attack, double defense, double speed) {
		super(name, attack, defense, speed);
	}

	public String toString() {
		return (getName() + " - Shield");
	}

	public boolean equals(Object other) {
		if (other == null) {return false;}
		if (this == other) {return true;}
		if (!(other instanceof Armor)) {
			return false;
		} else {
			return true;
		}
	}
}