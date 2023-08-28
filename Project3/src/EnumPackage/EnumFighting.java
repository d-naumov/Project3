package EnumPackage;

import java.util.Scanner;

public enum EnumFighting {
  UNEXPECTED(""),
  SELECTION("Выбор соперника"),
  EXIT("Выход");
  private final String message;

  EnumFighting(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static EnumFighting readCommand(Scanner scanner) {
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
    for (EnumFighting fighting : values()) {
      if (!fighting.message.isEmpty()) {
        System.out.println(fighting.ordinal() + "." + fighting.message);
      }
    }
  }
}
