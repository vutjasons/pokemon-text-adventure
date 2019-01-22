/**
 * This class creates opponents by reading a opponentlist text file
 * Will generate an opponent and random opponents
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OpponentMaker
{
	/** creates an opponent arraylist*/
	private ArrayList<Opponent> opponents;

	/**
	 * Constructor opponentMaker that reads the OpponentList and and creates the opponent class and adds it to the array to store
	 */
	public OpponentMaker()
	{
		/** declare the arrayList*/
		opponents = new ArrayList<Opponent>();
		
		/** declare the name*/
		String name;
		/** declare the health*/
		String hp;
		/** delare the attack speech*/
		String attack;
		/** declare the win speech */
		String win;
		/** declare the loss speech*/
		String loss;
		try
		{
			Scanner read  = new Scanner(new File("OpponentList.txt"));
			do
			{
				name = read.nextLine();
				hp = read.nextLine();
				attack = read.nextLine();
				loss = read.nextLine();
				win = read.nextLine();
				attack = attack.replace('#', '\n');
				loss = loss.replace('#',  '\n');
				
				Opponent o1 = new Opponent(name, Integer.parseInt(hp), attack, win, loss);
				opponents.add(o1);
				
			}while(read.hasNext());
			
			read.close();
		}
			
			catch( FileNotFoundException fnf)
			{
				System.out.println("File was not found.");
			}
	}
	
	/**
	 * Function generates a random number and from that number will be implemented in creating a opponent based on the index 0-2
	 * @return oppo - returns the opponent created
	 */
	public Opponent makeRandomOpponent()
	{
		
		Random random = new Random();
		
		int opponentGen = random.nextInt(2);
		
		Opponent oppo = new Opponent(opponents.get(opponentGen).getName(),opponents.get(opponentGen).getMaxHp()
				, opponents.get(opponentGen).attackSpeech(), opponents.get(opponentGen).winSpeech(),opponents.get(opponentGen).lossSpeech() );
		
		
		oppo.addPokemon(PokemonMaker.makeWildPokemon());
		opponents.add(oppo);
		return oppo;
	}
}
