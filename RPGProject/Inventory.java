import java.util.ArrayList;
public class Inventory {
	private ArrayList<Item> inv;

	public Inventory() {
		inv = new ArrayList<Item>();
	}

	public void open() {
		System.out.println("*------------------------------------------*");
		System.out.println("*------------------Inventory---------------*");
		System.out.println("*------------------------------------------*");
		int x = 1;
		for (Item items: inv) {
			System.out.println(x + ". " + items.getName()+ "-" + items.toString());
			x++;
		}
	}

	public void add(Item obj) {
		if (obj == null) {
			System.out.print("");
		} else {
			System.out.println("You found " + obj.getName());
			inv.add(obj);
		}
	}

	public void remove(int index) {
		inv.remove(index - 1);
	}


}