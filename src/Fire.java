/**
 * Interface class Fire that calls for the special moves from the Pokemon
 * @author jasonvu
 */
public interface Fire {
	
	static final int type = 0 ;
	static final String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
	
	public int ember();
	
	public int fireBlast();
	
	public int firePunch();

}
