/**
 * This class simulates an area that is predetermined by the text files given
 * Creates a 5x5 map for the user to be able to walk around and experience events
 */
import java.awt.Point;
import java.io.*;
import java.util.Scanner;

public class Map {
	/** a double array that represents the 5x5 map created, type char because each index of the array will be a character*/
	private char [][]map;
	/** a double array that represents a 5x5 map also, but will return a  boolean in order for us to reveal certain characters on map*/
	private boolean [][] revealed;
	
	/**
	 * Map constructor that creates a 5x5 array for both the character and the boolean
	 */
	public Map()
	{
		map = new char[5][5];
		revealed = new boolean[5][5];
	}
	
	/**
	 * Generates what the area given the number
	 * Creates the map by reading the Area text files and implementing them into the 2D array
	 * Will store each character in every index in the 5x5 array
	 * Will throw a FileNotFound exception if the text file could not be found
	 * @param mapNum - What area to generate
	 */
	public void generateArea(int mapNum)
	{
		try
		{
			Scanner read = new Scanner(new File("Area1.txt"));
			
			if(mapNum == 2)
			{
				read = new Scanner(new File("Area2.txt"));
			}
		
			else if(mapNum == 3)
			{
				read = new Scanner(new File("Area3.txt"));
			}
			
			do{
				for(int colo = 0; colo < 5; colo++)
				{
					String line = read.nextLine();
					String[] c = line.split(" ");
					for(int row = 0; row < 5; row++)
					{
						char check = c[row].charAt(0);
						map[row][colo] = check;
						if(map[row][colo] == 's' || map[row][colo] == 'c')
						{
							revealed[row][colo] = true;
						}
						
						else
						{
							revealed[row][colo] = false;
						}
					}
				}
		}
		while(read.hasNext());
	
		read.close();
	}
	catch( FileNotFoundException fnf)
	{
		System.out.println("File was not found.");
	}
}
		
	/**
	 * Gets the character at the location
	 * @param p - the location of the character
	 * @return location - returns the character at the location within the 2D array
	 */
	public char getCharAtLoc(Point p)
	{
		int x = (int) p.getX();
		int y= (int) p.getY();
		char location = map[x][y];
		
		if(x < 0 || x >5 || y < 0 || y > 5)
		{
			return 'X';
		}
		return location;
	}
	
	/**
	 * Generates the area and displays it to the user
	 * Will display the character's. 's' and 'c' and everything else will display as x's
	 * @param p - the location of where the user starts
	 */
	public void displayMap(Point p)
	{
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		for(int colo = 0; colo < 5; colo++)
		{
			for(int row = 0; row < 5; row++)
			{
					if(x == row & y == colo)
					{
						System.out.print("* ");
					}
					
					else if(revealed[row][colo] == true)
					{
						System.out.print(map[row][colo] + " ");
					}
				
				else
				{
					System.out.print("x ");
				}
				
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Locates the the start location in the maps by running through a for loop and searching each index
	 * If the character 's' is found in the index return the location
	 * @return new Point(row,colo); returns the location index of the start location
	 */
	public Point findStartLocation()
	{
		for(int colo = 0; colo<5; colo++)
		{
			for(int row = 0; row < 5; row++)
			{
				if(map[row][colo] == 's')
				{
					return new Point(row,colo);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Reveals the chracter at the location parameter
	 * @param p - location coordinates
	 */
	public void reveal(Point p)
	{
		int x =(int) p.getX();
		int y =(int) p.getY();
		
		revealed[x][y] = true;
	}
	
	/**
	 * Once a opponent or pokemon is defeated, it will swap out the characters to 'n'
	 * @param p - location coordinates
	 */
	public void removeOppAtLoc(Point p)
	{
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		map[x][y] = 'n';
	}

}
