import java.util.Random;
import java.util.Scanner;
public class Warrior extends Character {

	private Weapon[] equipped = new Weapon[1];
	
	public Warrior(String name, String gender, double hp, double att, double def, double spd) {
		super(name, gender, hp, att, def, spd);
	}

	public void equip(Weapon obj) {
		if (obj.toString().equals("Sword")) {
			if (equipped[0] == null) {
				setAtt(obj.getAttack());
				setDef(obj.getDefense());
				equipped[0] = obj;	
			} else {
				setAtt(-equipped[0].getAttack());
				setDef(-equipped[0].getDefense());
				setAtt(obj.getAttack());
				setDef(obj.getDefense());
				equipped[0] = obj;
			}
		} else {
			System.out.println("This is the wrong item for this class.");
		}
	}

	public void unequip(Weapon obj) {
		setAtt(-equipped[0].getAttack());
		setDef(-equipped[0].getDefense());
		equipped[0] = null;
	}

	public void attack(Monster target, String action) {
		if (getCanMove()){
			Random ran = new Random();
			if (action.equals("a") || action.equals("A")) {
				Weapon wep = equipped[0];
				double acc = wep.getAccuracy() * getAccuracyModifier();
				//System.out.println(acc);
				double accPer = 100 - acc;
				int random = ran.nextInt(100);
				if (random >= accPer) {
					double dam = target.damage(getAtt() * getAttackModifier());
					System.out.println("Damage is " + dam);
				} else {
					System.out.println("The attack missed...");
				}
			} else if (action.equals("s") || action.equals("S")) {
				System.out.println("You skipped your turn.");
			} else if (action.equals("k") || action.equals("K")) {

			} else if (action.equals("i") || action.equals("I")) {
				Scanner scan = new Scanner(System.in);
				getInventory().open();
				System.out.println("Which item would you like to use: (Enter \"-1\" to quit)");
				int choice = scan.nextInt();
				try {
					if (!(getInventory().view(choice) instanceof Potion)) {
						throw new UnusableItemException();
					} else if (choice == -1) {
						System.out.println("Choose a new action");
						String choose = scan.nextLine();
						attack(target, choose);
					} else {
						Item usable = getInventory().use(choice);
						//usable.use();
					}
				} catch (UnusableItemException k) {
					attack(target, action);
				}
			}

		} else {
			System.out.println("You are paralyzed and cannot move!");
		}
	}
}