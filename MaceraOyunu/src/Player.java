import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private int originalHealth;
    private String name;
    private String charName;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    Player(String name) {
        this.name = name;
        this.inventory = new Inventory(false, false, false);
    }

    //we select the characters
    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("----------------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getId());
            System.out.println("\t Character: " + gameChar.getName() +
                    "\t Damage: " + gameChar.getDamage() +
                    "\t Health: " + gameChar.getHealth() +
                    "\t Money: " + gameChar.getMoney());
        }
        System.out.println("----------------------------");
        System.out.print("Select a character: ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("Character: " + this.getCharName() +
                ", Damage: " + this.getDamage() +
                ", Health: " + this.getHealth() +
                ", Money: " + this.getMoney());
        System.out.println("----------------------------");
    }


    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());

    }

    public void printInfo() {
        System.out.println("Your gun: " + this.getInventory().getGun().getName() +
                ", Your armor: " + this.getInventory().getArmor().getName() +
                ", Your block power: " + this.getInventory().getArmor().getBlock() +
                ", Your Damage: " + this.getTotalDamage() +
                ", Your Health: " + this.getHealth() +
                ", Your Money: " + this.getMoney());

        if (this.getInventory().getHasFireWood()) {
            System.out.println("You have firewood.");
        }
        if (this.getInventory().getHasFood()) {
            System.out.println("You have food.");
        }
        if (this.getInventory().getHasWater()) {
            System.out.println("You have water.");
        }
    }

    public boolean hasWonGame() {
        return this.getInventory().getHasWater() && this.getInventory().getHasFood() && this.getInventory().getHasFireWood();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getGun().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

}
