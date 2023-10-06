import java.util.Scanner;

public class Game {
    private final Scanner input = new Scanner(System.in);

    public void start() {
        boolean isPlaying = true;

        while (isPlaying) {
            isPlaying = false;

            System.out.println("Welcome to the Adventure Game!");
            System.out.print("Please enter a name: ");
            String playerName = input.nextLine();
            Player player = new Player(playerName);
            System.out.println(player.getName() + " welcome to this dark and foggy island!");
            System.out.println("Eveything that happens here is real!");
            System.out.println("----------------------------");
            System.out.println("If you dare, select a character!");
            player.selectChar();

            boolean quit = false;

            while (true) {
                Location location = null;
                player.printInfo();
                System.out.println();
                System.out.println("### Locations ###");
                System.out.println();
                System.out.println("1- Safe House --- This is a safe house for you. There isn't any enemy here.");
                System.out.println("2- Tool Store --- You can buy guns or armors from here.");
                System.out.println("3- The Cave --- Reward: Food ### Be careful, zombies may appear!");
                System.out.println("4- The Forest --- Reward: Firewood ### Be careful, vampires are here!");
                System.out.println("5- The River --- Reward: Water ### Be careful, bears are here!");
                System.out.println("6- The Mine --- Reward: Money, Gun, Armor or NOTHING! ### Be careful, snakes may appear!");
                System.out.println("0 - Quit --- Quit the game.");
                if (player.hasWonGame()) {
                    System.out.println("*************************************");
                    System.out.println("Go to the Safe House to win the game!");
                    System.out.println("*************************************");
                }
                System.out.print("Select the location you want to go: ");

                int selectLoc = input.nextInt();

                switch (selectLoc) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        location = new SafeHouse(player);
                        break;
                    case 2:
                        location = new ToolStore(player);
                        break;
                    case 3:
                        if (!player.getInventory().getHasFood()) {
                            location = new Cave(player);
                        } else {
                            System.out.println("You have defeated whole obstacles here.");
                            System.out.println("--------------------------------------");
                            System.out.println("Select another location!");
                            System.out.println("^.^.^.^.^.^.^.^.^.^.^.^.^");
                            System.out.println();
                        }
                        break;
                    case 4:

                        if (!player.getInventory().getHasFireWood()) {
                            location = new Forest(player);
                        } else {
                            System.out.println("You have defeated whole obstacles here.");
                            System.out.println("--------------------------------------");
                            System.out.println("Select another location!");
                            System.out.println("^.^.^.^.^.^.^.^.^.^.^.^.^");
                            System.out.println();
                        }
                        break;
                    case 5:
                        if (!player.getInventory().getHasWater()) {
                            location = new River(player);
                        } else {
                            System.out.println("You have defeated whole obstacles here.");
                            System.out.println("--------------------------------------");
                            System.out.println("Select another location!");
                            System.out.println("^.^.^.^.^.^.^.^.^.^.^.^.^");
                            System.out.println();
                        }
                        break;
                    case 6:
                        location = new Mine(player);
                        break;
                    default:
                        System.out.println("Select a valid location!");
                }
                if (quit) {
                    System.out.println("You gave up too quickly! Bye bye!");
                    break;
                }
                if (location != null && !location.onLocation()) {
                    System.out.println("GAME OVER!");
                    System.out.print("Do you want to play again? <1>YES <2>NO: ");
                    int playAgain = input.nextInt();
                    input.nextLine();
                    if (playAgain == 1) {
                        isPlaying = true;
                    }
                    System.out.println();
                    break;
                }
                if (player.hasWonGame() && selectLoc == 1) {
                    System.out.println("----------------------------");
                    player.printInfo();
                    System.out.println();
                    System.out.println("You won the game!!!");
                    break;
                }
            }
        }
    }
}
