import java.util.Scanner;

public class Shop {

  private Buratino buratino;

  public Shop(Buratino buratino) {
    this.buratino = buratino;
  }

  public void selection() {
    System.out.println("Добро пожаловать в магазин!");
    System.out.println(
        "Вы имеете " + buratino.getMoney() + " монет"); // добавил getMoney в классе Buratino
    System.out.println("Что вы желаете?");
    System.out.println("1. Купить предмет");
    System.out.println("2. Продать предмет");
    System.out.println("3. Выйти из магазина");
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();
    switch (choice) {
      case 1:
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
