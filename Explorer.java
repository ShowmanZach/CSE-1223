import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Explorer {
	
	//Ints that define the values in the maze
	public static int empty = 0;
	public static int beast = 1;
	public static int pit = 2;
	public static int treasure = 3;
	public static int player = 4;
	
	/*
	 * Reads a file that has been input by the user 
	 */
	public static List<String> importMap (String fileName) {
		List<String> mapElements = new ArrayList<String>();
		
		try {
        	
    		File textFile = new File(fileName);
        	Scanner textScanner = new Scanner(textFile);
        	
        	
        	while (textScanner.hasNext()) {
        		String str = textScanner.nextLine();
        		for (int i=0; i<str.length(); i++) {
        			if (str.charAt(i) != ',') {
        				mapElements.add(str.substring(i, i+1));
        			}
        		}
        	}
        	textScanner.close();
        }
        
        catch (FileNotFoundException e) {
        	System.out.println("ERROR - Cannot load file " + fileName);
        }
		
		return mapElements;
	}
	
	/*
	 * 
	 */
	public static int[][] drawMap (List<String> mapElements) {
		int[][] gameMap = new int[Integer.parseInt(mapElements.get(0))][Integer.parseInt(mapElements.get(0))];
		
		int j=1;
		for (int i=0; i<Integer.parseInt(mapElements.get(0)); i++) {
			for (int k=0; k<Integer.parseInt(mapElements.get(0)); k++) {
				gameMap[i][k] = Integer.parseInt(mapElements.get(j));
				j++;
			}
		}
		
		return gameMap;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static int[] playerLocation (int[][] gameMap, List<String> mapElements) {
		int[] playerLocation = new int[2];
		
		for (int i=0; i<Integer.parseInt(mapElements.get(0)); i++) {
			for (int k=0; k<Integer.parseInt(mapElements.get(0)); k++) {
				if(gameMap[i][k] == player) {
					playerLocation[0] = i;
					playerLocation[1] = k;
					break;
				}
			}
		}
		
		System.out.println("You are in location row:" + playerLocation[0] + " col:" + playerLocation[1]);
		
		return playerLocation;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static List<Character> possibleMoves(int[][] gameMap, int[] playerLocation) {
		List<Character> possibleMoves = new ArrayList<Character>();
		
		if (playerLocation[0] != 0) {
			possibleMoves.add('N');
		}
		if (playerLocation[1] != 3) {
			possibleMoves.add('E');
		}
		if (playerLocation[0] != 3) {
			possibleMoves.add('S');
		}
		if (playerLocation[1] != 0) {
			possibleMoves.add('W');
		}
		
		System.out.print("There are exits to the: ");
		for (int i=0; i<possibleMoves.size(); i++) {
			System.out.print(possibleMoves.get(i));
		}
		System.out.println();
		
		return possibleMoves;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void areaCheck (int[] playerLocation, int[][] gameMap) {
		int breeze = 0;
		int growling = 0;
		
		if (playerLocation[0] !=0 && gameMap[playerLocation[0]-1][playerLocation[1]] != empty) {
			if (gameMap[playerLocation[0]-1][playerLocation[1]] == beast && growling < 1) {
				System.out.println("You hear a growling noise.");
				growling++;
			}
			else if(gameMap[playerLocation[0]-1][playerLocation[1]] == pit && breeze < 1) {
				System.out.println("You feel a breeze.");
				breeze++;
			}
		}
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
		
		System.out.print("Enter a map file name: ");
		String fileName = input.nextLine();
		
		List<String> mapElements = importMap(fileName);
		
		int[][] gameMap = drawMap(mapElements);
		
		gameMap[0][0] = player;
		
		int win = 0;
		 
		while (win == 0) {
			int[] playerLocation = playerLocation(gameMap, mapElements);
			
			List<Character> possibleMoves = possibleMoves(gameMap, playerLocation);
			
			areaCheck(playerLocation, gameMap);
			
			System.out.print("Which way do you want to move? ");
			String str = input.nextLine().toUpperCase();
			Character playerMove = str.charAt(0);
			
			while (!possibleMoves.contains(playerMove)) {
				System.out.println("You can't go that way.");
				
				System.out.print("Which way do you want to move? ");
				str = input.nextLine().toUpperCase();
				playerMove = str.charAt(0);
			}
			
			if (playerMove == 'N') {
				if (gameMap[playerLocation[0]-1][playerLocation[1]] == empty) {
					gameMap[playerLocation[0]-1][playerLocation[1]] = player;
					gameMap[playerLocation[0]][playerLocation[1]] = empty;
				}
				else if (gameMap[playerLocation[0]-1][playerLocation[1]] == beast) {
					System.out.println("Oh no! You have run into a ravenous Bugblatter Beast!");
					win = -1;
				}
				else if (gameMap[playerLocation[0]-1][playerLocation[1]] == pit) {
					System.out.println("AAAARGH! You have fallen into a pit!");
					win = -1;
				}
				else {
					System.out.println("You have found the gold!");
					win = 1;
				}
			}
			else if (playerMove == 'E') {
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
			else if (playerMove == 'S') {
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
			else {
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
		
		if (win == 1) {
			System.out.println("You have won!  Congratulations!");
		}
		else {
			System.out.println("You have died!  Game over!");
		}
		
		input.close();
	}
}
