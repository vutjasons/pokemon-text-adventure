/**
 * PokemonMaker creates wild pokemon, specified types of pokemon and the starter pokemon
 * @author jasonvu
 */
import java.util.Random;
public class PokemonMaker {
	/**
	 * function creates a random type, random level(1-2), and random hp wild pokemon
	 * @return p - pokemon generated from function
	 */
	public static Pokemon makeWildPokemon()
	{
		/**
		 * Create three random objects
		 * random, health, level
		 */
		Random random = new Random();
		Random hp = new Random();
		Random level = new Random();
		/**
		 * Creates random integer for type, hp, and level
		 */
		int pokeGen = random.nextInt(5)+1;
		int randHp = hp.nextInt(35-20)+20;
		int randLevel = level.nextInt(3-1)+1;
		
		/**
		 * If then statements when a certain integer
		 * Integer 1-2 creates a fire type with random health and level
		 * Integer 3-4 creates a water type with random health and level
		 * Integer 5-6 creates a grass type with random health and level
		 */
		if(pokeGen == 1)
		{
			Pokemon p = new Charmander("Charmander", randLevel, randHp);
			
			return p;
		}
		
		else if(pokeGen == 2)
		{
			Pokemon p = new Ponyta("Ponyta", randLevel, randHp);
			
			return p;
		}
		
		else if(pokeGen == 3)
		{
			Pokemon p = new Staryu("Staryu", randLevel, randHp);
			
			return p;
		}
		
		else if(pokeGen == 4)
		{
			Pokemon p = new Squirtle("Squirtle", randLevel, randHp);
			
			return p;
		}
		
		else if(pokeGen == 5)
		{
			Pokemon p = new Bulbasaur("Bulbasaur", randLevel, randHp);
			
			return p;
		}
		else
		{
			Pokemon p = new Oddish("Oddish", randLevel, randHp);
			
			return p;
		}
		
	}
	
	/**
	 * Function creates a random pokemon of certain type
	 * @param type - what type is wanted
	 * @return poke - the specified type pokemon
	 */
	public static Pokemon makeTypePokemon(int type)
	{
		//Random object and pokeTypeGen to generate a number for type of pokemon
		Random random = new Random();
		int pokeTypeGen = random.nextInt(0-1);
		
		/**
		 * if type = 0 or 1 return a fire type pokemon
		 * if type = 2 or 3 return a water type pokemon
		 * if type = 4 or 5 return a grass type pokemon
		 */
		if(type == 0)
		{
			if(pokeTypeGen == 1)
			{
				Pokemon poke = new Charmander("Charmander", 1 , 50);
				
				return poke;
			}
			
			else
			{
				Pokemon poke = new Ponyta("Ponyta", 1 , 50);
				return poke;
			}
		}
		
		else if(type == 2)
		{
			if(pokeTypeGen == 1)
			{
				Pokemon poke = new Squirtle("Squirtle", 1 , 50);
				
				return poke;
			}
			
			else
			{
				Pokemon poke = new Staryu("Staryu", 1 , 50);
				return poke;
			}
		}
		
		else
		{
			if(pokeTypeGen == 1)
			{
				Pokemon poke = new Bulbasaur("Bulbasaur", 1 , 50);
				
				return poke;
			}
			
			else
			{
				Pokemon poke = new Oddish("Oddish", 1 , 50);
				return poke;
			}
		}
	}
	
	/**
	 * Creates the users first starter pokemon
	 * 1 = Charmander, 2 = Squirtle, 3 = Bulbasaur
	 * @param start - input to define what pokemon the user wants
	 * @return poke - returns the starter pokemon the user wants
	 */
	public static Pokemon makeStartPokemon(int start)
	{
		//If user chooses 1, they will receive a Charmander
		if(start == 1)
		{
			
			Pokemon poke = new Charmander("Charmander", 1 , 150);
			
			return poke;
		}
		//If user chooses 2, they will receive a Squirtle
		else if(start == 2)
		{
			Pokemon poke = new Squirtle("Squirtle", 1, 150);
			
			return poke;
		}
		//If user chooses 3, they will receive a Bulbasaur
		else
		{

			Pokemon poke = new Bulbasaur("Bulbasaur", 1, 150);
			
			return poke;
		}
	}

}
