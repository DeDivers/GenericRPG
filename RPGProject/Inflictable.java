import java.util.HashSet;
public interface Inflictable { //Allows For monsters and humans alike to be affilicted by status effects.
	
	boolean isInflicted();

	HashSet<Ailment> ailments();

	void addAilment(Ailment ai);

	void healAilment(Ailment ai);

	void healAll();
}