import java.util.Scanner;
public class Battle {
	private Monster battleEnem;
	private Character battlePlay;
	private int turnCount;
	private String action;

	public Battle(Character ch, Monster mo) {
		battlePlay = ch;
		battleEnem = mo;
	}

	public void battleProcess() {
		turnCount = 0;
		Scanner s = new Scanner(System.in);
		while (!battlePlay.isDead() && !battleEnem.isDead()) {
			action = s.next();
			if (battlePlay.isInflicted()) {
				for (Ailment a : battlePlay.ailments()) {
					a.affect(battlePlay);
				}
			}
			if (battleEnem.isInflicted()) {
				for (Ailment a : battleEnem.ailments()) {
					a.affect(battleEnem);
				}
			}
			if (battleEnem.getSpd() >= battlePlay.getSpd()) {
				battleEnem.attack(battlePlay);
				battlePlay.attack(battleEnem, action);
			} else {
				battlePlay.attack(battleEnem, action);
				battleEnem.attack(battlePlay);
			}
			System.out.println("Turn #" + turnCount);
			System.out.println(battlePlay.getHP());
			System.out.println(battleEnem.getHP());
			System.out.println();
			turnCount++;
		}
		if (battlePlay.isDead()) {
			System.out.println("Game Over");
			System.exit(0);
		} else {
			battlePlay.getInventory().add(battleEnem.drop());
			battlePlay.gainEXP(battleEnem.dropEXP());
			battlePlay.levelUp();
		}
	}

	public void battleActions() {
		System.out.println();
	}
}