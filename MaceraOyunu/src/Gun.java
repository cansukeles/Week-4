public class Gun {
    private String name;
    private int id;
    private int damage;
    private int price;

    public Gun(String name, int id, int damage, int price) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.price = price;
    }

    public static Gun[] guns() {
        Gun[] gunList = new Gun[3];
        gunList[0] = new Gun("Revolver", 1, 2, 25);
        gunList[1] = new Gun("Sword", 2, 3, 35);
        gunList[2] = new Gun("Rifle", 3, 7, 45);
        return gunList;

    }

    public static Gun getGunObjById(int id) {
        for (Gun g : Gun.guns()) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
