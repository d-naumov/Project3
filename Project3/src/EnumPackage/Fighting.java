package EnumPackage;

import java.util.Scanner;

public enum Fighting {
  UNEXPECTED(""),
  SELECTION("Выбор соперника"),
  EXIT("Выход");
  private final String message;

  Fighting(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static Fighting readCommand(Scanner scanner) {
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
    for (Fighting fighting : values()) {
      if (!fighting.message.isEmpty()) {
        System.out.println(fighting.ordinal() + "." + fighting.message);
      }
    }
  }
}
