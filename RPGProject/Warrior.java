import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Warrior extends Character {
	private SkillTree skillList;
	private Weapon[] equipped = new Weapon[1];
	private Armor[] equipArmor = new Armor[3];
	
	public Warrior(String name, double hp, double att, double def, double spd, String gender) {
		super(name, hp, att, def, spd, gender, 25);
		skillList = new SkillTree();
	}

	public double getAccuracy() {
		return equipped[0].getAccuracy();
	}
	@Override
	public String getType() {//For the skill tree to get the correct skills for the class.
		return "W";
	}

	public void equip(Weapon obj) {
		if (obj instanceof Sword) {//More can be added as more Weapon types are added.
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

	public void equipA(Armor obj) {//Still an experimental setup, don't have enough armor pieces created to test properly.
		int x = 0;
		try {
			for (Armor a: equipArmor) {
				if (obj.equals(a)) {
					setAtt(-a.getAttack());
					setDef(-a.getDefense());
					setSpd(-a.getSpeed());
					setAtt(obj.getAttack());
					setDef(obj.getDefense());
					setSpd(obj.getSpeed());
					System.out.println(obj + " is equipped!");
					equipArmor[x] = obj;
					x = 4;
				}
			}
			if (x == 4){
				System.out.println("");
			} else if (equipArmor[x] == null) {
				setAtt(obj.getAttack());
				setDef(obj.getDefense());
				setSpd(obj.getSpeed());
				System.out.println(obj + " is equipped!");
				equipArmor[x] = obj;
			} else {
				x++;
			}
		} catch (IndexOutOfBoundsException i) {//Roundabout way of ignoring this for more pertinant features, will be changed later
			System.out.println("Please unequip a piece of armor first!");
			//Change THIS LATER
		}
	}

	public void unequip(int index) {
		Armor obj = equipArmor[index];
		setAtt(-obj.getAttack());
		setDef(-obj.getDefense());
		setSpd(-obj.getSpeed());
		equipArmor[index] = null;
	}

	public void attack(Monster target, String action) {
		if (getCanMove()){ //if the character is paralyzed (or sleep if I add it), nothing can happen
			Random ran = new Random();
			if (action.equals("a") || action.equals("A")) {
				double acc = getAccuracy() * getAccuracyModifier();
				double accPer = 100 - acc;
				int random = ran.nextInt(100);
				double rand = ran.nextDouble();
				double crit = 1;
				if (random >= accPer) {
					if (rand < getCriticalModifier()) {
						crit = 2.25;
						System.out.println("Critical Hit!");
					} 
					double dam = target.damage(getAtt() * getAttackModifier() * crit);
					System.out.println("Damage is " + dam);
				} else {
					System.out.println("The attack missed...");
				}
			} else if (action.equals("s") || action.equals("S")) { //This is to skip turns while in battle.
				System.out.println("You skipped your turn.");
			} else if (action.equals("k") || action.equals("K")) { //This allows the user to use skills in battle.
				int x = 1;
				Scanner scanLine = new Scanner(System.in);
				ArrayList<Skill> sk = skillList.authenticate(this);//This retrieves the usable skills for the class and level
				for (Skill s: sk) {
					if (getMana() >= s.getManaCost()) {//You can't see them if you can't use them.
						System.out.println(x + ") " + s + " - " + s.getManaCost());
						x++;
					}
				}
				System.out.print("Choose a skill (Press 0 to exit): ");
				int skillChoice = scanLine.nextInt();
				if (skillChoice == 0) {
					attack(target, "a");//This just auto attacks for the moment
				} else {
					skillChoice = skillChoice - 1;
					setMana(-(sk.get(skillChoice).getManaCost()));
					sk.get(skillChoice).use(this, target);
				}

			} else if (action.equals("i") || action.equals("I")) { //This opens the inventory to be able to use items in battle, currently half functioning
				Scanner scan = new Scanner(System.in);
				getInventory().open();
				System.out.println("Which item would you like to use: (Enter \"-1\" to quit)");
				int choice = scan.nextInt();
				try {
					if (!(getInventory().view(choice) instanceof Potion)) { //Potions are currently the only battle usable items but that could change
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
						} else if (t == Types.ATTACK) {//still working out the logic of increasing attack modifier for x turns.
							System.out.println("");
						}
						/*
						The remainder of this space should be used for
						the remainder of the types in the Types class.
						*/
						//usable.use();
					}
				} catch (UnusableItemException k) {
					attack(target, action); //currently does not function
				}
			} else if (action.equals("r") || action.equals("R")) { //Allows the player to run from battle
				double enemSpd = target.getSpd();
				int run = ran.nextInt(20);
				if (target instanceof Boss) { //You can't run from a boss battle, that defeats the purpose of a boss battle.
					System.out.println("Enemy is too strong! You cannot run.");
					System.out.print("Choose a new option: ");
					Scanner scaner = new Scanner(System.in);
					attack(target, scaner.nextLine());
				} else if (enemSpd >= getSpd()){ //There is only a 5% chance if a normal enemy is faster or just as fast as the player
					if (run == 20) {
						setRunAway(true);
					}
				} else {
					double runChance = getSpd() - enemSpd;//chance increases by 5% for every point of speed above the enemy. 
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