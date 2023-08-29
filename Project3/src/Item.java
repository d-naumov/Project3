public class Item {

  private String name;   // Название предмета
  private int price;     // Стоимость предмета
  private int increaseHealth;    // Влияние на характеристику здоровье персонажа
  private int increaseStrength;  // Влияние на характеристику сила персонажа


  public Item(String name, int price, int increaseHealth, int increaseStrength) {
    this.name = name;
    this.price = price;
    this.increaseHealth = increaseHealth;
    this.increaseStrength = increaseStrength;
  }

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", increaseHealth=" + increaseHealth +
        ", increaseStrength=" + increaseStrength +
        '}';
  }
}