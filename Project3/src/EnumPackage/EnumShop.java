package EnumPackage;

import java.util.Scanner;

public enum EnumShop {
  UNEXPECTED(""),
  SELECTION("Выбор товара"),
  EXIT("Выход");
  private final String message;

  EnumShop(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static EnumShop readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Введите команду");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Ожидается ввод команды");
    }
    String input = scanner.next();
    scanner.nextLine();
    switch (input.toLowerCase()) {
      case "1":
        return SELECTION;
      case "2":
        return EXIT;
      default:
        return UNEXPECTED;
    }
  }

  /**
   * Метод выводит меню в консоль
   */
  public static void printMenu() {
    for (EnumShop shop : values()) {
      if (!shop.message.isEmpty()) {
        System.out.println(shop.ordinal() + "." + shop.message);
      }
    }
  }

  public static void buyItem() {

  }
}
