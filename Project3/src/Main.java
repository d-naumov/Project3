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
          System.out.println("Начинаем новую игру. Выберите ваши действия!");
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