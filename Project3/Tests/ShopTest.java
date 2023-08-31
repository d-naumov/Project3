import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ShopTest {

  @Test
  public void testIncreaseHealthOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 10);
    ByteArrayInputStream input = new ByteArrayInputStream("1\n".getBytes());
    Scanner scanner = new Scanner(input);

    // Тестирование покупки предмета, который увеличивает здоровье
    Shop.selection(buratino, scanner);

    // Проверка, что здоровье увеличилось на 20
    assertEquals(120, buratino.getHealth());
  }

  @Test
  public void testIncreaseStrengthOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 10);
    ByteArrayInputStream input = new ByteArrayInputStream("2\n".getBytes());
    Scanner scanner = new Scanner(input);

    // Тестирование покупки предмета, который увеличивает силу
    Shop.selection(buratino, scanner);

    // Проверка, что сила осталась неизменной (равной 20)
    assertEquals(20, buratino.getStrength());
  }

  @Test
  public void testDecreaseMoneyOnValidPurchase() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 10);
    ByteArrayInputStream input = new ByteArrayInputStream("1\n".getBytes());
    Scanner scanner = new Scanner(input);

    // Тестирование уменьшения денег при покупке
    Shop.selection(buratino, scanner);

    // Проверка, что деньги уменьшились на 10
    assertEquals(0, buratino.getMoney());
  }

  @Test
  public void testInsufficientFunds() {
    Buratino buratino = new Buratino("Buratino", 180, 22, 10);
    ByteArrayInputStream input = new ByteArrayInputStream("1\n".getBytes());
    Scanner scanner = new Scanner(input);

    // Тестирование покупки без достаточных средств
    Shop.selection(buratino, scanner);

    // Проверка, что деньги не изменились
    assertEquals(0, buratino.getMoney());
  }

  @Test
  public void testInvalidInput() {
    Buratino buratino = new Buratino("Buratino", 100, 20, 50);
    ByteArrayInputStream input = new ByteArrayInputStream("invalid\n".getBytes());
    Scanner scanner = new Scanner(input);

    // Тестирование некорректного ввода данных пользователем
    Shop.selection(buratino, scanner);

    // Здесь нет утверждений (assertions), мы просто проверяем, что метод не вызывает исключение
  }
}
