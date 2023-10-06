public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("--- Welcome to the Tool Store! ---");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Guns");
            System.out.println("2- Armors");
            System.out.println("3- Quit from the store");
            System.out.print("Your choice: ");
            //This input comes from Location class
            int selectCase = Location.input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Invalid transaction. Try again: ");
                selectCase = Location.input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printGuns();
                    buyGun();
                    break;
                case 2:
                    printArmors();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("We wait you again!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printGuns() {
        System.out.println();
        System.out.println("--- Guns ---");
        System.out.println();
        for (Gun g : Gun.guns()) {
            System.out.println(g.getId() + "-" + g.getName() +
                    " < Money: " + g.getPrice() +
                    ", Damage: " + g.getDamage() + " >");
        }
        System.out.println("0 - Quit");
    }

    public void buyGun() {
        System.out.print("Select a gun: ");
        int selectGunID = input.nextInt();
        while (selectGunID < 0 || selectGunID > Gun.guns().length) {
            System.out.print("Invalid transaction. Try again: ");
            selectGunID = Location.input.nextInt();
        }
        if (selectGunID != 0) {
            Gun selectedGun = Gun.getGunObjById(selectGunID);

            if (selectedGun != null) {
                if (selectedGun.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have enough money!");
                } else {
                    //purchasing area
                    System.out.println("You bought " + selectedGun.getName());
                    int balance = this.getPlayer().getMoney() - selectedGun.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your remaining money: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setGun(selectedGun);

                }
            }

        }

    }

    public void printArmors() {
        System.out.println();
        System.out.println("--- Armors ---");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println(a.getID() + "-" + a.getName() +
                    " < Money: " + a.getPrice() +
                    ", Block: " + a.getBlock() + " >");

        }
        System.out.println("0 - Quit");
    }

    public void buyArmor() {
        System.out.print("Select an armor: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("Invalid transaction. Try again: ");
            selectArmorID = Location.input.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have enough money!");
                } else {
                    //purchasing area
                    System.out.println("You bought " + selectedArmor.getName());
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your remaining money: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);

                }
            }
        }
    }
}
