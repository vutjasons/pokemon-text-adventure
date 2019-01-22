/**
 * Interface class Water that calls for the special moves from the Pokemon
 * @author jasonvu
 */
import java.util.Random;
public interface Water {
	static final int type = 1;
	static final String specialMenu = "1. Water Gun\n2. Bubble Beam\n3. WaterFall";
	
	public int waterGun();

	
	public int bubbleBeam();
	
	
	public int waterFall();

}
