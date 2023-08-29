
import java.util.Scanner;

public class Shop {

  public static void selection(Buratino buratino, Scanner scanner) {
    System.out.println("Добро пожаловать в магазин!");
    System.out.println(
        "Вы имеете " + buratino.getMoney() + " монет"); // добавил getMoney в классе Buratino
    System.out.println("Что вы желаете?");
    System.out.println("1. Купить предмет");
    System.out.println("2. Продать предмет");
    System.out.println("3. Выйти из магазина");
    int choice = scanner.nextInt();
    switch (choice) {
      case 1:
        System.out.println("regbkb");
        System.out.println(MainMenu.buratino.getMoney());
        MainMenu.buratino.setMoney(1);
        System.out.println(MainMenu.buratino.getMoney());
        // Список имеющихся предметов в магазине
        break;
      case 2:
        // Предмет уже имеющийся у персонажа
        break;
      case 3:
        break;
      default:
        System.out.println("Некорректный выбор");
    }


  }
}
