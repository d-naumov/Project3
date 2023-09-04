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
    // Creating a fake object Buratino
    Buratino buratino = new Buratino("Test Buratino", 100, 20, 10);
    MainMenu.buratino = buratino;
    // Create a temporary file for a test
    File tempFile = new File(TEST_FILE_NAME);
    assertTrue(tempFile.createNewFile());

    // Method call addSaveToFile
    MainMenu.addSaveToFile("Test Buratino", TEST_FILE_NAME);

    // Checking the contents of a file
    BufferedReader bufferedReader = new BufferedReader(new FileReader(TEST_FILE_NAME));

    String lastLine = getLastLine(bufferedReader);

    // Checking if a line in a file is as expected
    assertEquals("Test Buratino,100,20,10", lastLine);

    // Closing the reader and deleting the temporary file
    bufferedReader.close();
    assertTrue(tempFile.delete());
  }


  @Test
  public void testLoadGame() {
    // Creating a fake List allLoads
    List<Buratino> allLoads = createFakeSaveData();

    // Mocked System.in to supply input
    InputStream mockedInputStream = new ByteArrayInputStream("1\n".getBytes());
    System.setIn(mockedInputStream);

    // Calling loadGame method
    Buratino loadedBuratino = MainMenu.loadGame();

    // Restoring the standard System.in
    System.setIn(System.in);

    // Checking that the method returned the expected Buratino object
    assertEquals("Denis", loadedBuratino.getName());
    assertEquals(180, loadedBuratino.getHealth());
    assertEquals(22, loadedBuratino.getStrength());
    assertEquals(10, loadedBuratino.getMoney());
  }

  @Test
  public void testGetSaveToFile() throws IOException {
    // Creating a temporary file with test data
    createTemporarySaveFile(TEST_FILE_NAME);

    // Calling getSaveToFile method
    List<Buratino> loadedData = MainMenu.getSaveToFile(false, TEST_FILE_NAME);

    // Checking the amount of data loaded
    assertEquals(2, loadedData.size());

    // Validating Loaded Data Values
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

    // Deleting a temporary file
    deleteTemporarySaveFile(TEST_FILE_NAME);
  }

  @Test
  public void testEnemyToFightWithValidInput() {
    Buratino buratino = new Buratino("Test Buratino", 180, 20, 10);
    MainMenu.buratino = buratino;
    String input = "1\n";
    setInput(input);
    Scanner scanner = new Scanner(System.in);
    // In this case, the test checks only for the absence of exceptions,
    // since the enemyToFight method returns nothing
    MainMenu.enemyToFight(scanner);

    // Restoring System.in
    restoreSystemIn();
  }

  @Test
  public void testEnemyToFightWithInvalidInput() {
    String input = "invalid\n";
    setInput(input);
    Scanner scanner = new Scanner(System.in);
    // Testing for invalid user input
    MainMenu.enemyToFight(scanner);

    // Checking that the method does not throw an exception on invalid input.
    // Restoring System.in
    restoreSystemIn();
  }

  @Test
  public void testFightWithValidChoice() {
    String input = "1\n";
    setInput(input);

    // Create a Buratino object for the test
    Buratino buratino = new Buratino("Test Buratino", 180, 20, 10);
    MainMenu.buratino = buratino;

    // The test checks that the fight method is executed without exceptions
    MainMenu.fight(1);

    // Restoring System.in
    restoreSystemIn();
  }

  @Test
  public void testFightWithInvalidChoice() {
    String input = "10\n"; // Incorrect input
    setInput(input);

    // Create a Buratino object for the test
    Buratino buratino = new Buratino("Test Buratino", 100, 20, 10);
    MainMenu.buratino = buratino;

    // The test checks that the fight method handles an invalid choice
    MainMenu.fight(10);

    // Restoring System.in
    restoreSystemIn();
  }

  private void setInput(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  }

  private void restoreSystemIn() {
    System.setIn(System.in);
  }

  private List<Buratino> createFakeSaveData() {
    List<Buratino> allLoads = new ArrayList<>();
    allLoads.add(new Buratino("Buratino", 180, 22, 10));
    allLoads.add(new Buratino("Alice", 150, 40, 20));
    return allLoads;
  }

  private String getLastLine(BufferedReader reader) throws IOException {
    String lastLine = null;
    String choicedLine;
    while ((choicedLine = reader.readLine()) != null) {
      lastLine = choicedLine;
    }
    return lastLine;
  }

  private void createTemporarySaveFile(String fileName) throws IOException {
    File tempFile = new File(fileName);
    FileWriter writer = new FileWriter(fileName);
    writer.write("Buratino,180,22,10\n");
    writer.write("Alice,150,40,20\n");
    writer.close();
  }

  private void deleteTemporarySaveFile(String fileName) {
    File tempFile = new File(fileName);
    tempFile.delete();
  }
}
