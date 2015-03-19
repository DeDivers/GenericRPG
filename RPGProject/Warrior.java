import java.util.Random;
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

	public void attack(Monster target) {
		Random ran = new Random();
		Weapon wep = equipped[0];
		int acc = wep.getAccuracy();
		//System.out.println(acc);
		int accPer = 100 - acc;
		int random = ran.nextInt(100);
		if (random >= accPer) {
			double dam = target.damage(getAtt());
			System.out.println("Damage is " + dam);
		} else {
			System.out.println("Damage = 0");
		}
	}
}