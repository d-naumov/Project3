public class Enemies {

    private String name;
    private int health;
    private int strength;

    private int money;


    public Enemies(String name, int health, int strength, int money) {
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

    public int getMoney() {
        return money;
    }

    /**
     * Decreases the player's health by the specified amount.
     *
     * @param amount the value of health to decrease
     */
    public void decreaseHealth(int amount) {
        health -= amount;
    }
    @Override
    public String toString() {
        return name + '\'' +
            "Health - " + health +
            ", Strength - " + strength +
            ", Money - " + money;
    }
}