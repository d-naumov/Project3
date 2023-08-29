import EnumPackage.EnumFighting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    List<Enemies> enemies = new ArrayList<>();
    static Buratino buratino = new Buratino("Buratino", 140, 22, 10);

    public MainMenu() {
    }


    public static Scanner scanner = new Scanner(System.in);

    public static List<Enemies> getEnemiesFromFile() {
        File enemiesFile = new File("res/Enemies.csv");
        List<Enemies> enemies = new ArrayList<>();
        try {
            scanner = new Scanner(enemiesFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length > 1) {
                    Enemies enemy = new Enemies(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    enemies.add(enemy);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return enemies;
    }

    public static void enemiesInfo(Scanner chouse) {
        List<Enemies> enemies = getEnemiesFromFile();
        int num = 1;
        EnumFighting.readCommand(chouse);
        for (Enemies enemy : enemies) {
            System.out.println(num + " " + enemy.toString());
            num++;
        }

        enemyToFight(chouse);


    }

    public static void enemyToFight(Scanner scanner) {
        int index = scanner.nextInt();
        System.out.println(index);
        fight(index);
    }

    public static void fight(int index) {
        int buratinoHealth = buratino.getHealth();
        int buratinoAtack = buratino.getStrength();
        List<Enemies> enemies = getEnemiesFromFile();
        int enemyHealth = enemies.get(index - 1).getHealth();
        int enemyAtack = enemies.get(index - 1).getStrength();
        int buratinoCount = 0;
        int enemyCount = 0;

        for (int i = 0; buratinoHealth > 10; i++) {
            buratinoHealth -= enemyAtack;
            enemyCount = i;
        }
        for (int i = 0; enemyHealth > 10; i++) {
            enemyHealth -= buratinoAtack;
            buratinoCount = i;
        }

        if (buratinoCount < enemyCount) {
            System.out.println("win");
            buratino.setMoney(buratino.getMoney() + enemies.get(index - 1).getMoney());
            System.out.println(buratino.getMoney());
        } else System.out.println("lose" + buratinoCount);

    }

//    public static void getBuratinoFromFile(nameFile) {
//        File inputFile = new File("res/Buratino.csv");
//        Buratino buratino;
//        try {
//            scanner = new Scanner(inputFile);
//            String line = scanner.nextLine();
//            String[] data = line.split(",");
//            buratino = new Buratino(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(buratino.toString()
//    }

//    public static void getBuratino(){
//        System.out.println(this.buratino.toString());
//    }

    public static Buratino getBurarino() {
        return buratino;
    }

}
