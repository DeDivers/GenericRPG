import java.util.HashSet;
import java.util.ArrayList;

public class SkillTree {
	private HashSet<Skill> skills;
	private ArrayList<Skill> usableSkill;

	public SkillTree() {
		skills = new HashSet<>();
		usableSkill = new ArrayList<>();
		skills.add(new CrossChop());
	}

	public ArrayList<Skill> authenticate(Character ch) {
		for (Skill s: skills) {
			for (Character c: s.getUseCharacter()) {
				if (ch.getClass() instanceof c.getClass() && ch.getLevel >= s.getLevel()) {
					usableSkill.add(s);
				}
			}
		}
		return usableSkill;
	}
}