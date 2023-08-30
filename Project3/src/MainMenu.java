import EnumPackage.EnumFighting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {

  public static Boolean newGame;

  public static Buratino buratino;

  public static void checkMainHero(Boolean gameNew) {
    newGame = gameNew;
    buratino = newGame ? startNew() : loadGame();
  }

  public static Buratino startNew() {
    Buratino buratino = new Buratino("Buratino", 180, 22, 10);
    return buratino;
  }

  public static Buratino loadGame() {
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
      allLoads.add(new Buratino(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
      System.out.println(data[0]);
    }
    Scanner scanner1 = new Scanner(System.in);
    System.out.println("Введите имя вашего сохранения");
    LoadChose = scanner1.next();
    for (int i = 0; i < allLoads.size(); i++) {
      if (allLoads.get(i).getName().equalsIgnoreCase(LoadChose)) {
        return allLoads.get(i);
      }
    }
    return null;
  }



  public static Scanner scanner = new Scanner(System.in);
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


  public static void enemiesInfo(Scanner chouse) {
    List<Enemies> enemies = getEnemiesFromFile();
    EnumFighting.readCommand(chouse);
    System.out.println("Выберите врага для сражения:");
    for (int i = 0; i < enemies.size(); i++) {
      System.out.println((i + 1) + ". " + enemies.get(i).getName());
    }
    enemyToFight(chouse);
  }

  public static void enemyToFight(Scanner scanner) {
    int index = scanner.nextInt();
    System.out.println(index);
    fight(index);
  }

  private static void fight(int choice) {
    List<Enemies> enemies = getEnemiesFromFile();

    if (choice < 1 || choice > enemies.size()) {
      System.out.println("Некорректный выбор врага.");
      return;
    }

    Enemies selectedEnemy = enemies.get(choice - 1);
    System.out.println("Вы выбрали сражаться с " + selectedEnemy.getName() + "!");
    System.out.println("Начинается битва...");

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
      System.out.println("Вы проиграли битву.");
      System.exit(0);
    } else {
      int reward = generateReward(random);
      displayVictoryMessage(reward, selectedEnemy);
    }
  }

  private static int calculateBuratinoAttack(int buratinoStrength, Random random) {
    return buratinoStrength + random.nextInt(5);
  }

  private static int calculateEnemyAttack(int enemyStrength, Random random) {
    return enemyStrength + random.nextInt(5);
  }

  private static void performAttacks(Enemies selectedEnemy, int buratinoAttack, int enemyAttack) {
    selectedEnemy.decreaseHealth(buratinoAttack);
    buratino.decreaseHealth(enemyAttack);

    System.out.println("Вы нанесли врагу " + buratinoAttack + " урона.");
    System.out.println("Враг нанес вам " + enemyAttack + " урона.");
  }

  private static int generateReward(Random random) {
    return random.nextInt(10, 20);
  }

  private static void displayVictoryMessage(int reward, Enemies enemy) {
    if (enemy.getName().equals("Karabas-Barabas")) {
      System.out.println("тут будет новый метод про з");
    }
    System.out.println("Вы победили врага!");
    buratino.increaseMoney(reward);
    System.out.println("Вы получили " + reward + " монет.");
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
    System.out.println("  Credits");
    displayCredits();
    System.out.println(" Producers");
    displayCredits();
    System.out.println(" Desiners");
    displayCredits();
    System.out.println(" Programmers");
    displayCredits();
    System.out.println("  Artists");
    displayCredits();
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
