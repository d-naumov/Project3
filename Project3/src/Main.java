import EnumPackage.EnumMainMenu;
import EnumPackage.EnumShop;
import java.util.Scanner;

public class Main {
  public static final int NEW_GAME = 1;
  public static final int LOAD_GAME = 2;
  public static final int EXIT = 3;

  public static void main(String[] args) {
    StartNewGameMenu();
  }

  /**
   * Method representing the main menu of the game
   */
  public static void StartNewGameMenu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int menu = readCommand(scanner);
      switch (menu) {
        case NEW_GAME -> {
          runNewGameMessage();
          System.out.println("Starting a new game. Choose Your Actions!");
          MainMenu.checkMainHero(true);
          startMenu();
        }
        case LOAD_GAME -> {
          loadGameMessage();
          MainMenu.checkMainHero(false);
          startMenu();
          // Load game logic
        }
        case EXIT -> {
          return;
        }
        default -> System.out.println("Incorrect command");
      }
    }
  }

  /**
   * Reads a command from the scanner and returns the corresponding command code.
   *
   * @param scanner data source for command input
   * @return command code (NEW_GAME, LOAD_GAME, EXIT) or -1 in case of incorrect command
   * (UNEXPECTED)
   */
  public static int readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Do you want to start a new game over or load saved game?");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Waiting for a command");
    }
    String input = scanner.next();
    scanner.nextLine();
    switch (input.toLowerCase()) {
      case "1":
        return NEW_GAME;
      case "2":
        return LOAD_GAME;
      case "3":
        return EXIT;
      default:
        return -1; // Represents UNEXPECTED
    }
  }

  /**
   * Method for displaying a message when starting a new game.
   */
  public static void runNewGameMessage() {
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Hi Buratino! We've been waiting for you for so long, we need your help.");
    System.out.println("    We want to start a game where you will be the main character.");
    System.out.println("                You will need to fight the enemy.");
    System.out.println("                 Win and get the \"Golden Key\".");
    System.out.println("---------------------------------------------------------------------");
  }

  /**
   * Method for displaying a message when loading a game.
   */
  public static void loadGameMessage() {
    System.out.println("----------------------------------------------------------");
    System.out.println("     Welcome back to the Buratino game! ");
    System.out.println("  We believe in you, try to defeat Karabas-Barabas.");
    System.out.println("----------------------------------------------------------");
  }

  /**
   * Method for displaying the action menu during the game. Depending on the user's choice, calls
   * methods for fighting enemies. Visiting the store, viewing character information, or exiting the
   * game.
   */
  public static void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean flag = true;
    while (flag) {
      EnumMainMenu menu = EnumMainMenu.readCommand(scanner);
      switch (menu) {
        case FIGHTING -> MainMenu.enemiesInfo(scanner);
        case SHOP -> {
          EnumShop.readCommand(scanner);
          Shop.selection(MainMenu.buratino, scanner);
        }
        case INFORMATION ->
          //Надо добавить
                System.out.println(MainMenu.buratino.toString());
        case SAVE -> {
          MainMenu.saveGame();
        }
        case EXIT -> {
          return;
        }
        default -> System.out.println("Incorrect command");
      }
    }
  }

  /**
   * Method for printing the main menu of the game
   */
  public static void printMenu() {
    System.out.println("1. Start a new game");
    System.out.println("2. Download game");
    System.out.println("3. Exit");
  }
}