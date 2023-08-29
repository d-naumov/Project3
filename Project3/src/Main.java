import EnumPackage.EnumMainMenu;
import EnumPackage.EnumShop;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
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
