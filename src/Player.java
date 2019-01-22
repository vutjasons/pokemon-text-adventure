/**
 * This class is created for the user who is playing the game
 * This class will be saved once user has hit the finished point on the map
 */
import java.awt.Point;
import java.io.Serializable;

public class Player extends Trainer implements Serializable {
	
	/** The starter pokemon */
	private int currentPokemon;
	/** Item to catch pokemon */
	private int pokeballs;
	/** Heals damaged pokemon and to the trainer */
	private int potions;
	/** Currency to buy items */
	private int money;
	/** Location coordinates of the user*/
	private Point location;
	/**Area of map the user is in*/
	private int mapNum;

	/**
	 * The constructor for creating a user and the starting point
	 * Default set area to 1, pokeballs,potions to 5, money to 50, and location to the start location
	 * @param name2 - Name of user
	 * @param hp2 - Health of user
	 * @param start - Where user starts on the map
	 */
	public Player(String name2, int hp2, Point start)
	{
		super(name2, hp2);
		mapNum = 1;
		pokeballs = 5;
		potions = 5;
		money = 50;
		location = start;
	}
	
	/**
	 * Using potion will heal the users current pokemons health back to full
	 * if user has no potions throw an error telling the user they have no potions
	 */
	public void usePotion()
	{
		if(potions == 0)
		{
			System.out.println("You do not have enough potions to heal your Pokemon");
		}
		else
		{
			getCurrentPokemon().heal();
			potions--;
		}
	}
	
	/**
	 * when user gains a potion, add to their inventory and increase by 1
	 */
	public void gainPotion()
	{
		potions = potions + 1;
	}
	
	/**
	 * @return potions - returns number of potions user has left in their inventory
	 */
	public int getNumPotions()
	{
		return potions;
	}
	
	/**
	 * when user throws a pokeball subtract 1 pokeball everytime they use it
	 */
	public void usePokeball()
	{
		pokeballs--;
	}
	
	/**
	 * add 1 pokeball everything they gain a pokeball
	 */
	public void gainPokeball()
	{
		pokeballs = pokeballs +1;
	}
	
	/** 
	 * @return pokeballs - returns how many pokeballs they have in their inventory
	 */
	public int getNumPokeballs()
	{
		return pokeballs;
	}
	
	/**
	 * substract the spent money to current money
	 * @param moneySpent - the price of the item bought
	 */
	public void spendMoney(int moneySpent)
	{
		if(money < moneySpent)
		{
			System.out.println("You do not have enough to make this purchase.");
		}
		else
		{
			money = money - moneySpent;
		}
	}
	
	/**
	 * @return money - returns how much money the user has left
	 */
	public int getAmtMoney()
	{
		return money;
	}
	/**
	 * when user wins a battle or beats a pokemon, they will receive money
	 * the money they are rewarded is added to their current money
	 * @param moneyGain - how much money they earned
	 * @return money - total money after summing the rewarded
	 */
	public int gainMoney(int moneyGain)
	{
		money = moneyGain+money;
		return money;
	}
	
	/**
	 * Gets the location of the user
	 * @return location - the coordinates of where the user is
	 */
	public Point getLocation()
	{
		return location;
	}
	
