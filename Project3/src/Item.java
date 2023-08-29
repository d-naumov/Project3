public class Item {

  private String name;   // Название предмета
  private int price;     // Стоимость предмета
  private int IncreaseHealth;    // Влияние на характеристику здоровье персонажа
  private int IncreaseStrength;  // Влияние на характеристику сила персонажа

  public Item(String name, int price, int health, int strength) {
    this.name = name;
    this.price = price;
    this.health = health;
    this.strength = strength;
  }

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", health=" + health +
        ", strength=" + strength +
        '}';
  }

}
