/**
 * Text adventure game that reads a file entered by the player, and uses it to form the game map. The player 
 * can then move North, East, South and West until the find the treasure. fall into a pit, or encounter the beast.
 * 
 * @author Zach Showman
 * @version December 9, 2020
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Explorer {
	
	//Integers that define the values in the maze
	public static int empty = 0;
	public static int beast = 1;
	public static int pit = 2;
	public static int treasure = 3;
	public static int player = 4;
	
	/**
	 * Reads a file that has been input by the user, and only adds the elements of the file 
	 * that aren't commas to the mapElements List, and prints an error message if the file
	 * cannot be found. 
	 * 
	 * @param fileName
	 * 				The name of the file that is being read
	 * @return List of numbers read from the file
	 */
	public static List<String> importMap (String fileName) {
		//List that will store the elements used to create the game map
		List<String> mapElements = new ArrayList<String>();
		
		try {
			//Creates a new text file from the user input file name
    		File textFile = new File(fileName);
    		//Creates a scanner to read the text file
        	Scanner textScanner = new Scanner(textFile);
        	
        	//While loop that iterates until the textScanner has read
        	//the last line of the file
        	while (textScanner.hasNext()) {
        		//String used to take the line being read and write it to
        		//the mapElements list
        		String str = textScanner.nextLine();
        		//For loop that creates substrings of str
        		for (int i=0; i<str.length(); i++) {
        			//If statement that only writes to the List if the current
        			//index is not a comma
        			if (str.charAt(i) != ',') {
        				mapElements.add(str.substring(i, i+1));
        			}
        		}
        	}
        	textScanner.close();
        }
        
		//Catch exception that outputs an error message if the file cannot be found
        catch (FileNotFoundException e) {
        	System.out.println("ERROR - Cannot load file " + fileName);
        }
		
		//Returns the mapElements List
		return mapElements;
	}
	
	/**
	 * Takes the elements from the mapElements list and translates them to integers, and writes them to 
	 * the 2D array gameMap.
	 * 
	 * @param mapElements
	 * 				List that is storing the elements from the file needed to create the game map
	 * @return 2D Array that contains the map used for the game
	 */
	public static int[][] drawMap (List<String> mapElements) {
		//Creates a 2D Array with dimensions defined by the first index of the mapElements List, used as the map
		//for the game
		int[][] gameMap = new int[Integer.parseInt(mapElements.get(0))][Integer.parseInt(mapElements.get(0))];
		
		int j=1;
		//For loop used to translate the String indexes from mapElements to integers for the gameMap Array
		//i is used for the rows, k is used for the columns, and j iterates through the mapElements List
		for (int i=0; i<Integer.parseInt(mapElements.get(0)); i++) {
			for (int k=0; k<Integer.parseInt(mapElements.get(0)); k++) {
				gameMap[i][k] = Integer.parseInt(mapElements.get(j));
				j++;
			}
		}
		
		return gameMap;
	}
	
	/**
	 * Finds the players location on the map using the player variable, and saves the row and column to the playerLocation Array
	 * 
	 * @param gameMap
	 * 				A 2D Array of the map for the game
	 * @param mapElements
	 * 				List that is storing the elements from the file needed to create the game map
	 * 				 used to get the size of the game map
	 * @return An Array with two indexes that stores the current player location using the 
	 * 			row and column
	 */
	public static int[] playerLocation (int[][] gameMap, List<String> mapElements) {
		//Array used to store the players current location
		int[] playerLocation = new int[2];
		
		//For loop that iterates through the game map until it finds the index that equals the 
		//player variable
		for (int i=0; i<Integer.parseInt(mapElements.get(0)); i++) {
			for (int k=0; k<Integer.parseInt(mapElements.get(0)); k++) {
				if(gameMap[i][k] == player) {
					//Sets the first index equal to the row
					playerLocation[0] = i;
					//Sets the second index equal to the column
					playerLocation[1] = k;
					break;
				}
			}
		}
		
		//Prints the players current location to the screen for the player to see
		System.out.println("You are in location row:" + playerLocation[0] + " col:" + playerLocation[1]);
		
		return playerLocation;
	}
	
	/**
	 * Takes the players current location into account in order to find 
	 * which direction the player is able to move
	 * 
	 * @param gameMap
	 * 				A 2D Array of the map for the game
	 * @param playerLocation
	 * 				Array that stores the row and column the player is currently in
	 * @return List that contains the directions the player can move, based on their current location
	 */
	public static List<Character> possibleMoves(int[][] gameMap, int[] playerLocation) {
		//List to store the directions the player can move
		List<Character> possibleMoves = new ArrayList<Character>();
		
		//If statement that only works if the player is not in the first row
		if (playerLocation[0] != 0) {
			//Adds North as a possible direction
			possibleMoves.add('N');
		}
		//If statement that only works if the player is not in the last column
		if (playerLocation[1] != 3) {
			//Adds East as a possible direction
			possibleMoves.add('E');
		}
		//If statement that only works if the player is not in the last row
		if (playerLocation[0] != 3) {
			//Adds South as a possible direction
			possibleMoves.add('S');
		}
		//If statement that only works if the player is not in the first column
		if (playerLocation[1] != 0) {
			//Adds West as a possible direction
			possibleMoves.add('W');
		}
		
		//Prints all the exits in the possibleMoves list to the screen for the player
		System.out.print("There are exits to the: ");
		for (int i=0; i<possibleMoves.size(); i++) {
			System.out.print(possibleMoves.get(i));
		}
		System.out.println();
		
		return possibleMoves;
	}
	
	/**
	 * Checks the game map for any pitfalls or beasts near the players current location, and if
	 * one is detected, it displays a message that warns the player of their presence.
	 * 
	 * @param playerLocation
	 * 					Array that stores the current row and column the player is in
	 * @param gameMap
	 * 					2D Array that stores the entire game map
	 */
	public static void areaCheck (int[] playerLocation, int[][] gameMap) {
		//Variables used to count how many times a pitfall or beast has been detected 
		int breeze = 0;
		int growling = 0;
		
		//If statement that checks the position north of the the player, and only iterates if the
		//player isn't in the first row and the index north of the player isn't empty
		if (playerLocation[0] !=0 && gameMap[playerLocation[0]-1][playerLocation[1]] != empty) {
			//If, else if statements that detects if the index north of the player contains a beast
			//or a pit, and displays a message depending on which is detected. Breeze and growling 
			//variables are used to only display the message once, even if a pit is both east and 
			//south of the player.
			if (gameMap[playerLocation[0]-1][playerLocation[1]] == beast && growling < 1) {
				System.out.println("You hear a growling noise.");
				growling++;
			}
			else if(gameMap[playerLocation[0]-1][playerLocation[1]] == pit && breeze < 1) {
				System.out.println("You feel a breeze.");
				breeze++;
			}
		}
		//Same as above, but checks the index east of the player
		if (playerLocation[1] != 3 && gameMap[playerLocation[0]][playerLocation[1]+1] != empty) {
			if (gameMap[playerLocation[0]][playerLocation[1]+1] == beast && growling < 1) {
				System.out.println("You hear a growling noise.");
				growling++;
			}
			else if(gameMap[playerLocation[0]][playerLocation[1]+1] == pit && breeze < 1) {
				System.out.println("You feel a breeze.");
				breeze++;
			}
		}
		//Checks the index south of the player
		if (playerLocation[0] != 3 && gameMap[playerLocation[0]+1][playerLocation[1]] != empty) {
			if (gameMap[playerLocation[0]+1][playerLocation[1]] == beast && growling < 1) {
				System.out.println("You hear a growling noise.");
				growling++;
			}
			else if(gameMap[playerLocation[0]+1][playerLocation[1]] == pit && breeze < 1) {
				System.out.println("You feel a breeze.");
				breeze++;
			}
		}
		//Checks the index west of the player
		if (playerLocation[1] != 0 && gameMap[playerLocation[0]][playerLocation[1]-1] != empty) {
			if (gameMap[playerLocation[0]][playerLocation[1]-1] == beast && growling < 1) {
				System.out.println("You hear a growling noise.");
				growling++;
			}
			else if(gameMap[playerLocation[0]][playerLocation[1]-1] == pit && breeze < 1) {
				System.out.println("You feel a breeze.");
				breeze++;
			}
		}
	}	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//Asks the user for a file name that will be used to build the game map
		System.out.print("Enter a map file name: ");
		String fileName = input.nextLine();
		
		//Creates a List of the map elements using the importMap method
		List<String> mapElements = importMap(fileName);
		
		//Creates a 2D array used for the game map from the drawMap method
		int[][] gameMap = drawMap(mapElements);
		
		//Places the player in the first index of the gameMap Array
		gameMap[0][0] = player;
		
		//Sets the win condition to 0, 1 is a win, -1 is a loss
		int win = 0;
		
		//While loop that iterates until the player wins or loses
		while (win == 0) {
			//Array used to store the row and column that the player currently occupies
			//using the playerLocation method
			int[] playerLocation = playerLocation(gameMap, mapElements);
			
			//Creates a list of moves that the player can make using the possibleMoves method
			List<Character> possibleMoves = possibleMoves(gameMap, playerLocation);
			
			//Checks the area directly north, south, east, and west of the player for any
			//beasts or pits
			areaCheck(playerLocation, gameMap);
			
			//Asks the user which direction they would like to move
			System.out.print("Which way do you want to move? ");
			//The the user input and makes it all capital letters
			String str = input.nextLine().toUpperCase();
			//Takes the first letter of the users entry and makes it the players move choice
			Character playerMove = str.charAt(0);
			
			//Only allows for moves that are contained in the possibleMoves List
			while (!possibleMoves.contains(playerMove)) {
				//Prints a message if an invalid move is input by the user
				System.out.println("You can't go that way.");
				
				//Asks the user for another move choice 
				System.out.print("Which way do you want to move? ");
				str = input.nextLine().toUpperCase();
				playerMove = str.charAt(0);
			}
			
			//If, else if, else statements that move the player based on the direction 
			//that was entered by the user
			if (playerMove == 'N') {
				//If the index north of the player is empty, the player is moved to this index
				//and the previously occupied index is set as empty
				if (gameMap[playerLocation[0]-1][playerLocation[1]] == empty) {
					gameMap[playerLocation[0]-1][playerLocation[1]] = player;
					gameMap[playerLocation[0]][playerLocation[1]] = empty;
				}
				//If the index north of the player contains a beast, a message is displayed and
				//the win variable is changed to -1
				else if (gameMap[playerLocation[0]-1][playerLocation[1]] == beast) {
					System.out.println("Oh no! You have run into a ravenous Bugblatter Beast!");
					win = -1;
				}
				//If the index north of the player contains a pit, a message is displayed and
				//the win variable is changed to -1
				else if (gameMap[playerLocation[0]-1][playerLocation[1]] == pit) {
					System.out.println("AAAARGH! You have fallen into a pit!");
					win = -1;
				}
				//Lastly, if the index north of the player is not empty, a beast, or a pit, then it
				//must contain the treasure. A message is displayed and the win variable is changed
				//to 1
				else {
					System.out.println("You have found the gold!");
					win = 1;
				}
			}
			//Same as above but iterates if the player chose east
			else if (playerMove == 'E') {
				//Same as above but checks the index east of the player
				if (gameMap[playerLocation[0]][playerLocation[1]+1] == empty) {
					gameMap[playerLocation[0]][playerLocation[1]+1] = player;
					gameMap[playerLocation[0]][playerLocation[1]] = empty;
				}
				else if (gameMap[playerLocation[0]][playerLocation[1]+1] == beast) {
					System.out.println("Oh no! You have run into a ravenous Bugblatter Beast!");
					win = -1;
				}
				else if (gameMap[playerLocation[0]][playerLocation[1]+1] == pit) {
					System.out.println("AAAARGH! You have fallen into a pit!");
					win = -1;
				}
				else {
					System.out.println("You have found the gold!");
					win = 1;
				}
			}
			//Same as above but iterates if the player chose south
			else if (playerMove == 'S') {
				//Same as above but checks the index south of the player
				if (gameMap[playerLocation[0]+1][playerLocation[1]] == empty) {
					gameMap[playerLocation[0]+1][playerLocation[1]] = player;
					gameMap[playerLocation[0]][playerLocation[1]] = empty;
				}
				else if (gameMap[playerLocation[0]+1][playerLocation[1]] == beast) {
					System.out.println("Oh no! You have run into a ravenous Bugblatter Beast!");
					win = -1;
				}
				else if (gameMap[playerLocation[0]+1][playerLocation[1]] == pit) {
					System.out.println("AAAARGH! You have fallen into a pit!");
					win = -1;
				}
				else {
					System.out.println("You have found the gold!");
					win = 1;
				}
			}
			//Same as above but iterates if the player chose west
			else {
				//Same as above but checks the index west of the player
				if (gameMap[playerLocation[0]][playerLocation[1]-1] == empty) {
					gameMap[playerLocation[0]][playerLocation[1]-1] = player;
					gameMap[playerLocation[0]][playerLocation[1]] = empty;
				}
				else if (gameMap[playerLocation[0]][playerLocation[1]-1] == beast) {
					System.out.println("Oh no! You have run into a ravenous Bugblatter Beast!");
					win = -1;
				}
				else if (gameMap[playerLocation[0]][playerLocation[1]-1] == pit) {
					System.out.println("AAAARGH! You have fallen into a pit!");
					win = -1;
				}
				else {
					System.out.println("You have found the gold!");
					win = -1;
				}
			}
			
			System.out.println();
		}
		
		//Different messages display based on if the player won or lost
		if (win == 1) {
			System.out.println("You have won!  Congratulations!");
		}
		else {
			System.out.println("You have died!  Game over!");
		}
		
		input.close();
	}
}
