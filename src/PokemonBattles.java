/**
 * PokemonBattles - Simulates a pokemon battle with a Player or wild pokemon
 * Simulates  catching pokemon
 * Simulates damage inflicted on user with a Player or pokemon
 * @author jasonvu
 */
import java.util.Random;

public class PokemonBattles {
	/**
	 * 2D array table to multiply damage depending on pokemon type
	 */
	private final static double [][] fightTable = {{1,.5,2},{2,1,.5},{.5,2,1}};
	
	/**
	 * Simulates a wild pokemon battle
	 * @param player - Player who is the user
	 * @param poke - wild generated pokemon
	 */
	public static void pokemonBattle(Player player, Pokemon poke, Map m)
	{
	
		/**
		 * While loop to check that player and wild pokemon is not less than 0
		 */
		while(player.getCurrentPokemon().getHp() > 0 && poke.getHp() > 0 && player.gainHp() >0)
		{
			/**
			 * Creates random function and random integers within a range for wild pokemon to choose its move and skills menu
			 */
			Random random = new Random();
			int styleChoice = random.nextInt(2)+1;
			int moveChoice = random.nextInt(3)+1;
			
			/**
			 * Asks user what option they would like to do during battle simulation
			 * Options: Fight, Catch Pokemon, Heal Pokemon, Run
			 */
			System.out.println("What do you want to do?");
			System.out.println("1. Fight\n2. Catch Pokemon\n3. Heal Pokemon \n4. Run");
			int input = CheckInput.checkIntRange(1,4);
			
			/**
			 * If user chooses to fight, we will call the battle function from Player class and distribute damage to wild pokemon
			 * Damage will also return to the users pokemon using using the fight function for the wild pokemon
			 */
			if(input == 1)
			{
				//Displays attack speech
				player.attackSpeech();
				
				//Initiate the battle
				int damage = player.battle();
				
				//Damage is calculated and is dealt to the wild pokemon
				damage = damage*=fightTable[player.getCurrentPokemon().getType()][poke.getType()];
				poke.loseHp(damage);
			
				/**
				 * Wild pokemon return damage
				 * @param styleChoice - wildpokemon randomly selects the basic menu or special menu
				 * @param moveChoice - wildpokemon randomly selects what move to you to inflict damage
				 */
				int enemyPokeDmg = poke.fight(styleChoice, moveChoice);
				enemyPokeDmg = enemyPokeDmg *= fightTable[poke.getType()][player.getCurrentPokemon().getType()];
				
				//User's pokemon loses HP from damage calculated from enemyPokeDmg
				player.getCurrentPokemon().loseHp(enemyPokeDmg);
				
				
				//Displays hp, level of both users pokemon and the wildpokemon
				System.out.println(player.getCurrentPokemon().getName()+ " HP: " + player.getCurrentPokemon().getHp() + " Level: "+ player.getCurrentPokemon().getLevel());
				System.out.println(poke.getName()+"[ENEMY]"+ " HP: " + poke.getHp() + " Level: "+ poke.getLevel());
				
				
			}
			
			/**
			 * Input 2 uses the catchPokemon function in order for a chance to catch the wild pokemon
			 * @param player - user as the Player
			 * @param poke - wild pokemon
			 */
			else if(input == 2)
			{
				//If catchPokemon returns a false then that means that the catch was successful
				//Exit loop and return to main menu
				if(catchPokemon(player, poke, m) == false)
				{
					break;
				}
				
			}
			
			/**
			 * Input 3 uses the users potion to heal their current pokemon
			 */
			else if(input == 3)
			{
				System.out.println("Your pokemon is healed.");
				player.getCurrentPokemon().heal();
			}
			
			/**
			 * Input 4 means that the user decides to run away from the battle
			 */
			else if(input == 4)
			{
				System.out.println("You ran away in a random direction!");
				Random r1 = new Random();
				boolean loop= true;
                while(loop==true) {
                    int direction = r1.nextInt(4)+1;
                    switch(direction) {
                    case 1: 
                        if(player.goNorth(m)!='x') {
                        	System.out.println("You ran North!");
                        	//player.goNorth(m);
                            loop=false;
                        }
                        break;
                    case 2:
                        if(player.goSouth(m)!='x') {
                        	System.out.println("You ran South!");
                        	//player.goSouth(m);
                            loop=false;
                        }
                        break;
                    case 3:
                        if(player.goEast(m)!='x') {
                        	System.out.println("You ran East!");
                        	//player.goEast(m);
                            loop=false;
                        }
                        break;
                    case 4:
                        if(player.goWest(m)!='x') {
                        	System.out.println("You ran West!");
                            loop=false;
                        }
                        break;
                    }
                    break;
			}
                break;
	
		}
		
		/**
		 * If function to check if players health is at 0
		 * if health = 0 close game and tell user that game is now over
		 */
		if(player.getHp() == 0)
		{
			System.out.println("You have died as a player, the game is now over. Please restart if you would like to play again");
		}
		
		/**
		 * Function checks to see if HP of wild pokemon is less than zero
		 * If wild pokemon is less than zero then the pokemon has fainted
		 */
		if(poke.getHp() == 0 && player.getCurrentPokemon().getHp() > 0)
		{
			System.out.println(poke.getName() +" Has fainted.");
			player.winSpeech();
			
			//Create two randoms for money and levelup
			Random random2 = new Random();
			Random moneyGive = new Random();
			
			//Give rates of possible chance to level up and gain money
			int money = moneyGive.nextInt(15-8)+8;
			int levelUp = random2.nextInt(10)+1;
			
			//If the integer levelUp randomizes to anything greater than 5 then increase users pokemon by 1 level and give money
			if(levelUp > 5)
			{
				System.out.println("Your pokemon leveled up!");
				player.getCurrentPokemon().gainLevel();
				System.out.println("Won: $" +money);
				player.gainMoney(money);
			}
			
			//Else just give money
			else
			{
				System.out.println("Won: $" +money);
				player.gainMoney(money);
			}
		}
		
		/**
		 * If users pokemon health hits below zero and wild is above 0 then users pokemon has fainted
		 * Player must retreat and receives damage for running
		 * Tells user to go to a town and heal pokemon
		 */
		else if(player.getCurrentPokemon().getHp() == 0 && poke.getHp() > 0)
		{
			player.lossSpeech();
			System.out.println("Your Pokemon is out of Hp, you run away and take 10 damage.");
			player.loseHp(10);
		}
		
		else if(player.getCurrentPokemon().getHp() == 0 && poke.getHp() == 0)
		{
			System.out.println("Both Pokemon's have fainted and you need to rush a Pokecenter quickly!");
		}
		}
	}
	
	
	/**
	 * Function creates a random events that may happen to the player as they are traveling
	 * @param player - the user as the Player
	 */
	public static void angryPokemon(Player player)
	{
		//Creates random class for what event to use when rolled.
		Random random = new Random();
		int lucky = random.nextInt(5)+1;
		
		/**
		 * Each case inflicts an amount of damage to user
		 */
		if(lucky == 1)
		{
			System.out.println("Player Joe has whacked you with his net -3 Damage");
			player.loseHp(3);
		}
		
		else if(lucky == 2)
		{
			System.out.println("You fell down a small hill -5 Damage");
			player.loseHp(5);
		}
		else if(lucky == 3)
		{
			System.out.println("Your girlfriend calls you and says that it isn't going to work out.");
			System.out.println("The emotional pain deals -8 Damage");
			player.loseHp(8);
		}
		else if(lucky == 4)
		{
			System.out.println("Someone threw a rock at you -6 Damage");
			player.loseHp(6);
		}
		else
		{
			System.out.println("You fell your bike -5 Damage");
			player.loseHp(5);
		}
	}
	
