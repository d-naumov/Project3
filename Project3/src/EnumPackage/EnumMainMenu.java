package EnumPackage;

import java.util.Scanner;

public enum EnumMainMenu {
  UNEXPECTED(""),
  FIGHTING("Battle"),
  SHOP("Store"),
  INFORMATION("Character Info"),
  SAVE("Save the game"),
  EXIT("Exit");
  private final String message;

  /**
   * An enumeration for the main menu with available options.
   */
  EnumMainMenu(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  /**
   * Read command from scanner and convert to appropriate EnumMainMenu.
   *
   * @param scanner data source
   * @return selected main menu command
   */
  public static EnumMainMenu readCommand(Scanner scanner) {
    printMenu();
    System.out.println("Enter command");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Waiting for a command");
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
        return SAVE;
      case "5":
        return EXIT;
      default:
        return UNEXPECTED;
    }
  }

  /**
   * Displaying the main menu menu in the console.
   */
  public static void printMenu() {
    for (EnumMainMenu menu : values()) {
      if (!menu.message.isEmpty()) {
        System.out.println(menu.ordinal() + "." + menu.message);
      }
    }
  }
}