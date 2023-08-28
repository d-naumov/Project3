package EnumPackage;

import java.util.Scanner;

public enum EnumMainMenu {
  UNEXPECTED(""),
  FIGHTING("Сражение"),
  SHOP("Магазин"),
  INFORMATION("Информация о персонаже"),
  EXIT("Выход");
  private final String message;

  EnumMainMenu(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }


  public static EnumMainMenu readCommand(Scanner scanner) {
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
        return INFORMATION;
      case "4":
        return EXIT;
      default:
        return UNEXPECTED;
    }
  }

  /**
   * Метод выводит меню в консоль
   */
  public static void printMenu() {
    for (EnumMainMenu menu : values()) {
      if (!menu.message.isEmpty()) {
        System.out.println(menu.ordinal() + "." + menu.message);
      }
    }
  }
}
