/**
 * Function pokemon Squirtle - overrides the specialFight from Pokemon class and uses the special moves given
 * Overrides also the type of pokemon it is
 * Calculates damage of special moves
 * @author jasonvu
 */
import java.util.Random;
public class Squirtle extends Pokemon implements Water {

	/**
	 * Constructor for pokemon for health level and hp
	 * contains superclass from Entity
	 * @param name - name of Pokemon
	 * @param level - level of Pokemon
	 * @param hp - health of Pokemon
	 */
	public Squirtle(String name, int level, int hp) {
		super(name, level, hp);
	}

	/**
	 * Overrides the abstract function from Pokemon specialFight
	 * Functions create damage from the special move
	 * @return damage - damage of special move
	 */
	@Override
	public int waterGun() {
		
		Random random = new Random();
		int damage = random.nextInt(15-8)+8;
		damage = damage *= getLevel();
		return damage;
	}

	@Override
	public int bubbleBeam() {
		Random random = new Random();
		int damage = random.nextInt(20-12)+12;
		damage = damage *= getLevel();
		return damage;
	}

	@Override
	public int waterFall() {
		Random random = new Random();
		int damage = random.nextInt(23-15)+15;
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
		
		return 1;
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
			fightDamage = bubbleBeam();
			System.out.println("Squirtle closes its mouth and releases a Bubble Beam!");
			return fightDamage;
		}
		else if(move == 2)
		{
			fightDamage = waterGun();
			System.out.println("Squirtle shoots out a Water Gun at insane speeds!");
			return fightDamage;
		}
		
		else
		{
			fightDamage = waterFall();
			System.out.println("Squirtle jumps up high as it uses Water Fall!");
			return fightDamage;
		}
	}

}
