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
			battleActions();
			action = s.next();
			System.out.println("Turn #" + turnCount);
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
			if (action.equals("a") || action.equals("A") || action.equals("k") || action.equals("K")){
				if (battleEnem.getSpd() >= battlePlay.getSpd()) {
					battleEnem.attack(battlePlay);
					battlePlay.attack(battleEnem, action);
				} else {
					battlePlay.attack(battleEnem, action);
					battleEnem.attack(battlePlay);
				}
			} else {
				battlePlay.attack(battleEnem, action);
				if (battlePlay.canRunAway()) {
					endBattle();
				} else {
					battleEnem.attack(battlePlay);
				}
			}
			
			System.out.println(battlePlay.getHP());
			System.out.println(battleEnem.getHP());
			System.out.println();
			turnCount++;
		}
		if (battlePlay.isDead()) {
			System.out.println("Game Over");
			System.exit(0);
		} else {
			if (battlePlay.canRunAway()) {
				System.out.println("You leave the enemy behind!");
				battlePlay.setRunAway(false);
			} else {
				battlePlay.getInventory().add(battleEnem.drop());
				battlePlay.gainEXP(battleEnem.dropEXP());
				battlePlay.levelUp();
			}
		}
	}

	public void battleActions() {
		System.out.println("----------Player Actions----------");
		System.out.println("[A]ttack\t[I]nventory");
		System.out.println("S[k]ills\t[S]kip");
		System.out.println("[R]un   \t[D]efend");
		System.out.print("Please choose an action: ");
	}

	public void endBattle() {
		System.out.println("You ran away!");
		battleEnem.setHP(-battleEnem.getMaxHP());
		//battleEnem.setAtt(-battleEnem.getAtt());
	}
}