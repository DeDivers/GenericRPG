import java.util.HashSet;
public interface Inflictable {
	
	boolean isInflicted();

	HashSet<Ailment> ailments();

	void addAilment(Ailment ai);

	void healAilment(Ailment ai);

	void healAll();
}