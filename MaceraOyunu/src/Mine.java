import java.util.Random;

public class Mine extends BattleLoc {
    Mine(Player player) {
        super(player, "Mine", new Snake(), "nothing", 5);
    }

    @Override
    public String getAward() {
        Random rand = new Random();
        String award = "nothing";
        int awardChance = rand.nextInt(100) + 1;

        System.out.println("awardChance: " + awardChance);
        if (awardChance <= 15) {
            int gunChance = rand.nextInt(100) + 1;
            Gun gun;
            if (gunChance <= 20) {
                // rifle
                gun = Gun.getGunObjById(3);
            } else if (gunChance <= 50) {
                // sword
                gun = Gun.getGunObjById(2);
            } else {
                // revolver
                gun = Gun.getGunObjById(1);
            }
            this.getPlayer().getInventory().setGun(gun);
            award = gun.getName();
            System.out.println("You won the gun: " + award);
        } else if (awardChance <= 30) {
            int armorChance = rand.nextInt(100) + 1;
            Armor armor;
            if (armorChance <= 20) {
                //heavy armor
                armor = Armor.getArmorObjByID(3);
            } else if (armorChance <= 30) {
                //medium armor
                armor = Armor.getArmorObjByID(2);
            } else {
                //light armor
                armor = Armor.getArmorObjByID(1);
                this.getPlayer().getInventory().setArmor(armor);
                award = armor.getName();
                System.out.println("You won a " + award + " armor");
            }

        } else if (awardChance <= 55) {
            int moneyChance = rand.nextInt(100) + 1;
            if (moneyChance <= 20) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                award = "10 money";
            } else if (moneyChance <= 30) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                award = "5 money";
            } else {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                award = "1 money";
            }

        }

        return award;
    }
}
