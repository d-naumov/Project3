package EnumPackage;

import java.util.Scanner;

public enum EnumStartMenu {
  UNEXPECTED(""),
  NEW_GAME("Начать новую игру"),
  LOAD_GAME("Загрузить игру"),
  EXIT("Выход");
  private final String message;

  EnumStartMenu(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static EnumStartMenu readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Вы хотите начать игру сначала или загрузить ");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Ожидается ввод команды");
    }
    String input = scanner.next();
    scanner.nextLine();
    switch (input.toLowerCase()) {
      case "1":
        return NEW_GAME;
      case "2":
        return LOAD_GAME;
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
    for (EnumStartMenu menu : values()) {
      if (!menu.message.isEmpty()) {
        System.out.println(menu.ordinal() + "." + menu.message);
      }
    }
  }
}