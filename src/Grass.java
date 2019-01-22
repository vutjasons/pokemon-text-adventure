/**
 * Interface class Grass that calls for the special moves from the Pokemon
 * @author jasonvu
 */
import java.util.Random;
public interface Grass {
	static final int type = 3;
	static final String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
	
	public int vineWhip();
	
	public int razorLeaf();
	
	
	public int solarBeam();

}
