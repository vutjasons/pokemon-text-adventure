/**
 * Pokemon class that shows the pokemons move including basic moves and special moves
 * Will show pokemons type and basic moves
 * @author jasonvu
 */
import java.util.Random;
public abstract class Pokemon extends Entity {
	private int level;
	private final String basicMenu = "1. Slam\n2. Tackle\n3. Punch";
	
	/**
	 * Creates constructor for the pokemons, level, name and hp
	 * contains super class
	 * @param name - Name of pokemon
	 * @param level1 - Level of pokemon
	 * @param hp - Health of pokemon
	 */
	public Pokemon(String name, int level1, int hp)
	{
		super(name,hp);
		hp = hp;
		level = level1;
	}
	
	/**
	 * Abstract method that calls on what type the pokemon is, water, fire, or grass
	 */
	public abstract int getType();
	
	/**Abstract method that calls on special menu to display the special moves
	 * 
	 */
	public abstract void displaySpecialMenu();
	
	/**
	 * Takes input for chosen special move
	 * @param move
	 * @return the move chosed for the pokemon to use
	 */
	public abstract int specialFight(int move);
	
	/**
	 * Returns an integer for level of pokemon
	 * @return level - level of pokemon
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Increases level of pokemon by 1 and also health by 25
	 * Heals pokemon after leveling up
	 */
	public void gainLevel()
	{
		gainHp();
		heal();
		level++;
	}
	
	/**
	 * Displays Hp, name, level of pokemon
	 */
	public void displayStats()
	{
		System.out.println(getHp());
		System.out.println(getName());
		System.out.println(getLevel());
	}
	
	/**
	 * Displays the basic menu to show user which moves to use
	 */
	public void displayBasicMenu()
	{
		System.out.println(basicMenu);
	}
	
	/**
	 * Takes input from basic fight and applies the move depending on input
	 * @param move - input that user chose for pokemon move
	 * @return fightDamage - damage from the move
	 */
	public int basicFight(int move)
	{
		int fightDamage;
		
		if(move == 1)
		{
			fightDamage = slam();
			System.out.println(getName() + " crushes down with a huge Slam!");
			return fightDamage;
		}
		else if(move == 2)
		{
			fightDamage = tackle();
			System.out.println(getName() +" hits a fast Tackle!");
			return fightDamage;
		}
		
		else
		{
			fightDamage = punch();
			System.out.println(getName()+" lands an incredible Punch!");
			return fightDamage;
		}
	}
	
	/**
	 * Takes input from basic fight and applies the move depending on input
	 * @param move - input that user chose for pokemon move
	 * @return damage - damage from the move
	 */
	public int fight(int style, int move)
	{
		int damage = 0;
		if(style == 1)
		{
			damage = basicFight(move);
		}
		
		else
		{
			damage = specialFight(move);
		}
		
		return damage;
	}
	
	/**
	 * Selected normal moves: slam,tackle and punch
	 * Functions that deal damage on normal move
	 * @return damage dealt from normal moves
	 */
	public int slam()
	{
		Random random = new Random();
		int damage = random.nextInt(10-8)+8;
		damage = damage * level;
		return damage;
	}
	
	public int tackle()
	{
		Random random = new Random();
		int damage = random.nextInt(10-8)+8;
		damage = damage * level;
		return damage;
	}
	
	public int punch()
	{
		Random random = new Random();
		int damage = random.nextInt(10-8)+8;
		damage = damage * level;
		return damage;
	}

}
