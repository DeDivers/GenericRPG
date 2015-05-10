import java.util.Random;
public class KingSlime extends Boss {
	
	public KingSlime() {
		super("King Slime", 200, 12, 6, 10, 95);
	}

	public Item drop() {
		Random r = new Random();
		int i = r.nextInt(100);
		if (i == 99) {
			return new Sword(15, 95, 7, "King Slayer");
		} else if (i > 10 && i < 26) {
			return new StrPot("Power of a King", "strength", "Gain the power of a King with this!",
				8);
		} else {
			return null;
		}
	}
}