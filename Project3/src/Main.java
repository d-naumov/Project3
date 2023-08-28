import EnumPackage.EnumMainMenu;
import EnumPackage.Fighting;
import EnumPackage.Shop;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      EnumMainMenu menu = EnumMainMenu.readCommand(scanner);

      switch (menu) {
        case FIGHTING:
          Fighting.readCommand(scanner);
          break;
        case SHOP:
          Shop.readCommand(scanner);
          break;
        case INFORMATION:
          //Надо добавить
          break;
        case EXIT:

          return;
        default:
          System.out.println("Некорректная команда");
      }
    }
  }
}
