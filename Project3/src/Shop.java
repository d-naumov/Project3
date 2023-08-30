
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

  private static final String HEALTH = "Health";
  private static final String STRENGTH = "Strength";

  /**
   * Выбор товара и процесс покупки в магазине.
   *
   * @param buratino главный герой
   * @param scanner источник данных
   */
  public static void selection(Buratino buratino, Scanner scanner) {
    List<Item> items = createItems();

    displayWelcomeMessage(buratino);
    displayItems(items);

    System.out.println("Press any other key to exit");

    if (scanner.hasNextInt()) {
      int choice = scanner.nextInt();

      if (choice >= 1 && choice <= items.size()) {
        buyItem(buratino, items.get(choice - 1));
      } else {
        System.out.println("Invalid choice. Exiting the shop.");
      }
    } else {
      System.out.println("Invalid input. Exiting the shop.");
      scanner.nextLine(); // Clear input buffer
    }
  }

  /**
   * Создание списка товаров.
   *
   * @return список товаров
   */
  private static List<Item> createItems() {
    List<Item> items = new ArrayList<>();
    items.add(new Item("Onion", 10, 20, 0));
    items.add(new Item("ABC Book", 40, 0, 3));
    items.add(new Item("Hand-Organ", 50, 50, 0));
    items.add(new Item("Log", 100, 0, 10));
    items.add(new Item("Golden Key", 10000, 100, 0));
    return items;
  }

  /**
   * Вывод приветственного сообщения магазина.
   *
   * @param buratino главный герой
   */
  private static void displayWelcomeMessage(Buratino buratino) {
    System.out.println("Welcome to our store!");
    System.out.println("You have " + buratino.getMoney() + " coins");
    System.out.println("What do you want?");
  }

  /**
   * Вывод списка товаров в магазине.
   *
   * @param items список товаров
   */
  private static void displayItems(List<Item> items) {
    for (int i = 0; i < items.size(); i++) {
      Item item = items.get(i);
      System.out.print(i + 1 + ". Buy " + item.getName() + ". Price - " + item.getPrice());
      if (item.getIncreaseHealth() > 0) {
        System.out.println(". Increases " + HEALTH + " by " + item.getIncreaseHealth());
      } else {
        System.out.println(". Increases " + STRENGTH + " by " + item.getIncreaseStrength());
      }
    }
  }

  /**
   * Покупка выбранного товара.
   *
   * @param buratino главный герой
   * @param item выбранный товар
   */
  private static void buyItem(Buratino buratino, Item item) {
    if (buratino.getMoney() < item.getPrice()) {
      System.out.println("You don't have enough coins");
    } else {
      System.out.println("You bought a " + item.getName());
      buratino.decreaseMoney(item.getPrice());

      if (item.getIncreaseHealth() > 0) {
        buratino.increaseHealth(item.getIncreaseHealth());
      } else {
        buratino.increaseStrength(item.getIncreaseStrength());
      }
    }
  }
}