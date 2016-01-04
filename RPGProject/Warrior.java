import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Warrior extends Character {
	private Weapon[] equipped = new Weapon[1];
	private Armor[] equipArmor = new Armor[3];
	
	public Warrior(String name, double hp, double att, double def, double spd, String gender) {
		super(name, hp, att, def, spd, gender, 25);
		changeEXPTable('w');
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
}