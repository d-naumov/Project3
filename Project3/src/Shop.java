
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Shop {

  private static final String HEALTH = "Health";
  private static final String STRENGTH = "Strength";

  public static void selection(Buratino buratino, Scanner scanner) {
    List<Item> items = createItems();

    displayWelcomeMessage(buratino);
    displayItems(items);

    System.out.println("Press any other key to exit");
    int choice = scanner.nextInt();

    if (choice >= 1 && choice <= items.size()) {
      buyItem(buratino, items.get(choice - 1));
    } else {
      System.out.println("Exiting the shop.");
    }
  }

  private static List<Item> createItems() {
    List<Item> items = new ArrayList<>();
    items.add(new Item("Onion", 10, 20, 0));
    items.add(new Item("ABC Book", 40, 0, 3));
    items.add(new Item("Hand-Organ", 50, 50, 0));
    items.add(new Item("Log", 100, 0, 10));
    items.add(new Item("Golden Key", 10000, 100, 0));
    return items;
  }

  private static void displayWelcomeMessage(Buratino buratino) {
    System.out.println("Welcome to our store!");
    System.out.println("You have " + buratino.getMoney() + " coins");
    System.out.println("What do you want?");
  }

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

/*public class Shop {

  public static void selection(Buratino buratino, Scanner scanner) {
    // Create a list of items
    Item onion = new Item("Onion", 10, 20, 0);
    Item abc = new Item("ABC Book", 40, 0, 3);
    Item handOrgan = new Item("Hand-Organ", 50, 50, 0);
    Item log = new Item("Log", 100, 0, 10);
    List<Item> items = new ArrayList<>();
    items.add(onion);
    items.add(abc);
    items.add(handOrgan);
    items.add(log);

    System.out.println("Welcome to our store!");
    System.out.println("You have " + buratino.getMoney() + " coins");
    System.out.println("What do you want?");
    // Print existing products
    for (int i = 0; i < items.size(); i++) {
      System.out.print(
          i + 1 + ". " + items.get(i).getName() + ". Price - " + items.get(i).getPrice());
      if (items.get(i).getIncreaseHealth() > 0) {
        System.out.println(". Increases health by " + items.get(i).getIncreaseHealth());
      } else {
        System.out.println(". Increases Strength by " + items.get(i).getIncreaseStrength());
      }
    }
    System.out.println("Press any other key to exit");
    int choice = scanner.nextInt();
    // Making a purchase
    if (MainMenu.buratino.getMoney() < items.get(choice - 1).getPrice()) {
      System.out.println("You don't have enough coins");
    } else {
      System.out.println("You bought a " + items.get(choice - 1).getName());
      MainMenu.buratino.setMoney(MainMenu.buratino.getMoney() - items.get(choice - 1).getPrice());
      if (onion.getIncreaseHealth() > 0) {
        MainMenu.buratino.setHealth(
            MainMenu.buratino.getHealth() + items.get(choice - 1).getIncreaseHealth());
        System.out.println("You health is now " + MainMenu.buratino.getHealth());
      } else {
        MainMenu.buratino.setStrength(
            MainMenu.buratino.getStrength() + items.get(choice - 1).getIncreaseStrength());
        System.out.println("You strength is now " + MainMenu.buratino.getStrength());
      }
    }
  }
}

 */