	/**
	 * When user moves around the map, check to make sure they are able to move within the mao
	 * @param Point p - location of the user
	 * @return true/false - returns true if they are able to move, returns false if they are out of bounds
	 */
	public boolean setLocation(Point p)
	{
		
		if( p.getX() > 0 && p.getX() <5 && p.getY() > 0 && p.getY() < 5)
		{
			location.setLocation(p);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * This returns an integer in order to display what area of the map we want
	 * @return mapNum - returns an integer for which Map needs to be generated
	 */
	public int getMapNum()
	{
		return mapNum;
	}
	
	/**
	 * When user hits the finish point on the map
	 * Generates the next map and increments
	 * If map == 3, generate map 1 again.
	 */
	public void incMapNum()
	{
		if(mapNum == 3)
		{
			mapNum = 1;
		}
		else
		{
			mapNum++;
		}
	}
	
	/**
	 * goNorth - Allows the user to move up
	 * @param Map m - the map object
	 * @returns m.getCharLoc(location) - returns what character the user is on the map
	 */
	public char goNorth(Map m)
	{
		int x = (int)location.getX();
		int y = (int)location.getY();
		
		if(location.getY()-1 < 0) 
		{
			return 'x';
		}
		else
		{
			location.setLocation(x,y-1);
			m.reveal(location);
		}
		
		return m.getCharAtLoc(location);
	}
	
	/**
	 * goSouth - Allows the user to move down
	 * @param Map m - the map object
	 * @returns m.getCharLoc(location) - returns what character the user is on the map
	 */
	public char goSouth(Map m)
	{
		int x = (int)location.getX();
		int y = (int)location.getY();
		
		if(location.getY()+1 > 4)
		{
			return 'x';
		}
		else
		{
			location.setLocation(x, y+1);
			m.reveal(location);
		}
		
		return m.getCharAtLoc(location);
	}
	
	/**
	 * goEast - Allows the user to move right
	 * @param Map m - the map object
	 * @returns m.getCharLoc(location) - returns what character the user is on the map
	 */
	public char goEast(Map m)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		if(location.getX()+1 > 4)
		{
			return 'x';
		}
		
		else
		{
			location.setLocation(x+1,y);
			m.reveal(location);
		}
		
		return m.getCharAtLoc(location);
	}
	
	/**
	 * goWest - Allows the user to move left
	 * @param Map m - the map object
	 * @returns m.getCharLoc(location) - returns what character the user is on the map
	 */
	public char goWest(Map m)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		
		if(location.getX()-1 < 0)
		{
			return 'x';
		}
		
		else
		{
			location.setLocation(x-1,y);
			m.reveal(location);
		}
		
		return m.getCharAtLoc(location);
	}

/**
 * When user summons their pokemon
 * @return a string Attack
 */
	@Override
	public String attackSpeech() 
	{
		String attack = "Attack!";
		return attack;
	}

	/**
	 * When user wins a pokemon battle
	 * @return a string Win
	 */
	@Override
	public String winSpeech() 
	{
		String win = "Nice we won!";
		return win;
	}

	/**
	 * When user loses a pokemon battle
	 * @return a string loss
	 */
	@Override
	public String lossSpeech() 
	{
		String win = "Nice you lost!";
		return win;
	}


	/**
	 * Overrides the abstract function within the Trainer class
	 * Asks user if they want to use a special menu or basic menu
	 * @return input - 1 for basic menu, 2 for special menu
	 */
	@Override
	public int chooseStyle() 
	{
		System.out.println("Choose Attack Type:");
		System.out.println("1. Basic Menu\n2. Special Menu");
		int input = CheckInput.checkIntRange(1,2);
		
		return input;
	}


	/**
	 * Once user has chosen what menu they want they pass in the number in order to display the skills of basic menu or special menu
	 * @param style - what moves to display
	 * @return input - what move they want to use
	 */
	@Override
	public int chooseMove(int style) 
	{
		
		if(style == 1)
		{
			getCurrentPokemon().displayBasicMenu();
			int input = CheckInput.checkIntRange(1,3);
			return input;
		}
		else
		{
			getCurrentPokemon().displaySpecialMenu();
			int input = CheckInput.checkIntRange(1,3);
			return input;
		}
	}

/**
 * Overrides the display stats in Trainer class in order to show users stats
 * Prints out the health, potions, pokeballs, money, and all the pokemon in their party
 */
	@Override
	public void displayStats()
	{
		System.out.println(getName() + " Stats:");
		System.out.println("HP:" + getHp());
		System.out.println("Number of Potions:" + getNumPotions());
		System.out.println("Number of Pokeballs:" + getNumPokeballs());
		System.out.println("Money: " +getAmtMoney());
		System.out.println("Pokemon:");
		displayAllPokemon();
	}
	
	

}
