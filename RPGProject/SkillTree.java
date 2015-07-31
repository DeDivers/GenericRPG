import java.util.ArrayList;

public class SkillTree {
	private ArrayList<Skill> skills;
	private ArrayList<Skill> usableSkill;

	public SkillTree() {
		skills = new ArrayList<>();
		usableSkill = new ArrayList<>();
		skills.add(new CrossChop());
	}

	public ArrayList<Skill> authenticate(Character ch) {
		for (Skill s: skills) {
			if (usableSkill.contains(s)) {//Keeps duplicate skill out of the list otherwise list full of duplicates
				System.out.print("");
			} else {
				for (String c: s.getUseCharacters()) {
					if (ch.getType().equals(c) && ch.getLevel() >= s.getLevel()) {
						usableSkill.add(s);
					}
				}
			}
		}
		return usableSkill;
	}
}