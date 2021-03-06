public class Sim { //used to test everything. Play to your heart's content

	public static void main(String[] args) {
		CrossChop cp = new CrossChop();
		System.out.println(cp.getUseCharacters().size());
		Character nemu = new Warrior("Slim", 100, 20, 10, 20, "Male");
		Monster slime = new Bear();

		Sword hellBreaker = new Sword(5, 90, 1, "Hell Breaker");
		Staff wander = new Staff(6, 91, 2, "Wander", 10);
		Sword wanderR = new Sword(10, 50, 3, "Wanderer");
		Shield blocker = new Shield("The Block", 1, 5, -2);

		Inventory i = new Inventory();

		//nemu.addAilment(new AttackChange(.5));

		i.add(hellBreaker);
		i.add(wander);
		i.add(wanderR);
		i.add(blocker);
		i.open();
		i.remove(2);
		i.open();

		nemu.equip(hellBreaker);
		nemu.equipA(blocker);

		//System.out.println(nemu.getAtt());
		nemu.equip(wander);
		//nemu.equip(wanderR);

		//System.out.println(nemu.getAtt());
		
		Battle b = new Battle(nemu, slime);
		b.battleProcess();
		
		System.out.println("Sim Done!");
	}
}