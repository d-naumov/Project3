public class Buratino {

    private String name;
    private int health;
    private int strength;

    private int money;


    public Buratino(String name, int health, int strength, int money) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public void increaseMoney(int amount) {
        money += amount;
    }

    public void decreaseMoney(int amount) {
        if (amount > money) {
            System.out.println("Недостаточно денег.");
        } else {
            money -= amount;
        }
    }
    @Override
    public String toString() {
        return "Buratino{" +
                "health=" + health +
                ", strength=" + strength +
                ", money=" + money +
                '}';
    }
}
