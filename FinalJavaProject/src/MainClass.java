import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class MainClass {

	private static ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Player> sortedPlayers = new ArrayList<Player>();
	private static Scanner sc = new Scanner(System.in);
	private static Random randomGenerator = new Random();

	public static void main(String[] args) {
		
		run();
	}
	
	public static void run() {

		
		
		
		System.out.println("What would you like to do?");
		System.out.println("1 - Enter player data");
		System.out.println("2 - Generate a custom team composition of 10 players with the highest SET scores");
		System.out.println("3 - Randomly select a custom number of players");
		System.out.println("4 - Count the number of players in each position");
		System.out.println("5 - Sort and display players by their APT from highest to lowest");
		System.out.println("6 - Show the player with the highest APT score");
		System.out.println("7 - Show the player with the lowest AVG score");
		System.out.println("8 - Exit application");
		System.out.println("");

		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			addPlayers();
			System.out.println("");
			break;
		case 2:
			
			insertionSort(0);
			
			System.out.println("How many defenders do you want on your team?");
			int requestedD = sc.nextInt();
			System.out.println("How many midfielders do you want on your team?");
			int requestedM = sc.nextInt();
			System.out.println("How many attackers do you want on your team?");
			int requestedA = sc.nextInt();
	
			int g = 0;
			int h = 0;
			
			if ((sortedPlayers.size() >= 10)&&(requestedD + requestedM + requestedA == 10)) {
				while(h < 10) {
					String currentPosition = sortedPlayers.get(g).getPosition();
					switch (currentPosition) {
					case "defender":
						if (requestedD > 0) {
							printPlayer(sortedPlayers.get(g));
							requestedD--;
							h++;
						}
						break;
					case "midfielder":
						if (requestedM > 0) {
							printPlayer(sortedPlayers.get(g));
							requestedM--;
							h++;
						}
						break;
					case "attacker":
						if (requestedA > 0) {
							printPlayer(sortedPlayers.get(g));
							requestedA--;
							h++;
						}
						break;
					}
					g++;
				}
			}
			
			System.out.println("");
			break;
		case 3:
			System.out.println("How many players would you like to select?");
			int z = sc.nextInt();

			if (z <= players.size()) {
				selectRandomPlayers(z);
			}
			else {
				System.out.println("Invalid input, returning to main menu");
			}
			
			System.out.println("");
			break;
		case 4:
			int d = 0;
			int m = 0;
			int a = 0;

			for (int y = 0; y < players.size(); y++) {

				String position = players.get(y).getPosition();

				switch (position) {
				case "attacker":
					a++;
					break;
				case "defender":
					d++;
					break;
				case "midfielder":
					m++;
					break;
				}
			}
			System.out.println("There are " + a + " attackers");
			System.out.println("There are " + d + " defenders");
			System.out.println("There are " + m + " midfielders");
			
			System.out.println("");
			break;
		case 5:

			insertionSort(1);

			sortedPlayers.forEach((p) -> {
				printPlayer(p);
			});
			
			System.out.println("");
			break;
		case 6:
			
			insertionSort(1);
			printPlayer(sortedPlayers.get(0));
			
			System.out.println("");
			break;
		case 7:
			
			insertionSort(2);
			printPlayer(sortedPlayers.get((sortedPlayers.size())-1));
			
			System.out.println("");
			break;
		case 8:
			sc.close();
			System.exit(0);
		}

		run();

	}

	public static void addPlayers() {

		// create while loop, end loop asking to continue, yes or no response
		int i = 1 + players.size();
		while (true) {
			System.out.println("Enter first name:");
			String firstName = sc.next();
			System.out.println("Enter second name:");
			String secondName = sc.next();
			System.out.println("Enter APT Score:");
			int apt = sc.nextInt();
			System.out.println("Enter SET Score:");
			int set = sc.nextInt();
			System.out.println("Enter association:");
			String association = sc.next();
			
			System.out.println("Enter position:");
			String position = sc.next();
			boolean validPositionInput = generatePosition(position);
			
			if (!validPositionInput) {
				System.out.println("Invalid input, returning to main menu");
				System.out.println("");
				return;				
			}
			
			players.add(new Player(i, firstName, secondName, apt, set, association, position)

			);

			i++;

			System.out.println("Do you want to add another player?");
			String nextplayer = sc.next();
			nextplayer.toLowerCase();

			if (!nextplayer.equals("yes")) {
				break;
			}
		}

		players.forEach((p) -> {
			printPlayer(p);
		});

	}

	public static void printPlayer(Player p) {
		// log all information, create for loop printing all information in structured
		// order
		System.out.print("Player " + (p.getIndex()) + " ");
		System.out.print(p.getFirstName() + " ");
		System.out.print(p.getSecondName() + " ");
		System.out.print(p.getApt() + " ");
		System.out.print(p.getSet() + " ");
		System.out.print(p.getAvg() + " ");
		System.out.print(p.getAssociation() + " ");
		System.out.print(p.getPosition());
		System.out.println();
	}

	public static void selectRandomPlayers(int z) {

		ArrayList<Player> randomPlayers = new ArrayList<Player>();

		for (int t = z; t > 0; t--) {
			int randInt = randomGenerator.nextInt(randomPlayers.size());

			printPlayer(randomPlayers.get(randInt));
			randomPlayers.remove(randInt);
		}

		System.out.print("");

	}

	public static void insertionSort(int element) {

		sortedPlayers = players;

		int k, s;
		for (k = 1; k < sortedPlayers.size(); k++) {
			Player tmp = sortedPlayers.get(k);
			s = k;
			while ((s > 0)
					&& (sortedPlayers.get(s - 1).getValueForSorting(element) < tmp.getValueForSorting(element))) {
				sortedPlayers.set(s, sortedPlayers.get(s - 1));
				s--;
			}
			sortedPlayers.set(s, tmp);
		}

	}
			
	public static boolean generatePosition(String positionInput) {
		
		positionInput.toLowerCase();
		
		if ((positionInput.equals("attacker"))||(positionInput.equals("defender"))||(positionInput.equals("midfielder"))){
	
			return true;
		}
		return false;
	}
	
}
