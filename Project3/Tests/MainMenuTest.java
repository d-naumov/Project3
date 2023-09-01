import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class MainMenuTest {

  private static final String TEST_FILE_NAME = "res/testSave.csv";


  @Test
  public void testAddSaveToFile() throws IOException {
    // Создание фейкового объекта Buratino
    MainMenu.buratino = new Buratino("Test Buratino", 100, 20, 10);

    // Создание временного файла для теста
    File tempFile = new File(TEST_FILE_NAME);
    assertTrue(tempFile.createNewFile());

    // Вызов метода addSaveToFile
    MainMenu.addSaveToFile("Test Buratino", TEST_FILE_NAME);

    // Проверка содержимого файла
    BufferedReader bufferedReader = new BufferedReader(new FileReader(TEST_FILE_NAME));

    String lastLine = bufferedReader.readLine();

    String choicedLine;
    while ((choicedLine = bufferedReader.readLine()) != null) {
      lastLine = choicedLine;
    }

    // Проверка, что строка в файле соответствует ожидаемой
    assertEquals("Test Buratino,100,20,10", lastLine);

    // Закрытие reader и удаление временного файла
    bufferedReader.close();
    assertTrue(tempFile.delete());
  }

  @Test
  public void testLoadGame() {
    // Создание фейкового списка allLoads
    List<Buratino> allLoads = new ArrayList<>();
    allLoads.add(new Buratino("Buratino", 180, 22, 10));
    allLoads.add(new Buratino("Alice", 150, 40, 20));

    // Мокирование System.in для предоставления входных данных
    InputStream mockedInputStream = new ByteArrayInputStream("Buratino\n".getBytes());
    System.setIn(mockedInputStream);

    // Вызов метода loadGame
    Buratino loadedBuratino = MainMenu.loadGame();

    // Восстановление стандартного System.in
    System.setIn(System.in);

    // Проверка, что метод вернул ожидаемый объект Buratino
    assertEquals("Buratino", loadedBuratino.getName());
    assertEquals(180, loadedBuratino.getHealth());
    assertEquals(22, loadedBuratino.getStrength());
    assertEquals(10, loadedBuratino.getMoney());
  }

  @Test
  public void testGetSaveToFile() throws IOException {
    // Создание временного файла с тестовыми данными
    File tempFile = new File(TEST_FILE_NAME);
    FileWriter writer = new FileWriter(TEST_FILE_NAME);
    writer.write("Buratino,180,22,10\n");
    writer.write("Alice,150,40,20\n");
    writer.close();
    // Вызов метода getSaveToFile
    List<Buratino> loadedData = MainMenu.getSaveToFile(false, TEST_FILE_NAME);

    // Проверка количества загруженных данных
    assertEquals(2, loadedData.size());

    // Проверка значений загруженных данных
    Buratino buratino1 = loadedData.get(0);
    assertEquals("Buratino", buratino1.getName());
    assertEquals(180, buratino1.getHealth());
    assertEquals(22, buratino1.getStrength());
    assertEquals(10, buratino1.getMoney());

    Buratino buratino2 = loadedData.get(1);
    assertEquals("Alice", buratino2.getName());
    assertEquals(150, buratino2.getHealth());
    assertEquals(40, buratino2.getStrength());
    assertEquals(20, buratino2.getMoney());

    // Удаление временного файла

    assertTrue(tempFile.delete());

  }


  @Test
  public void testEnemyToFightWithValidInput() {
    Buratino buratino = new Buratino("Test Buratino", 180, 20, 10);
    MainMenu.buratino = buratino;
    String input = "1\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Scanner scanner = new Scanner(System.in);

    MainMenu.enemyToFight(scanner);
    // В данном случае, тест проверяет только на отсутствие исключений,
    // так как метод enemyToFight ничего не возвращает
  }

  @Test
  public void testEnemyToFightWithInvalidInput() {
    String input = "invalid\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Scanner scanner = new Scanner(System.in);

    MainMenu.enemyToFight(scanner);

    // Тест проверяет, что метод не вызывает исключение при некорректном вводе
  }

  @Test
  public void testFightWithValidChoice() {
    String input = "1\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Scanner scanner = new Scanner(System.in);

    // Создаем объект Buratino для теста
    Buratino buratino = new Buratino("Test Buratino", 180, 20, 10);
    MainMenu.buratino = buratino;

    MainMenu.fight(1);

    // Тест проверяет, что метод fight выполняется без исключений
  }

  @Test
  public void testFightWithInvalidChoice() {
    String input = "10\n"; // Некорректный выбор
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Scanner scanner = new Scanner(System.in);

    // Создаем объект Buratino для теста
    Buratino buratino = new Buratino("Test Buratino", 100, 20, 10);
    MainMenu.buratino = buratino;

    MainMenu.fight(10);

    // Тест проверяет, что метод fight обрабатывает некорректный выбор
  }
}
