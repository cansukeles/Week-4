public class SafeHouse extends NormalLocation{

    SafeHouse(Player player){
        super(player, "Safe House");
    }
    @Override
    public boolean onLocation() {
        System.out.println("You are in the Safe House. ");
        System.out.println("Your lifeblood is full now.");
        System.out.println();
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;
    }
}
