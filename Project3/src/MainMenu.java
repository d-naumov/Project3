import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

  public static Boolean newGame;

  public static Buratino buratino;

  /**
   * Checks the game's condition and create's the object of the main character.
   *
   * @param gameNew Flag of the new game
   */
  public static void checkMainHero(Boolean gameNew) {
    newGame = gameNew;
    buratino = newGame ? startNew() : loadGame();
  }

  /**
   * Creates a new main character with initial settings
   *
   * @return the object of the new character
   */
  public static Buratino startNew() {
    Buratino buratino = new Buratino("Buratino", 180, 22, 10);
    return buratino;
  }

  /**
   * Загружает данные главного героя из файла сохранения. Loads the main heroes data from the saving
   * file
   *
   * @return The object of the main character with the loaded data
   */
  public static Buratino loadGame() {
    String LoadChose;
    List<Buratino> allLoads = getSaveToFile(true);
    Scanner scanner1 = new Scanner(System.in);
    System.out.println("Name your saving");
    LoadChose = scanner1.next();
    for (int i = 0; i < allLoads.size(); i++) {
      if (allLoads.get(i).getName().equalsIgnoreCase(LoadChose)) {
        return allLoads.get(i);
      }
    }
    return null;
  }

  /**
   * returns the list of loaded savings from the file.
   *
   * @param save Flag indicating the necessity of saving.
   * @return List of the loaded savings.
   */
  public static Scanner scanner = new Scanner(System.in);

  public static List<Buratino> getSaveToFile(boolean save) {
    boolean saveNew = save;
    List<Buratino> allLoads = new ArrayList<>();
    String loadName;
    String LoadChose;
    File inputFile = new File("res/Save.csv");
    try {
      scanner = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] data = line.split(",");
      loadName = data[0];
      allLoads.add(new Buratino(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]),
          Integer.parseInt(data[3])));
      if (saveNew) {
        System.out.println(data[0]);
      }
    }
    return allLoads;
  }

  /**
   * Reads the information about the enemies from the file "Enemies.csv" and returns the list of the enemies.
   *
   * @return List of the enemies, read from the file
   * @throws RuntimeException  in case the error occures while reading from the file
   */
  public static List<Enemies> getEnemiesFromFile() {
    File enemiesFile = new File("res/Enemies.csv");
    List<Enemies> enemies = new ArrayList<>();
    try {
      scanner = new Scanner(enemiesFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        if (data.length > 1) {
          Enemies enemy = new Enemies(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]),
              Integer.parseInt(data[3]));
          enemies.add(enemy);
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return enemies;
  }

  /**
   * Saves the game indicating the name of the game.
   *
   * @param "saveName" the name of the game's saving.
   */
  public static void saveGame() {
    Scanner saveGameScaner = new Scanner(System.in);
    String saveName;
    System.out.println("Save game as...");
    System.out.println("Indicate the name");
    saveName = saveGameScaner.next();
    addSaveToFile(saveName);
  }

  /**
   * Adds the saving in the file
   *
   * @param "res/Save.csv" Name of the saving.
   */
  public static void addSaveToFile(String name) {
    List<Buratino> allSave = getSaveToFile(false);
    Buratino buratinoSave;
    buratinoSave = buratino;
    buratinoSave.setName(name);
    allSave.add(buratinoSave);
    try {
      PrintWriter writer;
      writer = new PrintWriter("res/Save.csv");
      for (Buratino save : allSave) {
        writer.println(save.toStringFromFile());
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("The file not found: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Prints the information of the enemies available for a battle, and provides a choice.
   *
   * @param choice scanner for selecting an enemy.
   */
  public static void enemiesInfo(Scanner choice) {
    List<Enemies> enemies = getEnemiesFromFile();
    System.out.println("Choose the enemy for a battle:");
    for (int i = 0; i < enemies.size(); i++) {
      System.out.println(
          (i + 1) + ". " + enemies.get(i).getName() + ": Health " + enemies.get(i).getHealth()
              + ", Strength " + enemies.get(i)
              .getStrength() + ", Money " + enemies.get(i).getMoney());
    }
    enemyToFight(choice);
  }

  /**
   * Reads out  the choice of a user about the battle with an enemy.
   *
   * @param scanner is data source
   */
  public static void enemyToFight(Scanner scanner) {
    if (scanner.hasNextInt()) {
      int index = scanner.nextInt();
      fight(index);
    } else {
      System.out.println("Incorrect input. Enter the number.");
      scanner.nextLine(); // clear input buffer
    }
  }

  /**
   * Method realises the battle between the main character and the enemy.
   *
   * @param choice chousen enemy for a battle
   */
  private static void fight(int choice) {
    List<Enemies> enemies = getEnemiesFromFile();

    if (choice < 1 || choice > enemies.size()) {
      System.out.println("Incorrect choice of the enemy.");
      return;
    }

    Enemies selectedEnemy = enemies.get(choice - 1);
    System.out.println("You chose a battle with " + selectedEnemy.getName() + "!");
    System.out.println("The battle is starting...");

    Random random = new Random();

    while (buratino.getHealth() > 0 && selectedEnemy.getHealth() > 0) {
      int buratinoAttack = calculateBuratinoAttack(buratino.getStrength(), random);
      int enemyAttack = calculateEnemyAttack(selectedEnemy.getStrength(), random);

      int buratinoDefense = random.nextInt(3);
      int enemyDefense = random.nextInt(3);

      buratinoAttack -= enemyDefense;
      enemyAttack -= buratinoDefense;

      if (buratinoAttack < 0) {
        buratinoAttack = 0;
      }
      if (enemyAttack < 0) {
        enemyAttack = 0;
      }
      performAttacks(selectedEnemy, buratinoAttack, enemyAttack);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (buratino.getHealth() <= 0) {
      System.out.println("You lost the battle");
      System.exit(0);
    } else {
      int reward = generateReward(random);
      displayVictoryMessage(reward, selectedEnemy);
    }
  }

  /**
   * Calculates the main characters attack.
   *
   * @param buratinoStrength  the main character strength
   * @param random object  for generation random numbers
   * @return main character's attack value
   */
  private static int calculateBuratinoAttack(int buratinoStrength, Random random) {
    return buratinoStrength + random.nextInt(5);
  }

  /**
   * Calculates the enemies attack.
   *
   * @param enemyStrength strength of the enemy
   * @param random        object  for generation random numbers
   * @return enemy's attack value
   */
  private static int calculateEnemyAttack(int enemyStrength, Random random) {
    return enemyStrength + random.nextInt(5);
  }

  /**
   * Performs attacks between the main character and the enemy..
   *
   * @param selectedEnemy  chosen enemy
   * @param buratinoAttack main character attack
   * @param enemyAttack    enemy attack
   */
  private static void performAttacks(Enemies selectedEnemy, int buratinoAttack, int enemyAttack) {
    selectedEnemy.decreaseHealth(buratinoAttack);
    buratino.decreaseHealth(enemyAttack);

    System.out.println("You dealt enemy " + buratinoAttack + " damage.");
    System.out.println("Enemy dealt you " + enemyAttack + " damage.");
  }

  /**
   * Generates a random reward for a character upon victory.
   *
   * @param random object for generating random numbers
   * @return random reward value
   */
  private static int generateReward(Random random) {
    return random.nextInt(10, 20);
  }

  /**
   * Displays a victory message and reward processing.
   *
   * @param reward amount of reward
   * @param enemy  enemy defeated by the hero
   */
  private static void displayVictoryMessage(int reward, Enemies enemy) {
    if (enemy.getName().equals("Karabas-Barabas")) {
      displayKarabasVictoryMessage();
    } else {
      System.out.println("You defeated the enemy!");
      buratino.increaseMoney(reward);
      System.out.println("You received " + reward + " coins.");
    }
  }

  private static void displayKarabasVictoryMessage() {
    String text = "You have defeated the fearsome Karabas-Barabas.\n"
        + "You received the Golden Key and opened the secret room\n" +
        "in which you found the puppet theater\n" +
        "Buratino, Papa Carlo and friends successfully put on a play\n";
    int delayMillis = 40;
    for (char c : text.toCharArray()) {
      System.out.print(c);
      try {
        Thread.sleep(delayMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("   Credits");
    displayCredits();
    System.out.println("  Producers");
    displayCredits();
    System.out.println("  Designers");
    displayCredits();
    System.out.println(" Programmers");
    displayCredits();
    System.out.println("   Artists");
    displayCredits();
    System.exit(0);
  }

  private static void displayCredits() {
    String[] credits = {
        "Denis Kovtun",
        "Dimitri Naumov",
        "Natalja Röhm",
        "Egor Danilov"
    };
    int delayMillis = 800;
    for (String message : credits) {
      System.out.println(message);
      try {
        Thread.sleep(delayMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
