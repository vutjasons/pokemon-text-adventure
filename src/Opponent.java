
/**
 * Class function that is an object, Opponent that creates a player for the user to battle
 * Will contain the basic features of what a player has
 * win speech, loss speech, attack speech, name, hp, choose move and choose style
 */
import java.util.Random;

public class Opponent extends Trainer {
	/** attack speech for the opponent*/
	private String atkSpeech;
	/** win speech for the opponent*/
	private String winSpeech;
	/** lose speech for the opponent*/
	private String lossSpeech;
	
	/**
	 * Constructor for the opponent to create the opponent
	 * @param name1 - name of the opponent
	 * @param hp1 - health of the opponent
	 * @param atk - attack speech of opponent
	 * @param win - win speech of opponent
	 * @param loss - loss speech of opponent
	 */
	public Opponent(String name1, int hp1, String atk, String win, String loss) {
		super(name1, hp1);
		
		atkSpeech = atk;
		winSpeech = win;
		lossSpeech = loss;
	}
	
/**
 * Opponent says a speech in which they introduce themselves in a way
 * @return atkSpeech - returns the attack speech text pulled from the OpponentMaker class
 */
	@Override
	public String attackSpeech()
	{
		
		return atkSpeech;
		
	}
	/**
	 * Opponent says a speech in which they tell the player a speech when they beat the player
	 * @return winSpeech - returns the win speech text pulled from the OpponentMaker class
	 */
	@Override
	public String winSpeech() 
	{
		return winSpeech;
	}

	/**
	 * Opponent says a speech in which they tell the player a speech when they lose to the player
	 * @return lossSpeech - returns the lose speech text pulled from the OpponentMaker class
	 */
	@Override
	public String lossSpeech() 
	{
		return lossSpeech;
	}

	/**
	 * Function that randomly generates an integer for the opponent to choose what attack style they want to use
	 * Overrides the abstract function within the trainer class
	 */
	@Override
	public int chooseStyle()
	{
		Random random = new Random();
		int styleChoice = random.nextInt(2)+1;
		return styleChoice;
		
	}

	/**
	 * Function that randomly generates an integer for the opponent to choose what move they want to use
	 * Overrides the abstract function within the trainer class
	 */
	@Override
	/** Shows what moves of attacks user wants to choose
	 * user has an option of choose 3 moves when they select what style of attack
	 * @param style - input from chooseStyle
	 * @return input - what move the user decides to use to attack
	 */
	public int chooseMove(int style)
	{
		Random random = new Random();
		if(style == 1)
		{
			int moveChoice = random.nextInt(3)+1;
			return moveChoice;
		}
		else
		{
			int moveChoice = random.nextInt(3)+1;
			return moveChoice;
		}
	}

	/**
	 * Function that displays the opponents stats when the user runs into an opponent
	 * Overrides the display stats function in the trainer class
	 */
	@Override
	public void displayStats() 
	{
		System.out.println("ENEMY POKEMON: " +getCurrentPokemon().getName());
		System.out.println("Name: " +getName());
	}
	
	


}
