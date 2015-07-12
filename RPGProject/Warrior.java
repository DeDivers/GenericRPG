import java.util.Random;
import java.util.Scanner;
public class Warrior extends Character {
	
	private Weapon[] equipped = new Weapon[1];
	private Armor[] equipArmor = new Armor[3];
	
	public Warrior(String name, double hp, double att, double def, double spd, String gender) {
		super(name, hp, att, def, spd, gender);
		
	}

	public void equip(Weapon obj) {
		if (obj instanceof Sword) {
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

	public void equip(Armor obj) {
		int x = 0;
		try {
			for (Armor a: equip) {
				if (obj.equals(a)) {
					setAtt(-a.getAttack());
					setDef(-a.getDefense());
					setSpd(-a.getSpeed());
					setAtt(obj.getAttack());
					setDef(obj.getDefense());
					setSpd(obj.getSpeed());
					System.out.println(obj + " is equipped!");
					equip[x] = obj;
					x = 4;
				}
			}
			if (x == 4){
				System.out.println("");
			} else if (equip[x] == null) {
				setAtt(obj.getAttack());
				setDef(obj.getDefense());
				setSpd(obj.getSpeed());
				System.out.println(obj + " is equipped!");
				equip[x] = obj;
			} else {
				x++;
			}
		} catch (IndexOutOfBoundsException i) {
			System.out.println("Please unequip a piece of armor first!");
		}
	}

	public void unequip(int index) {
		Armor obj = equip[index];
		setAtt(-obj.getAttack());
		setDef(-obj.getDefense());
		setSpd(-obj.getSpeed());
		equip[index] = null;
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
			} else if (action.equals("k") || action.equals("K")) { //For skills if I get this Far

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
						Potion usable = (Potion) getInventory().use(choice);
						Types t = usable.getType();
						if (t == Types.HEALTH) {
							setHP(usable.use());
						} else if (t == Types.ATTACK) {
							System.out.println("");
						}
						//usable.use();
					}
				} catch (UnusableItemException k) {
					attack(target, action);
				}
			} else if (action.equals("r") || action.equals("R")) {
				double enemSpd = target.getSpd();
				int run = ran.nextInt(20);
				if (target instanceof Boss) {
					System.out.println("Enemy is too strong! You cannot run.");
					System.out.print("Choose a new option: ");
					Scanner scaner = new Scanner(System.in);
					attack(target, scaner.nextLine());
				} else if (enemSpd >= getSpd()){
					if (run == 20) {
						setRunAway(true);
					}
				} else {
					double runChance = getSpd() - enemSpd;
					if (run < runChance) {
						setRunAway(true);
					} else {
						System.out.println("Running away failed!");
					}
				}
			}

		} else {
			System.out.println("You are paralyzed and cannot move!");
		}
	}
}