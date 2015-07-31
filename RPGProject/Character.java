import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public abstract class Character extends Base implements Levelable{
	private String gender;
	private int level;
	private int exp;
	private Inventory inventory;
	private ExperienceTable table;
	private boolean canRun;
	private int mana;
	private int maxMana;
	private SkillTree skillList;
	
	public Character(String name, double hp, double att, double def, double spd, String gender, int mana) {
		super(name, hp, att, def, spd);
		this.gender = gender;
		maxMana = mana;
		this.mana = mana;
		level = 1;
		exp = 0;
		inventory = new Inventory();
		table = new ExperienceTable();
		skillList = new SkillTree();
		canRun = false;
	}

	public String getGender() {//Currently useless, may be functional later on
		return gender;
	}

	public int getLevel() {
		return level;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int i) {
		mana = mana + i;
		if (mana > maxMana) {
			mana = maxMana;
		}
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int plus) {
		maxMana = maxMana + plus;
	}

	public void levelUp() {
		if (exp >= table.getNextLevel(level)) { //Checks the ExperienceTable to see if the experience is higher than the next EXP level
			Scanner s = new Scanner(System.in);
			level += 1;
			System.out.println("Level up!");
			for (int i = 5; i > 0; i--) {
				String ans = s.next();
				System.out.println("Points remaining: " + i);
				if (ans.equals("a") || ans.equals("A")) { //Increases attack stat by 1
					setAtt(1);
				} else if (ans.equals("d") || ans.equals("D")) { //Increases defense stat by 1
					setDef(1);
				} else if (ans.equals("s") || ans.equals("S")) { //Increases Speed stat by 1
					setSpd(1);
				} else if (ans.equals("h") || ans.equals("H")) { // Increases Maximum HP by 7
					setMaxHP(7);
				} else if (ans.equals("m") || ans.equals("M")) { //Increases Maximum mana by 5
					setMaxMana(5);
				} else {
					System.out.println("Not a valid input.");
					i++;
				}
			}
		} else {
			System.out.print(""); // Does nothing if not enough Experience
		}
	}

	public int getEXP() {
		return exp;
	}

	public void gainEXP(int e) {
		exp += Math.abs(e);
	}

	public int getRemainingEXP() {
		return table.getNextLevel(level) - exp;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public boolean canRunAway() {
		return canRun;
	}

	public void setRunAway(boolean b) {
		canRun = b;
	}

	/*public boolean equals(Object other) {
		if (other.getType().equals(this.getType())) {
			return true;
		}
		return false;
	}*/

	public abstract void equip(Weapon obj); //Abstract to allow for more modularity for different classes.

	public abstract void equipA(Armor obj);

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

	public abstract String getType();

	public abstract double getAccuracy();
	
}