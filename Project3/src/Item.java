public class Item {

  private String name;
  private int price;
  private int increaseHealth;
  private int increaseStrength;


  public Item(String name, int price, int increaseHealth, int increaseStrength) {
    this.name = name;
    this.price = price;
    this.increaseHealth = increaseHealth;
    this.increaseStrength = increaseStrength;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getIncreaseHealth() {
    return increaseHealth;
  }

  public int getIncreaseStrength() {
    return increaseStrength;
  }

  @Override
  public String toString() {
    return name + '\'' +
        ", Price - " + price +
        " coins (+" + increaseHealth +
        " Health , +" + increaseStrength + " Strength)";
  }
}
