import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You are here now: " + this.getName());
        System.out.println("Be careful! " + obsNumber + " " + this.getObstacle().getName() + "s live here.");
        System.out.println("<F>IGHT or <E>SCAPE");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("F") && combat(obsNumber)) {

            System.out.println("You have defeated all the obstacles in the " + this.getName());
            String award = this.getAward();

            System.out.println("You won: " + award);

            if (!this.getName().equals("Mine")) {
                this.earnAward();
            }

            return true;
        }
        //if you are dead
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You are dead!");
            return false;
        }
        return true;
    }

    public void earnAward() {

    }

    //We fight till the obstacle number, so we can create a loop
    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            //There are more obstacles than one, we need to full health of the new obstacle
            //for the new loop

            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatus();
            obstacleStatus(i);
            boolean playerStartsFirst = coinFlip();
            // System.out.println("Player starts first: " + playerStartsFirst);
            //we don't know how many times we fight each other, so we need create a while loop
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if (playerStartsFirst) {
                    System.out.print("<H>IT or <R>UN: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("H")) {
                        System.out.println("You hit the obstacle!");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            //if we have an armor, we need to calculate the block power of armor
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();

                            System.out.println(this.getObstacle().getName() + " hits you for " + obstacleDamage + "!");
                            //we need prevent to minus number
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else if (selectCombat.equals("R")) {
                        return false;
                    }
                } else {
                    //if we have an armor, we need to calculate the block power of armor
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();

                    System.out.println(this.getObstacle().getName() + " hits you for " + obstacleDamage + "!");
                    //we need prevent to minus number
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    afterHit();
                    if (this.getPlayer().getHealth() > 0) {
                        System.out.print("<H>IT or <R>UN: ");
                        String selectCombat = input.nextLine().toUpperCase();
                        if (selectCombat.equals("H")) {
                            System.out.println("You hit the obstacle!");
                            this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        } else if (selectCombat.equals("R")) {
                            return false;
                        }
                    }
                }
            }

            /* if (!((this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0))) {
                return false;
            } */

            if (0 < this.getPlayer().getHealth()) {
                System.out.println("You have defeated the obstacle!");
                if (this.getObstacle().getAward() > 0) {
                    System.out.println("You won " + this.getObstacle().getAward() + " money");
                }
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your new money: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }

        return true;

    }


    public boolean coinFlip() {
        double randomValue = Math.random() * 100;
        if (randomValue > 50) {
            return true;
        }
        return false;
    }

    public void playerStatus() {
        System.out.println("-----------------");
        System.out.println("Player info");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Gun: " + this.getPlayer().getInventory().getGun().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
        System.out.println();


    }

    public void obstacleStatus(int i) {
        System.out.println("-----------------");
        System.out.println(i + ". " + this.getObstacle().getName() + " info");
        System.out.println("Health: " + this.getObstacle().getHealth());
        if (this.getObstacle().getName().equals("Snake")) {
            int minDamage = this.getObstacle().getMinDamage();
            int maxDamage = this.getObstacle().getMaxDamage();
            System.out.println("Damage: " + minDamage + "-" + maxDamage);
        } else {
            System.out.println("Damage: " + this.getObstacle().getDamage());
        }
        System.out.println("Money: " + this.getObstacle().getAward());
        System.out.println();
    }

    public void afterHit() {
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " health: " + this.getObstacle().getHealth());
        System.out.println("-------");
    }

    //We want to create an Obstacle between 1-3, so bound = maxObstacleNumber +1
    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
