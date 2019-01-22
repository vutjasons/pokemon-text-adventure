
/**
 * Trainer class simulates a trainer
 * User will start off with set potions, pokeballs, and money
 * @author jasonvu
 */
import java.io.Serializable;
import java.util.*;
public abstract class Trainer extends Entity implements Serializable{
	/** The pokemon team */
	private ArrayList<Pokemon>pokemon;
	
	/** The starter pokemon */
	private int currentPokemon;
	
	public Trainer( String name1, int hp1)
	{
		super(name1,hp1);
		currentPokemon = 0;
		pokemon = new ArrayList<Pokemon>();
	}
	
	
	public void displayStats()
	{
		
	}
	/** 
	 * Calls chooseStyle to choose what menu options to do
	 * Inserts menu option input into chooseMove to ask the player what move they wanna use to attack
	 * @return damage - damage of the move user chose to inflict damage
	 */
	public int battle()
	{
        int style2 = chooseStyle();
        int move = chooseMove(style2);
        int damage = pokemon.get(currentPokemon).fight(style2, move);
        
        return damage;
    }
	
	/**
	 * Displays all pokemon within the user's party
	 */
	public void displayAllPokemon()
	{
		System.out.println("Current Pokemon: " + pokemon.get(currentPokemon).getName());
		for(int i = 0; i < pokemon.size(); i++)
		{
			System.out.println((i+1)+". "+pokemon.get(i).getName() +" Level: "+ pokemon.get(i).getLevel() +" HP: " + pokemon.get(i).getHp());
		}
	}
	
	/**
	 * @return pokemon.get(currentPokemon) to display to user what their current pokemon is
	 */
	public Pokemon getCurrentPokemon()
	{
		return pokemon.get(currentPokemon);
	}
	
	/**
	 * adds the pokemon to users party
	 * @param p - the pokemon user wants to add to party
	 */
	public void addPokemon(Pokemon p)
	{
		pokemon.add(p);
	}
	
	/**
	 * Heals the users current set pokemon 
	 * if user has no potions return a text to notify user that they cannot heal
	 */
	public void healCurrentPokemon()
	{
		pokemon.get(currentPokemon).heal();
	}
	
	/**
	 * Heals all pokemon within the users party when user reaches the PokeCenter
	 */
	public void healAllPokemon()
	{
		int numOfParty = pokemon.size();
		for(int i = 0; i<numOfParty; i++)
		{
				pokemon.get(i).heal();
			
		}
	}
	
	
	/**
	 * when user decides to switch our their pokemon
	 * changes reference point to the chosen pokemon 
	 * @param cur - who user wants to set as current pokemon
	 * @return - the new current pokemon
	 */
	public int setCurrentPokemon(int cur)
	{
		currentPokemon = cur;
		
		return currentPokemon;
	}
	//Where all the pokemon balls and potions should be.
	
	/**
	 * @return pokemon.size() - the size of the users party
	 */
	public int getSize() {
		return pokemon.size();
	}
	
	/**
	 * Abstract function for attack speech
	 */
	public abstract String attackSpeech();
	public abstract String winSpeech();
	public abstract String lossSpeech();
	public abstract int chooseStyle();
	public abstract int chooseMove(int style);

}