	/**
	 * Function creates random event of a wild pokemon that inflicts damage to the player
	 * @param player - the user as the Player
	 */
	public static void angryPerson(Player player)
	{
		//Randomly generates the a number to choose what damage is inflicted on user
		Random random = new Random();
		int lucky = random.nextInt(3)+1;
		
		/**
		 * Each case results in a different damage to the user
		 */
		if(lucky == 1)
		{
			System.out.println("A Charmander used Flame Blast and burned you -10 Damage");
			player.loseHp(10);
		}
		
		else if(lucky == 2)
		{
			System.out.println("A Squirtle used Water Gun on you -4 Damage");
			player.loseHp(4);
		}
		else
		{
			System.out.println("A Ponyta stepped on your foot -3 Damage");
			player.loseHp(3);
		}
	}
	
	/**
	 * Function is used to try and catch a pokemon
	 * @param player - user as the Player
	 * @param poke - wild pokemon wanting to be catched
	 * @return true or false
	 */
	public static boolean catchPokemon(Player player, Pokemon poke, Map m)
	{
		Random ran1 = new Random();
		Random ran2 = new Random();
		int style = ran1.nextInt(2)+1;
		int move = ran2.nextInt(3)+1;
		/**
		 * Case if size is greater than 6 then the user is not allowed to catch the pokemon
		 * @return true
		 */
		if(player.getSize() == 6)
		{
			System.out.println("Your party is too full, you cannot catch this pokemon.");
			return true;
		}
		
		/**
		 * Case if user has no more pokeballs then user is not allowed to catch
		 * @return true
		 */
		else
		{
			if(player.getNumPokeballs() == 0)
			{
				System.out.println("You have no pokeballs to capture this pokemon");
				return true;
			}
		/**
		 * Last case user throws a pokeball for a chance to catch the pokemon
		 */
			else
			{
				//User uses a pokeball
				player.usePokeball();
				System.out.println("You throw a pokeball!");
				
				/**
				 * Function is to see if wild pokemon has health
				 * The less health the pokemon has the higher the chance user has of catching
				 */
				int hp = poke.getMaxHp() - poke.getHp();
				if(hp < 50)
				{
					Random random = new Random();
					int lucky = random.nextInt(60)+1;
				
					/**
					 * If health if wild pokemon is less than 65%, rate to catch pokemon is increased
					 * if the lucky randomizes to anything above 35 then catch is successful
					 * else catch fails and asks user if they would like to throw another one
					 * @returns false
					 */
					if( lucky > 35)
					{
						System.out.println("Shake..Shake...Shake...");
						System.out.println("You have successfully caught " + poke.getName());
						player.addPokemon(poke);
						m.removeOppAtLoc(player.getLocation());
						return false;
					}
				
					else
					{
						System.out.println("Shake..Shake...Shake...");
						System.out.println("You have failed to catch " + poke.getName());
						System.out.println(poke.getName() + " attacks back!");
						int returnDmg = poke.fight(style, move);
						player.getCurrentPokemon().loseHp(returnDmg);
						System.out.println(poke.getName() + " attacks back and deals " + returnDmg + " points of damage!");
						System.out.println(player.getCurrentPokemon().getName()+ " HP: " + player.getCurrentPokemon().getHp() + " Level: "+ player.getCurrentPokemon().getLevel());
						System.out.println(poke.getName()+"[ENEMY] "+" HP: " + poke.getHp() + " Level: "+ poke.getLevel());
						return true;
					}
				}
			
				/**
				 * If health of wild pokemon is 80% then the chance to catch wild pokemon is a 50%
				 * If lucky is above 50 then the catch will be successful
				 * @return false
				 * Else if it is less than the catch will fail
				 * @return true
				 */
			else
			{
				Random random = new Random();
				int lucky = random.nextInt(80)+1;
				if( lucky > 50)
				{
					System.out.println("Shake..Shake...Shake...");
					player.addPokemon(poke);
					System.out.println("You have successfully caught" + poke.getName());
					m.removeOppAtLoc(player.getLocation());
					return false;
				}
				
				else
				{
					System.out.println("Shake..Shake...Shake...");
					System.out.println("You have failed to catch" + poke.getName());
					System.out.println(poke.getName() + " attacks back!");
					int returnDmg = poke.fight(style, move);
					player.getCurrentPokemon().loseHp(returnDmg);
					System.out.println(poke.getName() + " attacks back and deals " + returnDmg + " points of damage!");
					System.out.println(player.getCurrentPokemon().getName()+ " HP: " + player.getCurrentPokemon().getHp() + " Level: "+ player.getCurrentPokemon().getLevel());
					System.out.println(poke.getName()+"[ENEMY] "+" HP: " + poke.getHp() + " Level: "+ poke.getLevel());
					return true;
				}
			}
		}
	}
	}
	
