package EnumPackage;

import java.util.Scanner;

public enum EnumFighting {
  UNEXPECTED(""),
  SELECTION("Opponent's choice"),
  EXIT("Exit");
  private final String message;

  EnumFighting(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public static EnumFighting readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Input the command");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Awaiting for an input of the command");
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
   * Method prints a menu to the console
   */
  public static void printMenu() {
    for (EnumFighting fighting : values()) {
      if (!fighting.message.isEmpty()) {
        System.out.println(fighting.ordinal() + "." + fighting.message);
      }
    }
  }
}
