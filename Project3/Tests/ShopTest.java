import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ShopTest {

  @Test
  public void testIncreaseHealthOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 10);
    String input = "1\n"; // The string the user enters
    Scanner scanner = new Scanner(new StringReader(input));

    // Testing the purchase of an item that increases health
    Shop.selection(buratino, scanner);

    // Checking that health has increased by 20
    assertEquals(120, buratino.getHealth());
  }

  @Test
  public void testIncreaseStrengthOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 10);
    String input = "2\n"; //  The string the user enters
    Scanner scanner = new Scanner(new StringReader(input));

    // Testing the purchase of an item that increases strength
    Shop.selection(buratino, scanner);

    // Checking that the strength has remained unchanged (equal to 20)
    assertEquals(20, buratino.getStrength());
  }

  @Test
  public void testDecreaseMoneyOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 20);
    String input = "1\n"; // The string the user enters
    Scanner scanner = new Scanner(new StringReader(input));

    // Testing money reduction when buying
    Shop.selection(buratino, scanner);

    // Checking if the money has decreased by 10
    assertEquals(10, buratino.getMoney());
  }

  @Test
  public void testInsufficientFunds() {
    Buratino buratino = new Buratino("Buratino", 180, 22, 10);
    String input = "2\n"; // The string the user enters
    Scanner scanner = new Scanner(new StringReader(input));

    // Testing a purchase without enough funds
    Shop.selection(buratino, scanner);

    // Checking that the money has not changed
    assertEquals(10, buratino.getMoney());
  }


  @Test
  public void testInvalidInput() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 50);
    String input = "invalid\n"; // The string the user enters
    Scanner scanner = new Scanner(new StringReader(input));

    // Testing for invalid user input
    Shop.selection(buratino, scanner);

    // There are no assertions here, we just check that the method does not throw an exception
  }
}
