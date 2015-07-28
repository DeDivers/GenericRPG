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
			//System.out.println(s);
			if (usableSkill.contains(s)) {
				System.out.print("");
			} else {
				for (String c: s.getUseCharacters()) {
					//System.out.println(c);
					//System.out.println(c);
					if (ch.getType().equals(c) && ch.getLevel() >= s.getLevel()) {
						//System.out.println("True");
						usableSkill.add(s);
					}
				}
			}
		}
		return usableSkill;
	}
}