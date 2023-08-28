package EnumPackage;

import java.util.Scanner;

public enum Shop {
  UNEXPECTED(""),
  SELECTION("Выбор товара"),
  EXIT("Выход");
  private final String message;

  Shop(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static Shop readCommand(Scanner scanner) {
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
    for (Shop shop : values()) {
      if (!shop.message.isEmpty()) {
        System.out.println(shop.ordinal() + "." + shop.message);
      }
    }
  }
}