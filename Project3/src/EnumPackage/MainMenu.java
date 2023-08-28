package EnumPackage;

import java.util.Scanner;

public enum MainMenu {
  UNEXPECTED(""),
  FIGHTING("Сражение"),
  SHOP("Магазин"),
  EXIT("Выход");
  private final String message;

  MainMenu(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }


  public static MainMenu readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Введите команду");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Ожидается ввод команды");
    }
    String input = scanner.next();
    scanner.nextLine();
    switch (input.toLowerCase()) {
      case "1":
        return FIGHTING;
      case "2":
        return SHOP;
      case "3":
        return EXIT;
      default:
        return UNEXPECTED;
    }
  }

  /**
   * Метод выводит меню в консоль
   */
  public static void printMenu() {
    for (MainMenu menu : values()) {
      if (!menu.message.isEmpty()) {
        System.out.println(menu.ordinal() + "." + menu.message);
      }
    }
  }
}
