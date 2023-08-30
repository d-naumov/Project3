import EnumPackage.EnumMainMenu;
import EnumPackage.EnumShop;
import EnumPackage.EnumStartMenu;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    StartNewGameMenu();
  }

  public static void StartNewGameMenu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      EnumStartMenu menu = EnumStartMenu.readCommand(scanner);
      switch (menu) {
        case NEW_GAME -> {
          System.out.println("Начинаем новую игру. Выберите ваши действия!");
          runNewGameMessage();
          startMenu();
        }
        case LOAD_GAME -> {
        }
        // Загрузить игру
        case EXIT -> {
          return;
        }
        default -> System.out.println("Некорректная команда");
      }
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
}