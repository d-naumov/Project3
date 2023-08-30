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
          System.out.println("Начинаем новую игру. Выберите ваши действия!");
          runNewGameMessage();
          startMenu();
        }
        case LOAD_GAME -> {
          // Load game logic
        }
        case EXIT -> {
          return;
        }
        default -> System.out.println("Некорректная команда");
      }
    }
  }

  public static int readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Вы хотите начать игру сначала или загрузить ");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Ожидается ввод команды");
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
    System.out.println("Привет Буратино! Мы тебя так долго ждали, нам нужна твоя помощь. ");
    System.out.println("  Хотим начать игру, где ты будешь главным героем.");
    System.out.println("     Тебе нужно будет сразиться с неприятелем.");
    System.out.println("     Победи и получишь \"Золотой ключик\".");
    System.out.println("---------------------------------------------------------------------");
  }

  public static void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean flag = true;
    while (flag) {
      int menu = readCommand(scanner); // Assuming readCommand method works for startMenu too
      switch (menu) {
        case 1 -> MainMenu.enemiesInfo(scanner); // Assuming 1 corresponds to FIGHTING
        case 2 -> {
          // Shop logic
          // Shop.selection(MainMenu.buratino, scanner);
        }
        case 3 -> {
          return;
        }
        default -> System.out.println("Некорректная команда");
      }
    }
  }
  public static void printMenu() {
    System.out.println("1. Начать новую игру");
    System.out.println("2. Загрузить игру");
    System.out.println("3. Выход");
  }
}