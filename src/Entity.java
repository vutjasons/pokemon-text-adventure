import java.io.Serializable;

/**
 * Entity class - creates the health of the pokemon and trainer
 * Functions include
 * get health, lose health, gain health, get max health, get name
 * @author jasonvu
 *CECS 277
 */
public abstract class Entity implements Serializable{
	/** Name of user or pokemon */
	private String name;
	/** Health of user or pokemon */
	private int hp;
	/** Max health of user or pokemon */
	private int maxHp;
	/** Constructor - initializes the name and health */
	public Entity( String name1, int hp1)
	{
		name = name1;
		hp = hp1;
		maxHp = hp;
	}
	
	/**
	 * gives the name of the pokemon or the users name
	 * @returns the name of pokemon or user
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * gives health of the pokemon/user
	 * @return the health of pokemon/user 
	 */
	public int getHp()
	{
		return hp;
	}
	
	/**
	 * when pokemon is damaged or trainer is damaged return health loss after hit
	 * @param hit - damage taken
	 * @returns the health loss of user or pokemon
	 */
	public int loseHp(int hit)
	{
		hp = hp - hit;
		
		if(hp < 0)
		{
			hp = 0;
		}
		return hp;
	}
	
	/**
	 * heals pokemon to max health
	 */
	public void heal()
	{
		hp = maxHp;
	}
	/**
	 * when pokemon levels up they gain an extra 25 health
	 * @return maxHp - total health after 25 is added
	 */
	public int gainHp()
	{
		maxHp = maxHp + 25;
		return maxHp;
	}
	
	/**
	 * returns the max health of the pokemon
	 * @return maxHp - max health of the pokemon
	 */
	public int getMaxHp()
	{
		return maxHp;
	}

}
