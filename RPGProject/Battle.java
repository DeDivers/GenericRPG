public class Battle {
	private Monster battleEnem;
	private Character battlePlay;
	private int turnCount;

	public Battle(Character ch, Monster mo) {
		battlePlay = ch;
		battleEnem = mo;
	}

	public void BattleProcess() {
		turnCount = 0;
		while (!battlePlay.isDead() || !battleEnem.isDead()) {
			if (battleEnem.getSpd() >= battlePlay.getSpd()) {
				battleEnem.attack(battlePlay)
			} else {

			}
			turnCount++;
		}
	}
}