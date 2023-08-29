import EnumPackage.EnumMainMenu;
import EnumPackage.EnumShop;

import EnumPackage.EnumStartMenu;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Привет Буратино! Мы тебя так долго ждали, нам нужна твоя помощь. ");
    System.out.println("  Хотим начать игру, где ты будешь главным героем.");
    System.out.println("     Тебе нужно будет сразиться с неприятелем.");
    System.out.println("     Победи и получишь \"Золотой ключик\".");
    System.out.println("---------------------------------------------------------------------");
    firstMenu();

  }
  public static void firstMenu(){
    Scanner scanner = new Scanner(System.in);
    boolean flag = true;
    while (flag) {
      EnumStartMenu menu = EnumStartMenu.readCommand(scanner);
      switch (menu) {
        case NEW_GAME:
          System.out.println("Начинаем новую игру. Выберите ваши действия!");
         startMenu();
          break;
        case LOAD_GAME:
          // Загрузить игру
          break;
        case EXIT:
          return;
        default:
          System.out.println("Некорректная команда");
      }
    }
  }

  public static void startMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean flag = true;
    while (flag) {
      EnumMainMenu menu = EnumMainMenu.readCommand(scanner);

      switch (menu) {
        case FIGHTING:
          MainMenu.enemiesInfo(scanner);

          break;
        case SHOP:
          EnumShop.readCommand(scanner);
          Shop.selection(MainMenu.buratino, scanner);
          break;
        case INFORMATION:
          //Надо добавить
          System.out.println(MainMenu.buratino.toString());
          break;
        case EXIT:
          return;
        default:
          System.out.println("Некорректная команда");
      }
    }
  }
}