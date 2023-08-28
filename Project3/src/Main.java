import EnumPackage.MainMenu;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      MainMenu menu = MainMenu.readCommand(scanner);

      switch (menu) {
        case FIGHTING:
          break;
        case SHOP:
          break;
        case EXIT:

          return;
        default:
          System.out.println("Некорректная команда");
      }
    }
  }


}
