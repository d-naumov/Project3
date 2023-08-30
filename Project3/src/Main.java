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
  public static void runNewGameMessage() {
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Hi Buratino! We've been waiting for you for so long, we need your help.");
    System.out.println("    We want to start a game where you will be the main character.");
    System.out.println("                You will need to fight the enemy.");
    System.out.println("                 Win and get the \"Golden Key\".");
    System.out.println("---------------------------------------------------------------------");
  }
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
        case EXIT -> {
          return;
        }
        default -> System.out.println("Incorrect command");
      }
    }
  }
  public static void printMenu() {
    System.out.println("1. Start a new game");
    System.out.println("2. Download game");
    System.out.println("3. Exit");
  }
}