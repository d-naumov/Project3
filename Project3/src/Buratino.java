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

    @Override
    public String toString() {
        return "Buratino{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", strength=" + strength +
                ", money=" + money +
                '}';
    }
}
