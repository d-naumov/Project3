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
   * Проверяет состояние игры и создает объект главного героя.
   *
   * @param gameNew флаг новой игры
   */
  public static void checkMainHero(Boolean gameNew) {
    newGame = gameNew;
    buratino = newGame ? startNew() : loadGame();
  }

  /**
   * Создает нового главного героя с начальными характеристиками.
   *
   * @return объект главного героя
   */
  public static Buratino startNew() {
    Buratino buratino = new Buratino("Buratino", 180, 22, 10);
    return buratino;
  }

  /**
   * Загружает данные главного героя из файла сохранения.
   *
   * @return объект главного героя с загруженными данными
   */
  public static Buratino loadGame() {
    String LoadChose;
    List<Buratino> allLoads = getSaveToFile(true);
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

  /**
   * Возвращает список загруженных сохранений из файла.
   *
   * @param save Флаг, указывающий на необходимость сохранения.
   * @return Список загруженных сохранений.
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
   * Читает информацию о врагах из файла "Enemies.csv" и возвращает список врагов.
   *
   * @return список врагов, прочитанных из файла
   * @throws RuntimeException если возникла ошибка при чтении файла
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
   * Сохраняет игру, указывая имя сохранения.
   *
   * @param "saveName" Имя сохранения игры.
   */
  public static void saveGame() {
    String saveName;
    System.out.println("Сохранить игру как...");
    System.out.println("Укажите имя");
    saveName = scanner.next();
    addSaveToFile(saveName);
  }

  /**
   * Добавляет сохранение в файл.
   *
   * @param "res/Save.csv" Имя сохранения.
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
      System.out.println("Файл не найден: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Выводит информацию о врагах, доступных для сражения, и предоставляет выбор.
   *
   * @param chouse Сканнер для выбора врага.
   */
  public static void enemiesInfo(Scanner chouse) {
    List<Enemies> enemies = getEnemiesFromFile();
    System.out.println("Выберите врага для сражения:");
    for (int i = 0; i < enemies.size(); i++) {
      System.out.println(
          (i + 1) + ". " + enemies.get(i).getName() + ": Health " + enemies.get(i).getHealth()
              + ", Strength " + enemies.get(i)
              .getStrength() + ", Money " + enemies.get(i).getMoney());
    }
    enemyToFight(chouse);
  }

  /**
   * Считывает выбор пользователя о сражении с врагом.
   *
   * @param scanner источник данных
   */
  public static void enemyToFight(Scanner scanner) {
    if (scanner.hasNextInt()) {
      int index = scanner.nextInt();
      fight(index);
    } else {
      System.out.println("Некорректный ввод. Введите число.");
      scanner.nextLine(); // Очистить буфер ввода
    }
  }

  /**
   * Метод осуществляет сражение между главным героем и выбранным врагом.
   *
   * @param choice выбранный враг для сражения
   */
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

  /**
   * Вычисляет атаку главного героя.
   *
   * @param buratinoStrength сила главного героя
   * @param random           объект для генерации случайных чисел
   * @return значение атаки главного героя
   */
  private static int calculateBuratinoAttack(int buratinoStrength, Random random) {
    return buratinoStrength + random.nextInt(5);
  }

  /**
   * Вычисляет атаку врага.
   *
   * @param enemyStrength сила врага
   * @param random        объект для генерации случайных чисел
   * @return значение атаки врага
   */
  private static int calculateEnemyAttack(int enemyStrength, Random random) {
    return enemyStrength + random.nextInt(5);
  }

  /**
   * Осуществляет атаки между главным героем и врагом.
   *
   * @param selectedEnemy  выбранный враг
   * @param buratinoAttack атака главного героя
   * @param enemyAttack    атака врага
   */
  private static void performAttacks(Enemies selectedEnemy, int buratinoAttack, int enemyAttack) {
    selectedEnemy.decreaseHealth(buratinoAttack);
    buratino.decreaseHealth(enemyAttack);

    System.out.println("Вы нанесли врагу " + buratinoAttack + " урона.");
    System.out.println("Враг нанес вам " + enemyAttack + " урона.");
  }

  /**
   * Генерирует случайную награду для героя после победы.
   *
   * @param random объект для генерации случайных чисел
   * @return случайное значение награды
   */
  private static int generateReward(Random random) {
    return random.nextInt(10, 20);
  }

  /**
   * Выводит сообщение о победе и обработку награды.
   *
   * @param reward количество награды
   * @param enemy  враг, побежденный героем
   */
  private static void displayVictoryMessage(int reward, Enemies enemy) {
    if (enemy.getName().equals("Karabas-Barabas")) {
      displayKarabasVictoryMessage();
    } else {
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
