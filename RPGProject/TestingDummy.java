public class TestingDummy extends Monster {

	 public TestingDummy() {
	 	super("Testing Dummy", 50000, 0, 1, 1, 0);
	 }

	 public void attack(Character ch) {
	 	setHP(50000);
	 }

	 public Item drop() {
	 	return null;
	 }

	 public int dropEXP() {
	 	return 0;
	 }
}