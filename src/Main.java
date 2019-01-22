/*
 * Test function which runs Pokemon Adventure text game
 * @author jasonvu
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Main {
	public static void main(String args[])
	{
		
		Opponent[][] opp = new Opponent[5][5];
		File f = new File("pokemon.dat");
		Player ash = null;
		Map m1 = new Map();
		m1.generateArea(1);
		if(f.exists())
		{
			System.out.println("Would you like to continue your saved game or start  a new one?");
			
			System.out.println("1. Continue Game\n2. Restart Game");
			int input = CheckInput.checkIntRange(1,2);
			if(input == 1)
			{
				try{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					ash = (Player) in.readObject();
					in.close();
				}catch(IOException e){
					System.out.println("Error processing");
				}catch(ClassNotFoundException e)
				{
					System.out.println("Could not find file.");
				}	
			}
			else{
				 ash = new Player("Ash Ketchum", 50, m1.findStartLocation());
				//m1.generateArea(ash.getMapNum());
				
				System.out.println("You are a brand new trainer traveling the world in search of battles to win and pokemone to capture!");
				System.out.println("How exciting!");
				
				System.out.println("Choose your first pokemon!");
				System.out.println("1. Charmander\n2. Squirtle\n3. Bulbasaur");
				int input1 = CheckInput.checkIntRange(1,3);
			
				
				if(input1 == 1)
				{
					//PokemonMaker starter = new PokemonMaker();
					Pokemon starter1 = PokemonMaker.makeStartPokemon(input1);
					ash.addPokemon(starter1);
					System.out.println("You set off on your adventure with your buddy, Charmander");
				}

				else if(input1 == 2)
				{
					//PokemonMaker starter = new PokemonMaker();
					Pokemon starter1 = PokemonMaker.makeStartPokemon(input1);
					ash.addPokemon(starter1);
					System.out.println("You set off on your adventure with your buddy, Squirtle");
				}
				
				else
				{
					//PokemonMaker starter = new PokemonMaker();
					Pokemon starter1 = PokemonMaker.makeStartPokemon(input1);
					ash.addPokemon(starter1);
					System.out.println("You set off on your adventure with your buddy, Bulbasaur");
				}
			}
				
			}
		else{
		 ash = new Player("Ash Ketchum", 50, m1.findStartLocation());
		//m1.generateArea(ash.getMapNum());
		
		System.out.println("You are a brand new trainer traveling the world in search of battles to win and pokemone to capture!");
		System.out.println("How exciting!");
		
		System.out.println("Choose your first pokemon!");
		System.out.println("1. Charmander\n2. Squirtle\n3. Bulbasaur");
		int input = CheckInput.checkIntRange(1,3);
	
		
		if(input == 1)
		{
			//PokemonMaker starter = new PokemonMaker();
			Pokemon starter1 = PokemonMaker.makeStartPokemon(input);
			ash.addPokemon(starter1);
			System.out.println("You set off on your adventure with your buddy, Charmander");
		}

		else if(input == 2)
		{
			//PokemonMaker starter = new PokemonMaker();
			Pokemon starter1 = PokemonMaker.makeStartPokemon(input);
			ash.addPokemon(starter1);
			System.out.println("You set off on your adventure with your buddy, Squirtle");
		}
		
		else
		{
			//PokemonMaker starter = new PokemonMaker();
			Pokemon starter1 = PokemonMaker.makeStartPokemon(input);
			ash.addPokemon(starter1);
			System.out.println("You set off on your adventure with your buddy, Bulbasaur");
		}
		}
		
		m1.generateArea(ash.getMapNum());
		boolean loop = true;
		while(loop = true)
		{
			int choice = menu();
			
			if(ash.getHp() == 0)
			{
				System.out.println("You as the player died and the game is now over, Please restart if you'd like to play again from scratch!");
				System.exit(1);
			}
			switch (choice)
			{
			case 1:
				
				System.out.println("Where would you like to move?");
				System.out.println("Map:");
				char location;
				m1.displayMap(ash.getLocation());

				System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West");
				int move = CheckInput.checkIntRange(1,4);
				if(move == 1)
				{
					location = ash.goNorth(m1);
				}
				
				else if(move == 2)
				{
					location = ash.goSouth(m1);
				}
				
				else if(move == 3)
				{
					location = ash.goEast(m1);	
				}
				
				else
				{
					location = ash.goWest(m1);
				}
				
				if(location == 'x')
				{
					System.out.println("You cannot move in that direction");
				}
				
				else if(location == 'n')
				{
					System.out.println("Nothing was found at this location.");
				}
				
				else if(location == 'w')
				{
					//PokemonMaker wildPoke = new PokemonMaker();
					Pokemon wild = PokemonMaker.makeWildPokemon();
					System.out.println("You've encountered a wild Pokemon " + wild.getName() );
					System.out.println(wild.getName() +" HP: "+wild.getHp() +" Level: " +wild.getLevel());
					//PokemonBattles battle = new PokemonBattles();
					PokemonBattles.pokemonBattle(ash,wild, m1);
					if(wild.getHp() == 0)
					{
						m1.removeOppAtLoc(ash.getLocation());
					}
				}
				
				else if( location == 'o')
				{
					OpponentMaker enemy = new OpponentMaker();
					Opponent opp1 = enemy.makeRandomOpponent();
					int x = (int)ash.getLocation().getX();
					int y = (int)ash.getLocation().getY();

					if(opp[x][y] != null)
					{
						System.out.println("You have returned haha!");
						opp1 = opp[x][y];
						opp1.attackSpeech();
						opp1.displayStats();
						//PokemonBattles trainer = new PokemonBattles();
						PokemonBattles.pokemonBattleEnemy(ash,opp1.getCurrentPokemon(), m1);
						if(opp1.getCurrentPokemon().getHp() <= 0)
						{
							System.out.println(opp1.lossSpeech());
							m1.removeOppAtLoc(ash.getLocation());
						}
						
					}
					if(opp1.getCurrentPokemon().getHp() > 0 && ash.getCurrentPokemon().getHp() >0)
					{
						opp[x][y] = opp1;
					}
					
					if(ash.getCurrentPokemon().getHp() == 0)
					{
						System.out.println(opp1.winSpeech());
					}
						
					
					System.out.println(opp1.attackSpeech());
					opp1.displayStats();
					PokemonBattles.pokemonBattleEnemy(ash, opp1.getCurrentPokemon(), m1);
					
					if(opp1.getCurrentPokemon().getHp() == 0)
					{
						System.out.println(opp1.lossSpeech());
						m1.removeOppAtLoc(ash.getLocation());
					}
				}
				
				else if(location == 'f')
				{
					System.out.println("Your data is saved.");
					ash.incMapNum();
					try
					{
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
						out.writeObject(ash);
						out.close();
					}catch(IOException e)
					{
						System.out.println("Error processing file");
					}
					System.out.println("You've moved on to a new map!");
					m1.generateArea(ash.getMapNum());
					ash.setLocation(m1.findStartLocation());
				}
				
				else if(location == 'c')
				{
					boolean loop2 = true;
					while(loop2)
					{
						//Declare choice2 to the function pokeMartMenu()
						int choice2 = pokeMartMenu();
						switch(choice2)
						{
						//User chooses to heal all his/her pokemon
						case 1:
							System.out.println("All your Pokemon has been healed.");
							ash.healAllPokemon();
							break;
						//User chooses to possibly purchase pokeballs or potions
						case 2:
							System.out.println("Welcome to Pokemart, what would you like to do here?");
							System.out.println("1. Buy Pokeball($3)\n2. Buy Potion($5)\n3. Leave");
							int buy = CheckInput.checkIntRange(1, 3);
							if(buy == 1)
							{
								System.out.println("You have gained one Pokeball.");
								ash.spendMoney(3);
								ash.gainPokeball();
							}
							else if(buy == 2)
							{
								System.out.println("You have gained one Potion.");
								ash.spendMoney(5);
								ash.gainPotion();
							}
							else
							{
								System.out.println("Thanks for visiting the Pokemart!");
								break;
							}
							break;
							//User decides to leave town and continue on with his/her adventure
						case 3:
							loop2 = false;
							System.out.println("You are now leaving town...");
						}
					}
				
				}
				break;
			case 2:
				ash.displayAllPokemon();
				
				System.out.println("Which Pokemon would you like to set as your new current Pokemon?");
				int input2 = CheckInput.checkIntRange(1,6);
				ash.setCurrentPokemon(input2-1);
				System.out.println("Your new current Pokemon is: " + ash.getCurrentPokemon().getName());
				break;
			case 3:
				if(ash.getNumPotions() == 0)
				{
					System.out.println("You don't have any potions to heal.");
				}
				
				else{
						System.out.println(ash.getCurrentPokemon().getName() + " has been healed");
						ash.usePotion();
				}
				break;
			case 4:
				ash.displayStats();
				break;
			case 5:
				System.out.println("Thanks for playing!");
				loop = false;
				System.exit(1);
				break;
			}
		}

	
	}
	
	/**
	 * Function menu displays the option the user can do while on their adventure
	 * They can, travel, switch pokemon, heal current pokemon, view their stats or quit the game
	 * @return menuChoice - what choice they chosed from integer 1-5
	 */
	public static int menu()
	{
		System.out.println("What would you like to do?");
		System.out.println("1. Travel\n2. Switch Pokemon\n3. Heal Current Pokemon\n4. View Stats\n5. Quit Game");
		int menuChoice = CheckInput.checkIntRange(1,5);
		return menuChoice;
	}
	/**
	 * Function menu that asks the user if they would like to enter the pokemon center or enter the pokemart
	 * pokecenter will heal all their pokemon, or to buy potions or pokeballs
	 * @return martMenu - returns an integer of the users choice of what they want to do
	 */
	public static int pokeMartMenu()
	{
		System.out.println("You have found a town, what would you like to do here?");
		System.out.println("1. Go to Pokemon Center\n2. Go to Pokemart\n3. Leave");
		int martMenu = CheckInput.checkIntRange(1,3);
		return martMenu;
	}

}
