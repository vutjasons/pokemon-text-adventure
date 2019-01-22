/**
 * Function pokemon Ponyta - overrides the specialFight from Pokemon class and uses the special moves given
 * Overrides also the type of pokemon it is
 * Calculates damage of special moves
 * @author jasonvu
 */
import java.util.Random;
public class Ponyta extends Pokemon implements Fire {
	/**
	 * Constructor for pokemon for health level and hp
	 * contains superclass from Entity
	 * @param name - name of Pokemon
	 * @param level - level of Pokemon
	 * @param hp - health of Pokemon
	 */
	public Ponyta(String name, int level, int hp) {
		super(name, level, hp);
	}

	/**
	 * Overrides the abstract function from Pokemon specialFight
	 * Functions create damage from the special move
	 * @return damage - damage of special move
	 */
	@Override
	public int ember() {
		
		Random random = new Random();
		int damage = random.nextInt(15-8)+8;
		damage = damage *= getLevel();
		return damage;
	}

	@Override
	public int fireBlast() {
		Random random = new Random();
		int damage = random.nextInt(20-12)+12;
		damage = damage *= getLevel();
		return damage;
	}

	@Override
	public int firePunch() {
		Random random = new Random();
		int damage = random.nextInt(25-15)+15;
		damage = damage *= getLevel();
		return damage;
	}

	/**
	 * return type of pokemon it is
	 * 0 = Water
	 * 1 = Fire
	 * 2 = Grass
	 * @return integer 0 or 1 or 2
	 */
	@Override
	public int getType() {
		
		return 0;
	}

	/**
	 * Overrides specialMenu from Pokemon and displays the specialMenu
	 */
	@Override
	public void displaySpecialMenu() {
		System.out.println(specialMenu);
		
	}

	/**
	 * Function takes input from user selected move and displays a special text that uses that move
	 * @returns fightDamage - damage of special move
	 */
	@Override
	public int specialFight(int move) {
		
		int fightDamage;
		
		if(move == 1)
		{
			fightDamage = ember();
			System.out.println("Ponyta charged and used Ember!");
			return fightDamage;
		}
		else if(move == 2)
		{
			fightDamage = fireBlast();
			System.out.println("Ponyta's mane fires up and uses Fire Blast!");
			return fightDamage;
		}
		
		else
		{
			fightDamage = firePunch();
			System.out.println("Ponyta's charges its hoof and goes in for the Fire Punch!");
			return fightDamage;
		}
	}

}
