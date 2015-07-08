public class Sim {
	//public static boolean alive = true;
	public static void main(String[] args) {
		Character nemu = new Warrior("Slim", 100, 20, 10, 20, "Male");
		Monster slime = new TestingDummy();
		Sword hellBreaker = new Sword(5, 90, 1, "Hell Breaker");
		Staff wander = new Staff(6, 91, 2, "Wander", 10);
		Sword wanderR = new Sword(10, 50, 3, "Wanderer");
		Inventory i = new Inventory();
		nemu.addAilment(new AttackDown(.5));
		i.add(hellBreaker);
		i.add(wander);
		i.add(wanderR);
		i.open();
		i.remove(2);
		i.open();
		nemu.equip(hellBreaker);
		System.out.println(nemu.getAtt());
		nemu.equip(wander);
		nemu.equip(wanderR);
		System.out.println(nemu.getAtt());
		Battle b = new Battle(nemu, slime);
		b.battleProcess();
		// while (alive) {
		// 	if (nemu.getSpd() > slime.getSpd()) {
		// 		System.out.println(nemu.getName() + " Turn");
		// 		nemu.attack(slime);
		// 		System.out.println(slime.getName() + " Turn");
		// 		slime.attack(nemu);
		// 	} else {
		// 		slime.attack(nemu);
		// 		nemu.attack(slime);
		// 	}
		// 	if (nemu.isDead() || slime.isDead()) {
		// 		alive = false;
		// 		if (slime.isDead()) {
		// 			Item k = slime.drop();
		// 			System.out.println(slime.getName() + " dropped " + k.getName() + " a " + k);
		// 		}
		// 		System.out.println("nemu is dead = " + nemu.isDead());
		// 	}
		// }
		System.out.println("Sim Done!");
	}
}