	/**
	 * Function for when a player encounters a Player/villain and simulates a battle between them
	 * @param player - user as the Player
	 * @param poke - pokemon of the enemy
	 */
	public static void pokemonBattleEnemy(Player player, Pokemon poke, Map m)
	{
		
		while(player.getCurrentPokemon().getHp() > 0 && poke.getHp() > 0)
		{
			//Randomizer for the enemy to attack and choose special or basic menu
			Random random = new Random();
			int styleChoice = random.nextInt(2)+1;
			int moveChoice = random.nextInt(3)+1;
			/**
			 * Asks user what option they would like to do during battle simulation
			 * Options: Fight, Heal Pokemon, Run
			 */
			System.out.println("What do you want to do?");
			System.out.println("1. Fight\n2. Heal Pokemon\n3. Run");
			int input = CheckInput.checkIntRange(1,3);
			/**
			 * If user chooses to fight, we will call the battle function from Player class and distribute damage to enemy pokemon
			 * Damage will also return to the users pokemon using using the fight function for the enemy pokemon
			 */
			if(input == 1)
			{
				//Display attack speech
				player.attackSpeech();
				
				//Initiates the battle between enemy and player
				int damage = player.battle();
				
				//Damage dealt to enemy pokemon
				damage = damage*=fightTable[player.getCurrentPokemon().getType()][poke.getType()];
				poke.loseHp(damage);
			
				/**
				 * Enemys turn to attack and return inflicted damage to user
				 */
				int enemyPokeDmg = poke.fight(styleChoice, moveChoice);
				enemyPokeDmg = enemyPokeDmg *= fightTable[poke.getType()][player.getCurrentPokemon().getType()];
				player.getCurrentPokemon().loseHp(enemyPokeDmg);
				
				
				System.out.println(player.getCurrentPokemon().getName()+ " HP: " + player.getCurrentPokemon().getHp() + " Level: "+ player.getCurrentPokemon().getLevel());
				System.out.println(poke.getName()+"[ENEMY] "+" HP: " + poke.getHp() + " Level: "+ poke.getLevel());
				
			}
			
			/**
			 * Input 2 will heal users current pokemon in battle
			 */
			else if(input == 2)
			{
				System.out.println("Your pokemon is healed.");
				player.getCurrentPokemon().heal();
			}
			/**
			 * If user decides to run then 5 damage is inflicted on the user's pokemon
			 */
			else if(input == 3)
			{
				System.out.println("You ran away in a random direction!");
				Random r1 = new Random();
				boolean loop = true;
                while(loop==true) {
                    int direction = r1.nextInt(4)+1;
                    switch(direction) {
                    case 1: 
                        if(player.goNorth(m)!='x') {
                        	System.out.println("You ran North!");
                        	//player.goNorth(m);
                            loop=false;
                        }
                        break;
                    case 2:
                        if(player.goSouth(m)!='x') {
                        	System.out.println("You ran South!");
                        	//player.goSouth(m);
                            loop=false;
                        }
                        break;
                    case 3:
                        if(player.goEast(m)!='x') {
                        	System.out.println("You ran East!");
                        	//player.goEast(m);
                            loop=false;
                        }
                        break;
                    case 4:
                        if(player.goWest(m)!='x') {
                        	System.out.println("You ran West!");
                            loop=false;
                        }
                        break;
                    }
           
                }
	break;
		}
			
			/**
			 * If enemy pokemon hp is 0 and users pokemon is great than 0
			 * reward user with money and a chance to levle up
			 */
			if(poke.getHp() == 0 && player.getCurrentPokemon().getHp() > 0)
			{
				player.winSpeech();
				Random random2 = new Random();
				Random moneyGive = new Random();
				int money = moneyGive.nextInt(15-8)+8;
				int levelUp = random2.nextInt(10)+1;
				if(levelUp > 4)
				{
					System.out.println("Your pokemon leveled up!");
					player.getCurrentPokemon().gainLevel();
					System.out.println("Won: $" +money);
					player.gainMoney(money);
				}
				else
				{
					player.gainMoney(money);
					System.out.println("You've won some money for winning! ");
					System.out.println("Won: $" +money);
				}
			}
			/**
			 * if users pokemon is 0 and enemy pokemon is greater than the user has lost the battle
			 * user gets inflicted with 10 damage 
			 */
			else if(player.getCurrentPokemon().getHp() == 0 && poke.getHp() > 0)
			{
				player.lossSpeech();
				System.out.println("Your Pokemon is out of Hp, you run away and take 10 damage.");
				player.loseHp(10);
			}
			
		}
	}
	
	

}
